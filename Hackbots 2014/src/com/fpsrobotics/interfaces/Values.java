/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.interfaces;

/**
 *
 * @author Ben
 */
public interface Values
{
    //Motor Speeds

    double MAX_SPEED = 1.0;
    double HALF_SPEED = 0.5;
    double NO_SPEED = 0.0;
    //Put more important reusable values here
    
    
    
    //Proportional, Integral, and Dervative constants.
    //These values will need to be tuned for your robot.
    double Kp = 0.3;
    double Ki = 0.0;
    double Kd = 0.0;
    
    // Are we in PID mode?
    boolean AdvancedPIDMode = false;
    boolean SimplePIDMode = true;
}
