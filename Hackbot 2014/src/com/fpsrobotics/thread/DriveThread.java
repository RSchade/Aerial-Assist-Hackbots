package com.fpsrobotics.thread;

import com.fpsrobotics.DriveObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.SingleSolenoid;
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
public class DriveThread extends Thread implements IsAThread
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

        DriveObject driveMotor = new DriveObject(new SimpleMotor(Talons.leftDrive, true), new SimpleMotor(Talons.rightDrive, false), new SingleSolenoid(Solenoids.gearSolenoid));

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Values.THREAD_REFRESH_RATE)
            {
                driveMotor.set(ThreadsAndClasses.constrain.deadzoneConstrain(Joysticks.leftJoystick), ThreadsAndClasses.constrain.deadzoneConstrain(Joysticks.rightJoystick));
//                driveControl.accelSwitchGears(leftJoystick, rightJoystick, accel);

                // Switch if we hold the button
                if (Joysticks.leftJoystick.getRawButton(ControlMap.GEAR_SWITCH_ONE) || Joysticks.rightJoystick.getRawButton(ControlMap.GEAR_SWITCH_TWO))
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
