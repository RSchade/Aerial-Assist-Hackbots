package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.IsAThread;
import com.fpsrobotics.constants.Analog;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.Controls;
import com.fpsrobotics.constants.Talons;
import com.fpsrobotics.constants.StaticClasses;
import com.fpsrobotics.constants.Values;
import edu.wpi.first.wpilibj.Joystick;

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
                dynamicPresetDistance += -Controls.gamepadJoystick.getRawAxis(2);
                dynamicPresetSpeed += Controls.gamepadJoystick.getRawAxis(1);

                if (StaticClasses.spinnySticks.getSpinnySticks())
                {
//                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_ONE))
//                    {
//                        shooterCatapult.regularLaunch(250, 1.0);
//                    }
//
//                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_TWO))
//                    {
//                        shooterCatapult.regularLaunch(350, 1.0);
//                    }
//
//                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_THREE))
//                    {
//                        shooterCatapult.regularLaunch(600, 1.0);
//                    }
//
//                    if (Controls.gamepadJoystick.getRawButton(ControlMap.SHOOTER_PRESET_FOUR))
//                    {
//                        if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Values.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Values.SHOOTER_MIN_SPEED)
//                        {
//                            shooterCatapult.regularLaunch((int) dynamicPresetDistance, (dynamicPresetSpeed / 100));
//                        }
//                    }

                    shooterPreset(Controls.gamepadJoystick, ControlMap.SHOOTER_PRESET_ONE, shooterCatapult, 250, 1.0);
                    shooterPreset(Controls.gamepadJoystick, ControlMap.SHOOTER_PRESET_TWO, shooterCatapult, 350, 1.0);
                    shooterPreset(Controls.gamepadJoystick, ControlMap.SHOOTER_PRESET_THREE, shooterCatapult, 600, 1.0);

                    if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) <= Values.SHOOTER_MAX_SPEED && (dynamicPresetSpeed / 100) >= Values.SHOOTER_MIN_SPEED)
                    {
                        shooterPreset(Controls.gamepadJoystick, ControlMap.SHOOTER_PRESET_FOUR, shooterCatapult, (int) dynamicPresetDistance, (dynamicPresetSpeed / 100));
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

    public void shooterPreset(Joystick gamepadJoystick, int presetButton, CatapultObject catapultObject, int preset, double speed)
    {
        if (gamepadJoystick.getRawButton(presetButton))
        {
            catapultObject.regularLaunch(preset, speed);
        }
    }

}
