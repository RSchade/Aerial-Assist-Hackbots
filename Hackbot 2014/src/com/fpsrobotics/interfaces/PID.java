/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.interfaces;

/**
 *
 * @author ray
 */
public interface PID
{

    // PID for shooter
//    double shooterP = 0.3;
//    double shooterI = 0.0;
//    double shooterD = 0.0;

    // high and low setpoint boundaries for shooter
//    int LOW_SHOOTER_PID_VALUE = 0;
//    int HIGH_SHOOTER_PID_VALUE = 100;

    // PID for autonomous
    double autoP = 0.3;
    double autoI = 0.0;
    double autoD = 0.0;

    // high and low setpoint boundaries for autonomous
    int LOW_SETPOINT_PID_AUTO = -100;
    int HIGH_SETPOINT_PID_AUTO = 100;
}
