package com.fpsrobotics;

import com.fpsrobotics.interfaces.Joysticks;
import com.fpsrobotics.interfaces.Solenoids;
import com.fpsrobotics.interfaces.Talons;
import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * Spins the sticks.
 *
 * @author ray
 */
public class SpinnySticks implements Runnable, Joysticks, Talons, Values, Solenoids
{
    public void run()
    {
        boolean spinnySticksOn = false;

        while (true)
        {
            if (leftJoystick.getRawButton(7))
            {
                this.spinnySticksMovement(spinnySolenoidOne, spinnySolenoidTwo, true);
            } else
            {
                this.stopSpinnySticksMovement(spinnySolenoidOne, spinnySolenoidTwo);
            }

            if (leftJoystick.getRawButton(6))
            {
                this.spinnySticksMovement(spinnySolenoidOne, spinnySolenoidTwo, false);
            } else
            {
                this.stopSpinnySticksMovement(spinnySolenoidOne, spinnySolenoidTwo);
            }

            if (leftJoystick.getRawButton(2) && !spinnySticksOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, HALF_SPEED);
                spinnySticksOn = !spinnySticksOn;
            }

            if (leftJoystick.getRawButton(2) && spinnySticksOn)
            {
                this.spinSticks(spinnyRightMotor, spinnyLeftMotor, NO_SPEED);
                spinnySticksOn = !spinnySticksOn;
            }
        }
    }

    public void spinSticks(Talon spinnyRightMotor, Talon spinnyLeftMotor, double speed)
    {
        spinnyRightMotor.set(speed);
        spinnyLeftMotor.set(speed);
    }

    public void spinnySticksMovement(DoubleSolenoid spinnyStickSolenoidOne, DoubleSolenoid spinnyStickSolenoidTwo, boolean forwardBackward)
    {
        if (forwardBackward)
        {
            spinnyStickSolenoidOne.set(DoubleSolenoid.Value.kForward);
            spinnyStickSolenoidTwo.set(DoubleSolenoid.Value.kForward);
        }

        if (!forwardBackward)
        {
            spinnyStickSolenoidOne.set(DoubleSolenoid.Value.kReverse);
            spinnyStickSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void stopSpinnySticksMovement(DoubleSolenoid spinnyStickSolenoidOne, DoubleSolenoid spinnyStickSolenoidTwo)
    {
        spinnyStickSolenoidOne.set(DoubleSolenoid.Value.kOff);
        spinnyStickSolenoidTwo.set(DoubleSolenoid.Value.kOff);
    }
}
