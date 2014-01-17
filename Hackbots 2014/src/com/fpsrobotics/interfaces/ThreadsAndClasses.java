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
public interface ThreadsAndClasses extends DIOs, Joysticks, Relays, Talons, Analog, Solenoids
{
    // Classes here
    DriveTrain driveTrain = new DriveTrain
            (leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, 
            leftJoystick, rightJoystick);
    
    HackbotStation hackbotStation = new HackbotStation
            (leftJoystick, rightJoystick);
    
    Shooter shooter = new Shooter();
    
    SpinnySticks spinnySticks = new SpinnySticks();
    
    //Threads here
    Thread driveThread = new Thread(driveTrain);
    Thread hackbotStationThread = new Thread(hackbotStation);
    Thread shooterThread = new Thread(shooter);
    Thread spinnySticksThread = new Thread(spinnySticks);
    
}
