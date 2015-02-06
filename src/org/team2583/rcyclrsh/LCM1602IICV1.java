/*
 * Copyright (c) 2015 noriah <vix@noriah.dev>.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 */

package org.team2583.rcyclrsh;

import io.github.robolib.module.iface.I2C;
import io.github.robolib.util.Timer;

/**
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class LCM1602IICV1 extends I2C {

    public static final byte I2C_ADDR = 0x27; 
    

    public static final int DATA_MAP_4 = 4;
    public static final int DATA_MAP_5 = 5;
    public static final int DATA_MAP_6 = 6;
    public static final int DATA_MAP_7 = 7;
    public static final int BACKLIGHT_PIN = 3;
    
    public static final byte LCD_NOBACKLIGHT = 0x00;
    public static final byte LCD_BACKLIGHT = (byte) 0xff;
    
    public static final byte LCD_CLEARDISPLAY = 0x01;
    public static final byte LCD_RETURNHOME = 0x02;
    public static final byte LCD_ENTRYMODESET = 0x04;
    public static final byte LCD_DISPLAYCONTROL = 0x08;
    public static final byte LCD_CURSORSHIFT = 0x10;
    public static final byte LCD_FUNCTIONSET = 0x20;
    public static final byte LCD_SETCGRAMADDR = 0x40;
    public static final byte LCD_SETDDRAMADDR = (byte) 0x80;

    public static final byte LCD_ENTRYRIGHT = 0x00;
    public static final byte LCD_ENTRYLEFT = 0x02;
    public static final byte LCD_ENTRYSHIFTINCREMENT = 0x01;
    public static final byte LCD_ENTRYSHIFTDECREMENT = 0x00;

    public static final byte LCD_DISPLAYON = 0x04;
    public static final byte LCD_DISPLAYOFF = 0x00;
    public static final byte LCD_CURSORON = 0x02;
    public static final byte LCD_CURSOROFF = 0x00;
    public static final byte LCD_BLINKON = 0x01;
    public static final byte LCD_BLINKOFF = 0x00;

    public static final byte LCD_DISPLAYMOVE = 0x08;
    public static final byte LCD_CURSORMOVE = 0x00;
    public static final byte LCD_MOVERIGHT = 0x04;
    public static final byte LCD_MOVELEFT = 0x00;
    
    public static final byte LCD_8BITMODE = 0x10;
    public static final byte LCD_4BITMODE = 0x00;
    public static final byte LCD_2LINE = 0x08;
    public static final byte LCD_1LINE = 0x00;
    public static final byte LCD_5x10DOTS = 0x04;
    public static final byte LCD_5x8DOTS = 0x00;

    public static final byte COMMAND = 0x01;
    public static final byte DATA = 0x00;
    public static final byte FOUR_BITS = 0x02;

    public static final double HOME_CLEAR_EXEC = 0.2;

    public static final int REGISTER_SELECT_PIN = 0;
    public static final byte REGISTER_SELECT_BYTE = (1 << REGISTER_SELECT_PIN);
    public static final int READ_WRITE_PIN = 1;
    public static final byte READ_WRITE_BYTE = (1 << READ_WRITE_PIN);
    public static final int ENABLE_PIN = 2;
    public static final byte ENABLE_BYTE = (1 << ENABLE_PIN);
    
    public static byte row_offsetDef[] = {0x00, 0x40, 0x14, 0x54};
    
    public static final byte BACKLIGHT_PIN_MASK = 0x08;
    
    private byte displayMode = 0;
    private byte displayFunction = 0;
    private byte displayControl;
    private byte numLines = 0;
    private byte backlightStsMask = 0x08;
    
    private byte[] dataPins = {0x10, 0x20, 0x40, (byte) 0x80, REGISTER_SELECT_BYTE, ENABLE_BYTE};
    /**
     * @param port
     * @param address
     */
    public LCM1602IICV1(Port port) {
        super(port, I2C_ADDR);
        
        
//        displayFunction = LCD_5x8DOTS;
//        displayFunction |= LCD_2LINE;
        numLines = (byte) 4;
        
//        command((byte) (LCD_FUNCTIONSET | displayFunction)); 
        
        displayControl = LCD_DISPLAYON | LCD_CURSOROFF | LCD_BLINKOFF;
        command(LCD_DISPLAYCONTROL | displayControl);
        
//        command(LCD_CLEARDISPLAY);
//        displayMode = LCD_ENTRYLEFT | LCD_ENTRYSHIFTDECREMENT;
        
//        updateDisplayMode();
        
//        backlight();
        
    }
    
    public void clear(){
        command(LCD_CLEARDISPLAY);
        Timer.delay(HOME_CLEAR_EXEC);
    }
    
    public void home(){
        command(LCD_RETURNHOME);
        Timer.delay(HOME_CLEAR_EXEC);
    }
    
    public void setCursor(int col, int row){
        if(row >= numLines)
            row = (byte) (numLines - 1);
        
        command(LCD_SETDDRAMADDR + ((byte)(col + row_offsetDef[row])));
    }
    
    public void noDisplay(){
        displayControl &= ~LCD_DISPLAYON;
        updateDisplayControl();
    }
    
    public void display(){
        displayControl |= LCD_DISPLAYON;
        updateDisplayControl();
    }
    
    public void noCursor(){
        displayControl &= ~LCD_CURSORON;
        updateDisplayControl();
    }
    
    public void cursor(){
        displayControl |= LCD_CURSORON;
        updateDisplayControl();
    }
    
    public void noBlink(){
        displayControl &= ~LCD_BLINKON;
        updateDisplayControl();
    }
    
    public void blink(){
        displayControl |= LCD_BLINKON;
        updateDisplayControl();
    }
    
    public void scrollDisplayLeft(){
        command(LCD_CURSORSHIFT | LCD_DISPLAYMOVE | LCD_MOVELEFT);
    }
    
    public void scrollDisplayRight(){
        command(LCD_CURSORSHIFT | LCD_DISPLAYMOVE | LCD_MOVELEFT);
    }
    
    public void leftToRight(){
        displayMode |= LCD_ENTRYLEFT;
        updateDisplayMode();
    }
    
    public void rightToLeft(){
        displayMode &= ~LCD_ENTRYLEFT;
        updateDisplayMode();
    }
    
    public void moveCursorRight(){
        command(LCD_CURSORSHIFT | LCD_CURSORMOVE | LCD_MOVERIGHT);
    }
    
    public void moveCursorLeft(){
        command(LCD_CURSORSHIFT | LCD_CURSORMOVE | LCD_MOVELEFT);
    }
    
    public void autoscroll(){
        displayMode |= LCD_ENTRYSHIFTINCREMENT;
        updateDisplayMode();
    }
    
    public void noAutoscroll(){
        displayMode &= ~LCD_ENTRYSHIFTINCREMENT;
        updateDisplayMode();
    }
    
    public void backlight(){
        backlightStsMask = BACKLIGHT_PIN_MASK & LCD_BACKLIGHT;
        writeBulk(new byte[]{backlightStsMask});
    }
    
    public void noBacklight(){
        backlightStsMask = BACKLIGHT_PIN_MASK & LCD_NOBACKLIGHT;
        writeBulk(new byte[]{backlightStsMask});
    }
    
    private void updateDisplayControl(){
        command(LCD_DISPLAYCONTROL | displayControl);
    }
    
    private void updateDisplayMode(){
        command(LCD_ENTRYMODESET | displayMode);
    }

    public void writeString(String str){        
        for(char s : str.toCharArray()){
            write(s);
        }
    }
    
    public void write(int value){
        send((byte)value, DATA);
    }
    
    public void command(int data){
        send((byte)data, COMMAND);
    }
    
    private void send(byte value, byte mode){
        write4bits((byte) (value >> 4), mode);
        write4bits((byte) (value & 0x0f), mode);
    }
    
    private void write4bits(byte value, byte mode){
        byte pinMapValue = 0;
        for(int i = 0; i < 4; i++)
            pinMapValue = _pinInterpret(i, pinMapValue, (value >> i) & 0x01);
        
        if(mode == DATA)
            pinMapValue = _pinInterpret(5, pinMapValue, 1);
        
        pinMapValue |= backlightStsMask;
        pulseEnable(pinMapValue);
    }
    
    byte _pinInterpret(int pin, byte v, int value){
        if(value != 0){
            return (byte) (v | dataPins[pin]);
        }else{
            return (byte) (v & (dataPins[pin] ^ 0xff));
        }
    }
    
    private void pulseEnable(byte data){
        writeBulk(new byte[]{
                _pinInterpret(6, data, 1),
                data
        });
    }
}
