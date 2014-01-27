package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.PID;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks, Values, Analog, PID
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

//        if (AdvancedPIDMode)
//        {
//            driveMotors.initPID(encoder, encoder, leftPIDOne, leftPIDTwo, rightPIDOne, rightPIDTwo, leftDriveOneCont, leftDriveTwoCont, rightDriveOneCont, rightDriveTwoCont);
//        }
//        
        while (true)
        {
            if (!AdvancedPIDMode || !SimplePIDMode)
            {
                driveMotors.drive(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2), leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, true);
                driveMotors.driveTurbo(leftJoystick, rightJoystick, leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo);
            }
            
//            if (AdvancedPIDMode)
//            {
//                System.out.println("Warning, you are in PID Mode!");
//                driveMotors.driveToPID(10, 10);
//            }


        }
    }
}
