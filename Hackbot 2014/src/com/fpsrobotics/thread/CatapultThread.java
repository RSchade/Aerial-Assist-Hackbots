package com.fpsrobotics.thread;

import com.fpsrobotics.Catapult;
import com.fpsrobotics.Dashboard;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;
import com.fpsrobotics.preset.*;
import edu.wpi.first.wpilibj.Timer;

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

    Catapult shoot;
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
        shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Constants.THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -Joysticks.GAMEPAD.getRawAxis(2) * 3;
                dynamicPresetSpeed += Joysticks.GAMEPAD.getRawAxis(1) * 2;

                Dashboard.setDynamicDistance(dynamicPresetDistance);
                Dashboard.setDynamicSpeed(dynamicPresetSpeed);

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_ONE))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
                    shoot.shoot(pass);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_TWO))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
                    shoot.shoot(truss);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_THREE))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
                    shoot.shoot(highGoal);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_FOUR))
                {
                    if (dynamicPresetDistance <= 800 && (dynamicPresetSpeed / 100) >= Constants.SHOOTER_MIN_SPEED)
                    {
                        if ((dynamicPresetSpeed / 100) <= Constants.SHOOTER_MAX_SPEED)
                        {
                            dynamicPresetSpeed = 100;
                        }
//                        double currentTime = Timer.getFPGATimestamp();
//                        Dashboard.setCurrentLaunchTime(currentTime);
                        Preset dynamic = new PresetDynamic();
                        shoot.shoot(dynamic);
                    }
                }

                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
