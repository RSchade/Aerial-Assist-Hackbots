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
    int SHOOTER_TALON_MAP_ONE = 4;
    int SHOOTER_TALON_MAP_TWO = 5;
    // Spinny Sticks
    int SPIN_MAP = 3;

    // Relays
    // Spike
    int COMPRESSOR_RELAY_SPIKE_MAP = 1;

    // DIOs
    // Compressor
    int COMPRESSOR_DIO_MAP = 14;
    // Encoders for Drive Train
    int LEFT_DRIVE_ENCODER_MAP_ONE = 1;
    int LEFT_DRIVE_ENCODER_MAP_TWO = 2;
    int RIGHT_DRIVE_ENCODER_MAP_ONE = 3;
    int RIGHT_DRIVE_ENCODER_MAP_TWO = 4;

    // Analog
    // Potentiometers
    int SHOOTER_POT_MAP = 1;
    // Accelerometer
    int ACCEL_MAP = 2;
    // Gyroscope
    int GYRO_MAP = 3;
    // Ultrasonic Sensor
    int DISTANCE_ANALOG_CHANNEL_MAP = 2;

    // Breakout Board
    // Spinny Stick Solenoid
    int SPINNY_SOLENOID_MAP_ONE = 1;
    int SPINNY_SOLENOID_MAP_TWO = 2;
    // Gear Shifter Slenoids
    int GEAR_SOLENOID_MAP = 3;
    // USB
    // Joysticks
    int LEFT_JOYSTICK_MAP = 1;
    int RIGHT_JOYSTICK_MAP = 2;
    int GAMEPAD_JOYSTICK_MAP = 3;
}
