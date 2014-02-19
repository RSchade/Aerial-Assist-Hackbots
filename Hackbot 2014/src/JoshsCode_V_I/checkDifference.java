package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Checks how long catapult takes to fire Potentiometer set High-Position Run
 * only if at HOME POSITION
 *
 * @author Josh
 */
public class checkDifference implements Runnable, Constants
{
    // Passing Variables
    private Talon shooterMoterLeft;
    private Talon shooterMoterRight;
    private Joystick rightStick;
    private Joystick leftStick;
    private AnalogChannel pot;
    
    //Checking Variables
    private int maxPotValue;   // Set to Max Value before Hard (Mechanical) Stop
    private double currentTime;
    private double fullBatteryTime = 0;     // Run first with Full Battery
    catapultFireThree launch = new catapultFireThree(maxPotValue, 0.7);
    private boolean stopped = false;
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
        SmartDashboard.putNumber("Time with Full Battery: ", fullBatteryTime);
        launch.reset();
        this.stopped = false;
//        while (!stopped)      // DO NOT RUN UNTIL MAX VARIABLE IS SET!!!
//        {
//            if (rightStick.getRawButton(3))
//            {
//                currentTime = Timer.getFPGATimestamp();
//                // Runs the motors until High Pot-Point Reached
//                shooterMoterLeft.set(0.70 * speedInverse);
//                shooterMoterRight.set(0.70);
//            }
//            SmartDashboard.putNumber("Current Time: ", currentTime);
//            if (pot.getValue() >= this.maxPotValue)
//            {
//                shooterMoterLeft.set(0.00);
//                shooterMoterRight.set(0.00);
//                break;
//            }
//        }
    }
    
    public void stop() {
        this.stopped = true;
    }
}
