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

    private AxisCamera robotCamera;

    public void run()
    {
        robotCamera = AxisCamera.getInstance();

        // To be replaced with image processing code
        // Sets axis camera stuff at the beginning of the robot
        robotCamera.writeMaxFPS(15);
        robotCamera.writeCompression(70);
        robotCamera.writeRotation(AxisCamera.RotationT.k180);
        robotCamera.writeResolution(AxisCamera.ResolutionT.k320x240);

        while (true)
        {
            if (robotCamera.freshImage())
            {
                try
                {
                    robotCamera.getImage();
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
                    robotCamera.getImage().free();
                } catch (NIVisionException ex)
                {
                    ex.printStackTrace();
                } catch (AxisCameraException ex)
                {
                    ex.printStackTrace();
                }

            }
        }
    }
    
    
}