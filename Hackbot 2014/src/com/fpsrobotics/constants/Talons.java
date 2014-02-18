package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * Where talons can be instantiated, so they can be easily changed
 *
 * @author ray
 */
public class Talons
{
    // Create drivetrain here

   public final static  SpeedController leftDrive = new Talon(DeviceMap.LEFT_DRIVE_MAP);
   public final static  SpeedController rightDrive = new Talon(DeviceMap.RIGHT_DRIVE_MAP);
    
  public final static   SpeedController spinnyMotor = new Talon(DeviceMap.SPIN_MAP);
//    SpeedController spinnyRightMotor = new Talon(RIGHT_SPIN_MAP);
    
   public final static  SpeedController shooterTalonOne = new Talon(DeviceMap.SHOOTER_TALON_MAP_ONE);
   public final static  SpeedController shooterTalonTwo = new Talon(DeviceMap.SHOOTER_TALON_MAP_TWO);
    // Create other Talons here
}
