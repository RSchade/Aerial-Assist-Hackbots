package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Hackbots
 */
public class SpinnySticks extends SimpleMotor
{

    private final TwoSolenoids spinnySolenoid;
    private static SpinnySticks singleton = null;

    private SpinnySticks(SpeedController spinnyMotor, TwoSolenoids spinnySolenoid)
    {
        super(spinnyMotor, false);
        this.spinnySolenoid = spinnySolenoid;
    }

    public static SpinnySticks createInstance(SpeedController spinnyMotor, TwoSolenoids spinnySolenoid)
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

    public void spinnySticksUp()
    {
        if (!Catapult.getInstance().isFiring())
        {
            spinnySolenoid.solenoidOn();
        }
    }

    public void spinnySticksDown()
    {
        spinnySolenoid.solenoidOff();
    }

    public boolean areSpinnySticksUp()
    {
        return spinnySolenoid.solenoidGet();
    }
}
