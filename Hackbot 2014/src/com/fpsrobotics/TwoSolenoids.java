/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Hackbots
 */
public class TwoSolenoids implements AirSolenoid
{
    private final DoubleSolenoid solenoid;
    
    public TwoSolenoids(DoubleSolenoid solenoid)
    {
        this.solenoid = solenoid;
    }

    public boolean solenoidGet()
    {
        if (solenoid.get() == DoubleSolenoid.Value.kForward)
        {
            return true;
        }
        
        if (solenoid.get() == DoubleSolenoid.Value.kReverse)
        {
            return false;
        }
        
        return false;
    }

    public void solenoidOn()
    {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void solenoidOff()
    {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void intermediateState()
    {
        solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
}
