/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public interface Solenoids extends DeviceMap
{
    //Create solenoids here
    DoubleSolenoid exampleSolenoid = new DoubleSolenoid(EXAMPLE_SOLENOID_MAP_ONE, EXAMPLE_SOLENOID_MAP_TWO);
}
