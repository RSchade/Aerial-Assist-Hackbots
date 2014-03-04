/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import java.util.Random;

/**
 *
 * @author ray
 */
public class LEDs
{

    private final DigitalOutput ledRed;
    private final DigitalOutput ledGreen;
    private final DigitalOutput ledBlue;
    private static LEDs singleton;

    Random rand = new Random();
    int randomNumber;

    private LEDs(DigitalOutput ledRed, DigitalOutput ledGreen, DigitalOutput ledBlue)
    {
        this.ledRed = ledRed;
        this.ledGreen = ledGreen;
        this.ledBlue = ledBlue;
    }

    public static LEDs createInstance(DigitalOutput ledRed, DigitalOutput ledGreen, DigitalOutput ledBlue)
    {
        if (singleton == null)
        {
            singleton = new LEDs(ledRed, ledGreen, ledBlue);
        }

        return singleton;
    }

    public static LEDs getInstance()
    {
        if (singleton == null)
        {
            throw new NullPointerException("LEDs aren't Defined and is null");
        }

        return singleton;
    }

    public void RedSet(boolean onOff)
    {
        ledRed.set(onOff);
    }

    public void GreenSet(boolean onOff)
    {
        ledGreen.set(onOff);
    }

    public void BlueSet(boolean onOff)
    {
        ledBlue.set(onOff);
    }

    public void RedFlash()
    {
        ledRed.set(true);
        Timer.delay(0.2);
        ledRed.set(false);
    }

    public void GreenFlash()
    {
        ledGreen.set(true);
        Timer.delay(0.2);
        ledGreen.set(false);
    }

    public void BlueFlash()
    {
        ledBlue.set(true);
        Timer.delay(0.2);
        ledBlue.set(false);
    }

    public void flashRandomly(long timeMillis)
    {
        long previousTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - previousTime < timeMillis)
        {
            randomNumber = rand.nextInt(2);

            if (randomNumber == 0)
            {
                ledRed.set(rand.nextInt(1) == 1);
            } else if (randomNumber == 1)
            {
                ledBlue.set(rand.nextInt(1) == 1);
            } else if (randomNumber == 2)
            {
                ledGreen.set(rand.nextInt(1) == 1);
            }

            Timer.delay(0.1);
        }
    }
}
