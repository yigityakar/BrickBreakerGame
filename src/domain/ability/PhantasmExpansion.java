package domain.ability;

import java.util.Timer;
import java.util.TimerTask;

import domain.NoblePhantasm;
import domain.controller.GameController;
import domain.timertask.PhantasmExpansionTask;

public class PhantasmExpansion extends GameObjectDecorator{
	//GameObject object;
	String name = "phantasmExpansion";
	

	public PhantasmExpansion(GameObject object) {
		this.object = object;
	}
	
	
	public void enhance() {
		NoblePhantasm n = (NoblePhantasm) object;
		System.out.println("before: " + n.getWidth());
		n.setWidth(2*n.getWidth());
		System.out.println("after: " + n.getWidth());
		Timer timer = new Timer(true);
	    TimerTask timerTask = new PhantasmExpansionTask(timer);  
	    System.out.println("after2: " + n.getWidth());
	    //running timer task as daemon thread

	    timer.schedule(timerTask, 30*1000);
		
	}

	
}

