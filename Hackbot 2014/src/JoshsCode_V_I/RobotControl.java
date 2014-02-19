package JoshsCode_V_I;

import edu.wpi.first.wpilibj.camera.AxisCameraException;

/**
 *
 * @author Josh
 */
public class RobotControl implements Constants
{

    Mechanics hardware = new Mechanics();
    // Autonomous
    private int goodImageCounter = 0;
    private RobotCamera robotCamera = new RobotCamera();
    private VisionProcessingSample visionSample = new VisionProcessingSample();
    private boolean autoCameraDone = false;
    private boolean autoLaunch = false;
    private boolean autoLaunchDone = false;
    // Teleop
    private DriveTrainRun myDrive = new DriveTrainRun();
    private SpinnySticksRun mySpinnySticks = new SpinnySticksRun();
    private catapultFireThree catapult = null;
    private Thread catapultThread = null;
    // Test
    private getPositions myPositions = new getPositions();
    private Thread myPositionsThread = new Thread(myPositions);
    private checkDifference myDifference = new checkDifference();
    private Thread myDifferenceThread = new Thread(myDifference);

    public RobotControl()
    {
        // Autonomous
        // Teleop
        myDrive.passVariables(hardware.leftMotor, hardware.rightMotor, hardware.rightStick, hardware.leftStick, hardware.motorGearSwitch);
        mySpinnySticks.passVariables(hardware.spinnyMotor, hardware.rightStick, hardware.leftStick, hardware.spinnyPositionSwitch);
        // Test
        myPositions.passVariables(hardware.shooterMoterLeft, hardware.shooterMoterRight, hardware.rightStick, hardware.leftStick, hardware.pot);
        myDifference.passVariables(hardware.shooterMoterLeft, hardware.shooterMoterRight, hardware.rightStick, hardware.leftStick, hardware.pot);
    }

    public void autonomous()
    {

        try
        {
            if (visionSample.autoImageFind() && !this.autoCameraDone)
            {
                this.goodImageCounter++;
            }
            if (this.goodImageCounter > 2)
            {
                this.goodImageCounter = 0;
                this.autoCameraDone = true;

//                    // **SHOOT TO AUTONOMOUS PRESETS**
//                    this.autoLaunch = true;
//
//                    //**AUTO DRIVE FUNCTION**
//                    myDrive.setDrive(0.7, 0.7);
            }


        } catch (AxisCameraException ex)
        {
            ex.printStackTrace();
        }
    }

    public void cameraInit()
    {
        visionSample.imageFindInit();
        robotCamera.init();
    }

    public void teleopInit()
    {
    }

    public void teleop()
    {
        //Drive Train and Spinny Sticks
        myDrive.freeDrive();
        mySpinnySticks.freeSpin();
        mySpinnySticks.freeShift();
        if (myDrive.isDriving())    //Restricts so if drive is active, S.S. will fire
        {
            mySpinnySticks.setShift(true);
        }

//        //Catapult Functions
//        if (mySpinnySticks.isShifted() == DOUBLESOLENOID_FORWARD)   //Restricts so catapult can't fire if S.S. are "IN"
//        {
        if (catapultThread == null)
        {
            if (hardware.rightStick.getRawButton(3))    // Set to Right Stick for Testing Purposes
            {
                catapult = new catapultFireThree(400, 0.8);
                catapult.passVariables(hardware.shooterMoterLeft, hardware.shooterMoterRight, hardware.pot);
                catapultThread = new Thread(catapult);
                catapultThread.start();
            }
//                if (this.autoLaunch && !this.autoLaunchDone)
//                {
//                    catapult = new catapultFireThree(400, 0.8);
//                    catapult.passVariables(hardware.shooterMoterLeft, hardware.shooterMoterRight, hardware.pot);
//                    catapultThread = new Thread(catapult);
//                    catapultThread.start();
//                    this.autoLaunchDone = true;
//                }
        }
//        }
        if (catapultThread != null && catapult != null)
        {
            if (catapult.done())
            {
                catapultThread = null;
                catapult = null;
            }
        }
    }

    public void test()
    {
        myDrive.freeDrive();
        mySpinnySticks.freeSpin();
        mySpinnySticks.freeShift();
        if (myDrive.isDriving())
        {
            mySpinnySticks.setShift(true);
        }
    }

    public void testThreads()
    {
        if (hardware.rightStick.getRawButton(8))    //Set so if button is held while enabled... For testing Purposes
        {
            if (myPositionsThread == null)
            {
                myPositionsThread = new Thread(myPositions);
            }
//        if (myDifferenceThread == null)
//        {
//            myDifferenceThread = new Thread(myDifference);
//        }
            myPositionsThread.start();
//        myDifferenceThread.start();
        }
    }

    public void disabledInit()
    {
        if (myPositionsThread != null)
        {
            myPositions.stop();
            myPositionsThread = null;
        }
        if (myDifferenceThread != null)
        {
            myDifference.stop();
            myDifferenceThread = null;
        }
        if (catapultThread != null)
        {
            catapultThread = null;
            catapult = null;
        }
    }

    public void disabled()
    {
    }
}
