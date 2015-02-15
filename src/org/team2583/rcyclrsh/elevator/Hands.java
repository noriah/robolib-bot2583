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
public final class Hands extends Subsystem {
    
    private static Solenoid m_leftHandFlip;
    private static Solenoid m_rightHandFlip;
    
    private static boolean right_flipped;
    private static boolean left_flipped;
    
    public static final void initialize(){
        m_leftHandFlip = RobotMap.getModule("solenoid_left_hand");
        m_rightHandFlip = RobotMap.getModule("solenoid_right_hand");
    }

    private static final Hands m_instance = new Hands();

    public static final Hands getInstance(){
        return m_instance;
    }

    private Hands(){
        super("Hands");
    }
    
    protected static void flipLeftIn(){
        m_leftHandFlip.set(Solenoid.Value.ON);
        left_flipped = true;
    }
    
    protected static void flipLeftOut(){
        m_leftHandFlip.set(Solenoid.Value.OFF);
        left_flipped = false;
    }
    
    protected static void flipRightIn(){
        m_rightHandFlip.set(Solenoid.Value.ON);
        right_flipped = true;
    }
    
    protected static void flipRightOut(){
        m_rightHandFlip.set(Solenoid.Value.OFF);
        right_flipped = false;
    }
    
    public static boolean getLeftIn(){
        return left_flipped;
    }
    
    public static boolean getRightIn(){
        return right_flipped;
    }

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
}

