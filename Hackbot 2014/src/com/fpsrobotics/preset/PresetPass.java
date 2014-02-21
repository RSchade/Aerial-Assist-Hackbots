  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Matthew
 */
public class PresetPass extends Preset
{
    public PresetPass()
    {
        addValue(new PresetValue(_50_PERCENT_THRUST, 160));       // Start shot
        addValue(new PresetValue(_70_PERCENT_THRUST, 175));       // Excelerate
        addValue(new PresetValue(_90_PERCENT_THRUST, 400));      // Full thrust
        addValue(new PresetValue(GO_HOME, 160));
        addValue(new PresetValue(STOP_SHOOTER, 160));
    }
}
