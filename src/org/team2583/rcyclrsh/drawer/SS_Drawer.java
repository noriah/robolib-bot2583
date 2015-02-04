package org.team2583.rcyclrsh.drawer;

import org.team2583.rcyclrsh.RMap;

import io.github.robolib.command.Subsystem;
import io.github.robolib.iface.DigitalIO.DigitalChannel;
import io.github.robolib.module.LimitSystem;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.module.actuator.SolenoidBase.SolenoidChannel;
import io.github.robolib.module.controller.LimitedController;
import io.github.robolib.module.controller.Talon;
import io.github.robolib.module.controller.Victor;
import io.github.robolib.module.sensor.LimitSwitch;
import io.github.robolib.module.sensor.LimitSwitch.SwitchType;


/**
 * 
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class SS_Drawer extends Subsystem {
    
    private LimitedController m_drawerMotor;
    private Solenoid m_tailGate;
    private Solenoid m_drawerRaiser;
    
    private SS_Drawer(){
        super("Drawer Subsystem");
        
        m_drawerMotor = new LimitedController(
                (Victor)RMap.getNewSpeedController("motor_drawer"),
                new LimitSystem(
                        new LimitSwitch(
                                DigitalChannel.Channel0,
                                SwitchType.OPEN),
                        new LimitSwitch(
                                DigitalChannel.Channel1,
                                SwitchType.OPEN)));
        
        m_tailGate = new Solenoid(SolenoidChannel.Channel0);
        m_drawerRaiser = new Solenoid(SolenoidChannel.Channel1);
        
    }
    
    public void raiseTailgate(){
        m_tailGate.set(Solenoid.Value.ON);
    }
    
    public void lowerTailgate(){
        m_tailGate.set(Solenoid.Value.OFF);
    }
    
    public void extendDrawer(){
        m_drawerMotor.setSpeed(1);
    }
    
    public void stopDrawer(){
        m_drawerMotor.setSpeed(0);
    }
    
    public void retractDrawer(){
        m_drawerMotor.setSpeed(-1);
    }
    
    public boolean getDrawerExtended(){
        return m_drawerMotor.atFrontLimit();
    }
    
    public boolean getDrawerRetracted(){
        return m_drawerMotor.atBackLimit();
    }
    
    public void raiseDrawer(){
        m_drawerRaiser.set(Solenoid.Value.ON);
    }
    
    public void lowerDrawer(){
        m_drawerRaiser.set(Solenoid.Value.OFF);
    }
    
    public boolean getDrawerRasied(){
        return m_drawerRaiser.get() == Solenoid.Value.ON;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

