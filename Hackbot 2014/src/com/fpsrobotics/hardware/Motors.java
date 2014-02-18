package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Josh
 */
public final class Motors
{
public final static SpeedController LEFT_DRIVE = new Talon(HardwarePorts.LEFT_DRIVE);
public final static SpeedController RIGHT_DRIVE = new Talon(HardwarePorts.RIGHT_DRIVE);
public final static SpeedController SPINNY_MOTOR = new Talon(HardwarePorts.SPIN_STICKS);
public final static SpeedController SHOOTER_ONE = new Talon(HardwarePorts.SHOOTER_ONE);
public final static SpeedController SHOOTER_TWO = new Talon(HardwarePorts.SHOOTER_TWO);
}
