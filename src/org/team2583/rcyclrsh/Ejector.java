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

import io.github.robolib.command.Command;
import io.github.robolib.command.SingleActionCommand;
import io.github.robolib.command.Subsystem;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.util.mapper.RobotMap;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public final class Ejector extends Subsystem {

    static Solenoid m_boxEjector;
    
    static boolean m_extended;
    
    public static void initialize(){
        m_boxEjector = RobotMap.getModule("solenoid_boxEjector"); 
    }

    static final Ejector m_instance = new Ejector();

    public static Ejector getInstance(){
        return m_instance;
    }

    private Ejector(){
        super("Ejector Subsystem");
    }
    
    public static Command extend(){
        return m_instance.new CMDExtendEjector();
    }
    
    public static Command retract(){
        return m_instance.new CMDRetractEjector();
    }
    
    public static Command toggle(){
        return m_instance.new CMDToggleEjector();
    }
    
    public static boolean isExtended(){
        return m_extended;
    }

    public void initDefaultCommand() {}

    private class CMDExtendEjector extends SingleActionCommand {
        public CMDExtendEjector(){requires(m_instance);}
        protected void execute(){
            m_boxEjector.set(Solenoid.Value.ON);
            m_extended = true;
        }
    }

    private class CMDRetractEjector extends SingleActionCommand {
        public CMDRetractEjector(){requires(m_instance);}
        protected void execute(){
            m_boxEjector.set(Solenoid.Value.OFF);
            m_extended = false;
        }
    }

    private class CMDToggleEjector extends SingleActionCommand {
        public CMDToggleEjector(){requires(m_instance);}
        protected void execute(){
            if(m_extended){
                m_boxEjector.set(Solenoid.Value.OFF);
                m_extended = false;
            }else{
                m_boxEjector.set(Solenoid.Value.ON);
                m_extended = true;
            }
        }
    }
}

