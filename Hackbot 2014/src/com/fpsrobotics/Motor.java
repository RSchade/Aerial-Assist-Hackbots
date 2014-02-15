/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Hackbots
 */
public interface Motor
{

//    private final boolean inverseDirection;
//    private final SpeedController motor;
//
//    public Motor(SpeedController motor, boolean inverseDirection)
//    {
//        this.motor = motor;
//        this.inverseDirection = inverseDirection;
//    }
//
//    public double get()
//    {
//        if (inverseDirection)
//        {
//            return -motor.get();
//        } else
//        {
//            return motor.get();
//        }
//    }
//
//    public void set(double d, byte b)
//    {
//        if (inverseDirection)
//        {
//            motor.set(-d, b);
//        } else
//        {
//            motor.set(d, b);
//        }
//    }
//
//    public void set(double d)
//    {
//        if (inverseDirection)
//        {
//            motor.set(-d);
//        } else
//        {
//            motor.set(d);
//        }
//    }
//
//    public void disable()
//    {
//        motor.disable();
//    }

    public abstract void stop();

    public abstract void forward(double speed);

    public abstract void forward(double speed, int motorStep);

    public abstract void backward(double speed);

    public abstract void backward(double speed, int motorStep);
    
    public abstract double getSpeed();
}
