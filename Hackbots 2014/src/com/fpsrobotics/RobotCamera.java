package com.fpsrobotics;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * Image processing later, camera settings now.
 *
 * @author ray
 */
public class RobotCamera implements Runnable
{

    public void run()
    {
        // To be replaced with image processing code

        // Sets axis camera stuff at the beginning of the robot
        AxisCamera.getInstance().writeMaxFPS(20);
        AxisCamera.getInstance().writeCompression(70);
        AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k640x480);
    }
}
