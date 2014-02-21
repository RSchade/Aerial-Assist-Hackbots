/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Matthew
 */
public class PresetHighGoal extends Preset
{
    public PresetHighGoal()
    {
//        addValue(new PresetValue(_50_PERCENT_THRUST, 250.0));       // Start shot
//        addValue(new PresetValue(_70_PERCENT_THRUST, 400.0));       // accelerate
        addValue(new PresetValue(_100_PERCENT_THRUST, 450.0));      // Full thrust
        addValue(new PresetValue(GO_HOME, 160.0));
        addValue(new PresetValue(STOP_SHOOTER, 160.0));
    }
}
