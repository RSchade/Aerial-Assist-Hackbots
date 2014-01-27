package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 * Spins the sticks.
 *
 * @author ray
 */
public class SpinnySticks implements Runnable, Joysticks, Talons, Values
{

    public SpinnySticks()
    {
    }

    public void run()
    {
        boolean isOn = true;
        
        while (true)
        {
            // spinny sticks stuff here
            if (rightJoystick.getRawButton(3))
            {
                spinnyRightMotor.set(HALF_SPEED);
                spinnyLeftMotor.set(-HALF_SPEED);
            } else if (leftJoystick.getRawButton(3))
            {
                spinnyRightMotor.set(-HALF_SPEED);
                spinnyLeftMotor.set(HALF_SPEED);
            } else
            {
                spinnyRightMotor.set(NO_SPEED);
                spinnyLeftMotor.set(NO_SPEED);
            }
            try
            {
                ledOutput.pulseLED(enhancedIO, SPINNY_STICKS_LED, isOn);
            } catch (DriverStationEnhancedIO.EnhancedIOException ex)
            {
                ex.printStackTrace();
            }
            isOn = !isOn;
        }
    }
}
