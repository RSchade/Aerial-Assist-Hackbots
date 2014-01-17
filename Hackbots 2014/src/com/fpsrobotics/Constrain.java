/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

/**
 *
 * @author ray
 */
public class Constrain
{

    public double constrainDouble(double input, double highValue, double lowValue)
    {
        if (input > highValue)
        {
            input = highValue;
        }

        if (input < lowValue)
        {
            input = lowValue;
        }

        return input;
    }

    public int constrainInt(int input, int highValue, int lowValue)
    {
        if (input > highValue)
        {
            input = highValue;
        }

        if (input < lowValue)
        {
            input = lowValue;
        }

        return input;
    }
}
