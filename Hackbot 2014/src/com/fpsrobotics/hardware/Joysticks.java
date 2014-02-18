
package com.fpsrobotics.hardware;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Josh
 */
public class Joysticks
{
 public final static Joystick leftJoystick = new Joystick(HardwarePorts.LEFT_JOYSTICK);
 public final static Joystick rightJoystick = new Joystick(HardwarePorts.RIGHT_JOYSTICK);
 public final static Joystick gamepadJoystick = new Joystick(HardwarePorts.GAMEPAD_JOYSTICK);
}
