package com.fpsrobotics.interfaces;

/**
 * Where the robot's device port mappings can be easily changed.
 *
 * @author ray
 */
public interface DeviceMap
{

    // Talons
    int LEFT_DRIVE_MAP = 1;
    int RIGHT_DRIVE_MAP = 2;
    
    int LEFT_SPIN_MAP = 5;
    int RIGHT_SPIN_MAP = 6;
    // Relays
    int ROBOT_RELAY_MAP = 1;
    // DIOs
    int ROBOT_SWITCH_INPUT_MAP = 1;
    // Joysticks
    int LEFT_JOYSTICK_MAP = 1;
    int RIGHT_JOYSTICK_MAP = 2;
    int GAMEPAD_JOYSTICK_MAP = 3;
    // Encoder
    int ENCODER_MAP_ONE = 3;
    int ENCODER_MAP_TWO = 4;
    // Potentiometers
    int BREADBOARD_POT_MAP = 5;
    // Solenoid
    int SHOOTER_SOLENOID_ONE_MAP_ONE = 2;
    int SHOOTER_SOLENOID_ONE_MAP_TWO = 3;
    int SHOOTER_SOLENOID_TWO_MAP_ONE = 4;
    int SHOOTER_SOLENOID_TWO_MAP_TWO = 5;
    // Gyro
    int GYRO_SCOPE_MAP = 2;
}
