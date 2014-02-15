package com.fpsrobotics;

import com.fpsrobotics.interfaces.IsAThread;
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
public class SpinnySticks extends Thread implements Joysticks, Talons, Values, Solenoids, ControlMap, ThreadsAndClasses, IsAThread
{

    boolean areWeExtended = true;
    boolean isInterrupted = false;

    /**
     * Spinny sticks thread.
     */
    public void run()
    {

        long previousTime = System.currentTimeMillis();
//        boolean previousButtonValueFwd = false;
//        boolean previousButtonValueBwd = false;
//        boolean spinnySticksForward = false;
//        boolean spinnySticksBackward = false;
        isInterrupted = false;

        while (!isInterrupted)
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
                
                if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
                {
                    controlSpinSticks.spinSticks(spinnyMotor, -0.25);
                }

                if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE))
                {
                    controlSpinSticks.spinSticks(spinnyMotor, 0.25);
                }

                if (!gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
                {
                    controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
                }

//                previousButtonValueBwd = gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE);
                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
//        controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
    }
}
