package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Relays;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, Analog, DIOs, Relays, Values
{

    /**
     *
     * Class which outputs most variables to the SmartDashboard and gives the
     * state of the battery through DashboardOutput's methods.
     *
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        DashboardOutputs variableOutputs = new DashboardOutputs();
        LEDOutput ledOutput = new LEDOutput();
        boolean isOn = true;

        while (true)
        {
            //Everything outputs every second, to reduce lag and heat
            if (System.currentTimeMillis() - previousTime >= 1000)
            {
                variableOutputs.batteryOutput();
                variableOutputs.teamOutput();
                variableOutputs.outputToDashboard(leftJoystick, rightJoystick, gyroScope, breadboardPot, robotSwitchInput, encoder);

                try
                {
                    // Pulse led
                    ledOutput.pulseLED(DriverStation.getInstance().getEnhancedIO(), HACKBOT_STATION_LED, Thread.currentThread().isAlive());
                } catch (DriverStationEnhancedIO.EnhancedIOException ex)
                {
                    ex.printStackTrace();
                }

                previousTime = System.currentTimeMillis();
                
                isOn = !isOn;

            }
        }

    }
}
