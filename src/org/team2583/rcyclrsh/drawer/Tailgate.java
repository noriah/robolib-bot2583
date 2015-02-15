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
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class Tailgate extends Subsystem {

    private static Solenoid m_tailGate;

    private static boolean m_tailgateDown;
    
    public static final void initialize(){
        m_tailGate = RobotMap.getModule("solenoid_tailgate");
    }

    private static final Tailgate m_instance = new Tailgate();

    public static final Tailgate getInstance(){
        return m_instance;
    }

    private Tailgate(){
        super("Tailgate");
    }
    
    protected static final void raise(){
        m_tailGate.set(Solenoid.Value.OFF);
        m_tailgateDown = false;
    }
    
    protected static final void lower(){
        m_tailGate.set(Solenoid.Value.ON);
        m_tailgateDown = true;
    }
    
    protected static final void toggle(){
        if(m_tailgateDown)
            raise();
        else
            lower();
    }
    
    public static final boolean isLowered(){
        return m_tailgateDown;
    }

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
}

