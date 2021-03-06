package JoshsCode_V_I;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * Image processing later, camera settings now.
 *
 * @author ray
 */
public class RobotCamera
{

    private AxisCamera robotCamera;

    /**
     * Initialize the camera.
     * 
     */
    public void init()
    {
        robotCamera = AxisCamera.getInstance();

        // To be replaced with image processing code
        // Sets axis camera stuff at the beginning of the robot
        robotCamera.writeMaxFPS(15);
        robotCamera.writeCompression(70);
        robotCamera.writeColorLevel(100);
        robotCamera.writeResolution(AxisCamera.ResolutionT.k320x240);
        robotCamera.writeBrightness(50);
    }

}
