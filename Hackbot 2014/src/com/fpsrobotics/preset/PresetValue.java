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

    private double _speed;
    private double _angle;

    public PresetValue(double speed, double angle)
    {
        this._speed = speed;
        this._angle = angle;
    }

    public double getSpeed()
    {
        return this._speed;
    }

    public double getAngle()
    {
        return this._angle;
    }
}
