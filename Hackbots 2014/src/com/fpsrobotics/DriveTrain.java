package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks, Values, Analog, Solenoids
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

            driveMotors.drive(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2), leftDrive, rightDrive, true);
            driveMotors.driveTurbo(leftJoystick, rightJoystick, leftDrive, rightDrive);

            if (rightJoystick.getRawButton(7))
            {
                driveMotors.switchGears(gearSolenoidOne, true);
            }

            if (rightJoystick.getRawButton(6))
            {
                driveMotors.switchGears(gearSolenoidOne, false);
            }
        }
    }
}
