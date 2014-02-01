/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class ShooterControl 
{

    public PIDController PIDInit(Encoder shooterEncoder, SpeedController shooterTalon, int lowValue, int highValue, double Kp, double Ki, double Kd)
    {
        PIDController shooterPID;

        shooterEncoder.setDistancePerPulse(.000623);
        shooterEncoder.start();
        shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        shooterPID = new PIDController(Kp, Ki, Kd, shooterEncoder, shooterTalon);
        shooterPID.enable();

        // min - max input values in inches
        shooterPID.setInputRange(lowValue, highValue);

        return shooterPID;
    }

    public void shootManual(Joystick joystick, SpeedController shooterTalon, int button)
    {
        if (joystick.getRawButton(button))
        {
            shooterTalon.set(1.0);
        } else
        {
            shooterTalon.set(0.0);
        }
    }
}
