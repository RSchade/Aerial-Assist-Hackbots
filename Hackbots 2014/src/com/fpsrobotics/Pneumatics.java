/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import com.fpsrobotics.interfaces.DIOs;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author ray
 */
public class Pneumatics
{

    public void init(Compressor compressor)
    {
        compressor.start();
    }

    public void stop(Compressor compressor)
    {
        compressor.stop();
    }

    public boolean isItFull(Compressor compressor)
    {
        if (compressor.enabled())
        {
            // code to sense compressor value maybe?
        }
        
        return false;
    }

    public void spinnySticksMovement(DoubleSolenoid spinnyStickSolenoid, boolean forwardBackward)
    {
        if (forwardBackward)
        {
            spinnyStickSolenoid.set(DoubleSolenoid.Value.kForward);
        }

        if (!forwardBackward)
        {
            spinnyStickSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void stopSpinnySticksMovement(DoubleSolenoid spinnyStickSolenoid)
    {
        spinnyStickSolenoid.set(DoubleSolenoid.Value.kOff);
    }
}
