package com.fpsrobotics.deprecated;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Method containing presets for the shooter.
 * 
 * @author ray
 */
public class Presets
{

    boolean areWeShooting = false;

    /**
     * Shooter preset using potentiometers.
     * 
     * @param joystick
     * @param shooterPot
     * @param shooterTalonOne
     * @param presetValue
     * @param shooterTalonTwo
     * @param button
     * @param speed 
     */
    public void shooterPresetPot(Joystick joystick, AnalogChannel shooterPot, SpeedController shooterTalonOne, SpeedController shooterTalonTwo, int presetValue, int button, double speed)
    {
        while (shooterPot.getValue() < presetValue && joystick.getRawButton(button))
        {
            shooterTalonOne.set(speed);
            shooterTalonTwo.set(speed);

            areWeShooting = true;
        }

        shooterTalonOne.set(0.0);
        shooterTalonTwo.set(0.0);

        areWeShooting = false;
    }

    /**
     * Dashboard output.
     * 
     * @return 
     */
    public boolean getAreWeShooting()
    {
        return areWeShooting;
    }
}
