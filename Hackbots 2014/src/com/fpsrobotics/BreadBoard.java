/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.DIOs;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Relays;
import edu.wpi.first.wpilibj.Relay;

/**
 * To run the BreadBoard.
 *
 * @author ray
 */
public class BreadBoard implements Runnable, DIOs, Joysticks, Relays
{

    public void run()
    {
        while (true)
        {
            // Switch turns on the fan
            if (!robotSwitchInput.get())
            {
                robotRelay.set(Relay.Value.kForward);
            } else
            {
                robotRelay.set(Relay.Value.kOff);
            }
        }
    }
}
