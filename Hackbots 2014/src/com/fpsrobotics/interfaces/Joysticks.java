/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ray
 */
public interface Joysticks
{
    // Create all joysticks here
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    Joystick gamepad = new Joystick(3);
}
