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

package org.team2583.rcyclrsh.drawer;

import io.github.robolib.command.Subsystem;
import io.github.robolib.module.LimitSystem;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.module.controller.CANJaguar;
import io.github.robolib.module.controller.LimitedController;
import io.github.robolib.module.iface.DigitalIO.DigitalChannel;
import io.github.robolib.module.sensor.LimitSwitch;
import io.github.robolib.module.sensor.LimitSwitch.SwitchType;
import io.github.robolib.util.mapper.RobotMap;

/**
 * 
 * @author noriah <vix@noriah.dev>
 */
public class Drawer extends Subsystem {
    
    private static LimitedController m_drawerMotor;
    private static Solenoid m_tailGate;
    private static Solenoid m_drawerRaiser;
    private static Solenoid m_boxEjector;
    
    public static final void initialize(){
        m_drawerMotor = new LimitedController(
                RobotMap.get("motor_drawer", CANJaguar.class),
                new LimitSystem(
                        new LimitSwitch(
                                DigitalChannel.Channel0,
                                SwitchType.OPEN),
                        new LimitSwitch(
                                DigitalChannel.Channel1,
                                SwitchType.OPEN)));
        
        m_tailGate = RobotMap.get("solenoid_tailgate", Solenoid.class);
        m_drawerRaiser = RobotMap.get("solenoid_drawerRaiser", Solenoid.class);
        m_boxEjector = RobotMap.get("solenoid_boxEjector", Solenoid.class);
    }
    
    private static final Drawer m_instance = new Drawer();
    
    public static final Drawer getInstance(){
        return m_instance;
    }
    
    private Drawer(){
        super("Drawer Subsystem");
    }
    
    protected static final void raiseTailgate(){
        m_tailGate.set(Solenoid.Value.OFF);
    }
    
    protected static final void lowerTailgate(){
        m_tailGate.set(Solenoid.Value.ON);
    }
    
    public static final boolean getTailgateLowered(){
        return m_tailGate.getState();
    }
    
    protected static final void extendEjector(){
        m_boxEjector.set(Solenoid.Value.ON);
    }
    
    protected static final void retractEjector(){
        m_boxEjector.set(Solenoid.Value.OFF);
    }
    
    public static final boolean getEjectorExtended(){
        return m_boxEjector.getState();
    }
    
    protected static final void extendDrawer(){
        m_drawerMotor.setSpeed(1);
    }
    
    protected static final void stopDrawer(){
        m_drawerMotor.setSpeed(0);
    }
    
    protected static final void retractDrawer(){
        m_drawerMotor.setSpeed(-1);
    }
    
    protected static final boolean getDrawerExtended(){
        return m_drawerMotor.atFrontLimit();
    }
    
    protected static final boolean getDrawerRetracted(){
        return m_drawerMotor.atBackLimit();
    }
    
    protected static final void raiseDrawer(){
        m_drawerRaiser.set(Solenoid.Value.ON);
    }
    
    protected static final void lowerDrawer(){
        m_drawerRaiser.set(Solenoid.Value.OFF);
    }
    
    public static final boolean getDrawerRasied(){
        return m_drawerRaiser.getState();
    }

    public void initDefaultCommand(){}
}

