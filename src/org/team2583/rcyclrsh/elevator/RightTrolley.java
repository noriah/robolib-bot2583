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

package org.team2583.rcyclrsh.elevator;

import io.github.robolib.command.Subsystem;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class RightTrolley extends Subsystem {

    private static Solenoid m_rightHandFlip;

    private static boolean m_flipped;
    
    public static final void initialize(){
        m_rightHandFlip = RobotMap.getModule("solenoid_right_hand");
    }

    private static final RightTrolley m_instance = new RightTrolley();

    public static final RightTrolley getInstance(){
        return m_instance;
    }

    private RightTrolley(){ super("RightTrolley"); }
    
    
    protected static void flipIn(){
        m_rightHandFlip.set(Solenoid.Value.ON);
        m_flipped = true;
    }
    
    protected static void flipOut(){
        m_rightHandFlip.set(Solenoid.Value.OFF);
        m_flipped = false;
    }
    
    protected static void toggle(){
        if(m_flipped)
            flipOut();
        else
            flipIn();
    }
    
    public static boolean isIn(){
        return m_flipped;
    }

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
}

