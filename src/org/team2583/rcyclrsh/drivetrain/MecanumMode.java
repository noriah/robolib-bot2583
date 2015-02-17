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

package org.team2583.rcyclrsh.drivetrain;

import org.team2583.rcyclrsh.OI;

import io.github.robolib.command.Command;


/**
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class MecanumMode extends Command {
    
    public MecanumMode(){
        super("MecanumMode");
        requires(Drivetrain.getInstance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute() {
        double scale = (OI.BTN_SPEED_SCALE.getState() ? 0.75 : 1.0);
        double rotation;
        Drivetrain.mecanum(
                OI.AXIS_DRIVER_LEFT_X.get() * scale,
                OI.AXIS_DRIVER_LEFT_Y.get() * scale,
                OI.AXIS_DRIVER_RIGHT_X.get() * scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void end() {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void interrupted() {
        
    }

}
