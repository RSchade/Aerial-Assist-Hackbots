package com.fpsrobotics;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Hackbots
 */
public class SpinnySticks extends SimpleMotor
{

    private final Solenoid spinnySolenoid;
    private static SpinnySticks singleton = null;

    private SpinnySticks(SpeedController spinnyMotor, Solenoid spinnySolenoid)
    {
        super(spinnyMotor, false);
        this.spinnySolenoid = spinnySolenoid;
    }

    public static SpinnySticks createInstance(SpeedController spinnyMotor, Solenoid spinnySolenoid)
    {
        if (singleton == null)
        {
            singleton = new SpinnySticks(spinnyMotor, spinnySolenoid);
        }
        
        return singleton;
    }

    public static SpinnySticks getInstance()
    {
        if (singleton == null)
        {
            throw new NullPointerException("Spinny Sticks Instance isn't Defined and is null");
        }

        return singleton;
    }

    public void spinnySticksOut()
    {
        if (!Catapult.getInstance().isFiring())
        {
            spinnySolenoid.set(true);
        }
    }

    public void spinnySticksIn()
    {
        spinnySolenoid.set(false);
    }

    public boolean areSpinnySticksOut()
    {
        return spinnySolenoid.get();
    }
}
