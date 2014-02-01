package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * Controls the piston shooter, button 11 on the joystick causes it to fire.
 * More code will be added later as we decide how to regulate the shooter's
 * throwing arm.
 *
 * @author ray
 */
public class Shooter implements Runnable, Joysticks, Analog, Talons, DIOs, ControlMap, ThreadsAndClasses
{
    //Proportional, Integral, and Dervative constants.
    //These values will need to be tuned for your robot.
    private final double Kp = 0.3;
    private final double Ki = 0.0;
    private final double Kd = 0.0;
    //This must be fully initialized in the constructor, after the settings
    //for the encoders have been done.
    private PIDController shooterPID;

    public void run()
    {
        shooterEncoder.setDistancePerPulse(.000623);
        shooterEncoder.start();
        shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        shooterPID = new PIDController(Kp, Ki, Kd, shooterEncoder, shooterTalon);
        shooterPID.enable();

        // min - max input values in inches
        shooterPID.setInputRange(0, 100);

        while (true)
        {
            // Manual shooting
            if (gamepadJoystick.getRawButton(SHOOTER_MANUAL))
            {
                shooterTalon.set(1.0);
            } else
            {
                shooterTalon.set(0.0);
            }

            // Presets (dummy, real presets to be added later)
            presets.shooterPresetBoth(gamepadJoystick, shooterPot, shooterEncoder, shooterTalon, 300, 5, SHOOTER_PRESET_ONE, 1.0);
            presets.shooterPresetPID(gamepadJoystick, shooterPID, SHOOTER_PRESET_TWO, 10);

        }
    }
}
