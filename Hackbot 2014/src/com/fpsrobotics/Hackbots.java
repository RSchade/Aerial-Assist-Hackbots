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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    SpinnySticksThread spinnySticksThread;
    CatapultThread shooterThread;
    DriveThread driveThread;
    HackbotStationThread hackbotStationThread;
    PIDController leftPID;
    PIDController rightPID;

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

    PresetAuto presetAuto;
    PresetHighGoal presetHighGoal;

    public void autonomousInit()
    {
        presetAuto = new PresetAuto();
        presetHighGoal = new PresetHighGoal();
        
        DigitalIOs.LEFT_DRIVE_ENCODER.setDistancePerPulse(.000623);
//        DigitalIOs.LEFT_DRIVE_ENCODER.setDistancePerPulse(.000623);

        //Starts the encoders.
        DigitalIOs.LEFT_DRIVE_ENCODER.start();
//        rightEncoder.start();

        //Sets the encoders to use distance for PID.
        //If this is not done, the robot may not go anywhere.
        //It is also possible to use rate, by changing kDistance to kRate.
        DigitalIOs.LEFT_DRIVE_ENCODER.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
//        DigitalIOs.LEFT_DRIVE_ENCODER.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        //Initializes the PID Controllers
        leftPID = new PIDController(0.3, 0.0, 0.0, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.LEFT_DRIVE);
        rightPID = new PIDController(0.3, 0.0, 0.0, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);

        //Enables the PID Controllers.
        leftPID.enable();
        rightPID.enable();

        //Sets the distance per pulse in inches.
        //Sets the input range of the PID Controller.
        //These will change, and you should change them based on how far
        //your robot will be driving.
        //For this example, we set them at 100 inches.
        leftPID.setInputRange(-100, 100);
        rightPID.setInputRange(-100, 100);
    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
    public void autonomousPeriodic()
    {
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        Catapult shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);
        SpinnySticks spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, new TwoSolenoids(Solenoids.SPINNY_SHIFTER));

//        try
//        {
//
//            while (goodImageCounter <= 2)
//            {
//                if (ThreadsAndClasses.visionSample.autoImageFind())
//                {
//                    goodImageCounter++;
//
//                    System.out.println("New Image");
//
//                }
//
//                hackbotWatch.feed();
//            }
//            goodImageCounter = 0;
//
//            System.out.println("Shooting");
//
        spinnyStick.spinnySticksUp();

        shoot.shoot(presetHighGoal);
        
//        leftPID.setSetpoint(-50);
//        rightPID.setSetpoint(50);
//
//        long previousTime = System.currentTimeMillis();
//
//        while (System.currentTimeMillis() - previousTime < 4200)
//        {
//            hackbotWatch.feed();
//        }
//
//        spinnyStick.spinnySticksDown();
//
//        leftPID.setSetpoint(0);
//        rightPID.setSetpoint(0);
//
//        leftPID.disable();
//        rightPID.disable();
//
//        shoot.shoot(presetAuto);

        while (super.isAutonomous())
        {
            hackbotWatch.feed();
        }

//        } catch (AxisCameraException ex)
//        {
//            ex.printStackTrace();
//        }
    }

    public void teleopInit()
    {
        spinnySticksThread = new SpinnySticksThread();
        shooterThread = new CatapultThread();
        driveThread = new DriveThread();
        hackbotStationThread = new HackbotStationThread();
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

        if (leftPID != null)
        {
            leftPID = null;
        }

        if (rightPID != null)
        {
            rightPID = null;
        }

        driveThread = null;
        hackbotStationThread = null;
        shooterThread = null;
        spinnySticksThread = null;

        doneAlready = false;

    }
}
