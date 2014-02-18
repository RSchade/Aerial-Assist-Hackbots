package com.fpsrobotics.thread;

import com.fpsrobotics.DriveObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.SingleSolenoid;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.hardware.Joysticks;
import com.fpsrobotics.hardware.Motors;

/**
 * Uses methods from other classes to control the drive train.
 *
 * @author ray
 */
public class DriveThread implements Runnable, ControlMap, ThreadsAndClasses
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

        DriveObject driveMotor = new DriveObject(new SimpleMotor(Motors.LEFT_DRIVE, true), new SimpleMotor(Motors.RIGHT_DRIVE, false), new SingleSolenoid(gearSolenoid));

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {
                driveMotor.set(constrain.deadzoneConstrain(Joysticks.leftJoystick), constrain.deadzoneConstrain(Joysticks.rightJoystick));
//                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);

                // Switch if we hold the button
                if (Joysticks.leftJoystick.getRawButton(GEAR_SWITCH_ONE) || Joysticks.rightJoystick.getRawButton(GEAR_SWITCH_TWO))
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
