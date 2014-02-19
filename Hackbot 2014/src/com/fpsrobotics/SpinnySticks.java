package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Hackbots
 */
public class SpinnySticks extends SimpleMotor
{

    private final TwoSolenoids spinnySolenoid;
    private static SpinnySticks singleton;

    private SpinnySticks(SpeedController spinnyMotor, TwoSolenoids spinnySolenoid)
    {
        super(spinnyMotor, false);
        this.spinnySolenoid = spinnySolenoid;
    }

    public static SpinnySticks createInstance(SpeedController spinnyMotor, TwoSolenoids spinnySolenoid)
    {
        singleton = new SpinnySticks(spinnyMotor, spinnySolenoid);
        return singleton;
    }

    public static SpinnySticks getInstance()
    {
        return singleton;
    }

    public void spinnySticksUp()
    {
        if (Catapult.getInstance().isFiring())
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
