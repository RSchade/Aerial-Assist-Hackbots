/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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