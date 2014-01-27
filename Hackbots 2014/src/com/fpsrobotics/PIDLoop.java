package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Talons;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Kinda maybe PID loop that might be able to run a Talon at a specified rate.
 *
 * @author ray
 */
public class PIDLoop implements Runnable, Talons, Analog
{

    public void run()
    {
        encoder.start();
        while (true)
        {
            try
            {
                this.loop(encoder, leftDriveOne, 200);
                this.loop(encoder, rightDriveOne, 200);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void loop(Encoder encoderThing, Talon motor, int target) throws InterruptedException
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
