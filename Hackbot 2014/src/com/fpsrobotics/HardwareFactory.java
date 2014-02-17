package com.fpsrobotics;

import edu.wpi.first.wpilibj.SpeedController;
import java.util.Hashtable;
import edu.wpi.first.wpilibj.Talon;

public class HardwareFactory
{

    private static Hashtable talons = new Hashtable();

    public static SpeedController createTalon(int port)
    {
        Integer portNum = new Integer(port);
        if (!talons.containsKey(portNum))
        {
            talons.put(portNum, new Talon(port));
        }
        return (SpeedController) talons.get(portNum);
    }
}
