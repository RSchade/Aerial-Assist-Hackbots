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
    Encoder shooterEncoder = new Encoder(SHOOTER_ENCODER_MAP_ONE, SHOOTER_ENCODER_MAP_TWO);
    Encoder encoder = new Encoder(ENCODER_MAP_ONE, ENCODER_MAP_TWO);
    Compressor compressor = new Compressor(COMPRESSOR_DIO_MAP, COMPRESSOR_RELAY_SPIKE_MAP);
}
