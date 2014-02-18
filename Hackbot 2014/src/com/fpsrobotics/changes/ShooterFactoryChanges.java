//package com.fpsrobotics;
//
//import com.fpsrobotics.interfaces.ControlMap;
//import com.fpsrobotics.interfaces.DIOs;
//import com.fpsrobotics.interfaces.Joysticks;
//import com.fpsrobotics.interfaces.PID;
//import com.fpsrobotics.interfaces.ThreadsAndClasses;
//import edu.wpi.first.wpilibj.AnalogChannel;
//import edu.wpi.first.wpilibj.SpeedController;
//
///**
// *
// * Controls the shooter, probably will be changed as it becomes more object oriented.
// *
// * @author ray
// */
//public class Shooter implements Runnable, Joysticks, DIOs, ControlMap, PID, ThreadsAndClasses
//{
//
//    double dynamicPresetDistance = 0;
//    double dynamicPresetSpeed = 0;
//    boolean isInterrupted = false;
//
//    boolean areWeShooting;
//
//    /**    presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, 300, SHOOTER_PRESET_ONE, 0.2);
//
//                prese
//     * Thread to control the shooter.
//     */
//    public void run()
//    {
//        SpeedController shooterTalonOne = HardwareFactory.createTalon(SHOOTER_TALON_MAP_ONE);
//        SpeedController shooterTalonTwo = HardwareFactory.createTalon(SHOOTER_TALON_MAP_TWO);
//        AnalogChannel shooterPot = HardwareFactory.createAnalog(SHOOTER_POT_MAP);
//        
//        long previousTime = System.currentTimeMillis();
//        isInterrupted = false;
//        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(shooterTalonOne, false), new SimpleMotor(shooterTalonTwo, true));
//        RobotShooter shooterCatapult = new RobotShooter(shooterTwinMotor, shooterPot);
//        while (!isInterrupted)
//            
//        {
//            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
//            {
//                // Presets (dummy, real presets to be added later)
////                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, 300, SHOOTER_PRESET_ONE, 0.2);
//
//                presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, (int) dynamicPresetDistance, SHOOTER_MANUAL, 0.2);
//
//                dynamicPresetDistance += -gamepadJoystick.getRawAxis(2);
//                dynamicPresetSpeed += gamepadJoystick.getRawAxis(1);
//
//                if (gamepadJoystick.getRawButton(4))
//                {
//                    shooterCatapult.regularLaunch(0.2);
//                }
//
//                if (gamepadJoystick.getRawButton(3))
//                {
//                    shooterCatapult.regularLaunch(200, 0.2);
//                }
//                
//                previousTime = System.currentTimeMillis();
//            }
//        }
//    }
//
//    public double getDynamicPresetDistance()
//    {
//        return dynamicPresetDistance;
//    }
//
//    public double getDynamicPresetSpeed()
//    {
//        return dynamicPresetSpeed;
//    }
//
//    public boolean getAreWeShooting()
//    {
//        return presets.getAreWeShooting();
//    }
//
//    public void interrupt()
//    {
//        isInterrupted = true;
//    }
//
//}
