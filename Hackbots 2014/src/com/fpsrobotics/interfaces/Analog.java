package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{

    AnalogChannel shooterPot = new AnalogChannel(SHOOTER_POT_MAP);
    Gyro gyroScope = new Gyro(GYRO_SCOPE_MAP);
}
