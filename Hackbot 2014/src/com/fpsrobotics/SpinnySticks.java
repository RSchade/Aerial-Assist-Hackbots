package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Hackbots
 */
public class SpinnySticks extends SimpleMotor implements AirSolenoid
{

    private final TwoSolenoids spinnySolenoid;

    public SpinnySticks(SpeedController spinnyMotor, TwoSolenoids spinnySolenoid)
    {
        super(spinnyMotor, false);
        this.spinnySolenoid = spinnySolenoid;
    }

    public boolean solenoidGet()
    {
        return spinnySolenoid.solenoidGet();
    }

    public void solenoidOn()
    {
        spinnySolenoid.solenoidOn();
    }

    public void solenoidOff()
    {
        spinnySolenoid.solenoidOff();
    }
}
