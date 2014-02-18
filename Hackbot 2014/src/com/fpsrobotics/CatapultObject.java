package com.fpsrobotics;

import com.fpsrobotics.preset.Preset;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Hackbots
 */
public class CatapultObject
{
    private AnalogChannel shooterPot;
    private TwinMotor shooterMotor;
    private Preset preset;

    public void presetShoot(AnalogChannel pot, Preset preset, TwinMotor shooterMotor)
    {
        this.shooterPot = pot;
        this.preset = preset;
        this.shooterMotor = shooterMotor;
        shoot();
    }

    public void shoot()
    {
        double speed = this.preset.presetSpeed(this.shooterPot.getValue());
        this.shooterMotor.forward(speed);
    }
}
