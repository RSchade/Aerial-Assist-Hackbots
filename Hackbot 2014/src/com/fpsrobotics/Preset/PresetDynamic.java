/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.Preset;

import com.fpsrobotics.preset.Preset;
import com.fpsrobotics.preset.PresetValue;
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
        addValue(new PresetValue(catapultThread.getDynamicPresetSpeed(),catapultThread.getDynamicPresetDistance() ));   
        addValue(new PresetValue(GO_HOME,60));
        addValue(new PresetValue(STOP_SHOOTER, 152));
    }
}
