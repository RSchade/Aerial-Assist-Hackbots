/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import java.util.Random;

/**
 *
 * @author ray
 */
public class LEDOutput implements Runnable
{

    Random rand = new Random();

    public void run()
    {

        DriverStationEnhancedIO enhancedIO = DriverStation.getInstance().getEnhancedIO();

        try
        {
            while (true)
            {
                if (rand.nextInt(1) == 1)
                {
                    enhancedIO.setLED(1, true);
                } else
                {
                    enhancedIO.setLED(1, false);
                }
            }
        } catch (DriverStationEnhancedIO.EnhancedIOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void pulseLED(DriverStationEnhancedIO hackbotEnhanced, int ledNumber, boolean onOff) throws DriverStationEnhancedIO.EnhancedIOException
    {
        hackbotEnhanced.setLED(ledNumber, onOff);
    }
}
