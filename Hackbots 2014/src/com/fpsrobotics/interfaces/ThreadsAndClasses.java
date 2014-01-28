package com.fpsrobotics.interfaces;

import com.fpsrobotics.*;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Relays, Talons, Analog, Solenoids
{
    // 1/18/14: got rid of input variables, don't work as expected (null pointer exception)
    // Classes here

    DriveTrain driveTrain = new DriveTrain();
//            (leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, 
//            leftJoystick, rightJoystick);
    HackbotStation hackbotStation = new HackbotStation();
//            (leftJoystick, rightJoystick);
    Shooter pistonShooter = new Shooter();
    SpinnySticks spinnySticks = new SpinnySticks();
    RobotCamera robotCamera = new RobotCamera();
    BreadBoard breadBoard = new BreadBoard();
    VisionProcessingSample visionSample = new VisionProcessingSample();
    PIDLoop pidloop = new PIDLoop();
    
    //Threads here
    Thread driveThread = new Thread(driveTrain);
    Thread hackbotStationThread = new Thread(hackbotStation);
    Thread pistonShooterThread = new Thread(pistonShooter);
    Thread spinnySticksThread = new Thread(spinnySticks);
    Thread breadBoardThread = new Thread(breadBoard);
    Thread pidLoopThread = new Thread(pidloop);
}
