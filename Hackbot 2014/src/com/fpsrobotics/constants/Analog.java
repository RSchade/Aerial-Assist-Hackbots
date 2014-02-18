package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author ray
 */
public class Analog
{

    public final static AnalogChannel shooterPot = new AnalogChannel(DeviceMap.SHOOTER_POT_MAP);
    public final static Accelerometer accel = new Accelerometer(DeviceMap.ACCEL_MAP);
//    Gyro gyro = new Gyro(GYRO_MAP);
}
