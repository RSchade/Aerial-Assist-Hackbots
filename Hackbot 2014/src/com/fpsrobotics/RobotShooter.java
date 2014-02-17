package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Hackbots
 */
public class RobotShooter implements Values
{

    private final AnalogChannel shooterPot;
    private final TwinMotor catapult;

    public RobotShooter(TwinMotor catapult, AnalogChannel shooterPot)
    {
        this.shooterPot = shooterPot;
        this.catapult = catapult;
    }

    public int potGetValue()
    {
        return shooterPot.getValue();
    }

    public void reset()
    {
        while (shooterPot.getValue() >= HOME_POT_VALUE)
        {
            catapult.backward(0.40);
        }
    }

    public void regularLaunch(double speed)
    {
        while (shooterPot.getValue() <= HIGH_POT_VALUE)
        {
            catapult.forward(speed);
        }
    }

    public void regularLaunch(int presetValue, double speed)
    {
        while (shooterPot.getValue() <= presetValue)
        {
            catapult.forward(speed);
        }
    }

    public void acceleratedLaunch(int presetValue, double speed, int stepValue)
    {
        while (shooterPot.getValue() <= presetValue)
        {
            catapult.forward(speed, stepValue);
        }
    }

    public void acceleratedLaunch(double speed, int stepValue)
    {
        while (shooterPot.getValue() <= HIGH_POT_VALUE)
        {
            catapult.forward(speed, stepValue);
        }
    }
}
