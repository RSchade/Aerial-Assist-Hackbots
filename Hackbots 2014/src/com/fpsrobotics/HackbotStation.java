package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Relays;
import com.fpsrobotics.interfaces.Values;

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

        while (true)
        {
            //Everything outputs every second, to reduce lag and heat
            if (System.currentTimeMillis() - previousTime >= THREAD_UPDATE_RATE)
            {
                variableOutputs.batteryOutput();
                variableOutputs.teamOutput();
                variableOutputs.outputToDashboard(leftJoystick, rightJoystick, gyroScope, breadboardPot, robotSwitchInput, encoder);

                previousTime = System.currentTimeMillis();

            }
        }

    }
}
