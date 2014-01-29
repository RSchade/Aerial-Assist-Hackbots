package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Where DIOs are created, so they can be easily changed
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{
    // DIOs here

    DigitalInput robotSwitchInput = new DigitalInput(ROBOT_SWITCH_INPUT_MAP);
    Encoder shooterEncoder = new Encoder(SHOOTER_ENCODER_MAP_ONE, SHOOTER_ENCODER_MAP_TWO);
    Encoder encoder = new Encoder(ENCODER_MAP_ONE, ENCODER_MAP_TWO);
    
    Encoder leftDriveEncoder = new Encoder(LEFT_DRIVE_ENCODER_MAP_ONE, LEFT_DRIVE_ENCODER_MAP_TWO);
    Encoder rightDriveEncoder = new Encoder(RIGHT_DRIVE_ENCODER_MAP_ONE, RIGHT_DRIVE_ENCODER_MAP_TWO);
    
    Compressor compressor = new Compressor(1, 1);
}
