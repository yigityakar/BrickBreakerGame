package domain.controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import domain.timertask.YmirAbilityTask;
import domain.ymir.Ymir;
import domain.ymir.YmirCoin;

public class YmirController {
	private static YmirController instance;
	private Ymir ymir;
	private YmirCoin coin;
	private Timer timer;
	private TimerTask timerTask;
	
	private YmirController() {
		ymir = new Ymir();
		coin = new YmirCoin();
	};
	
	public static YmirController getInstance() {
        if (instance == null) {
        	instance = new YmirController();
        }
        return instance;
	}
	
	public void initializeTimer() {
		timer = new Timer();
		timerTask = new YmirAbilityTask(timer);  
		timer.scheduleAtFixedRate(timerTask, 30*1000, 30*1000);
	}

	public void specifyYmirAbility() {
		int success = coin.flip();
		if (success == 1) {
			System.out.println("success!");
			ymir.chooseAbility();
		}
	}
	public void cancelTask() {
		// TODO Auto-generated method stub
		if(timerTask != null) {
			timerTask.cancel();
		}
	}
	
}
