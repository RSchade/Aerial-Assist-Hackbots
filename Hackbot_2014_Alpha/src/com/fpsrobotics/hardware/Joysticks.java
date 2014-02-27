
package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Josh
 */
public class Joysticks
{
 public final static Joystick LEFT = new Joystick(HardwarePorts.LEFT_JOYSTICK);
 public final static Joystick RIGHT = new Joystick(HardwarePorts.RIGHT_JOYSTICK);
 public final static Joystick GAMEPAD = new Joystick(HardwarePorts.GAMEPAD_JOYSTICK);
}
