/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Devices;
import com.fpsrobotics.interfaces.Joysticks;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Joysticks, Devices
{

    DriveTrain()
    {
        
    }
    
    public void run()
    {
        while (true)
        {
            leftDriveOne.set(leftJoystick.getRawAxis(2));
            leftDriveTwo.set(leftJoystick.getRawAxis(2));
            rightDriveOne.set(-rightJoystick.getRawAxis(2));
            rightDriveTwo.set(-rightJoystick.getRawAxis(2));
        }
    }

}
