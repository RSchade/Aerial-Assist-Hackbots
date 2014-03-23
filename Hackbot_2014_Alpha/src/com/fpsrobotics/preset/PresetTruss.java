/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.constants.Constants;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Matthew
 */
public class PresetTruss extends Preset
{

    public PresetTruss()
    {
//        addValue(new PresetValue(_30_PERCENT_THRUST, 690));       // Start shot
//        addValue(new PresetValue(_60_PERCENT_THRUST, 700));       // accelerate

//        addValue(new PresetValue(_90_PERCENT_THRUST, 535));      // Full thrust
//        addValue(new PresetValue(_90_PERCENT_THRUST, Constants.HOME_POT_VALUE + 85));
//        addValue(new PresetValue(_90_PERCENT_THRUST, 535-((90.0)*(DriverStation.getInstance().getBatteryVoltage()-11.6)) + Constants.ALPHA_BETA));
        addValue(new PresetValue(_90_PERCENT_THRUST, 535 + Constants.ALPHA_BETA));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
}
