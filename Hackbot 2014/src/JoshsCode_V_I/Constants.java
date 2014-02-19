package JoshsCode_V_I;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface Constants
{

    DoubleSolenoid.Value DOUBLESOLENOID_OFF = DoubleSolenoid.Value.kOff;
    DoubleSolenoid.Value DOUBLESOLENOID_FORWARD = DoubleSolenoid.Value.kForward;
    DoubleSolenoid.Value DOUBLESOLENOID_REVERSE = DoubleSolenoid.Value.kReverse;
    
    double DEAD_ZONE = 0.1;
    int TBDint = 0;
    
    //PWM slots
     int PWM_ONE = 1;
     int PWM_TWO = 2;
     int PWM_THREE = 3;
     int PWM_FOUR = 4;
     int PWM_FIVE = 5;
    
     //Solenoid slots
     int SOLENOID_ONE = 1;
     int SOLENOID_TWO = 2;
     int SOLENOID_THREE = 3;
     
     //Analog slots
     int ANALOG_ONE = 1;
     
     //cRio Modules
     int MODULE_ONE = 1;
     int MODULE_THREE = 3;
    
     //USB ports
     int USB_ONE = 1;
     int USB_TWO = 2;
     int USB_THREE = 3;
     
     //Joystick axis
     int HORIZ_AXIS = 1;
     int VERTICAL_AXIS = 2;
     int SWITCH_AXIS = 3;
     
     //Joystick buttons
     int TRIGGER = 1;
     int BUTTON_ONE = 1;
     int BUTTON_TWO = 2;
     int BUTTON_THREE = 3;
     int BUTTON_FOUR = 4;
     int BUTTON_FIVE = 5;
     int BUTTON_SIX = 6;
     int BUTTON_SEVEN = 7;
     int BUTTON_EIGHT = 8;
     int BUTTON_NINE = 9;
     int BUTTON_TEN = 10;
     
     //DIO slots
     int DIO_ONE = 1;
     int DIO_TWO = 2;
     int DIO_THREE = 3;
     int DIO_FOUR = 4;
     int DIO_FOURTEEN = 14;
      
     //Relay channels
     int RELAY_ONE = 1;
}
