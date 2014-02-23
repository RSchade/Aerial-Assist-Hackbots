/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.Dashboard;
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
        addValue(new PresetValue((Dashboard.getDynamicSpeed()/ 100), Dashboard.getDynamicDistance()));
        addValue(new PresetValue(GO_HOME, 160));
        addValue(new PresetValue(STOP_SHOOTER, 160));
    }
}
