package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author ray
 */
public class Solenoids
{
    //Create solenoids here

    public final static DoubleSolenoid spinnySolenoid = new DoubleSolenoid(DeviceMap.SPINNY_SOLENOID_MAP_ONE, DeviceMap.SPINNY_SOLENOID_MAP_TWO);

    public final static Solenoid gearSolenoid = new Solenoid(DeviceMap.GEAR_SOLENOID_MAP);
}
