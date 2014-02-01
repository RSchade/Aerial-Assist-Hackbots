/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class Presets
{
    public void shooterPresetPot(Joystick joystick, AnalogChannel shooterPot, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterPot.getValue() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }

    public void shooterPresetEncoder(Joystick joystick, Encoder shooterEncoder, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterEncoder.getDistance() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }

    public void shooterPresetBoth(Joystick joystick, AnalogChannel shooterPot, Encoder shooterEncoder, SpeedController shooterTalon, int presetValuePot, int presetValueEncoder, int button, double speed)
    {
        while ((shooterPot.getValue() < presetValuePot && joystick.getRawButton(button)) || (shooterEncoder.getDistance() < presetValueEncoder && joystick.getRawButton(button)))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);
    }

    public void shooterPresetPID(Joystick joystick, PIDController shooterPID, int setpoint, int button)
    {
        if (joystick.getRawButton(button))
        {
            shooterPID.setSetpoint(setpoint);
        }

        shooterPID.setSetpoint(0);

    }
}
