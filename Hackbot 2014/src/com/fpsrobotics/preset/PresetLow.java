/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Hackbots
 */
public class PresetLow extends Preset
{

    public PresetLow()
    {
        addValue(new PresetValue(_100_PERCENT_THRUST, 500));
        addValue(new PresetValue(STOP_SHOOTER, 160));
    }
}
