package com.fpsrobotics.interfaces;

/**
 * Where the robot's device port mappings can be easily changed.
 *
 * @author ray
 */
public interface DeviceMap
{

    // Talons
    int LEFT_DRIVE_ONE_MAP = 1;
    int LEFT_DRIVE_TWO_MAP = 3;
    int RIGHT_DRIVE_ONE_MAP = 2;
    int RIGHT_DRIVE_TWO_MAP = 4;
    int LEFT_SPIN_MAP = 5;
    int RIGHT_SPIN_MAP = 6;
    int ENCODER_MOTOR_MOTOR_MAP = 7;
    // Relays
    int ROBOT_RELAY_MAP = 1;
    // DIOs
    int ROBOT_SWITCH_INPUT_MAP = 1;
    // Joysticks
    int LEFT_JOYSTICK_MAP = 1;
    int RIGHT_JOYSTICK_MAP = 2;
    int GAMEPAD_JOYSTICK_MAP = 3;
    // Encoder
    int ENCODER_MOTOR_MAP_ONE = 1;
    int ENCODER_MOTOR_MAP_TWO = 2;
    // Potentiometers
    int BREADBOARD_POT_MAP = 6;
    // Solenoid
    int SHOOTER_SOLENOID_ONE_MAP_ONE = 5;
    int SHOOTER_SOLENOID_ONE_MAP_TWO = 2;
    int SHOOTER_SOLENOID_TWO_MAP_ONE = 3;
    int SHOOTER_SOLENOID_TWO_MAP_TWO = 4;
    // Gyro
    int GYRO_SCOPE_MAP = 1;
}
