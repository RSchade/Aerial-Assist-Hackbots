package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * Controls the piston shooter, button 11 on the joystick causes it to fire.
 * More code will be added later as we decide how to regulate the shooter's
 * throwing arm.
 *
 * @author ray
 */
public class Shooter implements Runnable, Joysticks, Analog, Talons, DIOs, ControlMap
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
            if (gamepadJoystick.getRawButton(SHOOTER_MANUAL))
            {
                shooterTalon.set(1.0);

            } else
            {

                shooterTalon.set(0.0);
            }

            this.shooterPresetBoth(gamepadJoystick, shooterPot, shooterEncoder, shooterTalon, 300, 5, SHOOTER_PRESET_ONE, 1.0);
            this.shooterPresetPID(gamepadJoystick, shooterPID, SHOOTER_PRESET_TWO, 10);

        }
    }

    private void shooterPresetPot(Joystick joystick, AnalogChannel shooterPot, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterPot.getValue() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }

    private void shooterPresetEncoder(Joystick joystick, Encoder shooterEncoder, SpeedController shooterTalon, int presetValue, int button, double speed)
    {
        while (shooterEncoder.getDistance() < presetValue && joystick.getRawButton(button))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);

    }

    private void shooterPresetBoth(Joystick joystick, AnalogChannel shooterPot, Encoder shooterEncoder, SpeedController shooterTalon, int presetValuePot, int presetValueEncoder, int button, double speed)
    {
        while ((shooterPot.getValue() < presetValuePot && joystick.getRawButton(button)) || (shooterEncoder.getDistance() < presetValueEncoder && joystick.getRawButton(button)))
        {
            shooterTalon.set(speed);
            shooterTalon.set(speed);
        }

        shooterTalon.set(0.0);
        shooterTalon.set(0.0);
    }

    private void shooterPresetPID(Joystick joystick, PIDController shooterPID, int setpoint, int button)
    {
        if (joystick.getRawButton(button))
        {
            shooterPID.setSetpoint(setpoint);
        }

        shooterPID.setSetpoint(0);

    }
}
