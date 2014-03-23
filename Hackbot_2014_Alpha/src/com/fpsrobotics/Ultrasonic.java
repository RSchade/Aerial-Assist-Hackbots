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
        ultrasonic.setAverageBits(30);
    }

    public double getDistanceFt()
    {
        return ultrasonic.getAverageVoltage()/ .1176;
    }

    public double getDistanceIn()
    {
        return getDistanceFt() / 12;
    }
}
