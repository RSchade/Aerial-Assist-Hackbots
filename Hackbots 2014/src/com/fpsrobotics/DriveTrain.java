/**
 *
 * This class controls the drive train of the robot in a different thread. It
 * gives the speed to the motors in a 1:1 ratio, and the left joystick input is
 * changed to negative.
 * 
*/
package com.fpsrobotics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable
{
    // Class creates it's own local variables
    private volatile Talon leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo;
    private volatile Joystick leftJoystick, rightJoystick;
    
    // Class requires talons and joysticks as input variables
    public DriveTrain
            (Talon leftDriveOne, Talon leftDriveTwo, Talon rightDriveOne, Talon rightDriveTwo, 
            Joystick leftJoystick, Joystick rightJoystick)
    {
        //Input variables are copied to local variables
        leftDriveOne = this.leftDriveOne;
        leftDriveTwo = this.leftDriveTwo;
        rightDriveOne = this.rightDriveOne;
        rightDriveTwo = this.rightDriveTwo;
        leftJoystick = this.leftJoystick;
        rightJoystick = this.rightJoystick;
    }
   

    public void run()
    {
        while (true)
        {
            // Run the drive train as normal, 1:1 input with joysticks
            leftDriveOne.set(-leftJoystick.getRawAxis(2));
            leftDriveTwo.set(-leftJoystick.getRawAxis(2));
            rightDriveOne.set(rightJoystick.getRawAxis(2));
            rightDriveTwo.set(rightJoystick.getRawAxis(2));

            //Turbo the drive train when button one is pressed on either joystick, 1:2 input with joysticks
            while (leftJoystick.getRawButton(1) || rightJoystick.getRawButton(1))
            {
                leftDriveOne.set(2*(-leftJoystick.getRawAxis(2)));
                leftDriveTwo.set(2*(-leftJoystick.getRawAxis(2)));
                rightDriveOne.set(2*(rightJoystick.getRawAxis(2)));
                rightDriveTwo.set(2*(rightJoystick.getRawAxis(2)));
            }
        }
    }

}
