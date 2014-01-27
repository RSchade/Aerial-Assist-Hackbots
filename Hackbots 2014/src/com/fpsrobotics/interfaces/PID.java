/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * @author ray
 */
public interface PID
{
    //This must be fully initialized in the constructor, after the settings
    //for the encoders have been done.

    final PIDController leftPIDOne = null;
    final PIDController leftPIDTwo = null;
    final PIDController rightPIDOne = null;
    final PIDController rightPIDTwo = null;
}
