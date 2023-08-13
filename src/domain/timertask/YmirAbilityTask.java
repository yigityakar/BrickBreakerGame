package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.controller.YmirController;

public class YmirAbilityTask extends TimerTask{
	Timer timer;
	
	public YmirAbilityTask(Timer timer) {
		this.timer = timer;
		
	}
	
	@Override
    public void run() {
		System.out.println("inside run");
        YmirController.getInstance().specifyYmirAbility();
    }


}
