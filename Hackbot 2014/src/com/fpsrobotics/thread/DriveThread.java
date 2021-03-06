package com.fpsrobotics.thread;

import com.fpsrobotics.DriveObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.SingleSolenoid;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;

/**
 * Uses methods from other classes to control the drive train.
 *
 * @author ray
 */
public class DriveThread extends Thread
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

        DriveObject driveMotor = new DriveObject(new SimpleMotor(Motors.LEFT_DRIVE, true), new SimpleMotor(Motors.RIGHT_DRIVE, false), new SingleSolenoid(Solenoids.GEAR_SHIFTER));

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Constants.THREAD_REFRESH_RATE)
            {
                driveMotor.set(ThreadsAndClasses.constrain.deadzoneConstrain(Joysticks.LEFT), ThreadsAndClasses.constrain.deadzoneConstrain(Joysticks.RIGHT));
//                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);

                // Switch if we hold the button
                if (Joysticks.LEFT.getRawButton(JoystickButtons.GEAR_SWITCH_ONE) || Joysticks.RIGHT.getRawButton(JoystickButtons.GEAR_SWITCH_TWO))
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
