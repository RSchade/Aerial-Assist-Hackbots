package com.fpsrobotics;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Constrains variables between a high and a low value.
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

    public double deadzoneConstrain(Joystick joystick)
    {
        if (joystick.getRawAxis(2) < 0.1 && joystick.getRawAxis(2) > -0.1)
        {
            return 0.0;
        }
        return joystick.getRawAxis(2);
    }
}
