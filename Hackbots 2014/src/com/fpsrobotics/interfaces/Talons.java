/**
 * 
 * Where talons can be instantiated, so they can be easily changed
 * 
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Talon;

/**
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
    
    // Create other Talons here
    
}
