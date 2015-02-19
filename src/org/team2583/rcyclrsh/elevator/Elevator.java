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

import io.github.robolib.command.Command;
import io.github.robolib.command.SingleActionCommand;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.controller.CANJaguar;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class Elevator extends Subsystem {
    
    private static CANJaguar m_motorLeft;
    private static CANJaguar m_motorRight;
    
    static double lift_speed;
    
    public static void initialize(){
        m_motorLeft = RobotMap.getModule("motor_elevator_left");
        m_motorRight = RobotMap.getModule("motor_elevator_right");

        lift_speed = RobotMap.getNumber("armlift_speed");
        setMotors(0);
    }

    static final Elevator m_instance = new Elevator();

    public static Elevator getInstance(){
        return m_instance;
    }

    private Elevator(){
        super("Elevator");
    }
    
    public static Command up(){
        return m_instance.new CMDElevatorUp();
    }
    
    public static Command down(){
        return m_instance.new CMDElevatorDown();
    }
    
    public static Command stop(){
        return m_instance.new CMDElevatorStop();
    }
    
    public static boolean isAtTopLimit(){
        return !m_motorLeft.getForwardLimitOK() && !m_motorRight.getForwardLimitOK();
    }
    
    public static boolean isAtBottomLimit(){
        return !m_motorLeft.getReverseLimitOK() && !m_motorRight.getReverseLimitOK();
    }
    
    static void setMotors(double value){
        m_motorLeft.setSpeed(value);
        m_motorRight.setSpeed(value);
    }
    
    public void initDefaultCommand(){}
    
    private final class CMDElevatorUp extends Command {
        public CMDElevatorUp(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){setMotors(lift_speed);}
        protected boolean isFinished(){return isAtTopLimit();}
        protected void end(){setMotors(0);}
        protected void interrupted(){setMotors(0);}
    }
    
    private final class CMDElevatorDown extends Command {
        public CMDElevatorDown(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){setMotors(-lift_speed);}
        protected boolean isFinished(){return isAtBottomLimit();}
        protected void end(){setMotors(0);}
        protected void interrupted(){setMotors(0);}
    }

    private final class CMDElevatorStop extends SingleActionCommand {
        public CMDElevatorStop(){requires(m_instance);}
        protected void execute(){setMotors(0);}
    }

}

