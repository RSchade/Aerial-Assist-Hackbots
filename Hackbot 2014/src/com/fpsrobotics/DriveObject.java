package com.fpsrobotics;

import com.fpsrobotics.hardware.Motors;

/**
 *
 * Controls the drive train in a object oriented manner.
 *
 * @author ray
 */
public class DriveObject implements Motor
{

    private final SimpleMotor leftDrive;
    private final SimpleMotor rightDrive;
    private final SingleSolenoid gearSolenoid;
    private static DriveObject singleton = null;

    public DriveObject(SimpleMotor leftDrive, SimpleMotor rightDrive, SingleSolenoid gearSolenoid)
    {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
        this.gearSolenoid = gearSolenoid;
    }
    public static DriveObject createInstance(SimpleMotor leftMotor, SimpleMotor rightMotor, SingleSolenoid gearSolenoid)
    {
        if (singleton == null)
        {
            singleton = new DriveObject(leftMotor, rightMotor, gearSolenoid);
        }

        return singleton;
    }

    public static DriveObject getInstance()
    {
        if (singleton == null)
        {
            throw new NullPointerException("Catapult Instance isn't Defined and is null");
        }
        
        return singleton;
    }
    
    public void stop()
    {
        leftDrive.stop();
        rightDrive.stop();
    }

    public void backward(double speed)
    {
        leftDrive.backward(speed);
        rightDrive.backward(speed);
    }

    public void forward(double speed, int motorStep)
    {
        leftDrive.forward(speed, motorStep);
        rightDrive.forward(speed, motorStep);
    }

    public void forward(double speed)
    {
        leftDrive.forward(speed);
        rightDrive.forward(speed);
    }

    public void backward(double speed, int motorStep)
    {
        leftDrive.backward(speed, motorStep);
        rightDrive.backward(speed, motorStep);
    }

    public double getSpeed()
    {
        return (leftDrive.getSpeed() + rightDrive.getSpeed()) / 2;
    }

    public void setWithDeadzones(double speedLeft, double speedRight)
    {
        if (speedLeft > 0.1)
        {
            leftDrive.backward(speedLeft);
        } else if (speedLeft < 0.1)
        {
            leftDrive.forward(Math.abs(speedLeft));
        }

        if (speedRight > 0.1)
        {
            rightDrive.backward(speedRight);
        } else if (speedRight < 0.1)
        {
            rightDrive.forward(Math.abs(speedRight));
        }
    }

    public void set(double speedLeft, double speedRight)
    {
        if (speedLeft > 0.0)
        {
            leftDrive.backward(speedLeft);
        } else if (speedLeft < 0.1)
        {
            leftDrive.forward(Math.abs(speedLeft));
        }

        if (speedRight > 0.0)
        {
            rightDrive.backward(speedRight);
        } else if (speedRight < 0.1)
        {
            rightDrive.forward(Math.abs(speedRight));
        }
    }

    public void shift(boolean inOut)
    {
        if (inOut)
        {
            gearSolenoid.solenoidOn();
        } else
        {
            gearSolenoid.solenoidOff();
        }
    }

    public void shift()
    {
        if (gearSolenoid.solenoidGet())
        {
            gearSolenoid.solenoidOn();
        } else
        {
            gearSolenoid.solenoidOff();
        }
    }
}
