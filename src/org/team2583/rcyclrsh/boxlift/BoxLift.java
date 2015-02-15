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
import io.github.robolib.module.controller.CANJaguar;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class BoxLift extends Subsystem {
    
    private static CANJaguar m_motorLeft;
    private static CANJaguar m_motorRight;
    
    private static double lift_speed;
    
    public static final void initialize(){
        
        m_motorLeft = RobotMap.getModule("motor_boxlift_left");
        m_motorRight = RobotMap.getModule("motor_boxlift_right");
        
        lift_speed = RobotMap.getNumber("boxlift_speed");
        stop();
    }

    private static final BoxLift m_instance = new BoxLift();

    public static final BoxLift getInstance(){
        return m_instance;
    }

    private BoxLift(){
        super("BoxLift");
    }
    
    private static void setMotors(double value){
        m_motorLeft.setSpeed(value);
        m_motorRight.setSpeed(value);
    }
    
    protected static void lift(){
        setMotors(lift_speed);
    }
    
    protected static void drop(){
        setMotors(-lift_speed);
    }
    
    protected static void stop(){
        setMotors(0);
    }
    
    public static boolean isAtTopLeftLimit(){
        return !m_motorLeft.getForwardLimitOK();
    }
    
    public static boolean isAtBottomLeftLimit(){
        return !m_motorLeft.getReverseLimitOK();
    }
    
    public static boolean isAtTopRightLimit(){
        return !m_motorRight.getForwardLimitOK();
    }
    
    public static boolean isAtBottomRightLimit(){
        return !m_motorRight.getReverseLimitOK();
    }
    
    public static boolean isAtTopLimit(){
        return isAtTopLeftLimit() && isAtTopRightLimit();
    }
    
    public static boolean isAtBottomLimit(){
        return isAtBottomLeftLimit() && isAtBottomRightLimit();
    }
    
    public void initDefaultCommand()    {
        //setDefaultCommand();
    }
}

