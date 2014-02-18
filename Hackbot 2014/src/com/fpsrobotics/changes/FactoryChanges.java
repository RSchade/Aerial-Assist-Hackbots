//package com.fpsrobotics;
//
//import com.fpsrobotics.constants.ControlMap;
//import com.fpsrobotics.constants.Joysticks;
//import com.fpsrobotics.constants.ThreadsAndClasses;
//import com.fpsrobotics.constants.Values;     // Don't use what you don't need
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.SpeedController;
//
///**
// * Uses methods from other classes to control the drive train.
// * @author ray
// */
//public class DriveTrain implements Runnable, Joysticks, Values, ControlMap, ThreadsAndClasses
//{
//    
//    boolean isInterrupted = false;
//
//    /**
//     *
//     * Controls the drive train through ControlDrive's methods in a separate
//     * thread.
//     *
//     */
//    public void run() 
//    {   
//        long previousTime = System.currentTimeMillis();
//        isInterrupted = false;
//
//        DriveMotorObject driveMotor = new DriveMotorObject(new SimpleMotor(leftDrive, true), new SimpleMotor(rightDrive, false), new SingleSolenoid(gearSolenoid));
//
//        while (!isInterrupted)
//        {
//            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
//            {
//                // Check if we need to adjust speed, or switch to turbo
////                driveControl.drive(driveControl.deadzoneConstrain(leftJoystick), driveControl.deadzoneConstrain(rightJoystick), leftDrive, rightDrive, true);
////                driveControl.driveTurbo(leftJoystick, rightJoystick, leftDrive, rightDrive, TURBO_BUTTON);
//                
//                driveMotor.set(constrain.deadzoneConstrain(leftJoystick), constrain.deadzoneConstrain(rightJoystick));
//                
////                if (leftJoystick.getRawButton(TURBO_BUTTON))
////                {
////                    driveMotor.set(constrain.constrainDouble(driveControl.deadzoneConstrain(leftJoystick) * 10, 1.0, 0.0), constrain.constrainDouble(driveControl.deadzoneConstrain(rightJoystick) * 10, 1.0, 0.0));
////                }
////                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);
//
//                // Shift if we hold the button
////                pneumatics.switchGears(gearSolenoid, leftJoystick.getRawButton(GEAR_SWITCH_ONE));
//                pneumatics.switchGears(driveMotor, leftJoystick.getRawButton(GEAR_SWITCH_ONE));
//
//                previousTime = System.currentTimeMillis();
//            }
//        }
//    }
//
//    public void interrupt()
//    {
//        isInterrupted = true;
//    }
//}
