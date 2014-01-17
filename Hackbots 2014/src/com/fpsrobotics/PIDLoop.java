/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author ray
 */
public class PIDLoop implements Runnable
{
    Encoder encoder;
    Talon motor;
    
    public PIDLoop(Encoder encoder, Talon motor)
    {
        encoder = this.encoder;
        motor= this.motor;
    }

    public void run()
    {
        int target = 100;
        
        while(true)
        {
            if(encoder.getRate() < target)
            {
                motor.set(motor.get()+0.001);
            }
            
            if (encoder.getRate() > target)
            {
                motor.set(motor.get()-0.001);
            }
        }
    }

}
