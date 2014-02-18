package com.fpsrobotics.constants;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Where DIOs are created, so they can be easily changed
 *
 * @author ray
 */
public class DIOs
{

    // DIOs here
    public final static Encoder leftDriveEncoder = new Encoder(DeviceMap.LEFT_DRIVE_ENCODER_MAP_ONE, DeviceMap.LEFT_DRIVE_ENCODER_MAP_TWO);
    public final static Encoder rightDriveEncoder = new Encoder(DeviceMap.RIGHT_DRIVE_ENCODER_MAP_ONE, DeviceMap.RIGHT_DRIVE_ENCODER_MAP_TWO);
    public final static Compressor compressor = new Compressor(DeviceMap.COMPRESSOR_DIO_MAP, DeviceMap.COMPRESSOR_RELAY_SPIKE_MAP);
    public final static AnalogChannel distanceSensor = new AnalogChannel(DeviceMap.DISTANCE_ANALOG_CHANNEL_MAP);

}
