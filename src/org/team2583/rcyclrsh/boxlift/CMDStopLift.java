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

package org.team2583.rcyclrsh.boxlift;

import io.github.robolib.command.Command;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class CMDStopLift extends Command {

    public CMDStopLift() {
        super("CMDStopLift");
        requires(BoxLift.getInstance());
    }

    /** Called just before this Command runs the first time */
    protected void initialize() {
        BoxLift.stopMotors();
    }

    /** Called repeatedly when this Command is scheduled to run */
    protected void execute() {
    
    }

    /** Make this return true when this Command no longer needs to run execute() */
    protected boolean isFinished() {
        return true;
    }

    /** Called once after isFinished returns true */
    protected void end() {
    
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {

    }
}
