package com.fpsrobotics.interfaces;

import com.fpsrobotics.*;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Analog, Solenoids, Values
{

    Constrain constrain = new Constrain();
    DashboardOutputs dashboardOutputs = new DashboardOutputs();
    DriveTrain driveTrain = new DriveTrain();
    HackbotStation hackbotStation = new HackbotStation();
    HackbotWatchdog hackbotWatch = new HackbotWatchdog();
    Pneumatics pneumatics = new Pneumatics();
    Presets presets = new Presets();
    RobotCamera robotCamera = new RobotCamera();
    VisionProcessingSample visionSample = new VisionProcessingSample();
    Shooter shooter = new Shooter();
    SpinnySticksControl spinnySticks = new SpinnySticksControl();
    DriveControl driveControl = new DriveControl();
    ShooterControl shooterControl = new ShooterControl();

}
