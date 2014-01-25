package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
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
public class ControlDrive implements Values
{
    //This must be fully initialized in the constructor, after the settings
    //for the encoders have been done.

    private final PIDController leftPIDOne = null;
    private final PIDController leftPIDTwo = null;
    private final PIDController rightPIDOne = null;
    private final PIDController rightPIDTwo = null;
    Constrain constrainTurbo = new Constrain();

    /**
     * Run drive train as normal, 1:1 input with joysticks.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param leftDriveOne
     * @param leftDriveTwo
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

    public void initPID(Encoder leftEncoder, Encoder rightEncoder,
            PIDController leftPIDOne, PIDController leftPIDTwo, PIDController rightPIDOne, PIDController rightPIDTwo,
            SpeedController leftDriveOneCont, SpeedController leftDriveTwoCont, SpeedController rightDriveOneCont, SpeedController rightDriveTwoCont)
    {
        //Sets the distance per pulse in inches.
        leftEncoder.setDistancePerPulse(.000623);
        rightEncoder.setDistancePerPulse(.000623);

        //Starts the encoders.
        leftEncoder.start();
        rightEncoder.start();

        //Sets the encoders to use distance for PID.
        //If this is not done, the robot may not go anywhere.
        //It is also possible to use rate, by changing kDistance to kRate.
        leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        //Initializes the PID Controllers
        leftPIDOne = new PIDController(Kp, Ki, Kd, leftEncoder, leftDriveOneCont);
        leftPIDTwo = new PIDController(Kp, Ki, Kd, leftEncoder, leftDriveTwoCont);
        rightPIDOne = new PIDController(Kp, Ki, Kd, rightEncoder, rightDriveOneCont);
        rightPIDTwo = new PIDController(Kp, Ki, Kd, rightEncoder, rightDriveTwoCont);

        //Enables the PID Controllers.
        leftPIDOne.enable();
        leftPIDTwo.enable();
        rightPIDOne.enable();
        rightPIDTwo.enable();

        //Sets the input range of the PID Controller.
        //These will change, and you should change them based on how far
        //your robot will be driving.
        //For this example, we set them at 100 inches.
        leftPIDOne.setInputRange(0, 100);
        leftPIDTwo.setInputRange(0, 100);
        rightPIDOne.setInputRange(0, 100);
        rightPIDTwo.setInputRange(0, 100);
    }

    public void driveToPID(double distance)
    {
        leftPIDOne.setSetpoint(distance);
        leftPIDTwo.setSetpoint(distance);
        rightPIDOne.setSetpoint(distance);
        rightPIDTwo.setSetpoint(distance);
    }
}
