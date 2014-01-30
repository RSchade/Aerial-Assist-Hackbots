package com.fpsrobotics;

import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Talons;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Kinda maybe PID loop that might be able to run a Talon at a specified rate.
 *
 * @author ray
 */
public class FakePIDLoop implements Runnable, Talons, DIOs
{

    public void run()
    {
        encoder.start();
        while (true)
        {
            try
            {
                this.loop(encoder, leftDrive, 200);
                this.loop(encoder, rightDrive, 200);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void loop(Encoder encoderThing, SpeedController motor, int target) throws InterruptedException
    {

        if (encoderThing.getRate() < target)
        {
            motor.set(motor.get() + 0.01);
        }

        if (encoderThing.getRate() > target)
        {
            motor.set(motor.get() - 0.01);
        }

        while (encoderThing.getRate() >= 180 && encoderThing.getRate() <= 220)
        {

        }

        Thread.sleep(500);
    }

    public void setSpeedByOneStep(Encoder encoder, Talon talon, int speed)
    {
        try
        {
            this.loop(encoder, talon, speed);
        } catch (InterruptedException ex)
        {
        }
    }
}
