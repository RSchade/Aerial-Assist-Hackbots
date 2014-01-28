/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fpsrobotics;

import com.fpsrobotics.interfaces.ThreadsAndClasses;
import edu.wpi.first.wpilibj.Compressor;
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
public class Hackbots extends IterativeRobot implements ThreadsAndClasses
{

    boolean doneAlready = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code. Watchdog init. Start camera thread
     * here, so we don't have to enable the robot to get a camera feed.
     */
    public void robotInit()
    {
        System.out.println("Hackbots Aerial Assist Code");

        // Watchdog init
        Watchdog.getInstance().setEnabled(true);
        Watchdog.getInstance().setExpiration(2);

        compressor.start();

        // Camera settings
        //robotCamera.init();
        visionSample.imageFindingRobotInit();
        robotCamera.init();
    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
    public void autonomousPeriodic()
    {
        try
        {
            visionSample.imageFindingAutonomous();
        } catch (AxisCameraException ex)
        {
            ex.printStackTrace();
        }

        Watchdog.getInstance().feed();
    }

    /**
     * This function is called periodically during operator control. Start all
     * threads here.
     *
     */
    public void teleopPeriodic()
    {
        if (!doneAlready)
        {

            // Don't start drive thread if simple PID is on
            if (!SimplePIDMode)
            {
                driveThread.start();
            }

            hackbotStationThread.start();
            shooterThread.start();
            spinnySticksThread.start();

            // For breadboard compatibility
            if (breadBoardMode)
            {
                breadBoardThread.start();
            }

            // SimplePID
            if (SimplePIDMode)
            {
                pidLoopThread.start();
            }

            breadBoardThread.start();;

            doneAlready = true;
        }

        // Feed the watchdog
        Watchdog.getInstance().feed();
    }

    public void testPeriodic()
    {
        Watchdog.getInstance().feed();
    }
}
