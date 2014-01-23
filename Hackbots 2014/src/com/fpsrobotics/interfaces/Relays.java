package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Relay;

/**
 * Where relays can be instantiated, so they can be easily changed
 *
 * @author ray
 */
public interface Relays extends DeviceMap
{
    Relay robotRelay = new Relay(ROBOT_RELAY_MAP);
}
