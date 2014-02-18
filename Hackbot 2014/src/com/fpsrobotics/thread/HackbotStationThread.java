package com.fpsrobotics.thread;


import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;

/**
 * Uses methods from dashboard outputs to control what gets outputted to the smart dashboard.
 * @author ray
 */
public class HackbotStationThread implements Runnable
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
            if (System.currentTimeMillis() - previousTime >= Constants.THREAD_REFRESH_RATE)
            {
                // Output variables to dashboard
                ThreadsAndClasses.dashboardOutputs.teamOutput();
                ThreadsAndClasses.dashboardOutputs.outputToDashboard(Joysticks.LEFT, Joysticks.RIGHT, DigitalIOs.LEFT_DRIVE_ENCODER, ThreadsAndClasses.catapult, ThreadsAndClasses.spinnySticks, Analogs.DISTANCE_SENSOR, Analogs.SHOOTER_POTENTIOMETER);

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
