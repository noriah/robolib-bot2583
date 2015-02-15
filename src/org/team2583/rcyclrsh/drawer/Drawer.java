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

package org.team2583.rcyclrsh.drawer;

import io.github.robolib.command.Subsystem;
import io.github.robolib.module.controller.LimitedController;
import io.github.robolib.util.mapper.RobotMap;

/**
 * 
 * @author noriah <vix@noriah.dev>
 */
public class Drawer extends Subsystem {
    
    private static LimitedController m_drawerMotor;
    
    private static double m_drawerSpeed;
    
    
    public static final void initialize(){
        m_drawerMotor = RobotMap.getModule("limited_controller_drawer");
        
        m_drawerSpeed = RobotMap.getNumber("drawer_speed");
    }
    
    private static final Drawer m_instance = new Drawer();
    
    public static final Drawer getInstance(){
        return m_instance;
    }
    
    private Drawer(){
        super("Drawer Subsystem");
    }

    protected static final void extend(){
        m_drawerMotor.setSpeed(m_drawerSpeed);
    }
    
    protected static final void stop(){
        m_drawerMotor.setSpeed(0);
    }
    
    protected static final void retract(){
        m_drawerMotor.setSpeed(-m_drawerSpeed);
    }
    
    protected static final void toggle(){
    }
    
    protected static final boolean isExtended(){
        return m_drawerMotor.atFrontLimit();
    }
    
    protected static final boolean isRetracted(){
        return m_drawerMotor.atBackLimit();
    }

    public void initDefaultCommand(){}
}

