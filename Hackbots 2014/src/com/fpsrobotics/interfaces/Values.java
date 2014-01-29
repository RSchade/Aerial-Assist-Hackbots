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
    
    int THREAD_UPDATE_RATE = 100;
    
    // Are we in PID mode?
    boolean SimplePIDMode = true;
    // Are we in breadboard mode
    boolean breadBoardMode = true;
}
