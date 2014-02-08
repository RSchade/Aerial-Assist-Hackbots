package com.fpsrobotics;

/**
 *
 * @author ray
 */
public class Constrain
{

    // Constrain a double to a high and low value
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

    // Constrain a int to a high and low value
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
