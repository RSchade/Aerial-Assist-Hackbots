package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public interface Solenoids extends DeviceMap
{
    //Create solenoids here

    DoubleSolenoid spinnySolenoidOne = new DoubleSolenoid(SPINNY_SOLENOID_LEFT_MAP_ONE, SPINNY_SOLENOID_LEFT_MAP_TWO);
    DoubleSolenoid spinnySolenoidTwo = new DoubleSolenoid(SPINNY_SOLENOID_RIGHT_MAP_ONE, SPINNY_SOLENOID_RIGHT_MAP_TWO);
    
    DoubleSolenoid gearSolenoidOne = new DoubleSolenoid(GEAR_SOLENOID_LEFT_MAP_ONE,GEAR_SOLENOID_LEFT_MAP_TWO );
     DoubleSolenoid gearSolenoidTwo = new DoubleSolenoid(GEAR_SOLENOID_RIGHT_MAP_ONE,GEAR_SOLENOID_RIGHT_MAP_TWO );
}
