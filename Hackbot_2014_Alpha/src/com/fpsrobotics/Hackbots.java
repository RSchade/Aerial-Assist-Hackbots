/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fpsrobotics;

import com.fpsrobotics.thread.SpinnySticksThread;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;
import com.fpsrobotics.preset.PresetAuto;
import com.fpsrobotics.preset.PresetHighGoal;
import com.fpsrobotics.thread.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.camera.AxisCameraException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 *
 *
 * Main thread of the code, it starts all the threads in the beginning of
 * enabling, as well as feeds the watchdog.
 *
 *
 */
public class Hackbots extends IterativeRobot
{

    // Local variables
    boolean doneAlready = false;
    boolean doneEverythingAuto = false;

    SpinnySticksThread spinnySticksThread;
    CatapultThread shooterThread;
    DriveThread driveThread;
    HackbotStationThread hackbotStationThread;
    RobotCamera robotCamera;

    // Watchdog
    HackbotWatchdog hackbotWatch = new HackbotWatchdog();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code. Watchdog init. Start camera thread
     * here, so we don't have to enable the robot to get a camera feed.
     */
    public void robotInit()
    {
        System.out.println("Hackbots Aerial Assist Code");

        // Init watchdog with 2 second timeout
        hackbotWatch.watchdogInit(2);

        // Init pneumatics
        DigitalIOs.COMPRESSOR.start();

        robotCamera = new RobotCamera();

        // Camera settings init
        robotCamera.init();
    }

    /**
     * Used for the autonomous code.
     */
    PIDController leftPID;
    PIDController rightPID;
    PresetAuto presetAuto;
    PresetHighGoal presetHighGoal;
    Autonomous autonomous = new Autonomous();
    int goodImageCounter = 1;
    boolean autonDone = false;

    // For use in timers
    long previousTime;

    Catapult shoot;
    SpinnySticks spinnyStick;
    LEDs leds;

    // Create vision sample
    VisionProcessingSample visionSample;
//    AutonThread autonThread;

    boolean isDoneAlready;
    /**
     * Called once when autonomous is enabled.
     */
    public void autonomousInit()
    {
        System.out.println("autonomousInit");

        // Create PID controllers
        leftPID = autonomous.createPIDController(leftPID, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.LEFT_DRIVE);
        rightPID = autonomous.createPIDController(rightPID, DigitalIOs.RIGHT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);

        // Create vision sample
        visionSample = new VisionProcessingSample();

        // Init vision sample
        visionSample.imageFindInit();

        // Creates the presets used for autonomous shooting
        presetAuto = new PresetAuto();
        presetHighGoal = new PresetHighGoal();

        // Creates motors and other things used for autonomous
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);
        spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, new TwoSolenoids(Solenoids.SPINNY_SHIFTER));
        leds = LEDs.createInstance(DigitalIOs.LED_RED, DigitalIOs.LED_GREEN, DigitalIOs.LED_BLUE);

        previousTime = System.currentTimeMillis();
        
        isDoneAlready = false;
        
