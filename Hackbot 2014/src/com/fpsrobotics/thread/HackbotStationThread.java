package com.fpsrobotics.thread;


import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.hardware.Analogs;
import com.fpsrobotics.hardware.DigitalIOs;
import com.fpsrobotics.hardware.Joysticks;

/**
 * Uses methods from dashboard outputs to control what gets outputted to the smart dashboard.
 * @author ray
 */
public class HackbotStationThread implements Runnable, ThreadsAndClasses
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
                dashboardOutputs.teamOutput();
                dashboardOutputs.outputToDashboard(Joysticks.leftJoystick, Joysticks.rightJoystick, DigitalIOs.LEFT_DRIVE_ENCODER, catapult, spinnySticks, distanceSensor, Analogs.SHOOTER_POTENTIOMETER);

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
