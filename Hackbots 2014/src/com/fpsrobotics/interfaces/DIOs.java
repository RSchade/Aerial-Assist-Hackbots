/**
 * 
 * Where DIOs are created, so they can be easily changed
 * 
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{
    // DIOs here
    DigitalOutput robotExampleOutput = new DigitalOutput(ROBOT_EXAMPLE_OUTPUT_MAP);
}
