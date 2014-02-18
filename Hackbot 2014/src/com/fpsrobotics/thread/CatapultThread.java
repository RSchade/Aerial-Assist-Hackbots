package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.Analog;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.DIOs;
import com.fpsrobotics.constants.Joysticks;
import com.fpsrobotics.constants.PID;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.constants.Values;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object
 * oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread implements IsAThread
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
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Talons.shooterTalonOne, false), new SimpleMotor(Talons.shooterTalonTwo, true));
        CatapultObject shooterCatapult = new CatapultObject(shooterTwinMotor, Analog.shooterPot);

        shooterCatapult.goHome();
        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Values.THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -Joysticks.gamepadJoystick.getRawAxis(2);
                dynamicPresetSpeed += Joysticks.gamepadJoystick.getRawAxis(1);

                if (ThreadsAndClasses.spinnySticks.getSpinnySticks())
                {
                    if (Joysticks.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_ONE))
                    {
                        shooterCatapult.regularLaunch(250, 1.0);
                    }

                    if (Joysticks.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_TWO))
                    {
                        shooterCatapult.regularLaunch(350, 1.0);
                    }

                    if (Joysticks.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_THREE))
                    {
                        shooterCatapult.regularLaunch(600, 1.0);
                    }

                    if (Joysticks.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_FOUR))
                    {
                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Values.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Values.SHOOTER_MIN_SPEED)
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
