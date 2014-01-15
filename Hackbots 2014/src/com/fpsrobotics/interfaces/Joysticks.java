/**
 * 
 * Where joysticks can be instantiated, so they can be easily changed
 * 
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ray
 */
public interface Joysticks extends DeviceMap
{
    // Create all joysticks here
    Joystick leftJoystick = new Joystick(LEFT_JOYSTICK_MAP);
    Joystick rightJoystick = new Joystick(RIGHT_JOYSTICK_MAP);
    Joystick gamepadJoystick = new Joystick(GAMEPAD_JOYSTICK_MAP);
}
