package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks
{
    /**
     *
     * Controls the drive train through ControlDrive's methods in a seperate
     * thread.
     *
     */
    public void run()
    {
        ControlDrive driveMotors = new ControlDrive();

        while (true)
        {
            driveMotors.drive(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2), leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, true);
            driveMotors.driveTurbo(leftJoystick, rightJoystick, leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo);

        }
    }
}
