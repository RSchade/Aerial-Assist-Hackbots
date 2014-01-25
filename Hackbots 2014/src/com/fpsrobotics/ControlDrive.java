package com.fpsrobotics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
public class ControlDrive
{

    Constrain constrainTurbo = new Constrain();

    /**
     * Run drive train as normal, 1:1 input with joysticks.
     *
     * @param leftSpeed
     * @param rightSpeed
     * @param leftDriveOne
     * @param leftDriveTwo
     * @param batteryComp
     * @param rightDriveOne
     * @param rightDriveTwo
     */
    public void drive(double leftSpeed, double rightSpeed, Talon leftDriveOne, Talon leftDriveTwo, Talon rightDriveOne, Talon rightDriveTwo, boolean batteryComp)
    {
        if (batteryComp)
        {

            leftDriveOne.set(this.batterySpeed() * (-leftSpeed));
            leftDriveTwo.set(this.batterySpeed() * (-leftSpeed));
            rightDriveOne.set(this.batterySpeed() * (rightSpeed));
            rightDriveTwo.set(this.batterySpeed() * (rightSpeed));
        } else
        {
            leftDriveOne.set(-leftSpeed);
            leftDriveTwo.set(-leftSpeed);
            rightDriveOne.set(rightSpeed);
            rightDriveTwo.set(rightSpeed);
        }
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

    public void switchGears(DoubleSolenoid gearSwitchOne, DoubleSolenoid gearSwitchTwo, boolean moreLess)
    {
        if (moreLess)
        {
            gearSwitchOne.set(DoubleSolenoid.Value.kForward);
            gearSwitchTwo.set(DoubleSolenoid.Value.kForward);
        }

        if (!moreLess)
        {
            gearSwitchOne.set(DoubleSolenoid.Value.kReverse);
            gearSwitchTwo.set(DoubleSolenoid.Value.kReverse);
        }

    }

}
