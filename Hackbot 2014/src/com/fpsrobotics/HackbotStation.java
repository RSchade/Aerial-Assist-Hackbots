package com.fpsrobotics;

import com.fpsrobotics.interfaces.IsAThread;
import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.ThreadsAndClasses;

/**
 * Uses methods from dashboard outputs to control what gets outputted to the smart dashboard.
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, Analog, DIOs, ThreadsAndClasses, IsAThread
{

    boolean isInterrupted = false;

    /**
     *
     * Class which outputs most variables to the SmartDashboard and gives the
     * state of the battery through DashboardOutput's methods.
     *
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;

        while (!isInterrupted)
        {
            //Everything outputs every second, to reduce lag and heat
            if (System.currentTimeMillis() - previousTime >= THREAD_REFRESH_RATE)
            {
                // Output variables to dashboard
                dashboardOutputs.batteryOutput();
                dashboardOutputs.teamOutput();
                dashboardOutputs.outputToDashboard(leftJoystick, rightJoystick, leftDriveEncoder, shooter, spinnySticks, distanceSensor, shooterPot);

                // Reset timer to current time
                previousTime = System.currentTimeMillis();
            }
        }

    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
