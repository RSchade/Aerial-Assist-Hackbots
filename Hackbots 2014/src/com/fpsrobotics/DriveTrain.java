package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
 * @author ray
 */
public class DriveTrain implements Runnable, Talons, Joysticks, Values
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
        LEDOutput ledOutput = new LEDOutput();
        boolean isOn = true;

        while (true)
        {
            driveMotors.drive(leftJoystick.getRawAxis(2), rightJoystick.getRawAxis(2), leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo, true);
            driveMotors.driveTurbo(leftJoystick, rightJoystick, leftDriveOne, leftDriveTwo, rightDriveOne, rightDriveTwo);

            // Pulse LED
            try
            {
                // Pulse led
                ledOutput.pulseLED(DriverStation.getInstance().getEnhancedIO(), HACKBOT_STATION_LED, isOn);
            } catch (DriverStationEnhancedIO.EnhancedIOException ex)
            {
                ex.printStackTrace();
            }
            
            isOn = !isOn;
        }
    }
}
