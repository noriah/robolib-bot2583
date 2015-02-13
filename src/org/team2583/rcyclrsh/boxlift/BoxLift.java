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

package org.team2583.rcyclrsh.boxlift;

import io.github.robolib.command.Subsystem;
import io.github.robolib.identifier.BooleanSource;
import io.github.robolib.module.controller.CANJaguar;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class BoxLift extends Subsystem {
    
    private static CANJaguar m_motorLiftLeft;
    private static CANJaguar m_motorLiftRight;
    
    private static BooleanSource m_limitLeft;
    private static BooleanSource m_limitRight;
//    private static S
    
    public static final void initialize(){
        
        m_motorLiftLeft = RobotMap.getModule("motor_box_lift_left");
        m_motorLiftRight = RobotMap.getModule("motor_box_lift_right");
        
        m_limitLeft = () -> m_motorLiftLeft.getForwardLimitOK();
        m_limitRight = () -> m_motorLiftRight.getForwardLimitOK();
    }

    private static final BoxLift m_instance = new BoxLift();

    public static final BoxLift getInstance(){
        return m_instance;
    }

    private BoxLift(){
        super("BoxLift");
    }

    public void initDefaultCommand()    {
        //setDefaultCommand();
    }
}

