/**
 * 
 * Where relays can be instantiated, so they can be easily changed
 * 
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author ray
 */
public interface Relays extends DeviceMap
{
    Relay exampleRelay = new Relay(EXAMPLE_RELAY_MAP);
}
