/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.preset;

import com.fpsrobotics.constants.Constants;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Matthew
 */
public class NewTwelveFt extends Preset
{

    private final DriverStation battery = DriverStation.getInstance();

    public NewTwelveFt()    {
//        addValue(new PresetValue(_50_PERCENT_THRUST, 250.0));       // Start shot
//        addValue(new PresetValue(_70_PERCENT_THRUST, 400.0));       // accelerate
        //   addValue(new PresetValue(_100_PERCENT_THRUST, 450.0));      // Full thrust
        
        //addValue(new PresetValue(_100_PERCENT_THRUST, 730.0 + Constants.ALPHA_BETA));
//        addValue(new PresetValue(_100_PERCENT_THRUST, 750-((90.0)*(DriverStation.getInstance().getBatteryVoltage()-11.6)) + Constants.ALPHA_BETA));
        
//        addValue(new PresetValue(_100_PERCENT_THRUST, this.getValue() + Constants.ALPHA_BETA));
        addValue(new PresetValue(_100_PERCENT_THRUST, this.getValue()+ Constants.ALPHA_BETA));
        addValue(new PresetValue(GO_HOME, Constants.HOME_POT_VALUE));
        addValue(new PresetValue(STOP_SHOOTER, Constants.HOME_POT_VALUE));
    }

    private int getValue()
    {
        if (battery.getBatteryVoltage() > 10.3 && battery.getBatteryVoltage() <= 10.8)
        {
            return 824;
        } else if (battery.getBatteryVoltage() > 10.8 && battery.getBatteryVoltage() <= 11.2)
        {
            return 785;
        } else if (battery.getBatteryVoltage() > 11.2 && battery.getBatteryVoltage() <= 11.65)
        {
            return 762;
        } else if (battery.getBatteryVoltage() > 11.65 && battery.getBatteryVoltage() <= 11.9)
        {
            return 768;
        } else if (battery.getBatteryVoltage() > 11.9 && battery.getBatteryVoltage() <= 12.1)
        {
            return 714;
        } else if (battery.getBatteryVoltage() > 12.1 && battery.getBatteryVoltage() <= 12.3)
        {
            return 696;
        } else if (battery.getBatteryVoltage() > 12.3 && battery.getBatteryVoltage() <= 12.5)
        {
            return 700;
        } else if (battery.getBatteryVoltage() > 12.5 && battery.getBatteryVoltage() <= 12.6)
        {
            return 678;
        } else if (battery.getBatteryVoltage() > 12.6 && battery.getBatteryVoltage() <= 12.75)
        {
            return 624;
        } else if (battery.getBatteryVoltage() > 12.75 && battery.getBatteryVoltage() <= 13.0)
        {
            return 622;
        } else
        {
            return 622;
        }
    }

    private int equation()
    {
        double x = battery.getBatteryVoltage();
        double y = (2.3012 * this.power(x, 4)) + (131.58 * this.power(x, 3)) + (2695.6 * this.power(x, 2)) - (23844 * x) + 78340;
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
