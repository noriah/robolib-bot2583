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

import org.team2583.rcyclrsh.OI;

import io.github.robolib.command.Command;
import io.github.robolib.command.ContinuousCommand;
import io.github.robolib.command.SingleActionCommand;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.actuator.DriveBase;
import io.github.robolib.module.actuator.Talon;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class Drivetrain extends Subsystem{
    
    /**
     * DriveMode Class
     * Used to determine the type of driving we are doing
     */
    private final static class DriveMode {
        public final Command m_command;
        public final String m_name;
        
        public DriveMode(Command command, String name){
            m_command = command;
            m_name = name;
        }
        
        public String toString(){return m_name;}
    }
    
    private static Talon m_motorFrontLeft;
    private static Talon m_motorFrontRight;
    private static Talon m_motorRearLeft;
    private static Talon m_motorRearRight;
    
    public static DriveMode MECANUM;
    public static DriveMode ARCADE;
    public static DriveMode TANK;
    
    static DriveBase m_driveBase;

    private static final Drivetrain m_instance = new Drivetrain();

    public static void initialize(){
        
        m_motorFrontLeft = RobotMap.getModule("motor_drive_front_left");
        m_motorFrontRight = RobotMap.getModule("motor_drive_front_right");
        m_motorRearLeft = RobotMap.getModule("motor_drive_back_left");
        m_motorRearRight = RobotMap.getModule("motor_drive_back_right");
        
        m_driveBase = new DriveBase(
                m_motorFrontLeft, m_motorFrontRight,
                m_motorRearLeft, m_motorRearRight);
        
        m_driveBase.setSafetyEnabled(false);
        
        MECANUM = new DriveMode(m_instance.new MecanumMode(), "Mecanum Drive");
        ARCADE = new DriveMode(m_instance.new ArcadeMode(), "Arcade Drive");
        TANK = new DriveMode(m_instance.new TankMode(), "Tank Drive");
    }
    
    public static Drivetrain getInstance(){
        return m_instance;
    }
    
    private Drivetrain(){
        super("Drive Base Subsystem");
    }

    public static Command setDriveMode(final DriveMode mode){
        return m_instance.new CMDChangeDrivemode(mode);
    }

    public void initDefaultCommand() {
        setDefaultCommand(MECANUM.m_command);
    }
    
    private final class CMDChangeDrivemode extends SingleActionCommand {
        private final DriveMode m_mode;
        public CMDChangeDrivemode(final DriveMode mode){
            super("C_ChangeDrivemode - " + mode);
            requires(m_instance);
            m_mode = mode;
        }
        protected void execute(){
            m_instance.setDefaultCommand(m_mode.m_command);
        }
    }
    
    private final class ArcadeMode extends ContinuousCommand {
        public ArcadeMode(){requires(m_instance);}
        protected void execute() {
            double scale = (OI.BTN_SPEED_SCALE.getState() ? 0.75 : 1.0);
            m_driveBase.arcade(
                    OI.AXIS_DRIVER_LEFT_Y.get() * scale,
                    OI.AXIS_DRIVER_RIGHT_X.get() * scale, true);
        }
    }

    private final class MecanumMode extends ContinuousCommand {
        private double slow = 1;
        private boolean pressed = false;
        public MecanumMode(){requires(m_instance);}
        protected void execute() {
            if(OI.BTN_SPEED_SCALE.getState()){
                if(!pressed){
                    pressed = true;
                    if(slow == 1)
                        slow = 0.65;
                    else
                        slow = 1;
                }
            }else{
                pressed = false;
            }
            m_driveBase.mecanum(
                    OI.AXIS_DRIVER_LEFT_X.get() * slow,
                    OI.AXIS_DRIVER_LEFT_Y.get() * slow,
                    OI.AXIS_DRIVER_RIGHT_X.get() * slow, true);
        }

    }
    
    private final class TankMode extends ContinuousCommand {
        public TankMode(){requires(m_instance);}
        protected void execute() {
            double scale = (OI.BTN_SPEED_SCALE.getState() ? 0.75 : 1.0);
            m_driveBase.tank(
                    OI.AXIS_DRIVER_LEFT_Y.get() * scale,
                    OI.AXIS_DRIVER_RIGHT_Y.get() * scale, true);
        }
    }
}

