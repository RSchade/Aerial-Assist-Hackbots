/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Matthew
 */
public class SonarShot extends Preset
{
    public SonarShot()
    {
        addValue(new PresetValue(_20_PERCENT_THRUST, 1.0));       // Start shot
        addValue(new PresetValue(_50_PERCENT_THRUST, 2.0));       // Excelerate
        addValue(new PresetValue(_100_PERCENT_THRUST, 3.0));      // Full thrust
        addValue(new PresetValue(GO_HOME, 80.0));
        addValue(new PresetValue(STOP_SHOOTER, 1.0));
    }
}
