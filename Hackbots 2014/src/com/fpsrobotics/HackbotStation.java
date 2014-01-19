/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, Analog
{
//    private volatile Joystick leftJoystick, rightJoystick;
    
//    public HackbotStation(Joystick leftJoystick, Joystick rightJoystick)
//    {
//        leftJoystick = this.leftJoystick;
//        rightJoystick = this.rightJoystick;
//    }

    public void run()
    {
        long previousTime = System.currentTimeMillis();
        
        while (true)
        {
            // Battery info output
            if (System.currentTimeMillis() - previousTime >= 1000)
            {
                if (DriverStation.getInstance().getBatteryVoltage() <= 11)
                {
                    System.out.println("Warning! Battery voltage low, replace soon!");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 10)
                {
                    System.out.println("Danger! Battery voltage very low, replace immediately!");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 9)
                {
                    System.out.println("Battery voltage extremely low! Replace immediately, robot may malfunction");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 8)
                {
                    System.out.println("Battery dead, replace now");
                }
                previousTime = System.currentTimeMillis();
            }
            
            if(DriverStation.getInstance().getTeamNumber() != 3414)
            {
                System.out.println("Come on, don't steal our code!");
            }
            
            // Variable outputs
            SmartDashboard.putNumber("Right Drive Train Speed", rightJoystick.getRawAxis(2));
            SmartDashboard.putNumber("Left Drive Train Speed", leftJoystick.getRawAxis(2));
            SmartDashboard.putNumber("Threads Currently Running",Thread.activeCount());
            SmartDashboard.putNumber("Gyro angle", gyroScope.getAngle());
            SmartDashboard.putNumber("Gyro rate", gyroScope.getRate());
            
        }
        
    }

}
