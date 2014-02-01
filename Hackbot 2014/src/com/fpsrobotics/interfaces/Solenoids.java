package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public interface Solenoids extends DeviceMap
{
    //Create solenoids here

    DoubleSolenoid spinnySolenoid = new DoubleSolenoid(SPINNY_SOLENOID_MAP_ONE, SPINNY_SOLENOID_MAP_TWO);
    
    DoubleSolenoid gearSolenoid = new DoubleSolenoid(GEAR_SOLENOID_MAP_ONE,GEAR_SOLENOID_MAP_TWO );
}
