package com.fpsrobotics;

import com.fpsrobotics.constants.Solenoids;
import com.fpsrobotics.constants.Talons;

/**
 *
 * @author Josh
 */
public class DriveTrainThreadSuggestedB implements Solenoids, Talons
{

    private int speedInverse = -1;      // We inverse one of the speeds because electronically, one motor is upside-down
    
    public void set(double speedLeft, double speedRight)
    {
        if (speedLeft > 1.0)
        {
            leftDrive.set(1.0);
        } else if (speedLeft < -1.0)
        {
            leftDrive.set(-1.0);
        } else
        {
            leftDrive.set(speedLeft);
        }
        if (speedRight > 1.0)
        {
            rightDrive.set(1.0 * speedInverse);
        } else if (speedRight < -1.0)
        {
            rightDrive.set(-1.0 * speedInverse);
        } else
        {
            rightDrive.set(speedLeft * speedInverse);
        }
    }

    public void stop()
    {
        leftDrive.set(0.0);
        rightDrive.set(0.0);
    }

    public void switchGear()
    {
        if (gearSolenoid.get())
        {
            gearSolenoid.set(false);
        } else if (!gearSolenoid.get())
        {
            gearSolenoid.set(true);
        }
    }
}
