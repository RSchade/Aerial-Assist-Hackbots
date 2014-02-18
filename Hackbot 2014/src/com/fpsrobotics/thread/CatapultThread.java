package com.fpsrobotics;

import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.Analog;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.DIOs;
import com.fpsrobotics.constants.Joysticks;
import com.fpsrobotics.constants.PID;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.ThreadsAndClasses;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object
 * oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread implements Joysticks, Analog, Talons, DIOs, ControlMap, PID, ThreadsAndClasses, IsAThread
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
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(shooterTalonOne, false), new SimpleMotor(shooterTalonTwo, true));
        CatapultObject shooterCatapult = new CatapultObject(shooterTwinMotor, shooterPot);

        shooterCatapult.goHome();

        while (!isInterrupted)

        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -gamepadJoystick.getRawAxis(2);
                dynamicPresetSpeed += gamepadJoystick.getRawAxis(1);

                if (spinnySticks.getSpinnySticks())
                {
                    if (gamepadJoystick.getRawButton(SHOOTER_PRESET_ONE))
                    {
                        shooterCatapult.regularLaunch(250, 1.0);
                    }

                    if (gamepadJoystick.getRawButton(SHOOTER_PRESET_TWO))
                    {
                        shooterCatapult.regularLaunch(350, 1.0);
                    }

                    if (gamepadJoystick.getRawButton(SHOOTER_PRESET_THREE))
                    {
                        shooterCatapult.regularLaunch(600, 1.0);
                    }

                    if (gamepadJoystick.getRawButton(SHOOTER_PRESET_FOUR))
                    {
                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= SHOOTER_MIN_SPEED)
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
