package com.fpsrobotics;

import com.fpsrobotics.thread.CatapultThread;
import com.fpsrobotics.thread.SpinnySticksThread;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * Outputs variables and other information to the smart dashboard and printlns.
 * @author ray
 */
public class DashboardOutputs
{
    int compressorCutoff;
//    DriverStationLCD userMessages = DriverStationLCD.getInstance();

    /**
     * Outputs values to SmartDashboard.
     *
     * @param leftJoystick
     * @param rightJoystick
     * @param encoder
     * @param dynamicDistance
     * @param dynamicSpeed
     * @param distanceSensor
     * @param shooterPot
     */
    public void outputToDashboard(Joystick leftJoystick, Joystick rightJoystick, Encoder encoder, double dynamicDistance, double dynamicSpeed, AnalogChannel distanceSensor, AnalogChannel shooterPot)
    {
        // Variable outputs to dashboard
        SmartDashboard.putNumber("Right Drive Train Speed", rightJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Left Drive Train Speed", leftJoystick.getRawAxis(2));
        SmartDashboard.putNumber("Threads Currently Running", Thread.activeCount());
        SmartDashboard.putNumber("Encoder Rate", encoder.getRate());
        SmartDashboard.putNumber("Encoder Value", encoder.getRaw());
        SmartDashboard.putNumber("Dynamic Preset Distance", dynamicDistance);
        SmartDashboard.putNumber("Dynamic Preset Speed", dynamicSpeed);
        SmartDashboard.putNumber("Ultrasonic Distance (ft)", distanceSensor.getVoltage()/.1176);
        SmartDashboard.putNumber("Shooter Pot", shooterPot.getValue());
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
