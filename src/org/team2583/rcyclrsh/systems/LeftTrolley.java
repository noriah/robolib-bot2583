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
import io.github.robolib.command.SingleActionCommand;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class LeftTrolley extends Subsystem {
    
    static Solenoid m_solenoid;
    
    static boolean m_flipped;
    
    public static void initialize(){
        m_solenoid = RobotMap.getModule("solenoid_left_hand");
    }

    private static final LeftTrolley m_instance = new LeftTrolley();

    public static LeftTrolley getInstance(){
        return m_instance;
    }

    private LeftTrolley(){
        super("LeftTrolley");
    }
    
    public static Command flipIn(){
        return m_instance.new CMDLeftHandFlipIn();
    }
    
    public static Command flipOut(){
        return m_instance.new CMDLeftHandFlipOut();
    }
    
    public static Command toggle(){
        return m_instance.new CMDLeftHandToggle();
    }
    
    public static boolean isIn(){
        return m_flipped;
    }

    public void initDefaultCommand() {}
    
    private final class CMDLeftHandFlipOut extends SingleActionCommand {
        public CMDLeftHandFlipOut(){requires(m_instance);}
        protected void execute(){
            m_solenoid.set(Solenoid.Value.OFF);
            m_flipped = false;
        }
    }
    
    private final class CMDLeftHandFlipIn extends SingleActionCommand {
        public CMDLeftHandFlipIn(){requires(m_instance);}
        protected void execute(){
            m_solenoid.set(Solenoid.Value.ON);
            m_flipped = true;
        }
    }
    
    private final class CMDLeftHandToggle extends SingleActionCommand {
        public CMDLeftHandToggle(){requires(m_instance);}
        protected void execute(){
            if(m_flipped){
                m_solenoid.set(Solenoid.Value.OFF);
                m_flipped = false;
            }else{
                m_solenoid.set(Solenoid.Value.ON);
                m_flipped = true;
            }
        }
    }
}

