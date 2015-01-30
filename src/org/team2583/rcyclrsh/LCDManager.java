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
import io.github.robolib.sensor.mpu6050.MPU6050;
import io.github.robolib.sensor.mpu6050.Quaternion;
import io.github.robolib.sensor.mpu6050.VectorDouble;
import io.github.robolib.util.ArdEx;
import io.github.robolib.util.RoboRIO;
import io.github.robolib.util.Timer;
import io.github.robolib.util.log.Logger;

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
    private MPU6050 mpu;
    private ArdEx ad0;
    
    public LCDManager(){
//        m_sem = new Object();
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
        mpu = new MPU6050(Port.ONBOARD);
        
        mpu.dmpInitialize();
        mpu.setXGyroOffset((short) 220);
        mpu.setYGyroOffset((short) 76);
        mpu.setZGyroOffset((short) -85);
        mpu.setZAccelOffset((short) 1788);
        
        mpu.setDMPEnabled(true);
        
        mpu.setI2CMasterModeEnabled(false);
        mpu.setI2CBypassEnabled(true);
        
        
        mag = new HMC5883L(Port.ONBOARD);
        ad0 = new ArdEx(Port.MXP, (byte)0x05);
        lcd0 = new LCD_LCM1602(Port.MXP);
        
        lcd0.clear();
        lcd0.noBlink();
        lcd0.noCursor();
        lcd0.clear();
        Timer.delay(0.01);
        Quaternion q = new Quaternion();
        VectorDouble gravity = new VectorDouble();
        double[] ypr = new double[3];
        
        byte[] fifoBuffer;
        byte fifoCount;
        byte mpuIntStatus;
        byte packetSize = (byte) mpu.dmpGetFIFOPacketSize();
        
        while(m_run){
            lcd0.home();
            lcd0.writeString(String.format("R %05.2fV", RoboRIO.getVoltage()));
            lcd0.setCursor(1, 0);
            lcd0.writeString(String.format("G %06.2f Deg",  mag.getHeadingXDeg()));
            
            mpuIntStatus = mpu.getIntStatus();
            fifoCount = (byte) mpu.getFIFOCount();
            if ((mpuIntStatus & 0x10) != 0 || fifoCount == 1024) {
                mpu.resetFIFO();
                Logger.get(this).warn("FIFO OVERFLOW");
            }else if((mpuIntStatus & 0x02) != 0){
                while (fifoCount < packetSize) fifoCount = (byte) mpu.getFIFOCount();
                fifoBuffer = new byte[packetSize];
                mpu.getFIFOBytes(fifoBuffer, packetSize);
                fifoCount -= packetSize;
                mpu.dmpGetQuaternion(q, fifoBuffer);
                mpu.dmpGetGravity(gravity, q);
                mpu.dmpGetYawPitchRoll(ypr, q, gravity);
                lcd0.setCursor(2, 0);
                lcd0.writeString(String.format("X %-7.2f  Y %-7.2f", ypr[0] * (180/Math.PI), ypr[1] * (180/Math.PI)));
                lcd0.setCursor(3, 0);
                lcd0.writeString(String.format("Z %-7.2f T %-7.2f ", ypr[2] * (180/Math.PI), mpu.getTemperature()));
            }

            lcd0.setCursor(3, 0);
//            lcd0.writeString("  DS: " + (DriverStation.isDSAttached() ? "Connected" : "Nope     "));
        }
        
    }

}
