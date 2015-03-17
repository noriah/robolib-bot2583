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

package org.team2583.rcyclrsh.systems;

import io.github.robolib.command.Command;
import io.github.robolib.command.ContinuousCommand;
//import io.github.robolib.command.ContinuousCommand;
import io.github.robolib.command.SingleActionCommand;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.actuator.CANJaguar;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class RightElevator extends Subsystem {
    
    private static CANJaguar m_motor;
    
    static double lift_speed;
    
    public static void initialize(){
        m_motor = RobotMap.getModule("motor_elevator_right");

        lift_speed = RobotMap.getNumber("armlift_speed");
        m_motor.setSpeed(0);
    }

    static final RightElevator m_instance = new RightElevator();

    public static RightElevator getInstance(){
        return m_instance;
    }

    private RightElevator(){
        super("Elevator");
    }
    
    public static Command up(){
        return m_instance.new CMDRightElevatorUp();
    }
    
    public static Command down(){
        return m_instance.new CMDRightElevatorDown();
    }
    
    public static Command upContinue(){
        return m_instance.new CMDRightElevatorUpContinue();
    }
    
    public static Command downContinue(){
        return m_instance.new CMDRightElevatorDownContinue();
    }
    
    public static Command stop(){
        return m_instance.new CMDRightElevatorStop();
    }
    
    public static boolean isAtTopLimit(){
        return !m_motor.getForwardLimitOK();
    }
    
    public static boolean isAtBottomLimit(){
        return !m_motor.getReverseLimitOK();
    }
    
    static void setMotor(double value){
        m_motor.setSpeed(0);
    }
    
    public void initDefaultCommand(){}
    
    private final class CMDRightElevatorUp extends Command {
        public CMDRightElevatorUp(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){m_motor.setSpeed(lift_speed);}
        protected boolean isFinished(){return isAtTopLimit();}
        protected void end(){m_motor.setSpeed(0);}
        protected void interrupted(){m_motor.setSpeed(0);}
    }
    
    private final class CMDRightElevatorDown extends Command {
        public CMDRightElevatorDown(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){m_motor.setSpeed(-lift_speed);}
        protected boolean isFinished(){return isAtBottomLimit();}
        protected void end(){m_motor.setSpeed(0);}
        protected void interrupted(){m_motor.setSpeed(0);}
    }

    private final class CMDRightElevatorUpContinue extends ContinuousCommand {
        public CMDRightElevatorUpContinue(){requires(m_instance);}
        protected void execute(){m_motor.setSpeed(lift_speed);}
        protected void end(){m_motor.setSpeed(0);}
        protected void interrupted(){m_motor.setSpeed(0);}
    }
    
    private final class CMDRightElevatorDownContinue extends ContinuousCommand {
        public CMDRightElevatorDownContinue(){requires(m_instance);}
        protected void execute(){m_motor.setSpeed(-lift_speed);}
        protected void end(){m_motor.setSpeed(0);}
        protected void interrupted(){m_motor.setSpeed(0);}
    }

    private final class CMDRightElevatorStop extends SingleActionCommand {
        public CMDRightElevatorStop(){requires(m_instance);}
        protected void execute(){m_motor.setSpeed(0);}
    }
}

