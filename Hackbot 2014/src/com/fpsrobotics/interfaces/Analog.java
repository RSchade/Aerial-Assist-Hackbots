package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{

    AnalogChannel shooterPot = new AnalogChannel(SHOOTER_POT_MAP);
    Accelerometer accel = new Accelerometer(ACCEL_MAP);
    Gyro gyro = new Gyro(GYRO_MAP);
}
