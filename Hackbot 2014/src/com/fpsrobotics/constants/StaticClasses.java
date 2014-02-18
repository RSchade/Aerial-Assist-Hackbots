package com.fpsrobotics.constants;

import com.fpsrobotics.thread.HackbotStationThread;
import com.fpsrobotics.thread.DriveThread;
import com.fpsrobotics.thread.CatapultThread;
import com.fpsrobotics.thread.SpinnySticksThread;
import com.fpsrobotics.*;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public class StaticClasses
{

    public final static Constrain constrain = new Constrain();
    public final static DashboardOutputs dashboardOutputs = new DashboardOutputs();
    public final static DriveThread driveTrain = new DriveThread();
    public final static HackbotStationThread hackbotStation = new HackbotStationThread();
    public final static HackbotWatchdog hackbotWatch = new HackbotWatchdog();
    public final static RobotCamera robotCamera = new RobotCamera();
    public final static VisionProcessingSample visionSample = new VisionProcessingSample();
    public final static CatapultThread catapult = new CatapultThread();
    public final static SpinnySticksThread spinnySticks = new SpinnySticksThread();
}
