package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * MAIN CLASS
 *
 * @author Josh
 */
public class HackbotsTwo extends IterativeRobot implements Constants
{
    MrHackbotWatchDog bobby;
    Compressor airCompresser = new Compressor(DIO_FOURTEEN, RELAY_ONE);
    RobotControl myRobot;
    DriverStationLCD userMessages = DriverStationLCD.getInstance();

    public HackbotsTwo()
    {
        bobby = new MrHackbotWatchDog();
        myRobot = new RobotControl();
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        System.out.println("\n\n\n==============================\nHackbots Aerial Assist Code\nRobot Initiated\n==============================\n\n\n");
        userMessages.println(DriverStationLCD.Line.kUser1, 1, "Hackbots Aerial Assist Code");
        userMessages.updateLCD();

        // Set Expiration with 2 second Timeout
        bobby.watchdogInit(2.0);

        // Init pneumatics
        airCompresser.start();

        // Camera settings init
        myRobot.cameraInit();
    }

    public void autonomousInit()
    {
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic()
    {
        while (isEnabled() && isAutonomous())
        {
            myRobot.autonomous();
            bobby.feed();
            Timer.delay(0.20);
        }
    }

    public void teleopInit()
    {
        myRobot.testThreads();
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic()
    {
        while (isEnabled() && isOperatorControl())      // DO I NEED THIS WHILE LOOP? If it's called periodically, do I need a double-check on the system?
        {
            myRobot.teleop();
            bobby.feed();
            Timer.delay(0.20);
        }
    }

    public void testInit()
    {
//        myRobot.testThreads();
    }

    public void testPeriodic()
    {
        while (isTest() && isEnabled())
        {
            myRobot.test();
            bobby.feed();
            Timer.delay(0.20);
        }
    }

    public void disabledInit()
    {
        myRobot.disabledInit();

    }

    public void disabledPeriodic()
    {
        while (isDisabled())
        {
            bobby.feed();
            Timer.delay(0.20);
        }
    }
}
