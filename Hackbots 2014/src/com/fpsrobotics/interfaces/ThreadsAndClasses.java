/**
 * 
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from other interfaces.
 * 
 */

package com.fpsrobotics.interfaces;

import com.fpsrobotics.*;

/**
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Relays, Talons
{
    // Classes here
    DriveTrain driveTrain = new DriveTrain
            (leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, 
            leftJoystick, rightJoystick);
    
    HackbotStation hackbotStation = new HackbotStation
            (leftJoystick, rightJoystick);
    
    //Threads here
    Thread driveThread = new Thread(driveTrain);
    Thread hackbotStationThread = new Thread(hackbotStation);
    
}
