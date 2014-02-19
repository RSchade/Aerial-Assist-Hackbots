
package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Mechanics implements Constants
{
    Joystick rightStick = new Joystick(USB_TWO);
    Joystick leftStick = new Joystick(USB_ONE);
    Joystick gamePad = new Joystick(USB_THREE);
    
    // Shooter Tools
    Talon shooterMoterLeft = new Talon(PWM_FOUR);
    Talon shooterMoterRight = new Talon(PWM_FIVE);
    AnalogChannel pot = new AnalogChannel(ANALOG_ONE);
    
    //Spinny Sticks Tools
    Talon spinnyMotor = new Talon(PWM_THREE);
    DoubleSolenoid spinnyPositionSwitch = new DoubleSolenoid(SOLENOID_ONE, SOLENOID_TWO);
    
    // Drive Train Tools
    Talon rightMotor = new Talon(PWM_ONE);
    Talon leftMotor = new Talon(PWM_TWO);
    Solenoid motorGearSwitch = new Solenoid(SOLENOID_THREE);

    // Drive Train Encoders
    Encoder leftDriveEncoder = new Encoder(DIO_ONE, DIO_TWO);
    Encoder rightDriveEncoder = new Encoder(DIO_THREE, DIO_FOUR);
    
    // Other Tools
//    AnalogChannel ultraSound = new AnalogChannel(2);
//    Compressor airCompresser = new Compressor(14, 1);
}
