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

import io.github.robolib.command.CommandGroup;

/**
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class CGEjectBoxes extends CommandGroup {
    
    public CGEjectBoxes(){
        addSequential(Drawer.extend());
        addSequential(Wait(0.5));
        addSequential(Tailgate.lower());
        addParallel(Ejector.extend());
        addSequential(Wait(1));
        addParallel(Ejector.retract());
        addSequential(Drawer.retract());
        addSequential(Tailgate.raise());
    }

}
