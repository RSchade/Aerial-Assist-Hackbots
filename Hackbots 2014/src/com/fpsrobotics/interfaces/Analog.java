package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author ray
 */
public interface Analog extends DeviceMap
{

    AnalogChannel breadboardPot = new AnalogChannel(BREADBOARD_POT_MAP);
    
    Gyro gyroScope = new Gyro(GYRO_SCOPE_MAP);
    
    Encoder encoder = new Encoder(ENCODER_MAP_ONE, ENCODER_MAP_TWO);
}
