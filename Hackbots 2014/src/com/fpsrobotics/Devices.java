/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpsrobotics;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author ray
 */
public interface Devices
{
    // Create drivetrain here
    Talon leftDriveOne = new Talon(1);
    Talon leftDriveTwo = new Talon(2);
    
    Talon rightDriveOne = new Talon(3);
    Talon rightDriveTwo = new Talon(4);
    
    // Create all other devices (DIO, Talon, Jaguar) here
    
}
