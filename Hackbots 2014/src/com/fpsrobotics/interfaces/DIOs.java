package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Where DIOs are created, so they can be easily changed
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{
    // DIOs here

    DigitalInput robotSwitchInput = new DigitalInput(ROBOT_SWITCH_INPUT_MAP);
}
