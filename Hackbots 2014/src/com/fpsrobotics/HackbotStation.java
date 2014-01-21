package com.fpsrobotics;

import com.fpsrobotics.interfaces.Analog;
import com.fpsrobotics.interfaces.Joysticks;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author ray
 */
public class HackbotStation implements Runnable, Joysticks, Analog
{

// Removed 1/18/14: Doesn't work! null pointer exception
//    private volatile Joystick leftJoystick, rightJoystick;
//    public HackbotStation(Joystick leftJoystick, Joystick rightJoystick)
//    {
//        leftJoystick = this.leftJoystick;
//        rightJoystick = this.rightJoystick;
//    }
    /**
     *
     * Class which outputs most variables to the SmartDashboard and gives the
     * state of the battery through DashboardOutput's methods.
     *
     */
    public void run()
    {
        long previousTime = System.currentTimeMillis();
        DashboardOutputs variableOutputs = new DashboardOutputs();

        while (true)
        {
            // Battery info output (every second)
            if (System.currentTimeMillis() - previousTime >= 1000)
            {
                if (DriverStation.getInstance().getBatteryVoltage() <= 11)
                {
                    System.out.println("Warning! Battery voltage low, replace soon!");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 10)
                {
                    System.out.println("Danger! Battery voltage very low, replace immediately!");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 9)
                {
                    System.out.println("Battery voltage extremely low! Replace immediately, robot may malfunction");
                } else if (DriverStation.getInstance().getBatteryVoltage() <= 8)
                {
                    System.out.println("Battery dead, replace now");
                }
                previousTime = System.currentTimeMillis();
            }

            if (DriverStation.getInstance().getTeamNumber() != 3414)
            {
                System.out.println("Come on, don't steal our code!");
            }

            variableOutputs.outputToDashboard(leftJoystick, rightJoystick, gyroScope);

        }

    }
}
