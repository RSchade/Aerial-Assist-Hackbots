package com.fpsrobotics;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

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
        AxisCamera.getInstance().writeMaxFPS(15);
        AxisCamera.getInstance().writeCompression(70);
        AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k320x240);

        while (true)
        {
            if (AxisCamera.getInstance().freshImage())
            {
                try
                {
                    AxisCamera.getInstance().getImage();
                } catch (AxisCameraException ex)
                {
                    ex.printStackTrace();
                } catch (NIVisionException ex)
                {
                    ex.printStackTrace();
                }
            } else
            {
                try
                {
                    try
                    {
                        AxisCamera.getInstance().getImage().free();
                    } catch (NIVisionException ex)
                    {
                        ex.printStackTrace();
                    }
                } catch (AxisCameraException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}
