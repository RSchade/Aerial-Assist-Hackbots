package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{

    AnalogChannel shooterPot = new AnalogChannel(SHOOTER_POT_MAP);
    Accelerometer accel = new Accelerometer(ACCEL_MAP);
//    Gyro gyro = new Gyro(GYRO_MAP);
}
