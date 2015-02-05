package org.team2583.rcyclrsh.drivetrain;

import org.team2583.rcyclrsh.RMap;

import io.github.robolib.command.Subsystem;
import io.github.robolib.module.DriveBase;
import io.github.robolib.module.controller.SpeedController;
import io.github.robolib.module.controller.Talon;
import io.github.robolib.util.log.Logger;


/**
 *
 */
public class SS_Drivetrain extends Subsystem {
    
    private SpeedController m_motorFrontLeft;
    private SpeedController m_motorFrontRight;
    private SpeedController m_motorRearLeft;
    private SpeedController m_motorRearRight;
    
//    private Encoder m_encoderFrontLeft = new Encoder()
    
    private DriveBase m_driveBase;
    
    private static SS_Drivetrain m_instance;
    
    public static final SS_Drivetrain getInstance(){
        return m_instance == null ? m_instance = new SS_Drivetrain() : m_instance;
    }
    
    private SS_Drivetrain(){
        super("Drive Base Subsystem");
        
        m_motorFrontLeft = (Talon)RMap.getNewSpeedController("motor_drive_front_left");
        
        m_motorFrontRight = (Talon)RMap.getNewSpeedController("motor_drive_front_right");
        
        m_motorRearLeft = (Talon)RMap.getNewSpeedController("motor_drive_back_left");
        
        m_motorRearRight = (Talon)RMap.getNewSpeedController("motor_drive_back_right");
        
        m_driveBase = new DriveBase(
                m_motorFrontLeft, m_motorFrontRight,
                m_motorRearLeft, m_motorRearRight);
    }
    
    public final void mecanum(double a, double b, double c){
        m_driveBase.mecanum(a, b, c);
        Logger.get(this).info(m_motorFrontLeft);
        Logger.get(this).info(m_motorFrontRight);
    }

    public void initDefaultCommand() {
    }
}

