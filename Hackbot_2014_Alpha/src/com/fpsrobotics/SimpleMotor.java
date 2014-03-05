package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Simple motor, extended by all complex motors.
 * 
 * @author Hackbots
 */
public class SimpleMotor implements Motor
{

    private final SpeedController motor;
    private final boolean inverseDirection;

    public SimpleMotor(SpeedController motor, boolean inverseDirection)
    {
        this.motor = motor;
        this.inverseDirection = inverseDirection;
    }

    public void stop()
    {
        motor.set(0.0);
    }

    public void backward(double speed)
    {
        double actualSpeed = inverseDirection ? -Math.abs(speed) : Math.abs(speed);
        motor.set(-actualSpeed);
    }

    public void forward(double speed, int motorStep)
    {
        double increaseIncrement = speed / motorStep;

        for (int currentStep = 1; currentStep <= motorStep + 1; currentStep++)
        {
            double nextSpeed = increaseIncrement * currentStep;

            if (inverseDirection)
            {
                motor.set(-nextSpeed);
            } else
            {
                motor.set(nextSpeed);
            }
        }
    }

    public void forward(double speed)
    {
        double actualSpeed = inverseDirection ? -Math.abs(speed) : Math.abs(speed);
        motor.set(actualSpeed);
    }

    public void backward(double speed, int motorStep)
    {
        double increment = Math.abs(speed) / motorStep;

        for (int currentStep = 1; currentStep <= motorStep + 1; currentStep++)
        {
            double nextSpeed = increment * currentStep;

            forward(nextSpeed);
        }
    }

    public double getSpeed()
    {
        return inverseDirection ? -motor.get() : motor.get();
    }
    
    public void set(double d)
    {
        motor.set(d);
    }
    
    public void set(double d, byte b)
    {
        motor.set(d, b);
    }
}
