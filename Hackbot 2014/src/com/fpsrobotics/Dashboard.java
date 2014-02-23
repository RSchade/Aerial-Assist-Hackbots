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
//    private static double currentSetSpeed;
//    private static double currentLaunchTime;

    public static double getDynamicDistance()
    {
        return dynamicDistance;
    }

    public static double getDynamicSpeed()
    {
        return dynamicSpeed;
    }
    
//    public static double getCurrentSetSpeed()
//    {
//        return currentSetSpeed;
//    }
//    
//    public static double getCurrentLaunchTime()
//    {
//        return currentLaunchTime;
//    }

    public static void setDynamicSpeed(double dynamicSpeed)
    {
        Dashboard.dynamicSpeed = dynamicSpeed;
    }

    public static void setDynamicDistance(double dynamicDistance)
    {
        Dashboard.dynamicDistance = dynamicDistance;
    }
    
//    public static void setCurrentSetSpeed(double currentSetSpeed)
//    {
//        Dashboard.currentSetSpeed = currentSetSpeed;
//    }
//    
//    public static void setCurrentLaunchTime(double currentLaunchTime)
//    {
//        Dashboard.currentLaunchTime = currentLaunchTime;
//    }

}
