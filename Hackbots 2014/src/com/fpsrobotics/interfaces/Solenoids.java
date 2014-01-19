package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public interface Solenoids extends DeviceMap
{
    //Create solenoids here

    DoubleSolenoid shooterSolenoidOne = new DoubleSolenoid(SHOOTER_SOLENOID_ONE_MAP_ONE, SHOOTER_SOLENOID_ONE_MAP_TWO);
    DoubleSolenoid shooterSolenoidTwo = new DoubleSolenoid(SHOOTER_SOLENOID_TWO_MAP_ONE, SHOOTER_SOLENOID_TWO_MAP_TWO);
}
