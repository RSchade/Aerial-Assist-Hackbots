/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.constants;

/**
 *
 * @author Ben
 */
public interface Values
{
    //Motor Speeds

    double FULL_SPEED = 1.0;
    double HALF_SPEED = 0.5;
    double NO_SPEED = 0.0;
    //Put more important reusable values here
    
    double HIGH_DRIVE_SPEED = 1.0;
    double LOW_DRIVE_SPEED = -1.0;
    
    int THREAD_REFRESH_RATE = 50;
    
    int HOME_POT_VALUE = 152;
    
    int HIGH_POT_VALUE = 900;
    
    double SHOOTER_MAX_SPEED = 1.0;
    double SHOOTER_MIN_SPEED = 0.2;
    double SHOOTER_RESET_SPEED = 0.2;
}