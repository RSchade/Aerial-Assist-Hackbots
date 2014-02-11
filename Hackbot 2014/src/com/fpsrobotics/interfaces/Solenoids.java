package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author ray
 */
public interface Solenoids extends DeviceMap
{
    //Create solenoids here

    DoubleSolenoid spinnySolenoid = new DoubleSolenoid(SPINNY_SOLENOID_MAP_ONE, SPINNY_SOLENOID_MAP_TWO);

    Solenoid gearSolenoid = new Solenoid(GEAR_SOLENOID_MAP);
}
