package JoshsCode_V_I;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * For Testing Purposes: Gets the values for Presets
 * 
 * @author Josh
 */
public class getPositions implements Runnable, Constants
{
    // Passing Variables
    private Talon shooterMoterLeft;
    private Talon shooterMoterRight;
    private Joystick rightStick;
    private Joystick leftStick;
    private AnalogChannel pot;
    
    // Pot Variables
    private int highPotValue;           // FIGURE OUT WHICH ONE (Value/ Voltage) IS BETTER SO WE DON'T HAVE TO CODE EVERYTHING TWICE
    private double highPotVoltage;
    private int lowPotValue;
    private double lowPotVoltage;
    private boolean stopped;
    private int speedInverse = -1;      // For Left Motor: We inverse one of the speeds because electronically, one motor is upside-down

    
    public void passVariables(Talon shooterMoterLeft, Talon shooterMoterRight, Joystick rightStick, Joystick leftStick, AnalogChannel pot)
    {
        this.shooterMoterLeft = shooterMoterLeft;
        this.shooterMoterRight = shooterMoterRight;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        this.pot = pot;
    }
    
    public void run()
    {
        this.stopped = false;
        while (!stopped)
        {
            shooterMoterLeft.set(rightStick.getRawAxis(2) * speedInverse);
            shooterMoterRight.set(rightStick.getRawAxis(2));

            // Sets the Highest Pot-Point Desired when Button 2 is pressed  
            if (rightStick.getRawButton(2))
            {
                this.highPotValue = pot.getValue();
                this.highPotVoltage = pot.getVoltage();
                SmartDashboard.putNumber("Pot High Value", highPotValue);
                SmartDashboard.putNumber("Pot High Voltage", highPotVoltage);
            }

            // Sets the Low Pot-Point Desired when Button 3 is pressed
            if (rightStick.getRawButton(3))
            {
                this.lowPotValue = pot.getValue();
                this.lowPotVoltage = pot.getVoltage();
                SmartDashboard.putNumber("Pot Low Value", lowPotValue);
                SmartDashboard.putNumber("Pot Low Voltage", lowPotVoltage);
            }

            // Updates current Potentiometer Value to Smart Dashboard
            this.potentiometerUpdate();
        }
    }

    public void potentiometerUpdate()
    {
        SmartDashboard.putNumber("Current Pot Value", pot.getValue());
        SmartDashboard.putNumber("Current Pot Voltage", pot.getVoltage());
    }
    
    public void stop() {
        this.stopped = true;
    }
}
