package com.fpsrobotics.constants;

/**
 * Where the robot's device port mappings can be easily changed.
 *
 * @author ray
 */
public class DeviceMap
{

    // Talons
    // Drive Thread
    public final static int LEFT_DRIVE_MAP = 2;
    public final static int RIGHT_DRIVE_MAP = 1;
    // Shooter
    public final static int SHOOTER_TALON_MAP_ONE = 4;
    public final static int SHOOTER_TALON_MAP_TWO = 5;
    // Spinny Sticks
    public final static int SPIN_MAP = 3;

    // Relays
    // Spike
    public final static int COMPRESSOR_RELAY_SPIKE_MAP = 1;

    // DIOs
    // Compressor
    public final static int COMPRESSOR_DIO_MAP = 14;
    // Encoders for Drive Train
    public final static int LEFT_DRIVE_ENCODER_MAP_ONE = 1;
    public final static int LEFT_DRIVE_ENCODER_MAP_TWO = 2;
    public final static int RIGHT_DRIVE_ENCODER_MAP_ONE = 3;
    public final static int RIGHT_DRIVE_ENCODER_MAP_TWO = 4;

    // Analog
    // Potentiometers
    public final static int SHOOTER_POT_MAP = 1;
    // Accelerometer
    public final static int ACCEL_MAP = 4;
    // Gyroscope
    public final static int GYRO_MAP = 5;
    // Ultrasonic Sensor
    public final static int DISTANCE_ANALOG_CHANNEL_MAP = 2;

    // Breakout Board
    // Spinny Stick Solenoid
    public final static int SPINNY_SOLENOID_MAP_ONE = 1;
    public final static int SPINNY_SOLENOID_MAP_TWO = 2;
    // Gear Shifter Slenoids
    public final static int GEAR_SOLENOID_MAP = 3;
    // USB
    // Joysticks
    public final static int LEFT_JOYSTICK_MAP = 1;
    public final static int RIGHT_JOYSTICK_MAP = 2;
    public final static int GAMEPAD_JOYSTICK_MAP = 3;
}
