package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Relays;

/**
 *
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, Analog, DIOs, Relays
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

        while (true)
        {
            //Everything outputs every second, to reduce lag and heat
            if (System.currentTimeMillis() - previousTime >= 1000)
            {
                variableOutputs.batteryOutput();
                variableOutputs.teamOutput();
                variableOutputs.outputToDashboard(leftJoystick, rightJoystick, gyroScope, breadboardPot, robotSwitchInput);

                previousTime = System.currentTimeMillis();

            }
        }

    }
}
