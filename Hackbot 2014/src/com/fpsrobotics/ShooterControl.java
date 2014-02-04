/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class ShooterControl implements Values
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
            shooterTalon.set(FULL_SPEED);
        } else
        {
            shooterTalon.set(NO_SPEED);
        }
    }
    
    public void shootAuto(SpeedController shooterTalon, AnalogChannel shooterPot)
    {
        shooterTalon.set(FULL_SPEED);
        
        if (shooterPot.getValue() >= 100)
        {
            shooterTalon.set(NO_SPEED);
        }
    }
}
