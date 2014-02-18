package com.fpsrobotics.changes;

import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.ThreadsAndClasses;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Josh
 */
public class DriveTrainThreadSuggestedA implements Runnable, ControlMap, ThreadsAndClasses
{
    DriveTrainThreadSuggestedB myDriveControl = new DriveTrainThreadSuggestedB();
    boolean isInterrupted = false;

    public void run()
    {
        while (!isInterrupted)
        {
                myDriveControl.set(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2));
                
                if (leftJoystick.getRawButton(TURBO_BUTTON))
                {
                    myDriveControl.set(leftJoystick.getRawAxis(2) * 2, rightJoystick.getRawAxis(2) * 2);
                }

                // Shift if we press the button
                if (leftJoystick.getRawButton(GEAR_SWITCH_ONE)) {
                    myDriveControl.switchGear();
                }

            Timer.delay(0.20);
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
