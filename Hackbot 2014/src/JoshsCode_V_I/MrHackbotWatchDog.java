package JoshsCode_V_I;

import edu.wpi.first.wpilibj.Watchdog;

public class MrHackbotWatchDog
{

    Watchdog dog = Watchdog.getInstance();

    public void feed()
    {
        dog.feed();
    }

    public void watchdogInit(double expirationTime)
    {
        dog.setEnabled(true);
        dog.setExpiration(expirationTime);
    }
    
    public void setEnabled(boolean enabled) {
        dog.setEnabled(enabled);
    }
    
    public void kill() {
        dog.kill();
    }
    
}
