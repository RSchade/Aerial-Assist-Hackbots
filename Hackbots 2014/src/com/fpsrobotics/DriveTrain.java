/**
 *
 * This class controls the drive train of the robot in a different thread. It
 * gives the speed to the motors in a 1:1 ratio, and the left joystick input is
 * changed to negative.
 * 
*/
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks
{
    // Class creates it's own local variables
//    private volatile Talon leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo;
//    private volatile Joystick leftJoystick, rightJoystick;
    
    
    // Class requires talons and joysticks as input variables
//    public DriveTrain()
//            (Talon leftDriveOne, Talon leftDriveTwo, Talon rightDriveOne, Talon rightDriveTwo, 
//            Joystick leftJoystick, Joystick rightJoystick)
//    {
//        //Input variables are copied to local variables
//        leftDriveOne = this.leftDriveOne;
//        leftDriveTwo = this.leftDriveTwo;
//        rightDriveOne = this.rightDriveOne;
//        rightDriveTwo = this.rightDriveTwo;
//        leftJoystick = this.leftJoystick;
//        rightJoystick = this.rightJoystick;
//    }
   

    public void run()
    {
        Constrain constrainTurbo = new Constrain();
            
        while (true)
        {
            // Run the drive train as normal, 1:1 input with joysticks
            leftDriveOne.set(-leftJoystick.getRawAxis(2));
            leftDriveTwo.set(-leftJoystick.getRawAxis(2));
            rightDriveOne.set(rightJoystick.getRawAxis(2));
            rightDriveTwo.set(rightJoystick.getRawAxis(2));

            //Turbo the drive train when button one is pressed on either joystick, 1:2 input with joysticks
            //ConstrianTurbo class makes any value over 1.0 or below -1.0 become 1.0 and -1.0 respectively. Just to be safe.
            while (leftJoystick.getRawButton(1) || rightJoystick.getRawButton(1))
            {
                leftDriveOne.set(constrainTurbo.constrainDouble(2*(-leftJoystick.getRawAxis(2)), 1.0, -1.0));
                leftDriveTwo.set(constrainTurbo.constrainDouble(2*(-leftJoystick.getRawAxis(2)), 1.0, -1.0));
                rightDriveOne.set(constrainTurbo.constrainDouble(2*(rightJoystick.getRawAxis(2)), 1.0, -1.0));
                rightDriveTwo.set(constrainTurbo.constrainDouble(2*(rightJoystick.getRawAxis(2)), 1.0, -1.0));
            }
        }
    }

}
