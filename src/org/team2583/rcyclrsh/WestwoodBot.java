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

import io.github.robolib.control.Joystick.JSID;
import io.github.robolib.control.XBoxController;
import io.github.robolib.framework.RoboLibBot;
import io.github.robolib.iface.PWM.PWMChannel;
import io.github.robolib.output.Victor;
import io.github.robolib.robot.TeleopMode;
import io.github.robolib.util.PDP.PowerChannel;

import edu.wpi.first.wpilibj.tables.ITable;

/**
 * 
 * @author noriah <vix@noriah.dev>
 */
public class WestwoodBot extends RoboLibBot {
    
    private Victor motor1, motor2;
    private XBoxController stick0;
    ITable table;

    public WestwoodBot(){
        super("Stacker", "1.0.0");
        enableDebug(true);
    }

    public void robotInit(){
       motor1 = new Victor(PWMChannel.Channel0, "Left Motor", PowerChannel.Channel0);
       motor2 = new Victor(PWMChannel.Channel1, "Right Motor", PowerChannel.Channel3);
       motor1.enableSafety(false);
       motor2.enableSafety(false);
       motor2.setInverted(true);

        stick0 = new XBoxController(JSID.JS0);
        table = getRobotTable().getSubTable("JS");
        table.putNumber("JS Scaler", 0.75);
        new TeleopMode(){

           double a, b, c, d;
            public void run(){
                a = b = stick0.getLeftY();
                if(a < 0){
                    b = -a;
                }
                a = a * b;

                c = d = stick0.getRightX();
                if(c < 0){
                    d = -c;
                }

                c = c * d;

                if(a > 0){
                    if(c > 0){
                        b = a - c;
                        d = Math.max(a, c);
                    }else{
                        b = Math.max(a, -c);
                        d = a + c;
                    }
                }else{
                    if(c > 0){
                        b = -Math.max(-a, c);
                        d = a + c;
                    }else{
                        b = a - c;
                        d = -Math.max(-a, -c);
                    }
                }

               b = b * table.getNumber("JS Scaler");
               d = d * table.getNumber("JS Scaler");

               motor1.setSpeed(d);
               motor2.setSpeed(b);
            }
        };
    }

}
