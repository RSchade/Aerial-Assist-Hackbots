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

    public void run()
    {
        boolean spinnySticksForwardOn = false;
        boolean spinnySticksBackwardOn = false;

        long previousTime = System.currentTimeMillis();

        while (true)
        {

            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {

                // Check if we need to extend or retract the spinny sticks
                if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, true);
                } else
                {
                    pneumatics.stopSpinnySticksMovement(spinnySolenoid);
                }

                if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
                {
                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
                } else
                {
                    pneumatics.stopSpinnySticksMovement(spinnySolenoid);
                }

                // Check if we need to make the spinny sticks run forward or backward (toggle)
                if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !spinnySticksForwardOn)
                {
                    controlSpinSticks.spinSticks(spinnyRightMotor, spinnyLeftMotor, HALF_SPEED);
                    spinnySticksForwardOn = !spinnySticksForwardOn;
                }

                if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && spinnySticksForwardOn)
                {
                    controlSpinSticks.spinSticks(spinnyRightMotor, spinnyLeftMotor, NO_SPEED);
                    spinnySticksForwardOn = !spinnySticksForwardOn;
                }

                if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && !spinnySticksBackwardOn)
                {
                    controlSpinSticks.spinSticks(spinnyRightMotor, spinnyLeftMotor, -HALF_SPEED);
                    spinnySticksBackwardOn = !spinnySticksBackwardOn;
                }

                if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && spinnySticksBackwardOn)
                {
                    controlSpinSticks.spinSticks(spinnyRightMotor, spinnyLeftMotor, NO_SPEED);
                    spinnySticksBackwardOn = !spinnySticksBackwardOn;
                }

                previousTime = System.currentTimeMillis();
            }
        }
    }
}
