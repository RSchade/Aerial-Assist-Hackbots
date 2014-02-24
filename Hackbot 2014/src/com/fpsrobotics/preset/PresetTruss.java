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
public class PresetTruss extends Preset
{
    public PresetTruss()
    {
        addValue(new PresetValue(_30_PERCENT_THRUST, 175));       // Start shot
        addValue(new PresetValue(_60_PERCENT_THRUST, 200));       // Excelerate
        addValue(new PresetValue(_90_PERCENT_THRUST, 275));      // Full thrust
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
}
