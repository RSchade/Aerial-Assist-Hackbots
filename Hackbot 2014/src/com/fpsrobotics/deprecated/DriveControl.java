package com.fpsrobotics.deprecated;

import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.constants.Values;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * Controls the drive train.
 *
 * @author ray
 *
 *
 */
public class DriveControl implements Values, ThreadsAndClasses
{

//    /**
//     * Run drive train as normal, 1:1 input with joysticks.
//     *
//     * @param leftSpeed
//     * @param rightSpeed
//     * @param leftDrive
//     * @param rightDrive
//     * @param batteryComp
//     */
//    public void drive(double leftSpeed, double rightSpeed, SpeedController leftDrive, SpeedController rightDrive, boolean batteryComp)
//    {
//        leftDrive.set(constrain.constrainDouble(leftSpeed, HIGH_DRIVE_SPEED, LOW_DRIVE_SPEED));
//        rightDrive.set(constrain.constrainDouble(-rightSpeed, HIGH_DRIVE_SPEED, LOW_DRIVE_SPEED));
//    }

    /**
     * Drive to a specified distance
     *
     * @param drivePID
     * @param distance
     */
    public void driveToPID(PIDController drivePID, int distance)
    {
        drivePID.setSetpoint(distance);
    }

    /**
     * Initialize the PID for driving
     *
     * @param drive
     * @param driveEncoder
     * @param lowInput
     * @param highInput
     * @param Kp
     * @param Ki
     * @param Kd
     * @return
     */
    public PIDController initDrivePID(SpeedController drive, Encoder driveEncoder, int lowInput, int highInput, double Kp, double Ki, double Kd)
    {
        PIDController drivePID;

        //Sets the distance per pulse in inches.
        driveEncoder.setDistancePerPulse(.000623);

        //Starts the encoders.
        driveEncoder.start();

        //Sets the encoders to use distance for PID.
        //If this is not done, the robot may not go anywhere.
        //It is also possible to use rate, by changing kDistance to kRate.
        driveEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        //Initializes the PID Controllers
        drivePID = new PIDController(Kp, Ki, Kd, driveEncoder, drive);

        drivePID.enable();

        drivePID.setInputRange(lowInput, highInput);

        return drivePID;
    }

//    /**
//     * Switch gears using accelerometer values, so if we are being rammed or are
//     * ramming we automatically switch into low gear.
//     *
//     * @param leftJoystick
//     * @param rightJoystick
//     * @param accel
//     */
//    public void accelSwitchGears(Joystick leftJoystick, Joystick rightJoystick, Accelerometer accel)
//    {
//        if ((accel.getAcceleration() <= 0.3 || accel.getAcceleration() >= -0.3) && (accel.getAcceleration() <= 0.3 || accel.getAcceleration() >= -0.3))
//        {
//            if ((leftJoystick.getRawAxis(2) >= 0.5 || leftJoystick.getRawAxis(2) <= -0.5) || (rightJoystick.getRawAxis(2) >= 0.5 || rightJoystick.getRawAxis(2) <= -0.5))
//            {
//                pneumatics.switchGears(gearSolenoid, true);
//            }
//        }
//    }
}
