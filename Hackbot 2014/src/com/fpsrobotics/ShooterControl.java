package com.fpsrobotics;

import com.fpsrobotics.interfaces.Values;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author ray
 */
public class ShooterControl implements Values {

    /**
     * Initialize the shooter using PIDs.
     *
     * @param shooterEncoder
     * @param shooterTalonOne
     * @param shooterTalonTwo
     * @param lowValue
     * @param highValue
     * @param Kp
     * @param Ki
     * @param Kd
     * @return
     */
    public PIDController PIDInit(Encoder shooterEncoder, SpeedController shooterTalonOne, SpeedController shooterTalonTwo, int lowValue, int highValue, double Kp, double Ki, double Kd) {
        PIDController shooterPID;

        shooterEncoder.setDistancePerPulse(.000623);
        shooterEncoder.start();
        shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        shooterPID = new PIDController(Kp, Ki, Kd, shooterEncoder, shooterTalonOne);
        shooterPID.enable();

        // min - max input values in inches
        shooterPID.setInputRange(lowValue, highValue);

        return shooterPID;
    }

    /**
     * Shoot manually.
     *
     * @param joystick
     * @param shooterTalonOne
     * @param shooterTalonTwo
     * @param button
     */
    public void shootManual(Joystick joystick, SpeedController shooterTalonOne, SpeedController shooterTalonTwo, int button) {
        if (joystick.getRawButton(button)) {
            shooterTalonOne.set(0.2);
            shooterTalonTwo.set(0.2);
        } else {
            shooterTalonOne.set(NO_SPEED);
            shooterTalonTwo.set(NO_SPEED);
        }
    }

    /**
     * Shoot in autonomous.
     *
     * @param shooterTalonOne
     * @param shooterTalonTwo
     * @param shooterPot
     */
    public void shootAuto(SpeedController shooterTalonOne, SpeedController shooterTalonTwo, AnalogChannel shooterPot) {
        shooterTalonOne.set(0.2);
        shooterTalonTwo.set(0.2);

        if (shooterPot.getValue() >= 100) {
            shooterTalonOne.set(NO_SPEED);
            shooterTalonTwo.set(NO_SPEED);
        }
    }
}
