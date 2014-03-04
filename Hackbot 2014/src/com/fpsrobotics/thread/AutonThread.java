///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.fpsrobotics.thread;
//
//import com.fpsrobotics.Autonomous;
//import com.fpsrobotics.Catapult;
//import com.fpsrobotics.LEDs;
//import com.fpsrobotics.SimpleMotor;
//import com.fpsrobotics.SpinnySticks;
//import com.fpsrobotics.TwinMotor;
//import com.fpsrobotics.TwoSolenoids;
//import com.fpsrobotics.VisionProcessingSample;
//import com.fpsrobotics.hardware.Analogs;
//import com.fpsrobotics.hardware.DigitalIOs;
//import com.fpsrobotics.hardware.Motors;
//import com.fpsrobotics.hardware.Solenoids;
//import com.fpsrobotics.preset.Preset;
//import com.fpsrobotics.preset.PresetAuto;
//import com.fpsrobotics.preset.PresetHighGoal;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.camera.AxisCameraException;
//
///**
// *
// * @author Hackbots
// */
//public class AutonThread extends Thread
//{
//    long previousTime;
//    int goodImageCounter = 1;
//    VisionProcessingSample visionSample;
//    boolean isDoneAlready;
//    PIDController leftPID;
//    PIDController rightPID;
//    Autonomous autonomous;
//    Preset presetAuto;
//    Preset presetHighGoal;
//    Catapult shoot;
//    SpinnySticks spinnyStick;
//    LEDs leds;
//    boolean autonDone;
//    
//    public AutonThread()
//    {
//        autonomous = new Autonomous();
//        
//        // Create PID controllers
//        leftPID = autonomous.createPIDController(leftPID, DigitalIOs.LEFT_DRIVE_ENCODER, Motors.LEFT_DRIVE);
//        rightPID = autonomous.createPIDController(rightPID, DigitalIOs.RIGHT_DRIVE_ENCODER, Motors.RIGHT_DRIVE);
//
//        // Create vision sample
//        visionSample = new VisionProcessingSample();
//
//        // Init vision sample
//        visionSample.imageFindInit();
//
//        // Creates the presets used for autonomous shooting
//        presetAuto = new PresetAuto();
//        presetHighGoal = new PresetHighGoal();
//
//        // Creates motors and other things used for autonomous
//        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
//        shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);
//        spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, new TwoSolenoids(Solenoids.SPINNY_SHIFTER));
//        leds = LEDs.createInstance(DigitalIOs.LED_RED, DigitalIOs.LED_GREEN, DigitalIOs.LED_BLUE);
//
//        previousTime = System.currentTimeMillis();
//        
//        isDoneAlready = false;
//    }
//
//    public void run()
//    {
//        System.out.println("autonomousPeriodic");
//
//        while (!isDoneAlready)
//        {
//            // Raise spinny sticks at the beginning of the match
//            spinnyStick.spinnySticksUp();
//
//            try
//            {
//                // Only run in the first five seconds, otherwise run the other code
//                while (System.currentTimeMillis() - previousTime < 4000)
//                {
//                    if (visionSample.autoImageFind())
//                    {
//                        goodImageCounter++;
//
//                        System.out.println("New Image");
//                    }
//
//                    if (goodImageCounter >= 3)
//                    {
//
//                        System.out.println("Shooting Hot");
//
//                        LEDs.getInstance().RedSet(true);
//
//                        // Spinny sticks go down then wait 800 ms for the ball to settle before shooting
//                        spinnyStick.spinnySticksDown();
//                        Timer.delay(0.8);
//
//                        // Shoot
//                        shoot.shoot(presetHighGoal);
//
//                        // Go forward for 2.3 seconds
//                        autonomous.autoSetSetpoint(leftPID, rightPID, 70);
//                        Timer.delay(2.3);
//
//                        // Stop and disable the PID so it doesn't interfere
//                        autonomous.autoSetSetpoint(leftPID, rightPID, 0);
//                        autonomous.autoDisablePID(leftPID, rightPID);
//
//                        LEDs.getInstance().RedSet(false);
//
//                        // Sets the autonomous done flag to true so it doesn't poison teleop
//                        autonDone = true;
//                    }
//                }
//
//                if (!autonDone)
//                {
//                    LEDs.getInstance().GreenSet(true);
//
//                    // Move forward for 2.3 seconds to get into the new zone
//                    autonomous.autoSetSetpoint(leftPID, rightPID, 70);
//                    Timer.delay(2.3);
//
//                    // Stop the drive train, since we moved far enough
//                    autonomous.autoSetSetpoint(leftPID, rightPID, 0);
//                    autonomous.autoDisablePID(leftPID, rightPID);
//
//                    // Make the spinny sticks go down and wait 800 ms for them to settle
//                    spinnyStick.spinnySticksDown();
//                    Timer.delay(0.8);
//
//                    System.out.println("Shooting Not Hot");
//
//                    // Shoot
//                    shoot.shoot(presetAuto);
//
//                    LEDs.getInstance().GreenSet(false);
//                }
//
//            } catch (AxisCameraException ex)
//            {
//                ex.printStackTrace();
//            }
//
//            isDoneAlready = true;
//        }
//    }
//    
//    public void interrupt()
//    {
//        isDoneAlready = true;
//    }
//}
