/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics;

/**
 *
 * @author Hackbots
 */
public interface AirSolenoid
{
    public abstract boolean solenoidGet();
    
    public abstract void solenoidOn();
    
    public abstract void solenoidOff();
}
