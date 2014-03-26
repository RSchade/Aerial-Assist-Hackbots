/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.Dashboard;
import com.fpsrobotics.constants.Constants;
import com.fpsrobotics.thread.CatapultThread;

/**
 *
 * @author Matthew
 */
public class PresetDynamic extends Preset
{

    CatapultThread catapultThread;

    public PresetDynamic()
    {
        addValue(new PresetValue((Dashboard.getDynamicSpeed()/ 100), (Dashboard.getDynamicDistance() + 225 + Constants.ALPHA_BETA)));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
}
