/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fpsrobotics;

import com.fpsrobotics.thread.SpinnySticksThread;
import com.fpsrobotics.hardware.*;
import com.fpsrobotics.preset.Preset;
import com.fpsrobotics.preset.PresetAuto;
import com.fpsrobotics.preset.PresetHighGoal;
import com.fpsrobotics.preset.SixFt;
import com.fpsrobotics.preset.TenFt;
import com.fpsrobotics.thread.*;
import com.ni.rio.NiRioStatus;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.fpga.tSystem;

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
    DriveObject drive;

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

        Analogs.DISTANCE_SENSOR.setAverageBits(5);
        Analogs.SHOOTER_POTENTIOMETER.setAverageBits(5);

        tSystem system = new tSystem()
        {
        };

        NiRioStatus status = new NiRioStatus();

        System.out.println(system.getFpgaGuid(status).toString());

    }
    /**
     * Used for the autonomous code.
     */
    //PIDController leftPID;
    //PIDController rightPID;
    PresetAuto presetAuto;
    PresetHighGoal presetHighGoal;
    Autonomous autonomous = new Autonomous();
    int goodImageCounter = 1;
    boolean autonDone = false;
    // DrveTrain

    double driveSpeed = 0.8;
    // For use in timers
    long previousTime;
    Catapult shoot;
    SpinnySticks spinnyStick;
    LEDs leds;
    // Create vision sample
    VisionProcessingSample visionSample;
//    AutonThread autonThread;
    boolean isDoneAlready;
    Preset sixFt;
    Preset tenFt;
    
    LEDThread ledThread;

    /**
     * Called once when autonomous is enabled.
     */
    public void autonomousInit()
    {
//        System.out.println("autonomousInit");

        // Create PID controllers
//        leftPID = autonomous.createPIDController(leftPID, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.LEFT_DRIVE);
//        rightPID = autonomous.createPIDController(rightPID, DigitalIOs.RIGHT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);
        // Create vision sample
        visionSample = new VisionProcessingSample();

        // Init vision sample
        visionSample.imageFindInit();

        // Creates the presets used for autonomous shooting
        presetAuto = new PresetAuto();
        presetHighGoal = new PresetHighGoal();
        sixFt = new SixFt();
        tenFt = new TenFt();

        // Creates motors and other things used for autonomous
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);
        spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, Solenoids.SPINNY_SHIFTER);
        leds = LEDs.createInstance(DigitalIOs.LED_RED, DigitalIOs.LED_GREEN, DigitalIOs.LED_BLUE);
        drive = DriveObject.createInstance(new SimpleMotor(Motors.LEFT_DRIVE, true), new SimpleMotor(Motors.RIGHT_DRIVE, false), new SingleSolenoid(Solenoids.GEAR_SHIFTER));
        previousTime = System.currentTimeMillis();

        isDoneAlready = false;

//        autonThread = new AutonThread();
    }

    /**
     * This function is called periodically during autonomous. Autonomous code
     * goes here. Something about shooting in the hot goal and then going back.
     */
