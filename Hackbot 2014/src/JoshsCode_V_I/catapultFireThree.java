package JoshsCode_V_I;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * Launches the Catapult 3 Functions: 1) Acceleration Launch 2) Regular Launch
 * 3) Resets the Catapult to Home Position
 *
 * @author Josh
 */
public class catapultFireThree implements Constants, Runnable
{
    // Passing Variables
    private Talon shooterMoterLeft;
    private Talon shooterMoterRight;
    private AnalogChannel pot;
    // Max Speed
    private double maxSpeed;
    // Max Distance
    private int highPotValue;
    // Acceleration Speed Percent
    private int stopAccelerationPercent;
    private boolean accelLaunch;
    // RESET VARIABLES
    private int homePotValue = 200;
    private boolean isDone;
    private int speedInverse = -1;      // For Left Motor: We inverse one of the speeds because electronically, one motor is upside-down


    /**
     * ACCELERATION LAUNCH CONSTRUCTOR
     *
     * @param highPotValue
     * @param maxSpeed Make as a value between -1.00 & 1.00
     * @param stopAccelerationPercent Make as a percent between 1 & 100
     */
    public catapultFireThree(int highPotValue, double maxSpeed, int stopAccelerationPercent)
    {
        this.highPotValue = highPotValue;
        this.maxSpeed = maxSpeed;
        this.stopAccelerationPercent = stopAccelerationPercent;
        this.accelLaunch = true;
        isDone = false;
    }

    /**
     * REGULAR LAUNCH CONSTRUCTOR
     *
     * @param highPotValue
     * @param maxSpeed
     */
    public catapultFireThree(int highPotValue, double maxSpeed)
    {
        this.highPotValue = highPotValue;
        this.maxSpeed = maxSpeed;
        this.accelLaunch = false;
        isDone = false;
    }

    public void passVariables(Talon shooterMoterLeft, Talon shooterMoterRight, AnalogChannel pot)
    {
        this.shooterMoterLeft = shooterMoterLeft;
        this.shooterMoterRight = shooterMoterRight;
        this.pot = pot;
    }

    /**
     * Resets the catapult Accelerated/ Regular Launch Resets the catapult.
     */
    public void run()
    {
        this.reset();
        if (this.accelLaunch)
        {
            this.acceleratedLaunchTwo();
        } else
        {
            this.regularLaunch();
        }
        this.reset();
    }

//    /**
//     * Accelerated Launch Function MAKE A PICTURE TO EXPLAIN THIS... :/
//     */
//    public void acceleratedLaunch()
//    {
//        // Accleration Distance Percent Variables
//        // LAUNCH VARIABLES: "Get to MAX SPEED after SOME PERCENT of MAX DISTANCE"
//        double currentSpeed = 0.5;
//        double fullAccelDistance = this.stopAccelerationPercent * this.highPotValue;
//        int currentAccelDistance;
//        int previousAccelDistance = pot.getValue();
//
//        // Restricts the Max Speed
//        if (this.maxSpeed > 1.00)
//        {
//            this.maxSpeed = 1.00;
//        }
//
//        // LAUNCH THE CATAPULT WITH ACCELERATION TO INPUTED SPEED
//        while (pot.getValue() <= this.highPotValue)
//        {
//            currentAccelDistance = pot.getValue();
//            if (currentAccelDistance == (previousAccelDistance + ((fullAccelDistance - previousAccelDistance) / 2)))
//            // MATH showing if distance has increased by half of what it was
//            {
//                currentSpeed += ((maxSpeed - currentSpeed) / 2);
//                // MATH showing for speed to increment by half of what's left
//                // Resulting in speed stabalization as distance approaches stop-accel-pot-distance (-2^(-x))
//                previousAccelDistance = currentAccelDistance;
//            }
//
//            shooterMoterLeft.set(currentSpeed * speedInverse);
//            shooterMoterRight.set(currentSpeed);
//        }
//        // Lets the motors settle
//        shooterMoterLeft.set(0.00);
//        shooterMoterRight.set(0.00);
//        Timer.delay(2.50);
//        this.isDone = true;
//    }
    public void acceleratedLaunchTwo()
    {
        // Accleration Distance Percent Variables
        // LAUNCH VARIABLES: "Get to MAX SPEED after SOME PERCENT of MAX DISTANCE"
        double currentSpeed = 0.5;
        double fullAccelDistance = this.stopAccelerationPercent * this.highPotValue;
        int currentAccelDistance = pot.getValue();
        double scaleFactor = 1.0;
        double percentOfFullSpeed = (currentAccelDistance * scaleFactor / fullAccelDistance);
        // "percentOfFullSpeed" is the percent the currentDistance is of the fullDistance, multiplied by a scale factor

        // Restricts the Max Speed
        if (this.maxSpeed > 1.00)
        {
            this.maxSpeed = 1.00;
        }

        // LAUNCH THE CATAPULT WITH ACCELERATION TO INPUTED SPEED
        while (currentAccelDistance <= this.highPotValue)
        {
            currentAccelDistance = pot.getValue();
            if (percentOfFullSpeed < 1.0)
            {

                percentOfFullSpeed = (currentAccelDistance * scaleFactor / fullAccelDistance);
                // "percentOfFullSpeed" is the percent the currentDistance is of the fullDistance, multiplied by a scale factor
                currentSpeed = (percentOfFullSpeed * maxSpeed);
                // "percentOfFullSpeed" is directly proportional to "maxSpeed"
            }

            shooterMoterLeft.set(currentSpeed * speedInverse);
            shooterMoterRight.set(currentSpeed);
        }
        // Lets the motors settle
        shooterMoterLeft.set(0.00);
        shooterMoterRight.set(0.00);
//        Timer.delay(2.50);
        this.isDone = true;

    }

    /**
     * Regular Launch Function LAUNCH THE CATAPULT TO INPUTED SPEED
     */
    public void regularLaunch()
    {
        while (pot.getValue() <= this.highPotValue)
        {
            shooterMoterLeft.set(this.maxSpeed * speedInverse);
            shooterMoterRight.set(this.maxSpeed);
        }

        // Lets the motors settle
        shooterMoterLeft.set(0.00);
        shooterMoterRight.set(0.00);
//        Timer.delay(2.50);
        this.isDone = true;     // If I put isDone here, will it nullify this instance in "ROBOT CONTROL?"
    }

    /**
     * Calls if done: back to user in "Robot Control"
     *
     * @return
     */
    public boolean done()
    {
        return this.isDone;
    }

    /**
     * Reset Function
     */
    public void reset()
    {
        while (pot.getValue() >= this.homePotValue)
        {
            shooterMoterLeft.set(-0.40 * speedInverse);
            shooterMoterRight.set(-0.40);
        }

        // Lets the motors settle
        shooterMoterLeft.set(0.00);
        shooterMoterRight.set(0.00);
        Timer.delay(1.00);
    }
}