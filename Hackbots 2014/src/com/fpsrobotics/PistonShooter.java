package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * Controls the piston shooter, button 11 on the joystick causes it to fire.
 * More code will be added later as we decide how to regulate the shooter's
 * throwing arm.
 *
 * @author ray
 */
public class PistonShooter implements Runnable, Solenoids, Joysticks, Analog
{

    public void run()
    {
        while (true)
        {
            if (leftJoystick.getRawButton(11))
            {
                shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
                shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
            } else
            {

                shooterSolenoidOne.set(DoubleSolenoid.Value.kReverse);
                shooterSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
            }

            while (leftJoystick.getRawButton(12))
            {
                this.shooterPreset(shooterPot, shooterSolenoidOne, shooterSolenoidTwo, 300);
            }
        }
    }

    private void shooterPreset(AnalogChannel shooterPot, DoubleSolenoid shooterSolenoidOne, DoubleSolenoid shooterSolenoidTwo, int presetValue)
    {
        while (shooterPot.getValue() < presetValue)
        {
            shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
            shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
        }

        shooterSolenoidOne.set(DoubleSolenoid.Value.kReverse);
        shooterSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
    }
}
