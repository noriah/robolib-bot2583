package org.team2583.rcyclrsh;

import io.github.robolib.module.hid.HIDButton;
import io.github.robolib.module.hid.Joystick;

/**
 *
 * @author noriah
 */
public class ArcadeController extends Joystick{
        
    public static final int BTN_TOGGLE_LEFT_ARM = 0;
    public static final int BTN_TOGGLE_RIGHT_ARM = 1;
    public static final int BTN_TOGGLE_DRAWER_GATE = 2;
    public static final int BTN_TOGGLE_RAILROAD = 3;
    public static final int BTN_ARMS_UP = 4;
    public static final int BTN_ARMS_DOWN = 5;
    public static final int BTN_JACK_UP = 6;
    public static final int BTN_JACK_DOWN = 7;
    public static final int BTN_DRAWER_IN = 8;
    public static final int BTN_DRAWER_OUT = 9;
    public static final int BTN_EJECT_SEQUENCE = 10;
    public static final int BTN_CANCEL_SEQUENCE = 11;
    
    public ArcadeController(final JSID port){
        super(port, 0, 14);
    }
    
    /**
     * Get the Left Arm Toggle Button.
     *
     * @return the Left Arm Toggle Button
     */
    public HIDButton getButtonLeftArmToggle(){return getButton(BTN_TOGGLE_LEFT_ARM);}
    
    /**
     * Get the Right Arm Toggle Button.
     *
     * @return the Right Arm Toggle Button
     */
    public HIDButton getButtonRightArmToggle(){return getButton(BTN_TOGGLE_RIGHT_ARM);}
    
    /**
     * Get the Drawer Gate Toggle Button.
     *
     * @return the Drawer Gate Toggle Button
     */
    public HIDButton getButtonDrawerGateToggle(){return getButton(BTN_TOGGLE_DRAWER_GATE);}

    /**
     * Get the Railroad Toggle Button.
     *
     * @return the Railroad Toggle Button
     */
    public HIDButton getButtonRailroadToggle(){return getButton(BTN_TOGGLE_RAILROAD);}
    
    /**
     * Get the Arms Up Button.
     *
     * @return the Arms Up button
     */
    public HIDButton getButtonArmsUp(){return getButton(BTN_ARMS_UP);}
    
    /**
     * Get the Arms Down Button.
     *
     * @return the Arms Down button
     */
    public HIDButton getButtonArmsDown(){return getButton(BTN_ARMS_DOWN);}
    
    /**
     * Get the Jack Up Button.
     *
     * @return the Jack Up Button
     */
    public HIDButton getButtonJackUp(){return getButton(BTN_JACK_UP);}
    
    /**
     * Get the Jack Down Button.
     *
     * @return the Jack Down Button
     */
    public HIDButton getButtonJackDown(){return getButton(BTN_JACK_DOWN);}
    
    /**
     * Get the Drawer In Button.
     *
     * @return the Drawer In button
     */
    public HIDButton getButtonDrawerIn(){return getButton(BTN_DRAWER_IN);}
    
    /**
     * Get the Drawer Out Button.
     *
     * @return the Drawer Out button
     */
    public HIDButton getButtonDrawerOut(){return getButton(BTN_DRAWER_OUT);}
    
    /**
     * Get the Eject Sequence Button.
     *
     * @return the Eject Sequence button
     */
    public HIDButton getButtonEjectSequence(){return getButton(BTN_EJECT_SEQUENCE);}
    
    /**
     * Get the Cancel Sequence Button.
     *
     * @return the Cancel Sequence button
     */
    public HIDButton getButtonCancelSequence(){return getButton(BTN_CANCEL_SEQUENCE);}

    /**
     * Get the Left Arm Toggle Button value.
     *
     * @return the Left Arm Toggle Button value
     */
    public boolean getButtonLeftArmToggleValue(){return getRawButton(BTN_TOGGLE_LEFT_ARM);}
    
    /**
     * Get the Right Arm Toggle Button value.
     *
     * @return the Right Arm Toggle Button value
     */
    public boolean getButtonRightArmToggleValue(){return getRawButton(BTN_TOGGLE_RIGHT_ARM);}
    
    /**
     * Get the Drawer Gate Toggle Button value.
     *
     * @return the Drawer Gate Toggle button value
     */
    public boolean getButtonDrawerGateToggleValue(){return getRawButton(BTN_TOGGLE_DRAWER_GATE);}

    /**
     * Get the Railroad Toggle Button value.
     *
     * @return the Railroad Toggle button value
     */
    public boolean getButtonRailroadToggleValue(){return getRawButton(BTN_TOGGLE_RAILROAD);}
    
    /**
     * Get the Arms Up Button value.
     *
     * @return the Arms Up button value
     */
    public boolean getButtonArmsUpValue(){return getRawButton(BTN_ARMS_UP);}
    
    /**
     * Get the Arms Down Button value.
     *
     * @return the Arms Down button value
     */
    public boolean getButtonArmsDownValue(){return getRawButton(BTN_ARMS_DOWN);}
    
    /**
     * Get the Jack Up Button value.
     *
     * @return the Jack Up button value
     */
    public boolean getButtonJackUpValue(){return getRawButton(BTN_JACK_UP);}
    
    /**
     * Get the Jack Down Button value.
     *
     * @return the Jack Down button value
     */
    public boolean getButtonJackDownValue(){return getRawButton(BTN_JACK_DOWN);}
    
    /**
     * Get the Drawer In Button value.
     *
     * @return the Drawer In button value
     */
    public boolean getButtonDrawerInValue(){return getRawButton(BTN_DRAWER_IN);}
    
    /**
     * Get the Drawer Out Button value.
     *
     * @return the Drawer Out button value
     */
    public boolean getButtonDrawerOutValue(){return getRawButton(BTN_DRAWER_OUT);}

    /**
     * Get the Eject Sequence Button value.
     *
     * @return the Eject Sequence button value.
     */
    public boolean getButtonEjectSequenceValue(){return getRawButton(BTN_EJECT_SEQUENCE);}
    
    /**
     * Get the Cancel Sequence Button value.
     *
     * @return the Cancel Sequence button value
     */
    public boolean getButtonCancelSequenceValue(){return getRawButton(BTN_CANCEL_SEQUENCE);}
}