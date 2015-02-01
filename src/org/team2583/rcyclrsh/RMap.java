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

package org.team2583.rcyclrsh;

import io.github.robolib.iface.DigitalIO.DigitalChannel;
import io.github.robolib.iface.PWM.PWMChannel;
import io.github.robolib.pneumatic.SolenoidBase.SolenoidChannel;
import io.github.robolib.util.PDP.PowerChannel;

/**
 * Robot Channel mapping.
 *
 * @author noriah <vix@noriah.dev>
 */
public interface RMap {
    
    public static final PWMChannel DRIVE_BASE_MOTOR_FRONT_LEFT_PWM_CHANNEL = PWMChannel.Channel0;
    
    public static final PowerChannel DRIVE_BASE_MOTOR_FRONT_LEFT_POWER_CHANNEL = PowerChannel.Channel0;
    
    public static final PWMChannel DRIVE_BASE_MOTOR_FRONT_RIGHT_PWM_CHANNEL = PWMChannel.Channel1;
    
    public static final PowerChannel DRIVE_BASE_MOTOR_FRONT_RIGHT_POWER_CHANNEL = PowerChannel.Channel1;
    
    public static final PWMChannel DRIVE_BASE_MOTOR_REAR_LEFT_PWM_CHANNEL = PWMChannel.Channel2;
    
    public static final PowerChannel DRIVE_BASE_MOTOR_REAR_LEFT_POWER_CHANNEL = PowerChannel.Channel2;
    
    public static final PWMChannel DRIVE_BASE_MOTOR_REAR_RIGHT_PWM_CHANNEL = PWMChannel.Channel3;
    
    public static final PowerChannel DRIVE_BASE_MOTOR_REAR_RIGHT_POWER_CHANNEL = PowerChannel.Channel3;
    
    public static final PWMChannel DRAWER_MOTOR_PWM_CHANNEL = PWMChannel.Channel4;
    
    public static final DigitalChannel DRAWER_TAILGATE_INNER_SWITCH_DIGITAL_CHANNEL = DigitalChannel.Channel0;
    
    public static final DigitalChannel DRAWER_TAILGATE_OUTER_SWITCH_DIGITAL_CHANNEL = DigitalChannel.Channel1;
    
    public static final PowerChannel DRAWER_MOTOR_POWER_CHANNEL = PowerChannel.Channel4;
    
    public static final SolenoidChannel DRAWER_TAILGAIT_SOLENOID_CHANNEL = SolenoidChannel.Channel0;
    
    public static final SolenoidChannel DRAWER_RAISER_SOLENOID_CHANNEL = SolenoidChannel.Channel1;

}
