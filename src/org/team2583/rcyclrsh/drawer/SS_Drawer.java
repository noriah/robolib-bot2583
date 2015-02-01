package org.team2583.rcyclrsh.drawer;

import org.team2583.rcyclrsh.RMap;

import io.github.robolib.command.Subsystem;
import io.github.robolib.input.limitswitch.DualLimitSwitchSystem;
import io.github.robolib.input.limitswitch.LimitSwitch.SwitchType;
import io.github.robolib.output.LimitSwitchController;
import io.github.robolib.output.Victor;
import io.github.robolib.pneumatic.Solenoid;


/**
 * 
 * 
 *
 * @author noriah <vix@noriah.dev>
 */
public class SS_Drawer extends Subsystem implements RMap {
    
    private LimitSwitchController m_drawerMotor;
    private Solenoid m_tailGate;
    private Solenoid m_drawerRaiser;
    
    private SS_Drawer(){
        super("Drawer Subsystem");
        
        m_drawerMotor = new LimitSwitchController(
                new Victor(
                        DRAWER_MOTOR_PWM_CHANNEL,
                        "Drawer Motor",
                        DRAWER_MOTOR_POWER_CHANNEL),
                new DualLimitSwitchSystem(
                        DRAWER_TAILGATE_INNER_SWITCH_DIGITAL_CHANNEL,
                        SwitchType.NO,
                        DRAWER_TAILGATE_OUTER_SWITCH_DIGITAL_CHANNEL,
                        SwitchType.NO));
        
        m_tailGate = new Solenoid(DRAWER_TAILGAIT_SOLENOID_CHANNEL);
        m_drawerRaiser = new Solenoid(DRAWER_RAISER_SOLENOID_CHANNEL);
        
    }
    
    public void raiseTailgate(){
        m_tailGate.set(Solenoid.Value.ON);
    }
    
    public void lowerTailgate(){
        m_tailGate.set(Solenoid.Value.OFF);
    }
    
    public void extendDrawer(){
        m_drawerMotor.set(1);
    }
    
    public void stopDrawer(){
        m_drawerMotor.set(0);
    }
    
    public void retractDrawer(){
        m_drawerMotor.set(-1);
    }
    
    public boolean getDrawerExtended(){
        return m_drawerMotor.atForwardLimit();
    }
    
    public boolean getDrawerRetracted(){
        return m_drawerMotor.atReverseLimit();
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

