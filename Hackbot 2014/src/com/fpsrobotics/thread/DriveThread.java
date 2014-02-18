package com.fpsrobotics;

import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.Joysticks;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.Solenoids;
import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.constants.Values;

/**
 * Uses methods from other classes to control the drive train.
 *
 * @author ray
 */
public class DriveThread implements Runnable, Joysticks, Talons, ControlMap, Solenoids, Values, ThreadsAndClasses, IsAThread
{

    boolean isInterrupted = false;

    /**
     *
     * Controls the drive train through ControlDrive's methods in a separate
     * thread.
     *
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;

        DriveObject driveMotor = new DriveObject(new SimpleMotor(leftDrive, true), new SimpleMotor(rightDrive, false), new SingleSolenoid(gearSolenoid));

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {
                driveMotor.set(constrain.deadzoneConstrain(leftJoystick), constrain.deadzoneConstrain(rightJoystick));
//                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);

                // Switch if we hold the button
                if (leftJoystick.getRawButton(GEAR_SWITCH_ONE) || rightJoystick.getRawButton(GEAR_SWITCH_TWO))
                {
                    driveMotor.shift(true);
                } else
                {
                    driveMotor.shift(false);
                }

                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
