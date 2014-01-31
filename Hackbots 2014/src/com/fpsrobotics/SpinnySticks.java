package com.fpsrobotics;

import com.fpsrobotics.interfaces.ControlMap;
import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Spins the sticks.
 *
 * @author ray
 */
public class SpinnySticks implements Runnable, Joysticks, Talons, Values, Solenoids, ControlMap
{

    public void run()
    {
        boolean spinnySticksForwardOn = false;
        boolean spinnySticksBackwardOn = false;

        while (true)
        {
            if (gamepadJoystick.getRawButton(SPINNY_EXTEND))
            {
                this.spinnySticksMovement(spinnySolenoidOne, true);
            } else
            {
                this.stopSpinnySticksMovement(spinnySolenoidOne);
            }

            if (gamepadJoystick.getRawButton(SPINNY_RETRACT))
            {
                this.spinnySticksMovement(spinnySolenoidOne, false);
            } else
            {
                this.stopSpinnySticksMovement(spinnySolenoidOne);
            }

            if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && !spinnySticksForwardOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, HALF_SPEED);
                spinnySticksForwardOn = !spinnySticksForwardOn;
            }

            if (gamepadJoystick.getRawButton(SPINNY_FORWARD_TOGGLE) && spinnySticksForwardOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, NO_SPEED);
                spinnySticksForwardOn = !spinnySticksForwardOn;
            }

            if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && !spinnySticksBackwardOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, -HALF_SPEED);
                spinnySticksBackwardOn = !spinnySticksBackwardOn;
            }

            if (gamepadJoystick.getRawButton(SPINNY_BACKWARD_TOGGLE) && spinnySticksBackwardOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, NO_SPEED);
                spinnySticksBackwardOn = !spinnySticksBackwardOn;
            }
        }
    }

    public void spinSticks(SpeedController spinnyRightMotor, SpeedController spinnyLeftMotor, double speed)
    {
        spinnyRightMotor.set(speed);
        spinnyLeftMotor.set(speed);
    }

    public void spinnySticksMovement(DoubleSolenoid spinnyStickSolenoid, boolean forwardBackward)
    {
        if (forwardBackward)
        {
            spinnyStickSolenoid.set(DoubleSolenoid.Value.kForward);
        }

        if (!forwardBackward)
        {
            spinnyStickSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void stopSpinnySticksMovement(DoubleSolenoid spinnyStickSolenoid)
    {
        spinnyStickSolenoid.set(DoubleSolenoid.Value.kOff);
    }
}
