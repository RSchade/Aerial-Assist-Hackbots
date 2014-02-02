package com.fpsrobotics.interfaces;

import com.fpsrobotics.*;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * Where threads and classes can be instantiated, so they can be easily changed.
 * This interface extends all others so the classes can be given variables from
 * other interfaces.
 *
 * @author ray
 */
public interface ThreadsAndClasses extends DIOs, Joysticks, Relays, Talons, Analog, Solenoids, Values
{

    Constrain constrain = new Constrain();
    ControlSpinSticks controlSpinSticks = new ControlSpinSticks();
    DashboardOutputs dashboardOutputs = new DashboardOutputs();
    DriveTrain driveTrain = new DriveTrain();
    FakePIDLoop pidloop = new FakePIDLoop();
    HackbotStation hackbotStation = new HackbotStation();
    HackbotWatchdog hackbotWatch = new HackbotWatchdog();
    Pneumatics pneumatics = new Pneumatics();
    Presets presets = new Presets();
    RobotCamera robotCamera = new RobotCamera();
    VisionProcessingSample visionSample = new VisionProcessingSample();
    Shooter shooter = new Shooter();
    SpinnySticks spinnySticks = new SpinnySticks();
    DriveControl driveControl = new DriveControl();
    ShooterControl shooterControl = new ShooterControl();
    OffloadProcesses offloadProcesses = new OffloadProcesses();

    //Threads here
    Thread driveThread = new Thread(driveTrain);
    Thread hackbotStationThread = new Thread(hackbotStation);
    Thread shooterThread = new Thread(shooter);
    Thread spinnySticksThread = new Thread(spinnySticks);
    Thread pidLoopThread = new Thread(pidloop);

    // Watchdog
    Watchdog dog = Watchdog.getInstance();
}
