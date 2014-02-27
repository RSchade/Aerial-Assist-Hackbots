/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class Autonomous
{

    public PIDController createPIDController(PIDController pidController, Encoder encoder, SpeedController motor)
    {
        encoder.setDistancePerPulse(.000623);
        //    DigitalIOs.LEFT_DRIVE_ENCODER.setDistancePerPulse(.000623);
        //Starts the encoders.
        encoder.start();
        // rightEncoder.start();
        // Sets the encoders to use distance for PID.
        // If this is not done, the robot may not go anywhere.
        // It is also possible to use rate, by changing kDistance to kRate.
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        //    DigitalIOs.LEFT_DRIVE_ENCODER.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        //Initializes the PID Controllers
        pidController = new PIDController(0.3, 0.0, 0.0, encoder, motor);
        // rightPID = new PIDController(0.3, 0.0, 0.0, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);
        //Enables the PID Controllers.
        pidController.enable();
        // rightPID.enable();
        //Sets the distance per pulse in inches.
        //Sets the input range of the PID Controller.
        // These will change, and you should change them based on how far
        //  your robot will be driving.
        // For this example, we set them at 100 inches.
        pidController.setInputRange(-100, 100);
        //  rightPID.setInputRange(-100, 100);

        return pidController;

    }

    public void autoTimer(int waitTimeMillis, HackbotWatchdog hackbotWatch)
    {
        long previousTime = System.currentTimeMillis();
        
        while (System.currentTimeMillis() - previousTime < waitTimeMillis)
        {
            hackbotWatch.feed();
        }
    }
    
    public void autoSetSetpoint(PIDController leftPID, PIDController rightPID, int rate)
    {
        leftPID.setSetpoint(-rate);
        rightPID.setSetpoint(rate);
    }
    
    public void autoDisablePID(PIDController leftPID, PIDController rightPID)
    {
        leftPID.disable();
        rightPID.disable();
    }
}
