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

    boolean areWeShooting = false;

    /**
     * Shooter preset using potentiometers.
     * 
     * @param joystick
     * @param shooterPot
     * @param shooterTalon
     * @param presetValue
     * @param button
     * @param speed 
     */
    public void shooterPresetPot(Joystick joystick, AnalogChannel shooterPot, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterPot.getValue() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);

            areWeShooting = true;
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

        areWeShooting = false;
    }

    /**
     * Shooter presets using an encoder.
     * 
     * @param joystick
     * @param shooterEncoder
     * @param shooterTalon
     * @param presetValue
     * @param button
     * @param speed 
     */
    public void shooterPresetEncoder(Joystick joystick, Encoder shooterEncoder, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterEncoder.getDistance() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);

            areWeShooting = true;
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }

    /**
     * Shooter preset with both.
     * 
     * @param joystick
     * @param shooterPot
     * @param shooterEncoder
     * @param shooterTalon
     * @param presetValuePot
     * @param presetValueEncoder
     * @param button
     * @param speed 
     */
    public void shooterPresetBoth(Joystick joystick, AnalogChannel shooterPot, Encoder shooterEncoder, SpeedController shooterTalon, int presetValuePot, int presetValueEncoder, int button, double speed)
    {
        while ((shooterPot.getValue() < presetValuePot && joystick.getRawButton(button)) || (shooterEncoder.getDistance() < presetValueEncoder && joystick.getRawButton(button)))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);

            areWeShooting = true;
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

        areWeShooting = false;
    }

    /**
     * Shooter preset with a PID.
     * 
     * @param joystick
     * @param shooterPID
     * @param setpoint
     * @param button 
     */
    public void shooterPresetPID(Joystick joystick, PIDController shooterPID, int setpoint, int button)
    {
        if (joystick.getRawButton(button))
        {
            shooterPID.setSetpoint(setpoint);
        }

        shooterPID.setSetpoint(0);

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