//    public void autonomousPeriodic()
//    {
//           System.out.println("autonomousPeriodic");
//
////        if (autonThread != null && !autonThread.isAlive())
////        {
////            autonThread.start();
////        }
////        hackbotWatch.feed();
//        
//        hackbotWatch.feed();
//        
//        if (!isDoneAlready)
//        {
//            // Raise spinny sticks at the beginning of the match
//            //spinnyStick.spinnySticksUp();
////            spinnyStick.spinnySticksDown();
//
//            try
//            {
//                spinnyStick.spinnySticksDown();
//                
//                autonomous.autoTimer(400, hackbotWatch);
//                
//                spinnyStick.spinnySticksUp();
//                
//                drive.forward(driveSpeed);
//                
//                autonomous.autoTimer(2000, hackbotWatch);
//                
//                drive.stop();
//                
//                previousTime = System.currentTimeMillis();
//                
//                // Only run in the first five seconds, otherwise run the other code 
//                while ((System.currentTimeMillis() - previousTime < 2000) && !autonDone)
//                {
//                    hackbotWatch.feed();
//                    
//                    if (visionSample.autoImageFind())
//                    {
//                        goodImageCounter++;
//
//                        hackbotWatch.feed();
//
//                        System.out.println("New Image");
//                    }
//
//                    if (goodImageCounter >= 3)
//                    {
//                        hackbotWatch.feed();
//
//                        spinnyStick.spinnySticksDown();
//                        
//                        autonomous.autoTimer(400, hackbotWatch);
//
//                        System.out.println("Shooting Hot");
//
//                        // Shoot
//                        shoot.shoot(sixFt);
//
//                        // Sets the autonomous done flag to true so it doesn't poison teleop
//                        autonDone = true;
//
//////                        System.out.println("Shooting Hot");
//////
//////                        LEDs.getInstance().RedSet(true);
//////
//////                        // Spinny sticks go down then wait 800 ms for the ball to settle before shooting
////////                        spinnyStick.spinnySticksDown();
//////                        
//////                        autonomous.autoTimer(800, hackbotWatch);
//////
//////                        // Shoot
//////                        shoot.shoot(presetHighGoal);
//////
//////                        // Go forward for 2.3 seconds
//////                        autonomous.autoSetSetpoint(leftPID, rightPID, 70);
//////                        autonomous.autoTimer(2300, hackbotWatch);
//////
//////                        // Stop and disable the PID so it doesn't interfere
//////                        autonomous.autoSetSetpoint(leftPID, rightPID, 0);
//////                        autonomous.autoDisablePID(leftPID, rightPID);
//////
//////                        LEDs.getInstance().RedSet(false);
//////
//////                        // Sets the autonomous done flag to true so it doesn't poison teleop
//////                        autonDone = true;
////
////                        LEDs.getInstance().GreenSet(true);
////
////                        // Make them go up while we move
////                        spinnyStick.spinnySticksUp();
////
////                        // Move forward for 2.3 seconds to get into the new zone
//////                        autonomous.autoSetSetpoint(leftPID, rightPID, 70);
//////                        autonomous.autoTimer(2300, hackbotWatch);
//////
//////                        // Stop the drive train, since we moved far enough
//////                        autonomous.autoSetSetpoint(leftPID, rightPID, 0);
//////                        autonomous.autoDisablePID(leftPID, rightPID);
////                        // Make the spinny sticks go down and wait 800 ms for them to settle
////                        
////                        hackbotWatch.feed();
////                        
////                        drive.forward(driveSpeed);
////
////                        autonomous.autoTimer(1000, hackbotWatch);
////
////                        drive.stop();
////
////                        spinnyStick.spinnySticksDown();
////
////                        autonomous.autoTimer(800, hackbotWatch);
////
////                        System.out.println("Shooting Not Hot");
////
////                        // Shoot
////                        shoot.shoot(presetAuto);
////
////                        LEDs.getInstance().GreenSet(false);
////
////                        // Sets the autonomous done flag to true so it doesn't poison teleop
////                        autonDone = true;
//                    }
//
//                    hackbotWatch.feed();
//                }
//
//                if (!autonDone)
//                {
//                    hackbotWatch.feed();
//
////                    System.out.println("Moving Forward!");
////
////                    drive.forward(driveSpeed);
////
////                    // Spinny sticks go down then wait 800 ms for the ball to settle before shooting
//////                        spinnyStick.spinnySticksDown();
////                    autonomous.autoTimer(2000, hackbotWatch);
////
////                    drive.stop();
//                    
//                    spinnyStick.spinnySticksDown();
//                    
//                    autonomous.autoTimer(400, hackbotWatch);
//                    
//                    // Shoot
//                    shoot.shoot(sixFt);
//
//                    
////                    hackbotWatch.feed();
////
////                    System.out.println("Shooting Hot!");
////
////                    LEDs.getInstance().RedSet(true);
////                    spinnyStick.spinnySticksDown();
////
////                    // Spinny sticks go down then wait 800 ms for the ball to settle before shooting
//////                        spinnyStick.spinnySticksDown();
////                    autonomous.autoTimer(800, hackbotWatch);
////
////                    // Shoot
////                    shoot.shoot(presetHighGoal);
////
////                    //NEW DRIVE
////                    // Make the spinny sticks go down and wait 1100 ms for them to settle
////                    autonomous.autoTimer(100, hackbotWatch);
////                    
////                    spinnyStick.spinnySticksUp();
////                    
////                    drive.forward(driveSpeed);
////
////                    autonomous.autoTimer(1000, hackbotWatch);
////
////                    drive.stop();
//
//                    // Go forward for 2.3 seconds
////                    autonomous.autoSetSetpoint(leftPID, rightPID, 70);
////                    autonomous.autoTimer(2300, hackbotWatch);
////
////                    // Stop and disable the PID so it doesn't interfere
////                    autonomous.autoSetSetpoint(leftPID, rightPID, 0);
////                    autonomous.autoDisablePID(leftPID, rightPID);
//                    LEDs.getInstance().RedSet(false);
//
////                    LEDs.getInstance().GreenSet(true);
////
////                    // Make them go up while we move
////                    spinnyStick.spinnySticksUp();
////                    
////                    // Move forward for 2.3 seconds to get into the new zone
////                    autonomous.autoSetSetpoint(leftPID, rightPID, 70);
////                    autonomous.autoTimer(2300, hackbotWatch);
////
////                    // Stop the drive train, since we moved far enough
////                    autonomous.autoSetSetpoint(leftPID, rightPID, 0);
////                    autonomous.autoDisablePID(leftPID, rightPID);
////
////                    // Make the spinny sticks go down and wait 800 ms for them to settle
////                    spinnyStick.spinnySticksDown();
////                    
////                    autonomous.autoTimer(800, hackbotWatch);
////
////                    System.out.println("Shooting Not Hot");
////
////                    // Shoot
////                    shoot.shoot(presetAuto);
////
////                    LEDs.getInstance().GreenSet(false);
//                }
//
//            } catch (AxisCameraException ex)
//            {
//                ex.printStackTrace();
//            }
//
//            isDoneAlready = true;
//        }
//
//        hackbotWatch.feed();
//    }
    public void autonomousPeriodic()
    {
//        System.out.println("autonomousPeriodic");

        hackbotWatch.feed();
        
        previousTime = System.currentTimeMillis();

        if (!isDoneAlready)
        {
            try
            {

                if (visionSample.autoImageFind())
                {
                    goodImageCounter++;

                    hackbotWatch.feed();

                    System.out.println("New Image");
                }

                if (goodImageCounter >= 3)
                {
                    auto();
                    
                    goodImageCounter = 0;
                    isDoneAlready = true;
                }

                if (System.currentTimeMillis() - previousTime < 3000 && isDoneAlready)
                {
                    auto();
                }
                
//                spinnyStick.spinnySticksIn();
//                
//                autonomous.autoTimer(750, hackbotWatch);
//                
//                spinnyStick.spinnySticksOut();
//                
//                autonomous.autoTimer(200, hackbotWatch);
//                
//                drive.forward(driveSpeed);
//                
//                autonomous.autoTimer(500, hackbotWatch);
//                
//                drive.stop();
//                
//                autonomous.autoTimer(1000, hackbotWatch);
//                
//                hackbotWatch.feed();
//                
//                spinnyStick.spinnySticksIn();
//                
//                autonomous.autoTimer(1500, hackbotWatch);
//                
//                // Shoot
//                shoot.shoot(presetAuto);

                isDoneAlready = true;
            } catch (AxisCameraException ex)
            {
                ex.printStackTrace();
            }
        }

        hackbotWatch.feed();
    }

    private void auto()
    {
        spinnyStick.spinnySticksIn();

        autonomous.autoTimer(750, hackbotWatch);

        spinnyStick.spinnySticksOut();

        autonomous.autoTimer(200, hackbotWatch);

        drive.forward(driveSpeed);

        autonomous.autoTimer(500, hackbotWatch);

        drive.stop();

        autonomous.autoTimer(1000, hackbotWatch);

        hackbotWatch.feed();

        spinnyStick.spinnySticksIn();

        autonomous.autoTimer(1500, hackbotWatch);

        // Shoot
        shoot.shoot(presetAuto);
    }

