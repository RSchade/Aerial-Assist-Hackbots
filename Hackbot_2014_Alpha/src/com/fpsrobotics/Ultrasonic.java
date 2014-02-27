package com.fpsrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Hackbots
 */
public class Ultrasonic
{

    private final AnalogChannel ultrasonic;

    public Ultrasonic(AnalogChannel ultrasonic)
    {
        this.ultrasonic = ultrasonic;
    }

    public double getDistanceFt()
    {
        return ultrasonic.getVoltage() / .1176;
    }

    public double getDistanceIn()
    {
        return getDistanceFt() / 12;
    }
}
