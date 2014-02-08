package com.fpsrobotics.interfaces;

/**
 * Where the robot's device port mappings can be easily changed.
 *
 * @author ray
 */
public interface DeviceMap
{

    // Talons
    // Drive Thread
    int LEFT_DRIVE_MAP = 2;
    int RIGHT_DRIVE_MAP = 1;
    // Shooter
    int SHOOTER_TALON_MAP = 4;
    // Spinny Sticks
//    int LEFT_SPIN_MAP = 4;
//    int RIGHT_SPIN_MAP = 5;
    int SPIN_MAP = 3;

    // Relays
    // Spike
    int COMPRESSOR_RELAY_SPIKE_MAP = 1;

    // DIOs
    // Compressor
    int COMPRESSOR_DIO_MAP = 1;
    // Encoders for Drive Train
    int LEFT_DRIVE_ENCODER_MAP_ONE = 2;
    int LEFT_DRIVE_ENCODER_MAP_TWO = 3;
    int RIGHT_DRIVE_ENCODER_MAP_ONE = 4;
    int RIGHT_DRIVE_ENCODER_MAP_TWO = 5;
    // Shooter Encoders
    int SHOOTER_ENCODER_MAP_ONE = 6;// depricated
    int SHOOTER_ENCODER_MAP_TWO = 7;// depricated
    // Ultrasonic Sensor
    int DISTANCE_OUTPUT_MAP = 8;
    int DISTANCE_INPUT_MAP = 9;

    // Analog
    // Potentiometers
    int SHOOTER_POT_MAP = 1;
    // Accelerometer
    int ACCEL_MAP = 2;
    // Gyroscope
    int GYRO_MAP = 3;

    // Breakout Board
    // Spinny Stick Solenoid
    int SPINNY_SOLENOID_MAP_ONE = 1;
    int SPINNY_SOLENOID_MAP_TWO = 2;
    // Gear Shifter Slenoids
    int GEAR_SOLENOID_MAP_ONE = 3;
    int GEAR_SOLENOID_MAP_TWO = 4;

    // USB
    // Joysticks
    int LEFT_JOYSTICK_MAP = 1;
    int RIGHT_JOYSTICK_MAP = 2;
    int GAMEPAD_JOYSTICK_MAP = 3;
}
