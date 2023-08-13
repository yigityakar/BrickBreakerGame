package domain.ability;

import java.util.Timer;
import java.util.TimerTask;

import domain.EnchantedSphere;
import domain.timertask.UnstoppableSphereTask;

public class UnstoppableSphere extends GameObjectDecorator{
	//GameObject object;
	String name = "unstoppableSphere";
	

	public UnstoppableSphere(GameObject object) {
		this.object = object;
	}
	
	
	public void enhance() {
		EnchantedSphere s = (EnchantedSphere)object;
		s.enhance();
		
		Timer timer = new Timer(true);
	    TimerTask timerTask = new UnstoppableSphereTask(timer);  
	    
	    //running timer task as daemon thread
	    timer.schedule(timerTask, 30*1000);
		
	}

	
}

