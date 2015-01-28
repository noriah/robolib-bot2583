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

import io.github.robolib.framework.DriverStation;
import io.github.robolib.iface.I2C.Port;
import io.github.robolib.sensor.HMC5883L;
import io.github.robolib.sensor.MPU6050;
import io.github.robolib.util.ArdEx;
import io.github.robolib.util.RoboRIO;
import io.github.robolib.util.Timer;

/**
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class LCDManager {
    
    private LCD_LCM1602 lcd0;
//    private Object m_sem;
    private static volatile boolean m_run = true;
    private Thread m_thread;
    private HMC5883L mag;
    private MPU6050 accl;
    private ArdEx ad0;
    
    public LCDManager(){
//        m_sem = new Object();
        lcd0 = new LCD_LCM1602(Port.kOnboard);
        accl = new MPU6050(Port.kOnboard);
        accl.setI2CMasterModeEnabled(false);

        mag = new HMC5883L(Port.kOnboard);
        
        ad0 = new ArdEx(Port.kOnboard, (byte)0x05);
        m_thread = new Thread(() -> run(), "LCD Manager");
        m_thread.setPriority(((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2) - 5);
    }
    
    protected void startThread(){
        if(!m_thread.isAlive())
            m_thread.start();
    }
    
    public static void die(){
        m_run = false;
    }
    
    private void run(){
        
        lcd0.clear();
        lcd0.noBlink();
        lcd0.noCursor();
        lcd0.clear();
        Timer.delay(0.01);
        while(m_run){
            lcd0.home();
            lcd0.writeString(String.format("%s %05.2f%s", "R", RoboRIO.getVoltage(), "V"));
            lcd0.setCursor(1, 0);
            lcd0.writeString(String.format("%s %06.2f %s", "G",  mag.getHeadingXDeg(), "Deg"));
            
            lcd0.setCursor(3, 0);
            lcd0.writeString("  DS: " + (DriverStation.isDSAttached() ? "Connected" : "Nope     "));
            ad0.digitalWrite(13, false);
            Timer.delay(0.075);
            ad0.digitalWrite(13, true);
            Timer.delay(0.075);
        }
        
    }

}
