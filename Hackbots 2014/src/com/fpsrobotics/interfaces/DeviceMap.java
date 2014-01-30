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
    int LEFT_DRIVE_MAP = 1;
    int RIGHT_DRIVE_MAP = 2;
    // Shooter
    int SHOOTER_TALON_MAP = 3;
    // Spinny Sticks
    int LEFT_SPIN_MAP = 4;
    int RIGHT_SPIN_MAP = 5;
    
    // Relays
    // Spike
    int COMPRESSOR_RELAY_SPIKE_MAP = 1;
    
    // DIOs
    // Compressor
    int COMPRESSOR_DIO_MAP = 1;
    // Encoder for Drive Train
    int ENCODER_MAP_ONE = 2;
    int ENCODER_MAP_TWO = 3;
    // Shooter Encoders
    int SHOOTER_ENCODER_MAP_ONE = 4;// depricated
    int SHOOTER_ENCODER_MAP_TWO = 5;// depricated
    
    // Analog
    // Potentiometers
    int SHOOTER_POT_MAP = 1;
    
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
