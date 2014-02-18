package com.fpsrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.Hashtable;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

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
    
//    private static Hashtable doubleSolenoids = new Hashtable();
//
//    public static DoubleSolenoid createDoubleSolenoid(int portA, int portB)
//    {
//        Integer portNumA = new Integer(portA);
//        Integer portNumB = new Integer(portB);
//        if (!doubleSolenoids.containsKey(portNumA) && !doubleSolenoids.containsKey(portNumB))
//        {
//            doubleSolenoids.put(portNumA, new DoubleSolenoid(portA, portB));
//            doubleSolenoids.put(portNumB, new DoubleSolenoid(portA, portB));
//        }
//        return (DoubleSolenoid) doubleSolenoids.get(portNumA);
//    }

    private static Hashtable solenoids = new Hashtable();

    public static Solenoid createSolenoid(int port)
    {
        Integer portNum = new Integer(port);
        if (!solenoids.containsKey(portNum))
        {
            solenoids.put(portNum, new Solenoid(port));
        }
        return (Solenoid) solenoids.get(portNum);
    }
    
    private static Hashtable analog = new Hashtable();

    public static AnalogChannel createAnalog(int port)
    {
        Integer portNum = new Integer(port);
        if (!analog.containsKey(portNum))
        {
            analog.put(portNum, new AnalogChannel(port));
        }
        return (AnalogChannel) analog.get(portNum);
    }
    
//    private static Hashtable compressor = new Hashtable();
//
//    public static Compressor createCompressor(int port)
//    {
//        Integer portNum = new Integer(port);
//        if (!compressor.containsKey(portNum))
//        {
//            compressor.put(portNum, new Compressor(port));
//        }
//        return (Compressor) compressor.get(portNum);
//    }
}
