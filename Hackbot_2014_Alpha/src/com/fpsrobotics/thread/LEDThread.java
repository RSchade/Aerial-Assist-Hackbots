/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.thread;

import com.fpsrobotics.LEDs;
import com.fpsrobotics.hardware.Analogs;
import com.fpsrobotics.hardware.DigitalIOs;

/**
 *
 * @author Hackbots
 */
public class LEDThread extends Thread
{

    boolean isInterrupted = false;

    public void run()
    {
        LEDs.createInstance(DigitalIOs.LED_RED, DigitalIOs.LED_GREEN, DigitalIOs.LED_BLUE);
        
        while (!isInterrupted)
        {
            try
            {
                //            while (Analogs.ULTRA_DISTANCE.getDistanceFt() >= 6 && Analogs.ULTRA_DISTANCE.getDistanceFt() <= 10)
//            {
//                LEDs.getInstance().GreenSet(true);
//                
//                LEDs.getInstance().RedSet(false);
//                LEDs.getInstance().BlueSet(false);
//            }
//            while (Analogs.ULTRA_DISTANCE.getDistanceFt() >= 10 && Analogs.ULTRA_DISTANCE.getDistanceFt() <= 12)
//            {
//                LEDs.getInstance().BlueSet(true);
//                
//                LEDs.getInstance().RedSet(false);
//                LEDs.getInstance().GreenSet(false);
//            }
//            while (Analogs.ULTRA_DISTANCE.getDistanceFt() >= 12)
//            {
//                LEDs.getInstance().RedSet(true);
//                
//                LEDs.getInstance().BlueSet(false);
//                LEDs.getInstance().GreenSet(false);
//            }
//            
//            LEDs.getInstance().RedSet(false);
//            LEDs.getInstance().BlueSet(false);
//            LEDs.getInstance().GreenSet(false);
                
                LEDs.getInstance().GreenSet(true);
                
                Thread.sleep(3000);
                
                LEDs.getInstance().RedSet(false);
                LEDs.getInstance().BlueSet(false);
                LEDs.getInstance().GreenSet(false);
                
                LEDs.getInstance().RedSet(true);
                
                Thread.sleep(4000);
                
                LEDs.getInstance().RedSet(false);
                LEDs.getInstance().BlueSet(false);
                LEDs.getInstance().GreenSet(false);

                LEDs.getInstance().BlueSet(true);

                Thread.sleep(5000);
                
                LEDs.getInstance().RedSet(false);
                LEDs.getInstance().BlueSet(false);
                LEDs.getInstance().GreenSet(false);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;

        LEDs.getInstance().RedSet(false);
        LEDs.getInstance().BlueSet(false);
        LEDs.getInstance().GreenSet(false);
    }
}
