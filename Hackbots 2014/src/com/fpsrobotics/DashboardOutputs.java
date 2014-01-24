package com.fpsrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
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
        // Variable outputs to dashboard
        SmartDashboard.putNumber("Right Drive Train Speed", rightJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Left Drive Train Speed", leftJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Threads Currently Running", Thread.activeCount());
        SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
        SmartDashboard.putNumber("Gyro angle", gyroScope.getAngle());
        SmartDashboard.putNumber("Gyro rate", gyroScope.getRate());
        SmartDashboard.putNumber("Potentiometer", shooterPot.getValue());
        SmartDashboard.putBoolean("Button Value", buttonInput.get());
    }

    public void batteryOutput()
    {
        // Battery warnings to the console
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
    }

    public void teamOutput()
    {
        // to make sure our driver station is configured correctly
        if (DriverStation.getInstance().getTeamNumber() != 3414)
        {
            System.out.println("Come on, don't steal our code!");
        }
    }
}
