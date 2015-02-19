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
public final class Tailgate extends Subsystem {

    static Solenoid m_tailGate;

    static boolean m_tailgateDown;
    
    public static void initialize(){
        m_tailGate = RobotMap.getModule("solenoid_tailgate");
    }

    static final Tailgate m_instance = new Tailgate();

    public static Tailgate getInstance(){
        return m_instance;
    }

    private Tailgate(){
        super("Tailgate Subsystem");
    }

    public static Command raise(){
        return m_instance.new CMDRaiseTailgate();
    }

    public static Command lower(){
        return m_instance.new CMDLowerTailgate();
    }
    
    public static Command toggle(){
        return m_instance.new CMDToggleTailgate();
    }
    
    public static boolean isLowered(){
        return m_tailgateDown;
    }

    public void initDefaultCommand() {}
    
    /**
     *
     * @author noriah <vix@noriah.dev>
     */
    private final class CMDLowerTailgate extends SingleActionCommand {
        public CMDLowerTailgate(){requires(m_instance);}
        protected void execute(){
            m_tailGate.set(Solenoid.Value.ON);
            m_tailgateDown = true;
        }
    }
    
    private final class CMDRaiseTailgate extends SingleActionCommand {
        public CMDRaiseTailgate(){requires(m_instance);}
        protected void execute(){
            m_tailGate.set(Solenoid.Value.OFF);
            m_tailgateDown = false;
        }
    }
    
    private final class CMDToggleTailgate extends SingleActionCommand {
        public CMDToggleTailgate(){requires(m_instance);}
        protected void execute(){
            if(m_tailgateDown){
                m_tailGate.set(Solenoid.Value.OFF);
                m_tailgateDown = false;
            }else{
                m_tailGate.set(Solenoid.Value.ON);
                m_tailgateDown = true;
            }
        }
    }
}

