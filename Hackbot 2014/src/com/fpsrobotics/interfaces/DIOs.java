package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Where DIOs are created, so they can be easily changed
 *
 * @author ray
 */
public interface DIOs extends DeviceMap
{

    // DIOs here
    Encoder leftDriveEncoder = new Encoder(LEFT_DRIVE_ENCODER_MAP_ONE, LEFT_DRIVE_ENCODER_MAP_TWO);
    Encoder rightDriveEncoder = new Encoder(RIGHT_DRIVE_ENCODER_MAP_ONE, RIGHT_DRIVE_ENCODER_MAP_TWO);
    Compressor compressor = new Compressor(COMPRESSOR_DIO_MAP, COMPRESSOR_RELAY_SPIKE_MAP);
//    AnalogChannel distanceSensor = new AnalogChannel(DISTANCE_ANALOG_CHANNEL_MAP);

}
