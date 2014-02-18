/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.Preset;

import com.fpsrobotics.preset.Preset;
import com.fpsrobotics.preset.PresetValue;

/**
 *
 * @author Matthew
 */
public class PresetDynamic extends Preset
{
    public PresetDynamic()
    {
        addValue(new PresetValue(_20_PERCENT_THRUST, ));    
    }
}
