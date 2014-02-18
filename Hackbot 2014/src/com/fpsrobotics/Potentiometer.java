/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackbots;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 *
 * @author Matthew
 */
public class Potentiometer
{
    AnalogPotentiometer pot;
    public final short SHOOTER_POT = 1;
    public Potentiometer(short port)
    {
        pot = new AnalogPotentiometer(port);
    }
    public double getPot()
    {
        return pot.get();
    }
    public AnalogPotentiometer getPotObj()
    {
        return pot;
    }
}
