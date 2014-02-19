
package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public interface MechanicsTwo
{
    Joystick rightStick = new Joystick(2);
    Joystick leftStick = new Joystick(1);
    Joystick gamePad = new Joystick(3);
    
    // Shooter Tools
    Talon shooterMoterLeft = new Talon(4);
    Talon shooterMoterRight = new Talon(5);
    AnalogChannel pot = new AnalogChannel(1);
    
    //Spinny Sticks Tools
    Talon spinnyMotor = new Talon(3);
    DoubleSolenoid spinnyPositionSwitch = new DoubleSolenoid(1, 2);
    
    // Drive Train Tools
    Talon rightMotor = new Talon(1);
    Talon leftMotor = new Talon(2);
    Solenoid motorGearSwitch = new Solenoid(3);

    // Drive Train Encoders
    Encoder leftDriveEncoder = new Encoder(1, 2);
    Encoder rightDriveEncoder = new Encoder(3, 4);
    
    // Other Tools
//    AnalogChannel ultraSound = new AnalogChannel(2);
//    Compressor airCompresser = new Compressor(14, 1);
}
