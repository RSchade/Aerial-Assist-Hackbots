package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

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

    Constrain constrainTurbo = new Constrain();

    /**
     * Run drive train as normal, 1:1 input with joysticks.
     *
     * @param leftSpeed
     * @param rightSpeed
     * @param leftDrive
     * @param rightDrive
     * @param batteryComp
     * @param leftDriveOne
     * @param leftDriveTwo
     * @param batteryComp
     * @param rightDriveOne
     * @param rightDriveTwo
     */
    public void drive(double leftSpeed, double rightSpeed, SpeedController leftDrive, SpeedController rightDrive, boolean batteryComp)
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
    public void driveTurbo(Joystick leftJoystick, Joystick rightJoystick, SpeedController leftDrive, SpeedController rightDrive)
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
    
    //Proportional, Integral, and Dervative constants.
    //These values will need to be tuned for your robot.
    private final double Kp = 0.3;
    private final double Ki = 0.0;
    private final double Kd = 0.0;

    public void driveToPID(PIDController leftDrivePID, PIDController rightDrivePID, int distance)
    {
        leftDrivePID.setSetpoint(distance);
        rightDrivePID.setSetpoint(distance);
    }

    public void initDriveToPID(SpeedController leftDrive, SpeedController rightDrive, Encoder leftDriveEncoder, Encoder rightDriveEncoder, PIDController leftDrivePID, PIDController rightDrivePID)
    {
        //Sets the distance per pulse in inches.
        leftDriveEncoder.setDistancePerPulse(.000623);
        rightDriveEncoder.setDistancePerPulse(.000623);
        
        //Starts the encoders.
        leftDriveEncoder.start();
        rightDriveEncoder.start();
        
        //Sets the encoders to use distance for PID.
        //If this is not done, the robot may not go anywhere.
        //It is also possible to use rate, by changing kDistance to kRate.
        leftDriveEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        rightDriveEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        
        //Initializes the PID Controllers
        leftDrivePID = new PIDController(Kp, Ki, Kd, leftDriveEncoder, leftDrive);
        rightDrivePID = new PIDController(Kp, Ki, Kd, rightDriveEncoder, rightDrive);
        
        leftDrivePID.enable();
        rightDrivePID.enable();
        
        leftDrivePID.setInputRange(-100, 100);
        rightDrivePID.setInputRange(-100, 100);
    }
}
