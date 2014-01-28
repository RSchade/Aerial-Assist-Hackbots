package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Talon;

/**
 * Where talons can be instantiated, so they can be easily changed
 *
 * @author ray
 */
public interface Talons extends DeviceMap
{
    // Create drivetrain here

    Talon leftDriveOne = new Talon(LEFT_DRIVE_ONE_MAP);
    Talon leftDriveTwo = new Talon(LEFT_DRIVE_TWO_MAP);
    Talon rightDriveOne = new Talon(RIGHT_DRIVE_ONE_MAP);
    Talon rightDriveTwo = new Talon(RIGHT_DRIVE_TWO_MAP);
    
    Talon spinnyLeftMotor = new Talon(LEFT_SPIN_MAP);
    Talon spinnyRightMotor = new Talon(RIGHT_SPIN_MAP);
    
    Talon encoderMotorMotor = new Talon(ENCODER_MOTOR_MOTOR_MAP);
    
    Talon shooterTalon = new Talon(SHOOTER_TALON_MAP);
    // Create other Talons here
}
