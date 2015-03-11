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

/**
*
* @author noriah <vix@noriah.dev>
*/
public class CMDBothArmsUp extends Command {

    public CMDBothArmsUp(){
        requires(LeftElevator.getInstance());
        requires(RightElevator.getInstance());
    }
    protected void initialize(){}
    protected void execute(){
        LeftElevator.setMotor(LeftElevator.lift_speed);
        RightElevator.setMotor(RightElevator.lift_speed);
    }
    protected boolean isFinished(){
        return LeftElevator.isAtTopLimit() && RightElevator.isAtTopLimit();
    }
    protected void end(){
        LeftElevator.setMotor(0);
        RightElevator.setMotor(0);
    }
    protected void interrupted(){
        LeftElevator.setMotor(0);
        RightElevator.setMotor(0);
    }
}
