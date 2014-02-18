package com.fpsrobotics.thread;

import com.fpsrobotics.CatapultObject;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.ControlMap;
import com.fpsrobotics.constants.PID;
import com.fpsrobotics.constants.ThreadsAndClasses;
import com.fpsrobotics.hardware.Analogs;
import com.fpsrobotics.hardware.Joysticks;
import com.fpsrobotics.hardware.Motors;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * Controls the shooter, probably will be changed as it becomes more object
 * oriented.
 *
 * @author ray
 */
public class CatapultThread extends Thread implements ControlMap, PID, ThreadsAndClasses 
{

    double dynamicPresetDistance = 0;
    double dynamicPresetSpeed = 0;
    boolean isInterrupted = false;
    Joystick gamepadJoystick = Joysticks.gamepadJoystick;
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
