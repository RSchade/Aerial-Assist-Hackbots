/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.constants.Constants;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Hackbots
 */
public class NewEightFt extends Preset
{

    public NewEightFt()
    {
        addValue(new PresetValue(_100_PERCENT_THRUST, this.equation() + Constants.ALPHA_BETA));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }
    
    private int equation()
    {
        double x = DriverStation.getInstance().getBatteryVoltage();
        double y = (68.294 * this.power(x, 2)) - (1711.9 * x) + 11285;
        return (int) y;
    }
    
    private double power(double a, int b)
    {
        int index = 1;
        double value = a;
        while (index < b)
        {
            value *= a;
            index ++;
        }
        return value;
    }
}
