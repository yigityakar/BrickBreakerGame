package domain;

import java.time.Duration;
import java.time.Instant;
import static java.lang.Math.toIntExact;

import domain.controller.GameController;

public class GameTime {
	private static GameTime instance;
	
	private Instant start;
	
	private Instant finish;
	
	private int CurrentTime;
	
	
	
	
	public GameTime() {
		CurrentTime=0;
	}
	
	public int getCurrentTime() {
		return CurrentTime;
	}

	public void setCurrentTime(int currentTime) {
		CurrentTime = currentTime;
	}
	
	
	public void startTime() {
		start = Instant.now();
	}
	
	public void stopTime() {
		finish = Instant.now();
	}
	
	public int measureBetween() {
		long timeElapsed =  Duration.between(start, finish).toMillis()/1000;
		int timeElapsedToInt = toIntExact(timeElapsed);
		System.out.println("time elapsed = " + timeElapsedToInt);
		return timeElapsedToInt;
	}
	public void addCurrentTime() {
		setCurrentTime(measureBetween() + getCurrentTime());
		System.out.println("current time = " + getCurrentTime());
	}
	
	
	
	
	
	

}

