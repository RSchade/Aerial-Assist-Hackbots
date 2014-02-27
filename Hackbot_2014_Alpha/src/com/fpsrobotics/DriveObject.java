package com.fpsrobotics;

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

    public DriveObject(SimpleMotor leftDrive, SimpleMotor rightDrive, SingleSolenoid gearSolenoid)
    {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
        this.gearSolenoid = gearSolenoid;
    }

    public void stop()
    {
        leftDrive.stop();
        rightDrive.stop();
    }

    public void forward(double speed)
    {
        leftDrive.forward(speed);
        rightDrive.forward(speed);
    }

    public void forward(double speed, int motorStep)
    {
        leftDrive.forward(speed, motorStep);
        rightDrive.forward(speed, motorStep);
    }

    public void backward(double speed)
    {
        leftDrive.backward(speed);
        rightDrive.backward(speed);
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
            leftDrive.forward(speedLeft);
        } else if (speedLeft < 0.1)
        {
            leftDrive.backward(Math.abs(speedLeft));
        }

        if (speedRight > 0.1)
        {
            rightDrive.forward(speedRight);
        } else if (speedRight < 0.1)
        {
            rightDrive.backward(Math.abs(speedRight));
        }
    }

    public void set(double speedLeft, double speedRight)
    {
        if (speedLeft > 0.0)
        {
            leftDrive.forward(speedLeft);
        } else if (speedLeft < 0.1)
        {
            leftDrive.backward(Math.abs(speedLeft));
        }

        if (speedRight > 0.0)
        {
            rightDrive.forward(speedRight);
        } else if (speedRight < 0.1)
        {
            rightDrive.backward(Math.abs(speedRight));
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
