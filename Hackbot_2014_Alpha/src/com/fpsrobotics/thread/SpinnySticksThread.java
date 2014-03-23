package com.fpsrobotics.thread;

import com.fpsrobotics.LEDs;
import com.fpsrobotics.SpinnySticks;
import com.fpsrobotics.TwoSolenoids;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Controls the spinny sticks either with an object oriented class or the spinny
 * sticks class.
 *
 * @author ray
 */
public class SpinnySticksThread extends Thread
{

    SpinnySticks spinnyStick = SpinnySticks.createInstance(Motors.SPINNY_MOTOR, Solenoids.SPINNY_SHIFTER);

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
//                    LEDs.getInstance().RedSet(true);
                    spinnyStick.spinnySticksOut();

                    spinnyStick.forward(0.35);
                } else
                {
//                    LEDs.getInstance().RedSet(false);
                    spinnyStick.stop();
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_RETRACT))
                {
//                    pneumatics.spinnySticksMovement(spinnySolenoid, false);
//                    LEDs.getInstance().RedSet(true);
                    spinnyStick.spinnySticksIn();
                }
                
                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_BACKWARD_TOGGLE)) {
//                    LEDs.getInstance().RedSet(true);
                    spinnyStick.backward(-0.35);
                }
//
//                if (!Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_FORWARD_TOGGLE) && !Joysticks.GAMEPAD.getRawButton(JoystickButtons.SPINNY_BACKWARD_TOGGLE))
//                {
////                    controlSpinSticks.spinSticks(spinnyMotor, NO_SPEED);
////                    spinnySimpleMotor.stop();
//                    LEDs.getInstance().RedSet(true);
//                    spinnyStick.stop();
//                }

//                LEDs.getInstance().RedSet(false);

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
