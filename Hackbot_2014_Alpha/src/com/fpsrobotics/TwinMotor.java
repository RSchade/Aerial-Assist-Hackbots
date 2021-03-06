package com.fpsrobotics;

/**
 * A class which controls two motors at the same time.
 * 
 * @author Hackbots
 */
public class TwinMotor implements Motor
{

    private final Motor motorOne;
    private final Motor motorTwo;

    public TwinMotor(Motor motorOne, Motor motorTwo)
    {
        this.motorOne = motorOne;
        this.motorTwo = motorTwo;
    }

    public void stop()
    {
        motorOne.stop();
        motorTwo.stop();
    }

    public void backward(double speed)
    {
        motorOne.backward(speed);
        motorTwo.backward(speed);
    }

    public void forward(double speed, int motorStep)
    {
        motorOne.forward(speed, motorStep);
        motorTwo.forward(speed, motorStep);
    }

    public void forward(double speed)
    {
        motorOne.forward(speed);
        motorTwo.forward(speed);
    }

    public void backward(double speed, int motorStep)
    {
        motorOne.forward(speed, motorStep);
        motorTwo.forward(speed, motorStep);
    }

    public double getSpeed()
    {
        return (motorOne.getSpeed() + motorTwo.getSpeed()) / 2;
    }
}
