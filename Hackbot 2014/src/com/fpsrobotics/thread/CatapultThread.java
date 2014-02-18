package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object
 * oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread implements PID 
{

    double dynamicPresetDistance = 0;
    double dynamicPresetSpeed = 0;
    boolean isInterrupted = false;
    boolean areWeShooting;

    /**
     * presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne,
     * shooterTalonTwo, 300, SHOOTER_PRESET_ONE, 0.2);
     *
     * prese Thread to control the shooter.
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        CatapultObject shooterCatapult = new CatapultObject(shooterTwinMotor, Analogs.SHOOTER_POTENTIOMETER);

        shooterCatapult.goHome();

        while (!isInterrupted)

        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Constants.THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -Joysticks.GAMEPAD.getRawAxis(2);
                dynamicPresetSpeed += Joysticks.GAMEPAD.getRawAxis(1);

                if (ThreadsAndClasses.spinnySticks.getSpinnySticks())
                {
                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_ONE))
                    {
                        shooterCatapult.regularLaunch(250, 1.0);
                    }

                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_TWO))
                    {
                        shooterCatapult.regularLaunch(350, 1.0);
                    }

                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_THREE))
                    {
                        shooterCatapult.regularLaunch(600, 1.0);
                    }

                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_FOUR))
                    {
                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Constants.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Constants.SHOOTER_MIN_SPEED)
                        {
                            shooterCatapult.regularLaunch((int) dynamicPresetDistance, (dynamicPresetSpeed / 100));
                        }
                    }
                }

                previousTime = System.currentTimeMillis();
            }
        }
    }

    public double getDynamicPresetDistance()
    {
        return dynamicPresetDistance;
    }

    public double getDynamicPresetSpeed()
    {
        return dynamicPresetSpeed;
    }

    public void interrupt()
    {
        isInterrupted = true;
    }

}
