package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Josh
 */
public final class Solenoids
{
public final static DoubleSolenoid SPINNY_SHIFTER = new DoubleSolenoid(HardwarePorts.SPINNY_STICKS_ONE, HardwarePorts.SPINNY_STICKS_TWO);
public final static Solenoid GEAR_SHIFTER = new Solenoid(HardwarePorts.GEAR_SHIFTER);
}
