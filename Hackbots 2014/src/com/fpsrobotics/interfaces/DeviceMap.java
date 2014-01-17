/**
 * 
 * Where the robot's device port mappings can be easily changed.
 * 
 */

package com.fpsrobotics.interfaces;

/**
 *
 * @author ray
 */
public interface DeviceMap
{
    // Talons
    int LEFT_DRIVE_ONE_MAP = 1;
    int LEFT_DRIVE_TWO_MAP = 2;
    int RIGHT_DRIVE_ONE_MAP = 3;
    int RIGHT_DRIVE_TWO_MAP = 4;
    
    // Relays
    int EXAMPLE_RELAY_MAP = 1;
    
    // DIOs
    int ROBOT_SWITCH_INPUT_MAP = 1;
    
    // Joysticks
    int LEFT_JOYSTICK_MAP = 1;
    int RIGHT_JOYSTICK_MAP = 2;
    int GAMEPAD_JOYSTICK_MAP = 3;
    
    // Encoder
    int ENCODER_ONE_MAP = 1;
    int ENCODER_TWO_MAP = 2;
    
    // Potentiometers
    int SHOOTER_POT_MAP = 1;
    
    // Solenoid
    int EXAMPLE_SOLENOID_MAP_ONE = 1;
    int EXAMPLE_SOLENOID_MAP_TWO = 2;
    
}
