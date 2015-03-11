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

package org.team2583.rcyclrsh.auton;

import org.team2583.rcyclrsh.systems.CMDBothArmsDown;
import org.team2583.rcyclrsh.systems.CMDBothArmsUp;
import org.team2583.rcyclrsh.systems.Drivetrain;

import io.github.robolib.command.CommandGroup;
import io.github.robolib.command.PrintCommand;
import io.github.robolib.util.mapper.RobotMap;

/**
*
* @author noriah <vix@noriah.dev>
*/
public class BasicAuton extends CommandGroup {
    
    public BasicAuton() {
        addSequential(new CMDBothArmsUp(), RobotMap.getNumber("auton_basic_lift_time"));
        addSequential(Drivetrain.mecanumDrive(
                0,
                RobotMap.getNumber("auton_basic_drive_forward_speed"),
                0), RobotMap.getNumber("auton_basic_drive_forward_time"));
        addSequential(new CMDBothArmsDown());
        addSequential(new PrintCommand("Basic Autonomous Complete"));
    }
}
