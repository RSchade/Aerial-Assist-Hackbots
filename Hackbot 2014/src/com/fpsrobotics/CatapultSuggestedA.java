package com.fpsrobotics;

import com.fpsrobotics.interfaces.IsAThread;
import com.fpsrobotics.interfaces.Joysticks;

/**
 *
 * @author Josh
 */
public class CatapultSuggestedA implements Runnable, Joysticks, IsAThread
{

    private boolean isInterrupted = false;
    private boolean autonomousThrow = false;
    
    private CatapultSuggestedB catapult = null;
    private Thread catapultThread = null;

    public void run()
    {
        while (!isInterrupted)
        {
            if (catapultThread == null)
            {
                // Different scenarios
                if (gamepadJoystick.getRawButton(3))
                {
                    catapult = new CatapultSuggestedB(3, 3.0, 3);
                    catapultThread = new Thread(catapult);
                    catapultThread.start();
                }
                if (autonomousThrow)
                {
                    catapult = new CatapultSuggestedB(3, 3.0, 3);
                    catapultThread = new Thread(catapult);
                    catapultThread.start();
                    autonomousThrow = false;
                }
            }
            if (catapultThread != null && catapult != null)
            {
                if (catapult.done())
                {
                    catapultThread = null;
                    catapult = null;
                }
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
    
    public void autonomous() {
        this.autonomousThrow = true;
    }
}
