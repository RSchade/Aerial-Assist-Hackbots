package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Controls the piston shooter, button 11 on the joystick causes it to fire.
 * More code will be added later as we decide how to regulate the shooter's
 * throwing arm.
 *
 * @author ray
 */
public class Shooter implements Runnable, Joysticks, Analog, Talons
{

    public void run()
    {
        while (true)
        {
            if (leftJoystick.getRawButton(11))
            {
//                shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
//                shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
                shooterTalon.set(1.0);

            } else
            {

//                shooterSolenoidOne.set(DoubleSolenoid.Value.kReverse);
//                shooterSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
                shooterTalon.set(0.0);
            }

//                this.shooterPreset(shooterPot, shooterSolenoidOne, shooterSolenoidTwo, 300);
            this.shooterPreset(leftJoystick, shooterPot, shooterTalon, 300, 11, 1.0);

        }
    }

//    private void shooterPreset(AnalogChannel shooterPot, DoubleSolenoid shooterSolenoidOne, DoubleSolenoid shooterSolenoidTwo, int presetValue)
//    {
//        while (shooterPot.getValue() < presetValue)
//        {
//            shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
//            shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
//        }
//
//        shooterSolenoidOne.set(DoubleSolenoid.Value.kReverse);
//        shooterSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
//    }
    private void shooterPreset(Joystick joystick, AnalogChannel shooterPot, Talon shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterPot.getValue() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }
}
