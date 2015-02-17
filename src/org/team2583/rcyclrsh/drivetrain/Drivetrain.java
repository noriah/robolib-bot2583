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
import io.github.robolib.module.RoboRIO;
import io.github.robolib.module.controller.Talon;
import io.github.robolib.module.iface.AnalogIO.AnalogChannel;
import io.github.robolib.module.iface.DigitalIO.DigitalChannel;
import io.github.robolib.module.iface.DigitalInput;
import io.github.robolib.module.iface.I2C.Port;
import io.github.robolib.module.sensor.Gyro;
import io.github.robolib.module.sensor.InternalAccelerometer;
import io.github.robolib.module.sensor.mpu6050.MPU6050;
import io.github.robolib.module.sensor.mpu6050.Quaternion;
import io.github.robolib.module.sensor.mpu6050.VectorDouble;
import io.github.robolib.module.sensor.mpu6050.VectorInt16;
import io.github.robolib.util.log.Logger;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 */
public final class Drivetrain extends Subsystem implements Runnable{
    
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
    
    private static MPU6050 m_mpu;

    private static DigitalInput m_dig;
    
    private static final Drivetrain m_instance = new Drivetrain();
    
    private static boolean m_initialized = false;
    
    private static Thread m_thread;
    

    protected static Gyro gyro;
    private static double m_rotation;
    
    public static void initialize(){
        if(m_initialized)
            throw new IllegalStateException("Drivetrain already Initialized");
        m_initialized = true;
        
        m_motorFrontLeft = RobotMap.getModule("motor_drive_front_left");
        m_motorFrontRight = RobotMap.getModule("motor_drive_front_right");
        m_motorRearLeft = RobotMap.getModule("motor_drive_back_left");
        m_motorRearRight = RobotMap.getModule("motor_drive_back_right");
        
        m_driveBase = new DriveBase(
                m_motorFrontLeft, m_motorFrontRight,
                m_motorRearLeft, m_motorRearRight);
        
        m_driveBase.setSafetyEnabled(false);
        
        q = new Quaternion();
        aa = new VectorInt16();
        aaReal = new VectorInt16();
        aaWorld = new VectorInt16();
        gravity = new VectorDouble();
        m_mpu = new MPU6050(Port.ONBOARD);
        
        m_dig = new DigitalInput(DigitalChannel.DIO4);
        m_mpu.initialize();
        
        devStatus = m_mpu.dmpInitialize();
        m_mpu.setXGyroOffset(220);
        m_mpu.setYGyroOffset(76);
        m_mpu.setZGyroOffset(-85);
        m_mpu.setZAccelOffset(1788);
        
        if(devStatus == 0){
            m_mpu.setDMPEnabled(true);
            
            m_dig.requestInterrupt((int a, Object b) -> m_instance.dmpDataReady());
            m_dig.setUpSourceEdge(true, false);
            m_dig.enableInterrupts();
            
            mpuIntStatus = m_mpu.getIntStatus();
            
            packetSize = m_mpu.dmpGetFIFOPacketSize();
        }
        
        gyro = new Gyro(AnalogChannel.AIO0);
        gyro.setSensitivity(0.0072);
        
        m_thread = new Thread(m_instance);
        m_thread.setPriority(2);
        m_thread.start();
        
    }
    
    public static Drivetrain getInstance(){
        return m_instance;
    }
    
    private Drivetrain(){
        super("Drive Base Subsystem");
    }
    
    private void dmpDataReady(){
        mpuInterrupt = true;
    }
    
    protected static void mecanum(double x, double y, double rotation){
        m_driveBase.mecanum(x, checkAccel(y), rotation, true);
    }
    
    protected static void arcade(double forward, double rotation){
        m_driveBase.arcade(checkAccel(forward), rotation, true);
    }
    
    protected static void tank(double left, double right){
        m_driveBase.tank(left, right, true);
    }
    
    public static void setDriveMode(DriveMode mode){
        m_driveMode = mode;
        m_instance.setDefaultCommand(m_driveMode.m_command);
    }
    
    private static double checkAccel(double y){
        if(Math.abs(InternalAccelerometer.getAccelerationZ()) >= 2)
            return 0.75 * y;
        return y;
    }
    
    private static byte mpuIntStatus;
    private static byte devStatus;
    private static short packetSize;
    private static short fifoCount;
    private static byte[] fifoBuffer = new byte[64];
    private static boolean mpuInterrupt = false;
    private static Quaternion q;
    private static VectorInt16 aa;
    private static VectorInt16 aaReal;
    private static VectorInt16 aaWorld;
    private static VectorDouble gravity;
    private static double[] euler = new double[3];
    private static double[] ypr = new double[3];
    
    public void run(){
        while(true){

//            Logger.get(this).info(String.format("%6.2f   %6.2f",gyro.getAngle(), gyro.getRate()));
//            while(!mpuInterrupt && fifoCount < packetSize){
//                
//            }
//            
//            mpuInterrupt = false;
//            
//            mpuIntStatus = m_mpu.getIntStatus();
//            
//            fifoCount = m_mpu.getFIFOCount();
//            
//            if(((mpuIntStatus & 0x10) != 0)|| fifoCount == 1024){
//                m_mpu.resetFIFO();
//                Logger.get(this).warn("FIFO overflow!");
//                
//            }else if((mpuIntStatus & 0x02) != 0){
//                while(fifoCount < packetSize) fifoCount = m_mpu.getFIFOCount();
//                
//                m_mpu.getFIFOBytes(fifoBuffer, packetSize);
//                
//                fifoCount -= packetSize;
//                
//                m_mpu.dmpGetQuaternion(q, fifoBuffer);
//                m_mpu.dmpGetAccel(aa, fifoBuffer);
//                m_mpu.dmpGetGravity(gravity, q);
//                m_mpu.dmpGetLinearAccel(aaReal, aa, gravity);
//                m_mpu.dmpGetYawPitchRoll(ypr, q, gravity);
//                ypr = m_mpu.getRotation();
//                RoboRIO.getFPGATimestamp();
////                Logger.get(this).info(String.format("%.2f %.2f %.2f", ypr[0], ypr[1], ypr[2]));
////                Logger.get(this).info(String.format("ypr %.2f %.2f %.2f", ypr[0] * MathUtils.M_180_OVER_PI, ypr[1] * MathUtils.M_180_OVER_PI, ypr[2] * MathUtils.M_180_OVER_PI));
////                Logger.get(this).info(String.format("realAccel %.2f %.2f %.2f", aaReal.x / 1024.0, aaReal.y / 1024.0, aaReal.z / 1024.0));
//            }
        }
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(DriveMode.MECANUM.getCommand());
    }
}

