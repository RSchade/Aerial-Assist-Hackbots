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

    /**
     * "OUT IS IN," MECHANICALLY.
     * Default Position is currently illegal with this error... :/
     */
    public void spinnySticksIn()
    {
        if (!Catapult.getInstance().isFiring())
        {
            spinnySolenoid.set(false);
        }
    }

    /**
     * "OUT IS IN," MECHANICALLY.
     */
    public void spinnySticksOut()
    {
        spinnySolenoid.set(true);
    }

    /**
     * "OUT IS IN," MECHANICALLY.
     * The '!' is there to make the statement "is out" accurate with the error
     */
    public boolean areSpinnySticksOut()
    {
        return spinnySolenoid.get();
    }
}
