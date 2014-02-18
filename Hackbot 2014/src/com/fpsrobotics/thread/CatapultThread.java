package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;
import com.fpsrobotics.Preset.*;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object
 * oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread
{

    Preset highGoal;
    Preset pass;
    Preset truss;
    Preset dynamic;
    CatapultObject shoot;
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
        highGoal = new PresetHighGoal();
        pass = new PresetPass();
        truss = new PresetTruss();
        long previousTime = System.currentTimeMillis();
        isInterrupted = false;
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));


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
                        shoot.presetShoot(Analogs.SHOOTER_POTENTIOMETER, highGoal, shooterTwinMotor);

                    }

                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_TWO))
                    {
                        shoot.presetShoot(Analogs.SHOOTER_POTENTIOMETER, pass, shooterTwinMotor);

                    }

                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_THREE))
                    {
                        shoot.presetShoot(Analogs.SHOOTER_POTENTIOMETER, pass, shooterTwinMotor);

                    }


                    if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_FOUR))
                    {
                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Constants.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Constants.SHOOTER_MIN_SPEED)
                        {
                            shoot.presetShoot(Analogs.SHOOTER_POTENTIOMETER, dynamic, shooterTwinMotor);
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
