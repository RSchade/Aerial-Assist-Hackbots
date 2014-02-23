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

        // Camera settings init
        ThreadsAndClasses.robotCamera.init();
    }

    /**
     * Used for the autonomous code.
     */
    PIDController leftPID;
    PIDController rightPID;
    PresetAuto presetAuto;
    PresetHighGoal presetHighGoal;

    /**
     * Called once when autonomous is enabled.
     */
    public void autonomousInit()
    {
        System.out.println("autonomousInit");

        DigitalIOs.LEFT_DRIVE_ENCODER.setDistancePerPulse(.000623);
        DigitalIOs.LEFT_DRIVE_ENCODER.setDistancePerPulse(.000623);
        //Starts the encoders.
        DigitalIOs.LEFT_DRIVE_ENCODER.start();
        // rightEncoder.start();
        // Sets the encoders to use distance for PID.
        // If this is not done, the robot may not go anywhere.
        // It is also possible to use rate, by changing kDistance to kRate.
        DigitalIOs.LEFT_DRIVE_ENCODER.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        DigitalIOs.LEFT_DRIVE_ENCODER.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        //Initializes the PID Controllers
        leftPID = new PIDController(0.3, 0.0, 0.0, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.LEFT_DRIVE);
        rightPID = new PIDController(0.3, 0.0, 0.0, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);
        //Enables the PID Controllers.
        leftPID.enable();
        rightPID.enable();
        //Sets the distance per pulse in inches.
        //Sets the input range of the PID Controller.
        // These will change, and you should change them based on how far
        //  your robot will be driving.
        // For this example, we set them at 100 inches.
        leftPID.setInputRange(-100, 100);
        rightPID.setInputRange(-100, 100);

        // Creates the presets used for autonomous shooting
        presetAuto = new PresetAuto();
        presetHighGoal = new PresetHighGoal();

        VisionProcessingSample visionSample;

        int goodImageCounter = 0;

        hackbotWatch.feed();

        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        Catapult shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);
        SpinnySticks spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, new TwoSolenoids(Solenoids.SPINNY_SHIFTER));

        boolean isImageFinding = true;

        spinnyStick.spinnySticksUp();

        visionSample = new VisionProcessingSample();
        visionSample.imageFindInit();

        long previousTime = System.currentTimeMillis();

        try
        {

            while (System.currentTimeMillis() - previousTime < 5000 && isImageFinding)
            {

                while (goodImageCounter <= 2)
                {
                    if (visionSample.autoImageFind())
                    {
                        goodImageCounter++;

                        System.out.println("New Image");

                        hackbotWatch.feed();

                    }

                    hackbotWatch.feed();
                }

                System.out.println("Shooting");

//        spinnyStick.spinnySticksUp();
//        long previousTime = System.currentTimeMillis();
//
//        while (System.currentTimeMillis() - previousTime < 600)
//        {
//            hackbotWatch.feed();
//        }
                spinnyStick.spinnySticksDown();

//            previousTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - previousTime < 200)
//            {
//                hackbotWatch.feed();
//            }
                shoot.shoot(presetHighGoal);

                leftPID.setSetpoint(-50);
                rightPID.setSetpoint(50);

                previousTime = System.currentTimeMillis();

                while (System.currentTimeMillis() - previousTime < 2600)
                {
                    hackbotWatch.feed();
                }

                spinnyStick.spinnySticksDown();

                leftPID.setSetpoint(0);
                rightPID.setSetpoint(0);

                leftPID.disable();
                rightPID.disable();

                while (super.isAutonomous())
                {
                    hackbotWatch.feed();
                }

            }

            previousTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - previousTime < 2000)
            {
                hackbotWatch.feed();
            }

            leftPID.setSetpoint(-50);
            rightPID.setSetpoint(50);

            previousTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - previousTime < 2600)
            {
                hackbotWatch.feed();
            }

            spinnyStick.spinnySticksDown();

            leftPID.setSetpoint(0);
            rightPID.setSetpoint(0);

            leftPID.disable();
            rightPID.disable();

            shoot.shoot(presetAuto);

            while (super.isAutonomous())
            {
                hackbotWatch.feed();
            }

        } catch (AxisCameraException ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
    public void autonomousPeriodic()
    {
        System.out.println("autonomousPeriodic");
    }

    public void teleopInit()
    {
        System.out.println("teleopInit");

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

        if (leftPID != null)
        {
            leftPID = null;
        }

        if (rightPID != null)
        {
            rightPID = null;
        }

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
