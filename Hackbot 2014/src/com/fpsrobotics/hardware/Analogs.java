package com.fpsrobotics.hardware;

import com.fpsrobotics.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Josh
 */
public class Analogs
{

    public final static AnalogChannel SHOOTER_POTENTIOMETER = new AnalogChannel(HardwarePorts.SHOOTER_POT);
    public final static AnalogChannel DISTANCE_SENSOR = new AnalogChannel(HardwarePorts.DISTANCE_SENSOR);
    
    public final static Ultrasonic ULTRA_DISTANCE = new Ultrasonic(DISTANCE_SENSOR);
    
//    public final static Accelerometer ACCELEROMETER = new Accelerometer(HardwarePorts.ACCEL_MAP);
//    public final static Gyro GYRO = new Gyro(HardwarePorts.GYRO_MAP);
}
