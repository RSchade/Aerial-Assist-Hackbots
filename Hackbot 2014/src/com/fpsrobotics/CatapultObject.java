package com.fpsrobotics;

import com.fpsrobotics.constants.Joysticks;
import com.fpsrobotics.constants.Values;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Hackbots
 */
public class CatapultObject implements Values, Joysticks
{

    private final AnalogChannel shooterPot;
    private final TwinMotor catapult;

    public CatapultObject(TwinMotor catapult, AnalogChannel shooterPot)
    {
        this.shooterPot = shooterPot;
        this.catapult = catapult;
    }

    public int potGetValue()
    {
        return shooterPot.getValue();
    }

    public void regularLaunch(int presetValue, double speed)
    {
        while (shooterPot.getValue() <= presetValue)
        {
            catapult.forward(speed);
        }

        goHome();
    }

    public void acceleratedLaunch(int presetValue, double speed, int stepValue)
    {
        while (shooterPot.getValue() <= presetValue)
        {
            catapult.forward(speed, stepValue);
        }

        goHome();
    }

    public void goHome()
    {
        while (shooterPot.getValue() > HOME_POT_VALUE)
        {
            catapult.backward(SHOOTER_RESET_SPEED);
        }

        while (shooterPot.getValue() < HOME_POT_VALUE)
        {
            catapult.forward(SHOOTER_RESET_SPEED);
        }

        catapult.stop();
    }
}
