package com.fpsrobotics.thread;

import com.fpsrobotics.SpinnySticksObject;
import com.fpsrobotics.TwoSolenoids;
import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.Controls;
import com.fpsrobotics.constants.Solenoids;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.Values;

/**
 * Controls the spinny sticks either with an object oriented class or the spinny
 * sticks class.
 *
 * @author ray
 */
public class SpinnySticksThread extends Thread implements IsAThread
{

    SpinnySticksObject spinnyStick = new SpinnySticksObject(Talons.spinnyMotor, new TwoSolenoids(Solenoids.spinnySolenoid));

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

            if (Math.abs(previousTime - System.currentTimeMillis()) >= Values.THREAD_REFRESH_RATE)
            // Switch the order of this -or- Timer.delay(0.20)
            {

                // Check if we need to extend or retract the spinny sticks
                if (Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_EXTEND))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, true);
                    spinnyStick.solenoidOn();
                }

                if (Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_RETRACT))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
                    spinnyStick.solenoidOff();
                }

                if (Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_BACKWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, -0.25);
//                    spinnySimpleMotor.backward(0.25);
                    spinnyStick.forward(-0.35);
                }

                if (Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_FORWARD_TOGGLE))
                {
//                    controlSpinSticks.spinSticks(spinnyMotor, 0.25);
//                    spinnySimpleMotor.forward(0.25);
                    spinnyStick.backward(0.35);
                }

                if (!Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_FORWARD_TOGGLE) && !Controls.gamepadJoystick.getRawButton(ControlMap.SPINNY_BACKWARD_TOGGLE))
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
