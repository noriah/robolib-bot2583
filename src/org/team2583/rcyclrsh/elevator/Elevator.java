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
import io.github.robolib.module.controller.CANJaguar;
import io.github.robolib.util.log.Logger;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class Elevator extends Subsystem {
    
    private static CANJaguar m_motorLeft;
    private static CANJaguar m_motorRight;
    
    private static double lift_speed;
    
    public static final void initialize(){
        m_motorLeft = RobotMap.getModule("motor_elevator_left");
//        m_motorRight = RobotMap.getModule("motor_elevator_right");
        
//        m_motorLeft.setPositionMode(CANJaguar.kQuadEncoder, 250, 0, 0, 0);
//        m_motorLeft.enableControl(0.0);
//        LiveWindow.addActuator("Elevator", "left", m_motorLeft);
        
        lift_speed = RobotMap.getNumber("armlift_speed");
//        stop();
    }

    private static final Elevator m_instance = new Elevator();

    public static final Elevator getInstance(){
        return m_instance;
    }

    private Elevator(){
        super("Elevator");
    }
    
    protected static void up(){
        setMotors(lift_speed);
    }
    
    protected static void down(){
        setMotors(-lift_speed);
    }
    
    protected static void stop(){
        setMotors(0);
    }
    
    protected static boolean isAtLimit(){
        return m_motorLeft.getReverseLimitOK();
    }
    
    private static void setMotors(double value){
        m_motorLeft.setSpeed(value);
//        m_motorRight.setSpeed(value);
    }
    
    public static void setVa(double value){
        m_motorRight.setSpeed(value);
    }
    
    public static void update(){
        Logger.get(Elevator.class).info(m_motorLeft.getPosition());
    }
    
    protected static void speedMode(){
        m_motorLeft.disableControl();
        m_motorLeft.setPercentMode();
    }
    
    protected static void posMode(){
    }

    public void initDefaultCommand() {
        
    }
}

