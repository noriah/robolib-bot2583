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

import io.github.robolib.command.SingleActionCommand;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class CMDTogglBoxeLift extends SingleActionCommand {
    
    private static CMDLiftBoxes up = new CMDLiftBoxes();
    private static CMDDropBoxes down = new CMDDropBoxes();

    public CMDTogglBoxeLift() {
        super("CMDToggleBoxLift");
    }

    /** Called just before this Command runs the first time */
    protected void doAction() {
        if(BoxLift.isAtTopLimit()){
            down.start();
        }else{
            up.start();
        }
    
    }
}
