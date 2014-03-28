package com.fpsrobotics;

import com.fpsrobotics.constants.Constants;
import com.fpsrobotics.hardware.Analogs;
import com.fpsrobotics.thread.CatapultThread;
import com.fpsrobotics.thread.SpinnySticksThread;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * Outputs variables and other information to the smart dashboard and printlns.
 *
 * @author ray
 */
public class DashboardOutputs
{
//    DriverStationLCD userMessages = DriverStationLCD.getInstance();

    /**
     * Outputs values to SmartDashboard.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param encoder
     * @param dynamicDistance
     * @param dynamicSpeed
     * @param ultraDistance
     */
    public void outputToDashboard(Joystick leftJoystick, Joystick rightJoystick, Encoder encoder, double dynamicDistance, double dynamicSpeed, Ultrasonic ultraDistance, AnalogChannel shooterPot, AnalogChannel gyro)
    {
        // Variable outputs to dashboard
        SmartDashboard.putNumber("Right Drive Train Speed", rightJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Left Drive Train Speed", leftJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Threads Currently Running", Thread.activeCount());
        SmartDashboard.putNumber("Left Encoder Count", encoder.get());
        SmartDashboard.putNumber("Left Encoder Raw Count", encoder.getRaw());
        SmartDashboard.putNumber("Left Encoder Distance", encoder.getDistance());
        SmartDashboard.putNumber("Left Encoder Value", encoder.getRate());
        SmartDashboard.putNumber("Dynamic Preset Distance", dynamicDistance + 225 + Constants.ALPHA_BETA);
        SmartDashboard.putNumber("Dynamic Preset Speed", dynamicSpeed);
        SmartDashboard.putNumber("Ultrasonic Distance (ft)", ultraDistance.getDistanceFt());
        SmartDashboard.putNumber("Shooter Pot", shooterPot.getAverageValue());
        SmartDashboard.putNumber("Shooter Pot Average", shooterPot.getAverageValue());
        SmartDashboard.putNumber("Gyro Value", gyro.getValue());

        if (DriverStation.getInstance().getBatteryVoltage() <= 11.4)
        {
            SmartDashboard.putBoolean("Can You Shoot?", false);
        } else
        {
            SmartDashboard.putBoolean("Can You Shoot?", true);
        }
        
        if (Constants.ALPHA_BETA == -225)
        {
            SmartDashboard.putBoolean("BETA BOT ACTIVATED", true);
            SmartDashboard.putBoolean("ALPHA BOT ACTIVATED", false);
        } else
        {
            SmartDashboard.putBoolean("ALPHA BOT ACTIVATED", true);
            SmartDashboard.putBoolean("BETA BOT ACTIVATED", false);
        }

        if (ultraDistance.getDistanceFt() >= 11 && ultraDistance.getDistanceFt() <= 13)
        {
            SmartDashboard.putBoolean("12 FEET IN RANGE", true);
        } else
        {
            SmartDashboard.putBoolean("12 FEET IN RANGE", false);
        }
        if (ultraDistance.getDistanceFt() >= 7 && ultraDistance.getDistanceFt() <= 9)
        {
            SmartDashboard.putBoolean("8 FEET IN RANGE", true);
        } else
        {
            SmartDashboard.putBoolean("8 FEET IN RANGE", false);
        }
        if (ultraDistance.getDistanceFt() >= 2 && ultraDistance.getDistanceFt() <= 4)
        {
            SmartDashboard.putBoolean("3 FEET IN RANGE", true);
        } else
        {
            SmartDashboard.putBoolean("3 FEET IN RANGE", false);
        }

//        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
//        SmartDashboard.putNumber("Temperature Gyro", temp.getValue());
    }

    public void teamOutput()
    {
        // to make sure our driver station is configured correctly (team)
        if (DriverStation.getInstance().getTeamNumber() != 3414)
        {
            System.out.println("Come on, don't steal our code!");
        }
    }

//    public void driverComments() {
//        userMessages.println(DriverStationLCD.Line.kUser1, 1, "Hackbots Aerial Assist Code");
//    }
}
