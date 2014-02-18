package com.fpsrobotics.preset;

/**
 *
 * @author Josh
 */
public class SonarShot extends Preset
{

    private int potAngle;

    public SonarShot()
    {
        addValue(new PresetValue(_20_PERCENT_THRUST, this.potAngle));
        addValue(new PresetValue(GO_HOME, 80.0));
        addValue(new PresetValue(STOP_SHOOTER, 1.0));

    }

    public void setPotAngle(int potAngle)
    {
        this.potAngle = potAngle;
    }
}
