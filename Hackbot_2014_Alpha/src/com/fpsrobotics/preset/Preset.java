/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import java.util.Enumeration;
import java.util.Vector;

/**
 * preset for truss shot
 *
 * @author Matthew
 */
public abstract class Preset
{
    protected Vector preset = null;
    protected final double GO_HOME = -0.20;              // 20% reverse thrust to go home
    public final static double STOP_SHOOTER = 0.0;
    protected final double _1_PERCENT_THRUST = 0.01;    // 1% forward thrust
    protected final double _2_PERCENT_THRUST = 0.02;
    protected final double _5_PERCENT_THRUST = 0.05;    // 5% forward thrust
    protected final double _10_PERCENT_THRUST = 0.1;    // 10% forward thrust
    protected final double _20_PERCENT_THRUST = 0.2;    // 20% forward thrust
    protected final double _30_PERCENT_THRUST = 0.3;    // 30% forward thrust
    protected final double _40_PERCENT_THRUST = 0.4;    // 40% forward thrust
    protected final double _50_PERCENT_THRUST = 0.5;    // 50% forward thrust
    protected final double _60_PERCENT_THRUST = 0.6;    // 60% forward thrust
    protected final double _70_PERCENT_THRUST = 0.7;    // 70% forward thrust
    protected final double _80_PERCENT_THRUST = 0.8;    // 80% forward thrust
    protected final double _90_PERCENT_THRUST = 0.9;    // 90% forward thrust
    protected final double _100_PERCENT_THRUST = 1.0;   // 100% forward thrust
    
    
    public Preset()
    {
        preset = new Vector();
    }
    
    protected void addValue(PresetValue value)
    {
       this.preset.addElement(value);
       
    }
    public Enumeration getElements()
    {
        return preset.elements();
    }
    
    
   
}
