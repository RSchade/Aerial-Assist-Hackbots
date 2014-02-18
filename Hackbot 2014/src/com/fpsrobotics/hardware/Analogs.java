package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author Josh
 */
public class Analogs
{

    public final static AnalogChannel SHOOTER_POTENTIOMETER = new AnalogChannel(HardwarePorts.SHOOTER_POT);
    public final static AnalogChannel DISTANCE_SENSOR = new AnalogChannel(HardwarePorts.DISTANCE_SENSOR);
//    public final static Accelerometer ACCELEROMETER = new Accelerometer(HardwarePorts.ACCEL_MAP);
//    public final static Gyro GYRO = new Gyro(HardwarePorts.GYRO_MAP);
}
