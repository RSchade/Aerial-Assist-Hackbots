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
public class PresetAuto extends Preset
{

    public PresetAuto()
    {
        //addValue(new PresetValue(_50_PERCENT_THRUST, 170.0));       // Start shot
        //addValue(new PresetValue(_80_PERCENT_THRUST, 250.0));       // accelerate
        //addValue(new PresetValue(_100_PERCENT_THRUST, 350));      // Full thrust

        addValue(new PresetValue(_100_PERCENT_THRUST, 725 + Constants.ALPHA_BETA));
//        addValue(new PresetValue(_100_PERCENT_THRUST, 725 - ((90.0) * (DriverStation.getInstance().getBatteryVoltage() - 11.6))));

//        addValue(new PresetValue(_100_PERCENT_THRUST, Constants.HOME_POT_VALUE + 258));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
}
