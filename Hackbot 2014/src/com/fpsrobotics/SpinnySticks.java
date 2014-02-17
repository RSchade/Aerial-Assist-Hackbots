package com.fpsrobotics;

import com.fpsrobotics.interfaces.IsAThread;      // Don't use what you don't need
import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;      // Don't use what you don't need
import com.fpsrobotics.interfaces.Talons;         // Don't use what you don't need
import com.fpsrobotics.interfaces.ThreadsAndClasses;
import com.fpsrobotics.interfaces.Values;         // Don't use what you don't need

/**
 * Controls the spinny sticks either with an object oriented class or the spinny sticks class.
 *
 * @author ray
 */
public class SpinnySticks extends Thread implements Joysticks, Talons, Values, Solenoids, ControlMap, ThreadsAndClasses, IsAThread
{

    boolean isInterrupted = false;

    /**
     * Spinny sticks thread.
     */
    public void run()
    {

        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
        SimpleMotor spinnySimpleMotor = new SimpleMotor(spinnyMotor, false);

        while (!isInterrupted)
        {

            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
                // Switch the order of this -or- Timer.delay(0.20)
            {

                // Check if we need to extend or retract the spinny sticks
                if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, true);
                }

                if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
                }

                if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, -0.25);
                    spinnySimpleMotor.backward(0.25);
                }

                if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, 0.25);
                    spinnySimpleMotor.forward(0.25);
                }

                if (!gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
                    spinnySimpleMotor.stop();
                }

//                previousButtonValueBwd = gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE);
                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
