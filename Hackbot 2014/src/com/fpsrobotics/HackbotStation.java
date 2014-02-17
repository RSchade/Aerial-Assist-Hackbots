package com.fpsrobotics;

import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * Uses methods from dashboard outputs to control what gets outputted to the smart dashboard.
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, DIOs, ThreadsAndClasses
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
        AnalogChannel shooterPot = HardwareFactory.createAnalog(SHOOTER_POT_MAP);
        AnalogChannel distanceSensor = HardwareFactory.createAnalog(DISTANCE_ANALOG_CHANNEL_MAP);
        
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
