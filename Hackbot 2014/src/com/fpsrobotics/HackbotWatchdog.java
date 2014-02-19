package com.fpsrobotics;

import edu.wpi.first.wpilibj.Watchdog;

/**
 * Controls the robot's watchdog.
 * 
 * @author ray
 */
public class HackbotWatchdog
{
    Watchdog mrMullen = Watchdog.getInstance();
    /**
     * Feed the watchdog so the robot doesn't die.
     * (Mr. Mullen is our watchdog)
     */
    public void feed()
    {
        mrMullen.feed();
    }

    /**
     * Initialize the robot's watchdog.
     * 
     * @param expire 
     */
    public void watchdogInit(int expire)
    {
        mrMullen.setEnabled(true);
        mrMullen.setExpiration(expire);
    }
}
