package com.fpsrobotics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Class which actually controls the drive train, it's called by the drive train
 * thread in DriveTrain.
 * @author ray
 *
 *
 */
public class ControlDrive
{

    Constrain constrainTurbo = new Constrain();

    /**
     * Run drive train as normal, 1:1 input with joysticks
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param leftDriveOne
     * @param leftDriveTwo
     * @param rightDriveOne
     * @param rightDriveTwo
     */
    public void drive(Joystick leftJoystick, Joystick rightJoystick, Talon leftDriveOne, Talon leftDriveTwo, Talon rightDriveOne, Talon rightDriveTwo)
    {

        leftDriveOne.set(-leftJoystick.getRawAxis(2));
        leftDriveTwo.set(-leftJoystick.getRawAxis(2));
        rightDriveOne.set(rightJoystick.getRawAxis(2));
        rightDriveTwo.set(rightJoystick.getRawAxis(2));
    }

    /**
     * Turbo the drive train when button one is pressed on either joystick, 1:2
     * input with joysticks ConstrianTurbo class makes any value over 1.0 or
     * below -1.0 become 1.0 and -1.0 respectively. Just to be safe.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param leftDriveOne
     * @param leftDriveTwo
     * @param rightDriveOne
     * @param rightDriveTwo
     */
    public void driveTurbo(Joystick leftJoystick, Joystick rightJoystick, Talon leftDriveOne, Talon leftDriveTwo, Talon rightDriveOne, Talon rightDriveTwo)
    {

        while (leftJoystick.getRawButton(1) || rightJoystick.getRawButton(1))
        {
            leftDriveOne.set(constrainTurbo.constrainDouble(2 * (-leftJoystick.getRawAxis(2)), 1.0, -1.0));
            leftDriveTwo.set(constrainTurbo.constrainDouble(2 * (-leftJoystick.getRawAxis(2)), 1.0, -1.0));
            rightDriveOne.set(constrainTurbo.constrainDouble(2 * (rightJoystick.getRawAxis(2)), 1.0, -1.0));
            rightDriveTwo.set(constrainTurbo.constrainDouble(2 * (rightJoystick.getRawAxis(2)), 1.0, -1.0));
        }
    }
}
