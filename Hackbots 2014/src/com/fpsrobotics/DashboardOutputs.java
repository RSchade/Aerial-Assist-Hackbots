/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author ray
 */
public class DashboardOutputs
{

    /**
     * Outputs values to SmartDashboard.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param gyroScope
     * @param shooterPot
     * @param buttonInput
     */
    public void outputToDashboard(Joystick leftJoystick, Joystick rightJoystick, Gyro gyroScope, AnalogChannel shooterPot, DigitalInput buttonInput)
    {
        // Variable outputs
        SmartDashboard.putNumber("Right Drive Train Speed", rightJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Left Drive Train Speed", leftJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Threads Currently Running", Thread.activeCount());
        SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
        SmartDashboard.putNumber("Gyro angle", gyroScope.getAngle());
        SmartDashboard.putNumber("Gyro rate", gyroScope.getRate());
        SmartDashboard.putNumber("Potentiometer", shooterPot.getValue());
        SmartDashboard.putBoolean("Button Value", buttonInput.get());
        
        
    }
}
