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
import edu.wpi.first.wpilibj.IterativeRobot;
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
    int goodImageCounter = 0;

    
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
//        pneumatics.init(compressor);
        DigitalIOs.COMPRESSOR.start();

        // Camera settings init
        ThreadsAndClasses.visionSample.imageFindInit();
        ThreadsAndClasses.robotCamera.init();
    }

    public void autonomousInit()
    {

    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
    public void autonomousPeriodic()
    {  
        try
        {

            while (goodImageCounter <= 2)
            {
                if (ThreadsAndClasses.visionSample.autoImageFind())
                {
                    goodImageCounter++;

                    System.out.println("New Image");

                }

                hackbotWatch.feed();
            }

            goodImageCounter = 0;

            System.out.println("Shooting");

            // shoot if three in a row
            // (shooter code here)

            hackbotWatch.feed();

            // Drive
//            driveControl.driveToPID(driveControl.initDrivePID(leftDrive, leftDriveEncoder, LOW_SETPOINT_PID_AUTO, HIGH_SETPOINT_PID_AUTO, autoP, autoI, autoD), -100);
//            driveControl.driveToPID(driveControl.initDrivePID(rightDrive, rightDriveEncoder, LOW_SETPOINT_PID_AUTO, HIGH_SETPOINT_PID_AUTO, autoP, autoI, autoD), -100);

            // Feed watchdog during auton
            hackbotWatch.feed();

            while (super.isAutonomous())
            {
                hackbotWatch.feed();
            }

        } catch (AxisCameraException ex)
        {
            ex.printStackTrace();
        }
    }

    public void teleopInit()
    {

    }

    /**
     * This function is called periodically during operator control. Start all
     * threads here.
     *
     */
    public void teleopPeriodic()
    {
        // Start all threads (only once)
        if (!doneAlready)
        {
            //Threads here
            Thread driveThread = new Thread(ThreadsAndClasses.driveTrain);
            Thread hackbotStationThread = new Thread(ThreadsAndClasses.hackbotStation);
            Thread shooterThread = new Thread(ThreadsAndClasses.catapult);
            Thread spinnySticksThread = new SpinnySticksThread();

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
        ThreadsAndClasses.driveTrain.interrupt();
        ThreadsAndClasses.hackbotStation.interrupt();
        ThreadsAndClasses.catapult.interrupt();
        ThreadsAndClasses.spinnySticks.interrupt();

        doneAlready = false;
    }
}
