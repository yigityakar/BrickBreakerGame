package domain.timertask;



import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import domain.EnchantedSphere;
public class UpdateSphereTask extends TimerTask {
    EnchantedSphere ball;
  
    public UpdateSphereTask(EnchantedSphere ball) {
    	 this.ball= ball;
    }
    @Override
    public void run() {
        // update ball position
    	ball.move();
 
    }

}

