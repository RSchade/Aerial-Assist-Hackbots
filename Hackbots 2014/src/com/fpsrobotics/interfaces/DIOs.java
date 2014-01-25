package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Where DIOs are created, so they can be easily changed
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{
    // DIOs here

    DigitalInput robotSwitchInput = new DigitalInput(ROBOT_SWITCH_INPUT_MAP);
    
    Compressor compressor = new Compressor(1, 1);
}
