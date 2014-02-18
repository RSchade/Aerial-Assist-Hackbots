package com.fpsrobotics.deprecated;

/**
 * Class which contains the methods to control the pneumatics.
 * 
 * @author ray
 */
public class Pneumatics
{

//    /**
//     * Initialize the compressor.
//     *
//     * @param compressor
//     */
//    public void init(Compressor compressor)
//    {
//        compressor.start();
//    }
//
//    /**
//     * Stop the compressor
//     *
//     * @param compressor
//     */
//    public void stop(Compressor compressor)
//    {
//        compressor.stop();
//    }
//
//    /**
//     * Move the spinny sticks.
//     *
//     * @param spinnyStickSolenoid
//     * @param forwardBackward
//     */
//    public void spinnySticksMovement(DoubleSolenoid spinnyStickSolenoid, boolean forwardBackward)
//    {
//        if (forwardBackward)
//        {
//            spinnyStickSolenoid.set(DoubleSolenoid.Value.kForward);
//        }
//
//        if (!forwardBackward)
//        {
//            spinnyStickSolenoid.set(DoubleSolenoid.Value.kReverse);
//        }
//    }
//
//    /**
//     * Stop the spinny sticks.
//     *
//     * @param spinnyStickSolenoid
//     */
//    public void stopSpinnySticksMovement(DoubleSolenoid spinnyStickSolenoid)
//    {
//        spinnyStickSolenoid.set(DoubleSolenoid.Value.kOff);
//    }
//
//    /**
//     * Switch gears (sonic shifters).
//     *
//     * @param gearSwitch
//     * @param areWeSwitched
//     */
//    public void switchGears(Solenoid gearSwitch, boolean areWeSwitched)
//    {
//        gearSwitch.set(areWeSwitched);
//    }
//
//    public void switchGears(DriveObject driveMotor, boolean switchState)
//    {
//        driveMotor.shift(switchState);
//    }
}
