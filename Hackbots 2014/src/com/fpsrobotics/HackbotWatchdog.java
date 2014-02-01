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

    public void feed(Watchdog dog)
    {
        dog.feed();
    }

    public void watchdogInit(Watchdog dog, int expire)
    {
        dog.setEnabled(true);
        dog.setExpiration(expire);
    }

}
