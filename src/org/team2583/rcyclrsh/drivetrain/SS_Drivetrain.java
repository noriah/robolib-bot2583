package org.team2583.rcyclrsh.drivetrain;

import org.team2583.rcyclrsh.RMap;

import io.github.robolib.command.Subsystem;
import io.github.robolib.output.DriveBase;
import io.github.robolib.output.SpeedController;
import io.github.robolib.output.Talon;


/**
 *
 */
public class SS_Drivetrain extends Subsystem implements RMap {
    
    private SpeedController m_motorFrontLeft;
    private SpeedController m_motorFrontRight;
    private SpeedController m_motorRearLeft;
    private SpeedController m_motorRearRight;
    
    private DriveBase m_driveBase;
    
    private SS_Drivetrain(){
        super("Drive Base Subsystem");
        
        m_motorFrontLeft = new Talon(
                DRIVE_BASE_MOTOR_FRONT_LEFT_PWM_CHANNEL,
                "Front Left Drive Motor",
                DRIVE_BASE_MOTOR_FRONT_LEFT_POWER_CHANNEL);
        
        m_motorFrontRight = new Talon(
                DRIVE_BASE_MOTOR_FRONT_RIGHT_PWM_CHANNEL,
                "Front Right Drive Motor",
                DRIVE_BASE_MOTOR_FRONT_RIGHT_POWER_CHANNEL);
        
        m_motorRearLeft = new Talon(
                DRIVE_BASE_MOTOR_REAR_LEFT_PWM_CHANNEL,
                "Rear Left Drive Motor",
                DRIVE_BASE_MOTOR_REAR_LEFT_POWER_CHANNEL);
        
        m_motorRearRight = new Talon(
                DRIVE_BASE_MOTOR_REAR_RIGHT_PWM_CHANNEL,
                "Rear RIght Drive Motor",
                DRIVE_BASE_MOTOR_REAR_RIGHT_POWER_CHANNEL);
        
        m_driveBase = new DriveBase(
                m_motorFrontLeft, m_motorFrontRight,
                m_motorRearLeft, m_motorRearRight);
    }

    public void initDefaultCommand() {
    }
}

