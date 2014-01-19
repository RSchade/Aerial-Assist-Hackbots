package com.fpsrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * Kinda maybe PID loop that might be able to run a Talon at a specified rate.
 *
 * @author ray
 */
public class PIDLoop implements Runnable
{

    Encoder encoder;
    Talon motor;

    public PIDLoop(Encoder encoder, Talon motor)
    {
        encoder = this.encoder;
        motor = this.motor;
    }

    public void run()
    {
        int target = 100;

        while (true)
        {
            if (encoder.getRate() < target)
            {
                motor.set(motor.get() + 0.001);
            }

            if (encoder.getRate() > target)
            {
                motor.set(motor.get() - 0.001);
            }
        }
    }
}