//        autonThread = new AutonThread();
    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
    public void autonomousPeriodic()
    {
        System.out.println("autonomousPeriodic");

//        if (autonThread != null && !autonThread.isAlive())
//        {
//            autonThread.start();
//        }

//        hackbotWatch.feed();


        if (!isDoneAlready)
        {
            // Raise spinny sticks at the beginning of the match
            spinnyStick.spinnySticksUp();

            try
            {
                // Only run in the first five seconds, otherwise run the other code
                while (System.currentTimeMillis() - previousTime < 4000)
                {
                    if (visionSample.autoImageFind())
                    {
                        goodImageCounter++;

                        hackbotWatch.feed();

                        System.out.println("New Image");
                    }

                    if (goodImageCounter >= 3)
                    {
                        hackbotWatch.feed();

                        System.out.println("Shooting Hot");

                        LEDs.getInstance().RedSet(true);

                        // Spinny sticks go down then wait 800 ms for the ball to settle before shooting
                        spinnyStick.spinnySticksDown();
                        autonomous.autoTimer(800, hackbotWatch);

                        // Shoot
                        shoot.shoot(presetHighGoal);

                        // Go forward for 2.3 seconds
                        autonomous.autoSetSetpoint(leftPID, rightPID, 70);
                        autonomous.autoTimer(2300, hackbotWatch);

                        // Stop and disable the PID so it doesn't interfere
                        autonomous.autoSetSetpoint(leftPID, rightPID, 0);
                        autonomous.autoDisablePID(leftPID, rightPID);

                        LEDs.getInstance().RedSet(false);

                        // Sets the autonomous done flag to true so it doesn't poison teleop
                        autonDone = true;
                    }

                    hackbotWatch.feed();
                }

                if (!autonDone)
                {
                    LEDs.getInstance().GreenSet(true);

                    // Move forward for 2.3 seconds to get into the new zone
                    autonomous.autoSetSetpoint(leftPID, rightPID, 70);
                    autonomous.autoTimer(2300, hackbotWatch);

                    // Stop the drive train, since we moved far enough
                    autonomous.autoSetSetpoint(leftPID, rightPID, 0);
                    autonomous.autoDisablePID(leftPID, rightPID);

                    // Make the spinny sticks go down and wait 800 ms for them to settle
                    spinnyStick.spinnySticksDown();
                    autonomous.autoTimer(800, hackbotWatch);

                    System.out.println("Shooting Not Hot");

                    // Shoot
                    shoot.shoot(presetAuto);

                    LEDs.getInstance().GreenSet(false);
                }

            } catch (AxisCameraException ex)
            {
                ex.printStackTrace();
            }
            
            isDoneAlready = true;
        }

        hackbotWatch.feed();
    }

    public void teleopInit()
    {
        System.out.println("teleopInit");

        spinnySticksThread = new SpinnySticksThread();
        shooterThread = new CatapultThread();
        driveThread = new DriveThread();
        hackbotStationThread = new HackbotStationThread();

//        autonThread.interrupt();

//        try
//        {
//            autonThread.join();
//        } catch (InterruptedException ex)
//        {
//            ex.printStackTrace();
//
//        } finally
//        {
//            autonThread = null;
//        }

        LEDs.createInstance(DigitalIOs.LED_RED, DigitalIOs.LED_GREEN, DigitalIOs.LED_BLUE);
    }

    /**
     * This function is called periodically during operator control. Start all
     * threads here.
     *
     */
    public void teleopPeriodic()
    {
        System.out.println("teleopPeriodic");

        // Start all threads (only once)
        if (!doneAlready)
        {
            //Threads here
            driveThread.start();
            hackbotStationThread.start();
            shooterThread.start();
            spinnySticksThread.start();

            doneAlready = true;
        }
        // Feed the watchdog
        hackbotWatch.feed();
    }

    public void testInit()
    {

    }

    public void testPeriodic()
    {
        // Feed watchdog during test
        hackbotWatch.feed();
    }

    public void disabledInit()
    {

    }

    public void disabledPeriodic()
    {
        System.out.println("disabledPeriodic");

        if (driveThread != null)
        {
            driveThread.interrupt();
        }

        if (hackbotStationThread != null)
        {
            hackbotStationThread.interrupt();
        }

        if (shooterThread != null)
        {
            shooterThread.interrupt();
        }

        if (spinnySticksThread != null)
        {
            spinnySticksThread.interrupt();
        }

//        if (leftPID != null)
//        {
//            leftPID = null;
//        }
//
//        if (rightPID != null)
//        {
//            rightPID = null;
//        }
//        if (visionSample != null)
//        {
//            visionSample = null;
//        }
        driveThread = null;
        hackbotStationThread = null;
        shooterThread = null;
        spinnySticksThread = null;

        doneAlready = false;

    }
}
