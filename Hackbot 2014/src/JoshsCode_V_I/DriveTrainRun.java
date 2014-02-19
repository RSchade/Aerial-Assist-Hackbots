package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Drives the Robot outside of Dead Zone on Controllers Gear Switching Function
 *
 * Gets called Periodically
 *
 * @author Josh
 */
public class DriveTrainRun implements Constants
{

    // Passing Variables
    private Talon rightMotor;
    private Talon leftMotor;
    private Joystick rightStick;
    private Joystick leftStick;
    private Solenoid motorGearSwitch;
    // Speed
    private double rightSpeed;
    private double leftSpeed;
    private int speedInverse = -1;      // For Left Motor: We inverse one of the speeds because electronically, one motor is upside-down

    public void passVariables(Talon rightMotor, Talon leftMotor, Joystick rightStick, Joystick leftStick, Solenoid motorGearSwitch)
    {
        this.rightMotor = rightMotor;
        this.leftMotor = leftMotor;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        this.motorGearSwitch = motorGearSwitch;
    }

    /**
     * Drives the Robot outside of Dead Zone on Controllers Gear Switching
     * Function
     */
    public void freeDrive()
    {
        this.rightSpeed = rightStick.getRawAxis(VERTICAL_AXIS);
        this.leftSpeed = leftStick.getRawAxis(VERTICAL_AXIS);

        rightMotor.set(this.rightSpeed);
        leftMotor.set(this.leftSpeed * this.speedInverse);
        // Sets speed if joysticks outside of Dead Zone
        if (this.rightSpeed > DEAD_ZONE || this.rightSpeed < -DEAD_ZONE)
        {
            rightMotor.set(this.rightSpeed);
        } else
        {
            rightMotor.set(0);
        }
        if (this.leftSpeed > DEAD_ZONE || this.leftSpeed < -DEAD_ZONE)
        {
            leftMotor.set(this.leftSpeed * this.speedInverse);
        } else
        {
            leftMotor.set(0);
        }

        // Sets Motor Gear Position
        if (rightStick.getRawButton(5) && !motorGearSwitch.get())
        {
            motorGearSwitch.set(true);
        } else if (rightStick.getRawButton(4) && motorGearSwitch.get())
        {
            motorGearSwitch.set(false);
        }
    }

    /**
     * Controlled Drive by User **DOESN'T OVERRIDE REGULAR DRIVE
     *
     **
     * @param setLeft
     * @param setRight
     */
    public void setDrive(double setLeft, double setRight)
    {
        leftMotor.set(setLeft * this.speedInverse);
        rightMotor.set(setRight);
    }
    
    public boolean isDriving() {
        if ((this.leftSpeed > 0.5  || this.leftSpeed < -0.5 )&& (this.rightSpeed > 0.5 || this.rightSpeed < -0.5)) {
            return true;
        } else {
            return false;
        }
    }
}
