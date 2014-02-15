package com.fpsrobotics;

import com.fpsrobotics.interfaces.IsAThread;
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
public class Shooter implements Runnable, Joysticks, Analog, Talons, DIOs, ControlMap, PID, ThreadsAndClasses, IsAThread
{

    double dynamicPresetDistance = 0;
    double dynamicPresetSpeed = 0;
    boolean isInterrupted = false;
    
    boolean areWeShooting;

    /**
     * Thread to control the shooter.
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
        
        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {
                // Presets (dummy, real presets to be added later)
                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, 300, SHOOTER_PRESET_ONE, 0.2);

                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, (int) dynamicPresetDistance, SHOOTER_MANUAL, 0.2);

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

    public void interrupt()
    {
        isInterrupted = true;
    }
    
}
