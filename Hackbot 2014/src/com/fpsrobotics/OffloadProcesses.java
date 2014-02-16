package com.fpsrobotics;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import java.io.IOException;

/**
 *
 * @author ray
 */
public class OffloadProcesses
{

    NetworkTable offload;

    /**
     * Offload some processes (undetermined) to another microprocessor, like a beaglebone or arduino.
     * 
     * @return 
     */
    public boolean initOffload()
    {
        try
        {
            NetworkTable.initialize();
            NetworkTable.setTeam(3414);
            NetworkTable.setServerMode();

            offload = NetworkTable.getTable("offloadTable");

            return true;
        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.out.println("Dang, we can't offload non-essential processes!");
            return false;
        }
    }
    
    
}