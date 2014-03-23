package com.fpsrobotics.thread;

import com.fpsrobotics.Catapult;
import com.fpsrobotics.Dashboard;
import com.fpsrobotics.LEDs;
import com.fpsrobotics.SimpleMotor;
import com.fpsrobotics.TwinMotor;
import com.fpsrobotics.constants.*;
import com.fpsrobotics.hardware.*;
import com.fpsrobotics.preset.*;

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
    Preset tenft;
    Preset sixft;

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
        tenft = new TenFt();
        sixft = new SixFt();

        long previousTime = System.currentTimeMillis();
        isInterrupted = false;

        TwinMotor shooterTwinMotor = new TwinMotor(new SimpleMotor(Motors.SHOOTER_ONE, false), new SimpleMotor(Motors.SHOOTER_TWO, true));
        shoot = Catapult.createInstance(Analogs.SHOOTER_POTENTIOMETER, shooterTwinMotor);

        while (!isInterrupted)
        {
            if (Math.abs(previousTime - System.currentTimeMillis()) >= Constants.THREAD_REFRESH_RATE)
            {
                dynamicPresetDistance += -Joysticks.GAMEPAD.getRawAxis(2) * 6;
                dynamicPresetSpeed += Joysticks.GAMEPAD.getRawAxis(1) * 5;

                Dashboard.setDynamicDistance(dynamicPresetDistance);
                Dashboard.setDynamicSpeed(dynamicPresetSpeed);

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_ONE))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
//                    LEDs.getInstance().BlueSet(true);
//                    shoot.shoot(pass);
                    shoot.shoot(truss);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_TWO))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
//                    LEDs.getInstance().BlueSet(true);
//                    shoot.shoot(truss);
                    shoot.shoot(highGoal);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_THREE))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
//                    LEDs.getInstance().BlueSet(true);
//                    shoot.shoot(highGoal);
                    shoot.shoot(tenft);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_FOUR))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
//                    LEDs.getInstance().BlueSet(true);
//                    shoot.shoot(highGoal);
                    shoot.shoot(pass);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_SIX))
                {
//                    double currentTime = Timer.getFPGATimestamp();
//                    Dashboard.setCurrentLaunchTime(currentTime);
//                    LEDs.getInstance().BlueSet(true);
//                    shoot.shoot(highGoal);
                    shoot.shoot(sixft);
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_DYNAMIC))
                {
                    if (dynamicPresetDistance <= Constants.HIGH_POT_VALUE && dynamicPresetDistance >= Constants.HOME_POT_VALUE && (dynamicPresetSpeed / 100) >= Constants.SHOOTER_MIN_SPEED)
                    {
                        if ((dynamicPresetSpeed / 100) <= Constants.SHOOTER_MAX_SPEED)
                        {
                            dynamicPresetSpeed = 100;
                        }
//                        double currentTime = Timer.getFPGATimestamp();
//                        Dashboard.setCurrentLaunchTime(currentTime);
//                        LEDs.getInstance().BlueSet(true);
                        Preset dynamic = new PresetDynamic();
                        shoot.shoot(dynamic);
                    }
                }

                if (Joysticks.GAMEPAD.getRawButton(JoystickButtons.SHOOTER_PRESET_SONAR))
                {
                    if (Analogs.ULTRA_DISTANCE.getDistanceFt() >= 12 && Analogs.ULTRA_DISTANCE.getDistanceFt() <= 18)
                    {
                        shoot.shoot(highGoal);
                        System.out.println("12ft");
                    } else if (Analogs.ULTRA_DISTANCE.getDistanceFt() >= 10 && Analogs.ULTRA_DISTANCE.getDistanceFt() <= 12)
                    {
                        shoot.shoot(tenft);

                        System.out.println("Tenft");
                    } else if (Analogs.ULTRA_DISTANCE.getDistanceFt() > 4 && Analogs.ULTRA_DISTANCE.getDistanceFt() <= 10)
                    {
                        shoot.shoot(sixft);
                        System.out.println("Sixft");
                    } else 
                    {
                        System.out.println("nope! Too close or too far!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                }

                previousTime = System.currentTimeMillis();

//                LEDs.getInstance().BlueSet(false);
            }
        }
    }

    public void interrupt()
    {
        isInterrupted = true;
    }
}
