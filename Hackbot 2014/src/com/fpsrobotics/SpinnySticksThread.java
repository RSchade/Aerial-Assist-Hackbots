package com.fpsrobotics;

import com.fpsrobotics.constants.IsAThread; 
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.Joysticks;
import com.fpsrobotics.constants.ThreadsAndClasses;

/**
 * Controls the spinny sticks either with an object oriented class or the spinny sticks class.
 *
 * @author ray
 */
public class SpinnySticksThread extends Thread implements Joysticks, ControlMap, ThreadsAndClasses, IsAThread
{

    boolean isInterrupted = false;

    /**
     * Spinny sticks thread.
     */
    public void run()
    {

        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
//        SimpleMotor spinnySimpleMotor = new SimpleMotor(spinnyMotor, false);
        SpinnySticksObject spinnyStick = new SpinnySticksObject(spinnyMotor, new TwoSolenoids(spinnySolenoid));

        while (!isInterrupted)
        {

            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
                // Switch the order of this -or- Timer.delay(0.20)
            {

                // Check if we need to extend or retract the spinny sticks
                if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, true);
                    spinnyStick.solenoidOn();
                }

                if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
                    spinnyStick.solenoidOff();
                }

                if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, -0.25);
//                    spinnySimpleMotor.backward(0.25);
                    spinnyStick.forward(-0.25);
                }

                if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, 0.25);
//                    spinnySimpleMotor.forward(0.25);
                    spinnyStick.backward(0.25);
                }

                if (!gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE))
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
}
