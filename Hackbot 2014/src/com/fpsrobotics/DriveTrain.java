package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;     // Don't use what you don't need
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.IsAThread;  // Don't use what you don't need
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import com.fpsrobotics.interfaces.Values;     // Don't use what you don't need

/**
 * Uses methods from other classes to control the drive train.
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks, Values, Analog, Solenoids, ControlMap, ThreadsAndClasses, IsAThread
{

    boolean isInterrupted = false;

    /**
     *
     * Controls the drive train through ControlDrive's methods in a separate
     * thread.
     *
     */
    public void run()   // You say in SEVEN classes what can be said in TWO
    {
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;

        DriveMotor driveMotor = new DriveMotor(new SimpleMotor(leftDrive, true), new SimpleMotor(rightDrive, false), gearSolenoid);

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE) // previous time will always be less than current time
                // So: if 50 milliseconds have past...
            {
                // Check if we need to adjust speed, or switch to turbo
//                driveControl.drive(driveControl.deadzoneConstrain(leftJoystick), driveControl.deadzoneConstrain(rightJoystick), leftDrive, rightDrive, true);
//                driveControl.driveTurbo(leftJoystick, rightJoystick, leftDrive, rightDrive, TURBO_BUTTON);
                
                driveMotor.set(driveControl.deadzoneConstrain(leftJoystick), driveControl.deadzoneConstrain(rightJoystick));
                // DeadZoneConstrain makes no sense
                
                if (leftJoystick.getRawButton(TURBO_BUTTON))
                {
                    driveMotor.set(constrain.constrainDouble(driveControl.deadzoneConstrain(leftJoystick) * 10, 1.0, 0.0), constrain.constrainDouble(driveControl.deadzoneConstrain(rightJoystick) * 10, 1.0, 0.0));
                }
//                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);

                // Shift if we hold the button
//                pneumatics.switchGears(gearSolenoid, leftJoystick.getRawButton(GEAR_SWITCH_ONE));
                pneumatics.switchGears(driveMotor, leftJoystick.getRawButton(GEAR_SWITCH_ONE));

                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
