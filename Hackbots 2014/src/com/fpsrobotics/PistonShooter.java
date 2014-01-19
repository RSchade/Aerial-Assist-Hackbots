/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public class PistonShooter implements Runnable, Solenoids, Joysticks
{

    public void run()
    {
        while (true)
        {
            while (leftJoystick.getRawButton(11))
            {
                shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
                shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
            }

            shooterSolenoidOne.set(DoubleSolenoid.Value.kOff);
            shooterSolenoidTwo.set(DoubleSolenoid.Value.kOff);
        }
    }

}
