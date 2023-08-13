package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import constants.UI;
import domain.controller.GameController;

public class PhantasmExpansionTask extends TimerTask {
	Timer timer;
	public PhantasmExpansionTask(Timer timer) {
		this.timer = timer;
		
	}
	
	
	@Override
    public void run() {
        GameController.getInstance().getPhantasm().setWidth(UI.RUNNING_MODE_FRAME_WIDTH/10);
        System.out.println("after run");
        timer.cancel(); //Terminate the timer thread
    }
    
}
