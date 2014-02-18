package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.Analog;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.Controls;
import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.StaticClasses;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.Values;
import com.fpsrobotics.preset.Preset;
import com.fpsrobotics.preset.PresetHighGoal;
import com.fpsrobotics.preset.PresetPass;
import com.fpsrobotics.preset.PresetTruss;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread implements IsAThread
{
    Preset highGoal;
    Preset pass;
    Preset truss;
    CatapultObject shoot;
    double dynamicPresetDistance = 0;
    double dynamicPresetSpeed = 0;
    boolean isInterrupted = false;
    boolean areWeShooting;

    /**
     * presets.shooterPresetPot(gamepadJoystick, shooterPot, shooterTalonOne, shooterTalonTwo, 300, SHOOTER_PRESET_ONE, 0.2);
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
        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Talons.shooterTalonOne, false), new SimpleMotor(Talons.shooterTalonTwo, true));


        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Values.THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -Controls.gamepadJoystick.getRawAxis(2);
                dynamicPresetSpeed += Controls.gamepadJoystick.getRawAxis(1);

                if (StaticClasses.spinnySticks.getSpinnySticks())
                {
                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_ONE))
                    {
                        shoot.presetShoot(Analog.shooterPot, highGoal, shooterTwinMotor);

                    }

                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_TWO))
                    {
                        shoot.presetShoot(Analog.shooterPot, pass, shooterTwinMotor);

                    }

                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_THREE))
                    {
                        shoot.presetShoot(Analog.shooterPot, pass, shooterTwinMotor);

                    }

                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_FOUR))
                    {
                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Values.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Values.SHOOTER_MIN_SPEED)
                        {
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
