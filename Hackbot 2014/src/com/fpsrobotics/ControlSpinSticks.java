package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Controls the spinny sticks.
 * @author ray
 */
public class ControlSpinSticks
{
    // Spin sticks to a speed

    public void spinSticks(SpeedController spinnyMotor, double speed)
    {
        spinnyMotor.set(speed);
    }
}
