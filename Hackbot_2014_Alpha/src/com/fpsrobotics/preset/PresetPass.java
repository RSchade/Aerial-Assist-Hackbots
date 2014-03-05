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
public class PresetPass extends Preset
{
    public PresetPass()
    {
//        addValue(new PresetValue(_50_PERCENT_THRUST, 500));       // Start shot
//        addValue(new PresetValue(_70_PERCENT_THRUST, 575));       // Excelerate
        
        addValue(new PresetValue(_90_PERCENT_THRUST, 600));      // Full thrust
//        addValue(new PresetValue(_90_PERCENT_THRUST, Constants.HOME_POT_VALUE + 150));   
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
        
    }
}
