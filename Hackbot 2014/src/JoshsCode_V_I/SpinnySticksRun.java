package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Spinny Stick Functions: Runs the Motors Sets Position
 *
 * Gets called Periodically
 *
 * @author Josh
 */
public class SpinnySticksRun implements Constants
{
    // Passing Variables

    private Talon spinnyMotor;
    private Joystick rightStick;
    private Joystick leftStick;
    private DoubleSolenoid spinnyPositionSwitch;

    public void passVariables(Talon spinnyMotor, Joystick rightStick, Joystick leftStick, DoubleSolenoid spinnyPositionSwitch)
    {
        this.spinnyMotor = spinnyMotor;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        this.spinnyPositionSwitch = spinnyPositionSwitch;
    }

    public void freeSpin()
    {
        // Sets start/stop for Spinny Sticks
        if (rightStick.getRawButton(6))
        {
            spinnyMotor.set(0.35);
        } else if (rightStick.getRawButton(7))
        {
            spinnyMotor.set(-0.35);
        } else
        {
            spinnyMotor.set(0.0);
        }
    }

    public void freeShift()
    {
        // Sets position for Spinny Sticks
        if (rightStick.getRawButton(11))
        {
            spinnyPositionSwitch.set(DOUBLESOLENOID_FORWARD);
        } else if (rightStick.getRawButton(10))
        {
            spinnyPositionSwitch.set(DOUBLESOLENOID_REVERSE);
        }
    }
    
    public void setShift(boolean shift) {
        if (shift)
        {
            spinnyPositionSwitch.set(DOUBLESOLENOID_FORWARD);
        } else if (!shift)
        {
            spinnyPositionSwitch.set(DOUBLESOLENOID_REVERSE);
        }
    }
    
    public DoubleSolenoid.Value isShifted() {
        return spinnyPositionSwitch.get();
    }
}
