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

        while (true)
        {
            // Check if we need to extend or retract the spinny sticks
            if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
            {
                pneumatics.spinnySticksMovement(spinnySolenoidOne, true);
            } else
            {
                pneumatics.stopSpinnySticksMovement(spinnySolenoidOne);
            }

            if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
            {
                pneumatics.spinnySticksMovement(spinnySolenoidOne, false);
            } else
            {
                pneumatics.stopSpinnySticksMovement(spinnySolenoidOne);
            }
            
            // Check if we need to make the spinny sticks run forward or backward
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
        }
    }
}
