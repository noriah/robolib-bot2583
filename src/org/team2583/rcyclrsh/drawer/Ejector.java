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
public class Ejector extends Subsystem {

    private static Solenoid m_boxEjector;
    
    private static boolean m_extended;
    
    public static final void initialize(){
        m_boxEjector = RobotMap.getModule("solenoid_boxEjector"); 
    }

    private static final Ejector m_instance = new Ejector();

    public static final Ejector getInstance(){
        return m_instance;
    }

    private Ejector(){
        super("Ejector");
    }

    protected static final void extend(){
        m_boxEjector.set(Solenoid.Value.ON);
        m_extended = true;
    }
    
    protected static final void retract(){
        m_boxEjector.set(Solenoid.Value.OFF);
        m_extended = false;
    }
    
    protected static final void toggle(){
        if(m_extended)
            retract();
        else
            extend();
    }
    
    public static final boolean isExtended(){
        return m_extended;
    }

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
}

