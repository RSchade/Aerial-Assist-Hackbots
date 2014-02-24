/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.constants.Constants;

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
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
}
