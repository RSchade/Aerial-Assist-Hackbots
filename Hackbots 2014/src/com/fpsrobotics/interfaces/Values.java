/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.interfaces;

import com.fpsrobotics.LEDOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
 * @author Ben
 */
public interface Values
{
    //Motor Speeds
    double MAX_SPEED = 1.0;
    double HALF_SPEED = 0.5;
    double NO_SPEED = 0.0;
    //Put more important reusable values here
    
    int MAIN_THREAD_LED = (byte)(2);
    int DRIVE_TRAIN_LED = (byte)(3);
    int PISTON_SHOOTER_LED = (byte)(4);
    int SPINNY_STICKS_LED = (byte)(5);
    int HACKBOT_STATION_LED = (byte)(6);
    
    DriverStationEnhancedIO enhancedIO = DriverStation.getInstance().getEnhancedIO();
    LEDOutput ledOutput = new LEDOutput();
    
}
