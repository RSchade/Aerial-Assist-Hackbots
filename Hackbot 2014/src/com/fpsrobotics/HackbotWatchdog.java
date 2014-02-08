package com.fpsrobotics;

import edu.wpi.first.wpilibj.Watchdog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ray
 */
public class HackbotWatchdog
{
    /**
     * Feed the watchdog so the robot doesn't die.
     * 
     * @param dog 
     */
    public void feed(Watchdog dog)
    {
        dog.feed();
    }

    /**
     * Initialize the robot's watchdog.
     * 
     * @param dog
     * @param expire 
     */
    public void watchdogInit(Watchdog dog, int expire)
    {
        dog.setEnabled(true);
        dog.setExpiration(expire);
    }
}
