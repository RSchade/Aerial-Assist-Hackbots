package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * Where talons can be instantiated, so they can be easily changed
 *
 * @author ray
 */
public interface Talons extends DeviceMap
{
    // Create drivetrain here

    SpeedController leftDrive = new Talon(LEFT_DRIVE_MAP);
    SpeedController rightDrive = new Talon(RIGHT_DRIVE_MAP);
    
    SpeedController spinnyMotor = new Talon(SPIN_MAP);
//    SpeedController spinnyRightMotor = new Talon(RIGHT_SPIN_MAP);
    
    SpeedController shooterTalonOne = new Talon(SHOOTER_TALON_MAP_ONE);
    SpeedController shooterTalonTwo = new Talon(SHOOTER_TALON_MAP_TWO);
    // Create other Talons here
}
