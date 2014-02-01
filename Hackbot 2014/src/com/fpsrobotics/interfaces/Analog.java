package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{
    AnalogChannel shooterPot = new AnalogChannel(SHOOTER_POT_MAP);
}
