/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Hackbots
 */
public class SingleSolenoid implements AirSolenoid
{

    private final Solenoid solenoid;

    public SingleSolenoid(Solenoid solenoid)
    {
        this.solenoid = solenoid;
    }

    public boolean solenoidGet()
    {
        return solenoid.get();
    }

    public void solenoidOn()
    {
        solenoid.set(true);
    }

    public void solenoidOff()
    {
        solenoid.set(false);
    }
}
