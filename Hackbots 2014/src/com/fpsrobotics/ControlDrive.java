package com.fpsrobotics;

import com.fpsrobotics.interfaces.PID;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Class which actually controls the drive train, it's called by the drive train
 * thread in DriveTrain.
 *
 * @author ray
 *
 *
 */
public class ControlDrive implements Values, PID
{
    Constrain constrainTurbo = new Constrain();

    /**
     * Run drive train as normal, 1:1 input with joysticks.
     *
     * @param leftSpeed
     * @param rightSpeed
     * @param leftDrive
     * @param rightDrive
     * @param batteryComp
     */
    public void drive(double leftSpeed, double rightSpeed, Talon leftDrive, Talon rightDrive, boolean batteryComp)
    {
        if (batteryComp)
        {

            leftDrive.set(this.batterySpeed() * (-leftSpeed));
            rightDrive.set(this.batterySpeed() * (rightSpeed));
        } else
        {
            leftDrive.set(-leftSpeed);
            rightDrive.set(rightSpeed);
        }
    }

    /**
     * Turbo the drive train when button one is pressed on either joystick, 1:2
     * input with joysticks ConstrianTurbo class makes any value over 1.0 or
     * below -1.0 become 1.0 and -1.0 respectively. Just to be safe.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param leftDrive
     * @param rightDrive
     */
    public void driveTurbo(Joystick leftJoystick, Joystick rightJoystick, Talon leftDrive, Talon rightDrive)
    {

        while (leftJoystick.getRawButton(1) || rightJoystick.getRawButton(1))
        {
            leftDrive.set(constrainTurbo.constrainDouble(2 * (-leftJoystick.getRawAxis(2)), 1.0, -1.0));
            rightDrive.set(constrainTurbo.constrainDouble(2 * (rightJoystick.getRawAxis(2)), 1.0, -1.0));
        }
    }

    /**
     *
     * Create a number to multiply the input joystick value by to make the robot
     * respond to dropping battery voltage
     *
     * @return
     */
    public double batterySpeed()
    {
        if (DriverStation.getInstance().getBatteryVoltage() >= 12)
        {
            return 1;
        }

        return (12 / DriverStation.getInstance().getBatteryVoltage());

    }
}
