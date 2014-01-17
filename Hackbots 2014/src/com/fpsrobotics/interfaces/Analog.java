/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{
    AnalogChannel shooterPot = new AnalogChannel(SHOOTER_POT_MAP);
}
