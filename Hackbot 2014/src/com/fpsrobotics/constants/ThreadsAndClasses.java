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
public class ThreadsAndClasses
{

    public static final Constrain constrain = new Constrain();
    public static final DashboardOutputs dashboardOutputs = new DashboardOutputs();
    public static final DriveThread driveTrain = new DriveThread();
    public static final HackbotStationThread hackbotStation = new HackbotStationThread();
    public static final RobotCamera robotCamera = new RobotCamera();
    public static final VisionProcessingSample visionSample = new VisionProcessingSample();
    public static final CatapultThread catapult = new CatapultThread();
    public static final SpinnySticksThread spinnySticks = new SpinnySticksThread();
}
