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
import io.github.robolib.module.controller.LimitedController;
import io.github.robolib.util.mapper.RobotMap;

/**
 * 
 * @author noriah <vix@noriah.dev>
 */
public final class Drawer extends Subsystem {
    
    static LimitedController m_drawerMotor;
    
    static double m_drawerOutSpeed;
    static double m_drawerInSpeed;
    static boolean m_drawerExtended;
    
    public static void initialize(){
        m_drawerMotor = RobotMap.getModule("limited_controller_drawer");
        
        m_drawerOutSpeed = RobotMap.getNumber("drawer_out_speed");
        m_drawerInSpeed = -RobotMap.getNumber("drawer_in_speed");
    }
    
    static final Drawer m_instance = new Drawer();
    
    public static Drawer getInstance(){
        return m_instance;
    }
    
    private Drawer(){
        super("Drawer Subsystem");
    }
    
    public static Command extend(){
        return m_instance.new CMDExtendDrawer();
    }
    
    public static Command retract(){
        return m_instance.new CMDRetractDrawer();
    }
    
    public static Command toggle(){
        return m_instance.new CMDToggleDrawer();
    }
    
    public static Command stop(){
        return m_instance.new CMDStopDrawer();
    }
    
    protected static boolean isExtended(){
        return m_drawerMotor.atFrontLimit();
    }
    
    protected static boolean isRetracted(){
        return m_drawerMotor.atBackLimit();
    }

    public void initDefaultCommand(){}
    
    private class CMDExtendDrawer extends Command {
        public CMDExtendDrawer(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){m_drawerMotor.setSpeed(m_drawerOutSpeed);}
        protected boolean isFinished(){return m_drawerMotor.atFrontLimit();}
        protected void end(){
            m_drawerMotor.setSpeed(0);
            m_drawerExtended = true;
        }
        protected void interrupted(){m_drawerMotor.setSpeed(0);}
    }
    
    private class CMDRetractDrawer extends Command {
        public CMDRetractDrawer(){requires(m_instance);}
        protected void initialize(){}
        protected void execute(){m_drawerMotor.setSpeed(m_drawerInSpeed);}
        protected boolean isFinished(){return m_drawerMotor.atBackLimit();}
        protected void end(){
            m_drawerMotor.setSpeed(0);
            m_drawerExtended = false;
        }
        protected void interrupted(){m_drawerMotor.setSpeed(0);}
    }
    
    private class CMDToggleDrawer extends Command {
        public CMDToggleDrawer(){requires(m_instance);}
        double dir;
        protected void initialize(){
            dir = m_drawerExtended ? m_drawerInSpeed : m_drawerOutSpeed;
        }
        protected void execute(){m_drawerMotor.setSpeed(dir);}
        protected boolean isFinished(){
            return dir > 0 ? m_drawerMotor.atFrontLimit() : m_drawerMotor.atBackLimit();
        }
        protected void end(){
            m_drawerMotor.setSpeed(0);
            m_drawerExtended = dir > 0;
        }
        protected void interrupted(){m_drawerMotor.setSpeed(0);}
    }
    
    private class CMDStopDrawer extends SingleActionCommand {
        public CMDStopDrawer(){requires(m_instance);}
        protected void execute(){m_drawerMotor.setSpeed(0);}
    }
}

