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
    
    static{

        JS_DRIVER = new XBoxController(JSID.JS0);
        JS_ACTOR = new XBoxController(JSID.JS1);
        AXIS_DRIVER_LEFT_X = JS_DRIVER.getAxisLeftX();
        AXIS_DRIVER_LEFT_Y = JS_DRIVER.getAxisLeftY();
        AXIS_DRIVER_RIGHT_X = JS_DRIVER.getAxisRightX();
        AXIS_DRIVER_RIGHT_Y = JS_DRIVER.getAxisRightY();
        BTN_SPEED_SCALE = JS_DRIVER.getButtonLeftShoulder();

        JS_DRIVER.getButtonA().runWhenPressed(new CMDChangeDrivemode(DriveMode.MECANUM));
        JS_DRIVER.getButtonB().runWhenPressed(new CMDChangeDrivemode(DriveMode.ARCADE));
        JS_DRIVER.getButtonX().runWhenPressed(new CMDChangeDrivemode(DriveMode.TANK));
        
    }
    
    
}
