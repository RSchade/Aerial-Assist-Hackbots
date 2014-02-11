package com.fpsrobotics;

import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import com.fpsrobotics.interfaces.Values;

/**
 * Spins the sticks.
 *
 * @author ray
 */
public class SpinnySticks implements Runnable, Joysticks, Talons, Values, Solenoids, ControlMap, ThreadsAndClasses
{

    boolean spinnySticksForward = false;
    boolean spinnySticksBackward = false;
    boolean areWeExtended = true;

    /**
     * Spinny sticks thread.
     */
    public void run()
    {

        long previousTime = System.currentTimeMillis();
        boolean previousButtonValueFwd = false;
        boolean previousButtonValueBwd = false;

        while (true)
        {

            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {

                // Check if we need to extend or retract the spinny sticks
                if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, true);

                    areWeExtended = true;
                } 

                if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, false);

                    areWeExtended = false;
                }

                // Check if we need to make the spinny sticks run forward or backward (toggle)
                if (!previousButtonValueFwd)
                {
                    if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !spinnySticksForward)
                    {
                        controlSpinSticks.spinSticks(spinnyMotor, HALF_SPEED);
                        spinnySticksForward = true;
                    } else if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && spinnySticksForward)
                    {
                        controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
                        spinnySticksForward = false;
                    }

                }
                
                previousButtonValueFwd = gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE);

                if (!previousButtonValueBwd)
                {
                    if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && spinnySticksBackward)
                    {
                        controlSpinSticks.spinSticks(spinnyMotor, -HALF_SPEED);
                        spinnySticksBackward = false;
                    } else if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && !spinnySticksBackward)
                    {
                        controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
                        spinnySticksBackward = true;
                    }
                    
                }
                
                previousButtonValueBwd = gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE);
 
                previousTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * Dashboard output.
     * 
     * @return 
     */
    public boolean getAreWeForward()
    {
        return spinnySticksForward;
    }

    /**
     * Dashboard output.
     * 
     * @return 
     */
    public boolean getAreWeBackward()
    {
        return spinnySticksBackward;
    }

    /**
     * Dashboard output.
     * 
     * @return 
     */
    public boolean getAreWeExtended()
    {
        return areWeExtended;
    }
}
