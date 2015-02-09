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

package org.team2583.rcyclrsh.drivetrain;

import io.github.robolib.command.Command;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.DriveBase;
import io.github.robolib.module.RobotMap;
import io.github.robolib.module.controller.Talon;
import io.github.robolib.util.log.Logger;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    /**
     * DriveMode Class
     * Used to determine the type of driving we are doing
     */
    public static class DriveMode{
        private final Command m_command;
        private final String m_name;
        
        private DriveMode(Command command, String name){
            m_command = command;
            m_name = name;
        }
        
        public Command getCommand(){return m_command;}
        public String toString(){return m_name;}
        
        public boolean inMode(){
            return m_driveMode == this;
        }
        
        public void init(){
            m_instance.setDefaultCommand(m_command);
            setDriveMode(this);
        }
        
        public static final DriveMode MECANUM = new DriveMode(new MecanumMode(), "Mecanum Drive");
        public static final DriveMode ARCADE = new DriveMode(new ArcadeMode(), "Arcade Drive");
        public static final DriveMode TANK = new DriveMode(new TankMode(), "Tank Drive");
    }
    
    private static Talon m_motorFrontLeft;
    private static Talon m_motorFrontRight;
    private static Talon m_motorRearLeft;
    private static Talon m_motorRearRight;
    
    private static DriveBase m_driveBase;
    
    private static DriveMode m_driveMode;
    
    private static final Drivetrain m_instance = new Drivetrain();
    
    private static boolean m_initialized = false;
    
    public static final void initialize(){
        if(m_initialized)
            throw new IllegalStateException("Drivetrain already Initialized");
        m_initialized = true;
        
        m_motorFrontLeft = RobotMap.get("motor_drive_front_left", Talon.class);
        m_motorFrontRight = RobotMap.get("motor_drive_front_right", Talon.class);
        m_motorRearLeft = RobotMap.get("motor_drive_back_left", Talon.class);
        m_motorRearRight = RobotMap.get("motor_drive_back_right", Talon.class);
        
        m_driveBase = new DriveBase(
                m_motorFrontLeft, m_motorFrontRight,
                m_motorRearLeft, m_motorRearRight);
        
        m_driveMode = DriveMode.MECANUM;
    }
    
    public static final Drivetrain getInstance(){
        return m_instance;
    }
    
    private Drivetrain(){
        super("Drive Base Subsystem");
    }
    
    protected static final void mecanum(double x, double y, double rotation){
        m_driveBase.mecanum(x, y, rotation, true);
        Logger.get(Drivetrain.class).info("sdakslad");
    }
    
    protected static final void arcade(double forward, double rotation){
        m_driveBase.arcade(forward, rotation, true);
    }
    
    protected static final void tank(double left, double right){
        m_driveBase.tank(left, right, true);
    }
    
    public static void setDriveMode(DriveMode mode){
        m_driveMode = mode;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(DriveMode.MECANUM.getCommand());
    }
}

