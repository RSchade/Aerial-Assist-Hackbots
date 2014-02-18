/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Matthew
 */
public class PresetTruss extends Preset
{
    public PresetTruss()
    {
        addValue(new PresetValue(_2_PERCENT_THRUST, 175));       // Start shot
        addValue(new PresetValue(_5_PERCENT_THRUST, 200));       // Excelerate
        addValue(new PresetValue(_20_PERCENT_THRUST, 275));      // Full thrust
        addValue(new PresetValue(GO_HOME, 300.0));
        addValue(new PresetValue(STOP_SHOOTER, 150.0));
    }
}
