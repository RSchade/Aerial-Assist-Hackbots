/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class ControlSpinSticks
{
    public void spinSticks(SpeedController spinnyRightMotor, SpeedController spinnyLeftMotor, double speed)
    {
        spinnyRightMotor.set(speed);
        spinnyLeftMotor.set(speed);
    }
}
