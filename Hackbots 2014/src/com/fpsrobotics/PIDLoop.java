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
        while (true)
        {
            encoder.start();

            this.loop(encoder, leftDriveOne, 100);
            this.loop(encoder, rightDriveOne, 100);
        }
    }

    private void loop(Encoder encoderThing, Talon motor, int target)
    {

        if (encoderThing.getRate() < target)
        {
            motor.set(motor.get() + 0.001);
        }

        if (encoderThing.getRate() > target)
        {
            motor.set(motor.get() - 0.001);
        }

    }
}