//        try{
//              DO DRIVE FORWARD STUFF
//                while (!autonDone)
//        {
//            hackbotWatch.feed();
//
//            if (visionSample.autoImageFind())
//            {
//                goodImageCounter++;
//
//            }
//
//            if (goodImageCounter >= 3)
//            {
//                autonDone = true;
//            }
//            if (System.currentTimeMillis() - previousTime < 5000)
//            {
//                autonDone = true;
//            }
//              }//DO SHOOT STUFF
//        }catch (AxisCameraException ex)
//            {
//                ex.printStackTrace();
//            }
        //                autonomous.autoTimer(600, hackbotWatch);
    //                spinnyStick.spinnySticksUp();
    //                previousTime = System.currentTimeMillis();
    //                // Only run in the first five seconds, otherwise run the other code 
    //                while ((System.currentTimeMillis() - previousTime < 5000) && !autonDone)
    //                {
    //                    hackbotWatch.feed();
    //
    //                    if (visionSample.autoImageFind())
    //                    {
    //                        goodImageCounter++;
    //
    //                        hackbotWatch.feed();
    //
    //                        System.out.println("New Image");
    //                    }
    //
    //                    if (goodImageCounter >= 3)
    //                    {
    ////                        hackbotWatch.feed();
    ////
    ////                        spinnyStick.spinnySticksDown();
    ////
    ////                        autonomous.autoTimer(400, hackbotWatch);
    ////
    ////                        System.out.println("Shooting Not Hot");
    ////
    ////                        // Shoot
    ////                        shoot.shoot(tenFt);
    ////
    ////                        // Sets the autonomous done flag to true so it doesn't poison teleop
    ////                        autonDone = true;
    //
    //                        hackbotWatch.feed();
    //
    //                        spinnyStick.spinnySticksDown();
    //
    //                        autonomous.autoTimer(400, hackbotWatch);
    //
    //                        System.out.println("Shooting Hot");
    //
    //                        // Shoot
    //                        shoot.shoot(tenFt);
    //
    //                        // Sets the autonomous done flag to true so it doesn't poison teleop
    //                    autonDone = true;
    //                    }
    //
    //                    hackbotWatch.feed();
    //                }
    //
    //                if (!autonDone)
    //                {
    ////                    hackbotWatch.feed();
    ////
    ////                    spinnyStick.spinnySticksDown();
    ////
    ////                    autonomous.autoTimer(400, hackbotWatch);
    ////
    ////                    System.out.println("Shooting Hot");
    ////                    
    ////                    // Shoot
    ////                    shoot.shoot(tenFt);
    //                    LEDs.getInstance().RedSet(false);
    //                    System.out.println("Shooting Not Hot");
    //                }
    //            autonomous.autoTimer(150, hackbotWatch);
    //
    //            drive.forward(driveSpeed);
    //
    //            autonomous.autoTimer(500, hackbotWatch);
    //
    //            drive.stop();
    //            } catch (AxisCameraException ex)
    //            {
    //                ex.printStackTrace();
    //            }
    public void teleopInit()
    {
//        System.out.println("teleopInit");

        spinnySticksThread = new SpinnySticksThread();
        shooterThread = new CatapultThread();
        driveThread = new DriveThread();
        hackbotStationThread = new HackbotStationThread();
        ledThread = new LEDThread();
        
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
//        System.out.println("teleopPeriodic");

        // Start all threads (only once)
        if (!doneAlready)
        {
            //Threads here
            driveThread.start();
            hackbotStationThread.start();
            shooterThread.start();
            spinnySticksThread.start();
            ledThread.start();
            
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
//        System.out.println("disabledPeriodic");

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
        if(ledThread != null)
        {
            ledThread.interrupt();
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
        ledThread = null;

        doneAlready = false;

        hackbotWatch.feed();

    }
}
