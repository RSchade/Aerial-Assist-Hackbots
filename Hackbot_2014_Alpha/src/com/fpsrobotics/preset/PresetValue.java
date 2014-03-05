/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

/**
 *
 * @author Matthew
 */
public class PresetValue
{

    private final double speed;
    private final double angle;

    public PresetValue(double speed, double angle)
    {
        this.speed = speed;
        this.angle = angle;
    }

    public double getSpeed()
    {
        return this.speed;
    }

    public double getAngle()
    {
        return this.angle;
    }
}