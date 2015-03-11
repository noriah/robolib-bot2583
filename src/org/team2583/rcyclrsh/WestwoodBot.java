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

import org.team2583.rcyclrsh.auton.AdvancedAuton;
import org.team2583.rcyclrsh.auton.BasicAuton;
import org.team2583.rcyclrsh.systems.CrateJack;
import org.team2583.rcyclrsh.systems.Drawer;
import org.team2583.rcyclrsh.systems.Drivetrain;
import org.team2583.rcyclrsh.systems.Ejector;
import org.team2583.rcyclrsh.systems.LeftElevator;
import org.team2583.rcyclrsh.systems.LeftTrolley;
import org.team2583.rcyclrsh.systems.RightElevator;
import org.team2583.rcyclrsh.systems.RightTrolley;
import org.team2583.rcyclrsh.systems.Tailgate;

import io.github.robolib.RoboLib;
import io.github.robolib.robot.AutonCommandMode;
import io.github.robolib.util.TableSender;
import io.github.robolib.util.mapper.RobotMap;

/**
 * 
 * @author noriah <vix@noriah.dev>
 */
public class WestwoodBot extends RoboLib{
//    private LCDManager m_lcdManager;

    public WestwoodBot(){
        super("Tetris", "1.0.0");
        RobotMap.setMapFile("/home/lvuser/rmap.json");
        enableDebug(true);
//        m_lcdManager = new LCDManager();
//        m_lcdManager.startThread();
        
        
        

        TableSender.setEnabled(false);
        
        Drivetrain.initialize();
        Drawer.initialize();
        Ejector.initialize();
        Tailgate.initialize();
        LeftElevator.initialize();
        RightElevator.initialize();
        CrateJack.initialize();
        LeftTrolley.initialize();
        RightTrolley.initialize();
        
        new OI();
    }
    
    public void robotInit(){
        new AutonCommandMode(new BasicAuton(), "Strat 1", true);
        
        new AutonCommandMode(new AdvancedAuton(), "Strat 2");
    }
}
