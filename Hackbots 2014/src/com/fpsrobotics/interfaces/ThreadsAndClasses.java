package com.fpsrobotics.interfaces;

import com.fpsrobotics.*;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Relays, Talons, Analog, Solenoids, Values
{

    DriveTrain driveTrain = new DriveTrain();
    HackbotStation hackbotStation = new HackbotStation();
    Shooter shooter = new Shooter();
    SpinnySticks spinnySticks = new SpinnySticks();
    RobotCamera robotCamera = new RobotCamera();
    VisionProcessingSample visionSample = new VisionProcessingSample();
    FakePIDLoop pidloop = new FakePIDLoop();
    
    //Threads here
    Thread driveThread = new Thread(driveTrain);
    Thread hackbotStationThread = new Thread(hackbotStation);
    Thread shooterThread = new Thread(shooter);
    Thread spinnySticksThread = new Thread(spinnySticks);
    Thread pidLoopThread = new Thread(pidloop);
}
