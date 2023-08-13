package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import constants.UI;
import domain.controller.GameController;

public class UnstoppableSphereTask extends TimerTask {
	Timer timer;
	public UnstoppableSphereTask(Timer timer) {
		this.timer = timer;
		
	}
	
	
	@Override
    public void run() {
        GameController.getInstance().getBall().setUnstoppable(false);
        timer.cancel(); //Terminate the timer thread
    }
}
