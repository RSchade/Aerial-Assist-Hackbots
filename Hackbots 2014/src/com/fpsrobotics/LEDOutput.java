/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import java.util.Random;

/**
 *
 * @author ray
 */
public class LEDOutput implements Runnable, Values
{

    Random rand = new Random();

    public void run()
    {

        

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
        hackbotEnhanced.setLED((byte)(ledNumber), onOff);
    }
}
