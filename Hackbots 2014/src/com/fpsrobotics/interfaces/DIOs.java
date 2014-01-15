/**
 * 
 * Where DIOs are created, so they can be easily changed
 * 
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{
    // DIOs here
    DigitalInput robotSwitchInput = new DigitalInput(ROBOT_SWITCH_INPUT_MAP);
}
