package com.fpsrobotics;

import edu.wpi.first.wpilibj.Watchdog;

/**
 * Controls the robot's watchdog.
 * 
 * @author ray
 */
public class HackbotWatchdog
{
    Watchdog dog = Watchdog.getInstance();
    /**
     * Feed the watchdog so the robot doesn't die.
     * 
     * @param dog 
     */
    public void feed()
    {
        dog.feed();
    }

    /**
     * Initialize the robot's watchdog.
     * 
     * @param dog
     * @param expire 
     */
    public void watchdogInit(int expire)
    {
        dog.setEnabled(true);
        dog.setExpiration(expire);
    }
}
