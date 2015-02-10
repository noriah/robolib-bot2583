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

import org.team2583.rcyclrsh.drawer.CGEjectBoxes;
import org.team2583.rcyclrsh.drawer.CMDDrawerInContinue;
import org.team2583.rcyclrsh.drawer.CMDDrawerOutContinue;
import org.team2583.rcyclrsh.drivetrain.CMDChangeDrivemode;
import org.team2583.rcyclrsh.drivetrain.Drivetrain.DriveMode;

import io.github.robolib.control.HIDAxis;
import io.github.robolib.control.HIDButton;
import io.github.robolib.control.Joystick.JSID;
import io.github.robolib.control.XBoxController;

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
    public static final HIDButton BTN_DRIVEMODE_MECANUM;
    public static final HIDButton BTN_DRIVEMODE_ARCADE;
    public static final HIDButton BTN_DRIVEMODE_TANK;
    
    public static final HIDButton BTN_EJECT_BOXES;
    
    public static final HIDButton BTN_DRAWER_OUT;
    public static final HIDButton BTN_DRAWER_IN;
    
    static{

        JS_DRIVER = new XBoxController(JSID.JS0);
        JS_ACTOR = new XBoxController(JSID.JS1);
        AXIS_DRIVER_LEFT_X = JS_DRIVER.getAxisLeftX();
        AXIS_DRIVER_LEFT_Y = JS_DRIVER.getAxisLeftY();
        AXIS_DRIVER_RIGHT_X = JS_DRIVER.getAxisRightX();
        AXIS_DRIVER_RIGHT_Y = JS_DRIVER.getAxisRightY();
        BTN_DRIVEMODE_MECANUM = JS_DRIVER.getButtonA();
        BTN_DRIVEMODE_ARCADE = JS_DRIVER.getButtonB();
        BTN_DRIVEMODE_TANK = JS_DRIVER.getButtonX();
        BTN_EJECT_BOXES = JS_DRIVER.getButtonY();
        BTN_DRAWER_OUT = JS_DRIVER.getButtonStart();
        BTN_DRAWER_IN = JS_DRIVER.getButtonSelect();
        BTN_SPEED_SCALE = JS_DRIVER.getButtonLeftShoulder();

        BTN_DRIVEMODE_MECANUM.runWhenPressed(new CMDChangeDrivemode(DriveMode.MECANUM));
        BTN_DRIVEMODE_ARCADE.runWhenPressed(new CMDChangeDrivemode(DriveMode.ARCADE));
        BTN_DRIVEMODE_TANK.runWhenPressed(new CMDChangeDrivemode(DriveMode.TANK));
        
        BTN_EJECT_BOXES.runWhenPressed(new CGEjectBoxes());
        
        BTN_DRAWER_OUT.runWhileHeld(new CMDDrawerOutContinue());
        BTN_DRAWER_IN.runWhileHeld(new CMDDrawerInContinue());
    }
    
    
}
