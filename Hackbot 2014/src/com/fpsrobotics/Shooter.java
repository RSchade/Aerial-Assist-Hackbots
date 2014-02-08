package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.PID;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.ThreadsAndClasses;

/**
 *
 * Controls the piston shooter, button 11 on the joystick causes it to fire.
 * More code will be added later as we decide how to regulate the shooter's
 * throwing arm.
 *
 * @author ray
 */
public class Shooter implements Runnable, Joysticks, Analog, Talons, DIOs, ControlMap, PID, ThreadsAndClasses
{

    double dynamicPresetDistance = 0;
    double dynamicPresetSpeed = 0;
    
    boolean areWeShooting;

    /**
     * Thread to control the shooter.
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();

        while (true)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {
                // Presets (dummy, real presets to be added later)
                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalon, 300, SHOOTER_PRESET_ONE, 1.0);

                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalon, (int) dynamicPresetDistance, SHOOTER_MANUAL, dynamicPresetSpeed);

                dynamicPresetDistance += -gamepadJoystick.getRawAxis(2);
                dynamicPresetSpeed += gamepadJoystick.getRawAxis(1);

                previousTime = System.currentTimeMillis();
            }
        }
    }

    public double getDynamicPresetDistance()
    {
        return dynamicPresetDistance;
    }

    public double getDynamicPresetSpeed()
    {
        return dynamicPresetSpeed;
    }
    
    public boolean getAreWeShooting()
    {
        return presets.getAreWeShooting();
    }
    
}
