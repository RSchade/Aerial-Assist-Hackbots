/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author ray
 */
public class Pneumatics
{

    /**
     * Initialize the compressor.
     *
     * @param compressor
     */
    public void init(Compressor compressor)
    {
        compressor.start();
    }

    /**
     * Stop the compressor
     *
     * @param compressor
     */
    public void stop(Compressor compressor)
    {
        compressor.stop();
    }

    /**
     * Check if the compressor is full. (nonfunctional)
     *
     * @param compressor
     * @return
     */
    public void stopIfFull(Compressor compressor)
    {
        if (compressor.enabled())
        {
            if (!compressor.getPressureSwitchValue())
            {
                compressor.stop();
            }
        }
    }

    /**
     * Move the spinny sticks.
     *
     * @param spinnyStickSolenoid
     * @param forwardBackward
     */
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

    /**
     * Stop the spinny sticks.
     *
     * @param spinnyStickSolenoid
     */
    public void stopSpinnySticksMovement(DoubleSolenoid spinnyStickSolenoid)
    {
        spinnyStickSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    /**
     * Switch gears (sonic shifters).
     *
     * @param gearSwitch
     * @param areWeSwitched
     */
    public void switchGears(Solenoid gearSwitch, boolean areWeSwitched)
    {
        gearSwitch.set(areWeSwitched);
    }
}
