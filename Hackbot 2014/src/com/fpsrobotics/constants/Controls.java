package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Where joysticks can be instantiated, so they can be easily changed
 *
 * @author ray
 */
public class Controls
{
    // Create all joysticks here

    public final static Joystick leftJoystick = new Joystick(DeviceMap.LEFT_JOYSTICK_MAP);
    public final static Joystick rightJoystick = new Joystick(DeviceMap.RIGHT_JOYSTICK_MAP);
    public final static Joystick gamepadJoystick = new Joystick(DeviceMap.GAMEPAD_JOYSTICK_MAP);
}
