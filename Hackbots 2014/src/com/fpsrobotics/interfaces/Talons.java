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

    SpeedController leftDrive = new Talon(LEFT_DRIVE_MAP);
    SpeedController rightDrive = new Talon(RIGHT_DRIVE_MAP);
    
<<<<<<< HEAD
    SpeedController spinnyLeftMotor = new Talon(LEFT_SPIN_MAP);
    SpeedController spinnyRightMotor = new Talon(RIGHT_SPIN_MAP);
  
=======
    Talon spinnyLeftMotor = new Talon(LEFT_SPIN_MAP);
    Talon spinnyRightMotor = new Talon(RIGHT_SPIN_MAP);
>>>>>>> e19c519264f301b9af14cd0b6de9f5b4e624862f
    
    SpeedController shooterTalon = new Talon(SHOOTER_TALON_MAP);
    // Create other Talons here
}
