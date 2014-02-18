package com.fpsrobotics.constants;

import com.fpsrobotics.deprecated.Presets;
import com.fpsrobotics.deprecated.DriveControl;
import com.fpsrobotics.*;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Talons, Analog, Solenoids, Values
{

    Constrain constrain = new Constrain();
    DashboardOutputs dashboardOutputs = new DashboardOutputs();
    DriveThread driveTrain = new DriveThread();
    HackbotStation hackbotStation = new HackbotStation();
    HackbotWatchdog hackbotWatch = new HackbotWatchdog();
    Pneumatics pneumatics = new Pneumatics();
    Presets presets = new Presets();
    RobotCamera robotCamera = new RobotCamera();
    VisionProcessingSample visionSample = new VisionProcessingSample();
    CatapultThread catapult = new CatapultThread();
    SpinnySticksThread spinnySticks = new SpinnySticksThread();
    DriveControl driveControl = new DriveControl();
}
