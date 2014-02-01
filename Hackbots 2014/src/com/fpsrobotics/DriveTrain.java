package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import com.fpsrobotics.interfaces.Values;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks, Values, Analog, Solenoids, ControlMap, ThreadsAndClasses
{

    /**
     *
     * Controls the drive train through ControlDrive's methods in a seperate
     * thread.
     *
     */
    public void run()
    {
        while (true)
        {
            // Check if we need to adjust speed, or switch to turbo
            drivingControl.drive(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2), leftDrive, rightDrive, true);
            drivingControl.driveTurbo(leftJoystick, rightJoystick, leftDrive, rightDrive);

            // Check if we need to switch gears
            if (leftJoystick.getRawButton(GEAR_SWITCH_ONE))
            {
                drivingControl.switchGears(gearSolenoidOne, true);
            }

            if (rightJoystick.getRawButton(GEAR_SWITCH_TWO))
            {
                drivingControl.switchGears(gearSolenoidOne, false);
            }
        }
    }
}
