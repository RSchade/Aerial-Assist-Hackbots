/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics;

/**
 *
 * @author Hackbots
 */
public class Dashboard
{

    private static double dynamicDistance;
    private static double dynamicSpeed;

    public static double getDistance()
    {
        return dynamicDistance;
    }

    public static double getSpeed()
    {
        return dynamicSpeed;
    }

    public static void setSpeed(double dynamicSpeed)
    {
        Dashboard.dynamicSpeed = dynamicSpeed;
    }

    public static void setDistance(double dynamicDistance)
    {
        Dashboard.dynamicDistance = dynamicDistance;
    }

}
