package com.fpsrobotics.preset;

import com.fpsrobotics.hardware.Analogs;

/**
 *
 * @author Josh
 */
public class SonarShot extends Preset
{

    public SonarShot()
    {
        Analogs.ULTRA_DISTANCE.getDistanceFt();
                
        addValue(new PresetValue(_20_PERCENT_THRUST, 200));
        addValue(new PresetValue(GO_HOME, 160.0));
        addValue(new PresetValue(STOP_SHOOTER, 160.0));
    }
}
