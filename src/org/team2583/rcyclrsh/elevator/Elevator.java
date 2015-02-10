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

package org.team2583.rcyclrsh.elevator;

import io.github.robolib.command.Subsystem;
import io.github.robolib.module.controller.SpeedController;

/**
 *
 * @author noriah <vix@noriah.dev>
 */
public class Elevator extends Subsystem {
    
    private static SpeedController m_leftTrolleyMotor;
    private static SpeedController m_rightTrolleyMotor;
//    private static S
    
    public static final void initialize(){
        //Do Shit
    }

    private static final Elevator m_instance = new Elevator();

    public static final Elevator getInstance(){
        return m_instance;
    }

    private Elevator(){
        super("Lifter");
    }

    public void initDefaultCommand()    {
        //setDefaultCommand();
    }
}

