/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fpsrobotics;

import com.fpsrobotics.constants.DIOs;
import com.fpsrobotics.thread.SpinnySticksThread;
import com.fpsrobotics.constants.StaticClasses;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
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
    Watchdog dog = Watchdog.getInstance();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code. Watchdog init. Start camera thread
     * here, so we don't have to enable the robot to get a camera feed.
     */
    public void robotInit()
    {
        System.out.println("Hackbots Aerial Assist Code");

        // Init watchdog with 2 second timeout
        StaticClasses.hackbotWatch.watchdogInit(dog, 2);

        // Init pneumatics
//        pneumatics.init(compressor);
        DIOs.compressor.start();

        // Camera settings init
        StaticClasses.visionSample.imageFindInit();
        StaticClasses.robotCamera.init();
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
                if (StaticClasses.visionSample.autoImageFind())
                {
                    goodImageCounter++;

                    System.out.println("New Image");

                }

                StaticClasses.hackbotWatch.feed(dog);
            }

            goodImageCounter = 0;

            System.out.println("Shooting");

            // shoot if three in a row
            // (shooter code here)

            StaticClasses.hackbotWatch.feed(dog);

            // Drive
//            driveControl.driveToPID(driveControl.initDrivePID(leftDrive, leftDriveEncoder, LOW_SETPOINT_PID_AUTO, HIGH_SETPOINT_PID_AUTO, autoP, autoI, autoD), -100);
//            driveControl.driveToPID(driveControl.initDrivePID(rightDrive, rightDriveEncoder, LOW_SETPOINT_PID_AUTO, HIGH_SETPOINT_PID_AUTO, autoP, autoI, autoD), -100);

            // Feed watchdog during auton
            StaticClasses.hackbotWatch.feed(dog);

            while (super.isAutonomous())
            {
                StaticClasses.hackbotWatch.feed(dog);
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
            Thread driveThread = new Thread(StaticClasses.driveTrain);
            Thread hackbotStationThread = new Thread(StaticClasses.hackbotStation);
            Thread shooterThread = new Thread(StaticClasses.catapult);
            Thread spinnySticksThread = new SpinnySticksThread();

            driveThread.start();
            hackbotStationThread.start();
            shooterThread.start();
            spinnySticksThread.start();

            doneAlready = true;
        }
        // Feed the watchdog
        StaticClasses.hackbotWatch.feed(dog);
    }

    public void testInit()
    {

    }

    public void testPeriodic()
    {
        // Feed watchdog during test
        StaticClasses.hackbotWatch.feed(dog);
    }

    public void disabledInit()
    {

    }

    public void disabledPeriodic()
    {
        StaticClasses.driveTrain.interrupt();
        StaticClasses.hackbotStation.interrupt();
        StaticClasses.catapult.interrupt();
        StaticClasses.spinnySticks.interrupt();

        doneAlready = false;
    }
}
