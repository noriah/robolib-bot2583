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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.robolib.iface.AnalogIO;
import io.github.robolib.iface.DigitalIO;
import io.github.robolib.iface.PWM.PWMChannel;
import io.github.robolib.module.PDP.PowerChannel;
import io.github.robolib.module.actuator.DoubleSolenoid;
import io.github.robolib.module.actuator.Solenoid;
import io.github.robolib.module.actuator.SolenoidBase;
import io.github.robolib.module.controller.Jaguar;
import io.github.robolib.module.controller.SpeedController;
import io.github.robolib.module.controller.Talon;
import io.github.robolib.module.controller.TalonSRX;
import io.github.robolib.module.controller.Victor;
import io.github.robolib.module.controller.VictorSP;
import io.github.robolib.util.log.Logger;


/**
 * The Class RMap.
 *
 * @author noriah <vix@noriah.dev>
 */
public class RMap {
    
    private static final Map<String, Class<? extends SpeedController>> map_SpeedController = new HashMap<>();
    
    private static final Map<String, Class<? extends SolenoidBase>> map_Solenoid = new HashMap<>();
    
    private static final Map<String, Class<? extends DigitalIO>> map_DigitalIO = new HashMap<>();
    
    static{
        addSpeedControllerType("Talon", Talon.class);
        addSpeedControllerType("TalonSRX", TalonSRX.class);
        addSpeedControllerType("Victor", Victor.class);
        addSpeedControllerType("VictorSP", VictorSP.class);
        addSpeedControllerType("Jaguar", Jaguar.class);
//        addSpeedControllerType()
        
        addSolenoidType("Solenoid", Solenoid.class);
        addSolenoidType("SingleSolenoid", Solenoid.class);
        addSolenoidType("DoubleSolenoid", DoubleSolenoid.class);
        
        map_DigitalIO.put("INPUT", DigitalIO.class);
        map_DigitalIO.put("OUTPUT", DigitalIO.class);
        
        
    }
    
    private static RMap m_instance;
    
    private static JSONObject m_jMap;
    
    public static final RMap getInstance(){
        return m_instance == null ? m_instance = new RMap() : m_instance;
    }
    
    private RMap(){
        File file = new File("rmap.json");
        try {
            m_jMap = new JSONObject(new String(Files.readAllBytes(file.toPath()), "UTF-8"));
        } catch (JSONException | IOException e) {
            Logger.get(this).fatal("Failed to load config file", e);
        }
    }
    
    
    public static final void addSpeedControllerType(String key, Class<? extends SpeedController> type){
        key = key.toUpperCase();
        if(map_SpeedController.containsKey(key)){
            Logger.get(RMap.class).warn("SpeedController map already contains key '" + key + "'");
        }else{
            map_SpeedController.put(key, type);
        }
        
    }
    
    public static final void addSolenoidType(String key, Class<? extends SolenoidBase> type){
        key = key.toUpperCase();
        if(map_Solenoid.containsKey(key)){
            Logger.get(RMap.class).warn("Solenoid map already contains key '" + key + "'");
        }else{
            map_Solenoid.put(key, type);
        }
    }
    
    public static final void addDigitalIOType(String key, Class<? extends DigitalIO> type){
        key = key.toUpperCase();
        
    }
    
    public static final void addAnalogType(String key, Class<? extends AnalogIO> type){
        key = key.toUpperCase();
        
    }
    
    @SuppressWarnings("rawtypes")
    public static SpeedController getNewSpeedController(String key){
        JSONObject arr = m_jMap.getJSONObject(key);
        String type = arr.getString("type").toUpperCase();
        Constructor cont = null;
        try {
            cont = map_SpeedController.get(type).getConstructor(PWMChannel.class, String.class, PowerChannel.class);
            return (SpeedController) cont.newInstance(
                    PWMChannel.values()[arr.getInt("pwm_channel")],
                    arr.getString("name"),
                    PowerChannel.values()[arr.getInt("power_channel")]);
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | JSONException e) {
            Logger.get(RMap.class).fatal("Unable to create SpeedController for '" + key + "'!", e);
        }
        return null;
    }
    

}