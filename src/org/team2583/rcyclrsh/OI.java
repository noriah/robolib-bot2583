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

import org.team2583.rcyclrsh.boxlift.CMDTogglBoxeLift;
import org.team2583.rcyclrsh.drawer.CGEjectBoxes;
import org.team2583.rcyclrsh.drawer.CMDDrawerInContinue;
import org.team2583.rcyclrsh.drawer.CMDDrawerOutContinue;
import org.team2583.rcyclrsh.drawer.CMDToggleTailgate;
import org.team2583.rcyclrsh.elevator.CMDArmsDownContinue;
import org.team2583.rcyclrsh.elevator.CMDArmsUpContinue;
import org.team2583.rcyclrsh.elevator.CMDLeftHandToggle;

import io.github.robolib.control.HIDAxis;
import io.github.robolib.control.HIDButton;
import io.github.robolib.control.Joystick.JSID;
import io.github.robolib.control.XBoxController;
import io.github.robolib.module.sensor.LimitSwitch;
import io.github.robolib.util.mapper.RobotMap;

/**
 * The Operator Interface Class. Controls Things that interface with the operator.
 *
 * @author noriah <vix@noriah.dev>
 */
public final class OI {

    public static final XBoxController JS_DRIVER;
    public static final XBoxController JS_ACTOR;
    public static final HIDAxis AXIS_DRIVER_LEFT_X;
    public static final HIDAxis AXIS_DRIVER_LEFT_Y;
    public static final HIDAxis AXIS_DRIVER_RIGHT_X;
    public static final HIDAxis AXIS_DRIVER_RIGHT_Y;
    public static final HIDButton BTN_SPEED_SCALE;
//    public static final HIDButton BTN_DRIVEMODE_MECANUM;
//    public static final HIDButton BTN_DRIVEMODE_ARCADE;
//    public static final HIDButton BTN_DRIVEMODE_TANK;
    
    public static final HIDButton BTN_EJECT_BOXES;
    
    public static final HIDButton BTN_DRAWER_OUT;
    public static final HIDButton BTN_DRAWER_IN;
    
    public static final HIDButton BTN_BOXES_TOGGLE;
    
    public static final HIDButton BTN_TOGGLE_GATE;
    
    public static final HIDButton BTN_ARM_TOGGLE;
    
    public static final HIDButton BTN_ARM_UP;
    
    public static final HIDButton BTN_ARM_DOWN;
    
    public static final LimitSwitch SWITCH_HAND_LEFT;
    
    static{

        JS_DRIVER = new XBoxController(JSID.JS0);
        JS_ACTOR = new XBoxController(JSID.JS1);
        AXIS_DRIVER_LEFT_X = JS_DRIVER.getAxisLeftX();
        AXIS_DRIVER_LEFT_Y = JS_DRIVER.getAxisLeftY();
        AXIS_DRIVER_RIGHT_X = JS_DRIVER.getAxisRightX();
        AXIS_DRIVER_RIGHT_Y = JS_DRIVER.getAxisRightY();
        BTN_TOGGLE_GATE = JS_DRIVER.getButtonA();
        BTN_BOXES_TOGGLE = JS_DRIVER.getButtonB();
        BTN_EJECT_BOXES = JS_DRIVER.getButtonY();
        BTN_DRAWER_OUT = JS_DRIVER.getButtonSelect();
        BTN_DRAWER_IN = JS_DRIVER.getButtonStart();
        BTN_SPEED_SCALE = JS_DRIVER.getButtonRightStick();
        
        BTN_ARM_UP = JS_DRIVER.getButtonLeftShoulder();
        
        BTN_ARM_DOWN = JS_DRIVER.getButtonRightShoulder();
        
        BTN_ARM_TOGGLE = JS_DRIVER.getButtonX();
        
        SWITCH_HAND_LEFT = RobotMap.getModule("limit_switch_hand_left");
//        SWITCH_HAND_RIGHT = RobotMap.getModule("limit_switch_hand_right");

//        BTN_DRIVEMODE_MECANUM.runWhenPressed(new CMDChangeDrivemode(DriveMode.MECANUM));
//        BTN_DRIVEMODE_ARCADE.runWhenPressed(new CMDChangeDrivemode(DriveMode.ARCADE));
//        BTN_DRIVEMODE_TANK.runWhenPressed(new CMDChangeDrivemode(DriveMode.TANK));
        
        BTN_BOXES_TOGGLE.runWhenPressed(new CMDTogglBoxeLift());
        
        BTN_TOGGLE_GATE.runWhenPressed(new CMDToggleTailgate());
        
        BTN_ARM_TOGGLE.runWhenPressed(new CMDLeftHandToggle());
        
        BTN_EJECT_BOXES.runWhenPressed(new CGEjectBoxes());
        
        BTN_DRAWER_OUT.runWhileHeld(new CMDDrawerOutContinue());
        BTN_DRAWER_IN.runWhileHeld(new CMDDrawerInContinue());
        
        BTN_ARM_DOWN.runWhileHeld(new CMDArmsDownContinue());
        
        BTN_ARM_UP.runWhileHeld(new CMDArmsUpContinue());
    }
    
    
}
