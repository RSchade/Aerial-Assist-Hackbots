package com.fpsrobotics.thread;

import com.fpsrobotics.SpinnySticksObject;
import com.fpsrobotics.TwoSolenoids;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;

/**
 * Controls the spinny sticks either with an object oriented class or the spinny
 * sticks class.
 *
 * @author ray
 */
public class SpinnySticksThread extends Thread
{

    SpinnySticksObject spinnyStick = new SpinnySticksObject(Motors.SPINNY_MOTOR, new TwoSolenoids(Solenoids.SPINNY_SHIFTER));

    boolean isInterrupted = false;

    /**
     * Spinny sticks thread.
     */
    public void run()
    {

        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
//        SimpleMotor spinnySimpleMotor = new SimpleMotor(spinnyMotor, false);

        while (!isInterrupted)
        {

            if (Math.abs(previousTime - System.currentTimeMillis()) >= Constants.THREAD_REFRESH_RATE)
            // Switch the order of this -or- Timer.delay(0.20)
            {

                // Check if we need to extend or retract the spinny sticks
                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_EXTEND))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, true);
                    spinnyStick.solenoidOn();
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_RETRACT))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
                    spinnyStick.solenoidOff();
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, -0.25);
//                    spinnySimpleMotor.backward(0.25);
                    spinnyStick.forward(-0.35);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_FORWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, 0.25);
//                    spinnySimpleMotor.forward(0.25);
                    spinnyStick.backward(0.35);
                }

                if (!Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_FORWARD_TOGGLE) && !Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
//                    spinnySimpleMotor.stop();
                    spinnyStick.stop();
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

    public boolean getSpinnySticks()
    {
        return spinnyStick.solenoidGet();
    }
}
