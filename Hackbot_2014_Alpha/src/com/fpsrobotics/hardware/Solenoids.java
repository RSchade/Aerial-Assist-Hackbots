package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Josh
 */
public final class Solenoids
{

    public final static Solenoid SPINNY_SHIFTER = new Solenoid(HardwarePorts.SPINNY_STICKS_ONE);
    public final static Solenoid GEAR_SHIFTER = new Solenoid(HardwarePorts.GEAR_SHIFTER);
}
