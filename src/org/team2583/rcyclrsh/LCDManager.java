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
import io.github.robolib.util.RoboRIO;
import io.github.robolib.util.StringUtils;

/**
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class LCDManager {
    
    private LCD_LCM1602 lcd0;
    private Object m_sem;
    private static volatile boolean m_run = true;
    private Thread m_thread;
    
    public LCDManager(){
        m_sem = new Object();
        lcd0 = new LCD_LCM1602(Port.kOnboard);
        lcd0.clear();
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
        
        while(m_run){
            lcd0.home();
            lcd0.writeString("R: " + StringUtils.getNumber2DWithUnits(RoboRIO.getVoltage(), "V"));
            
            lcd0.setCursor(3, 0);
            lcd0.writeString("  DS: " + (DriverStation.isDSAttached() ? "Connected" : "Nope     "));
            synchronized (m_sem) {
                try {
                    m_sem.wait(100);
                } catch (InterruptedException e) {
                }
            }
        }
        
    }

}
