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
public class NewThreeFt extends Preset
{

    public NewThreeFt()
    {
        addValue(new PresetValue(_100_PERCENT_THRUST, (DriverStation.getInstance().getBatteryVoltage() > 12.4 ? 570 : this.equation()) + Constants.ALPHA_BETA));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }

    private int equation()
    {
        double x = DriverStation.getInstance().getBatteryVoltage();
        double y = (14.20 * this.power(x, 2)) - (376.9 * x) + 2967;
        return (int) y;
    }

    private double power(double a, int b)
    {
        int index = 1;
        double value = a;
        while (index < b)
        {
            value *= a;
            index++;
        }
        return value;
    }

}

// 255 value voltage 12.4 to 12
// 260 11.8 to 11.4
// 292 11.4
// 304 11.2
