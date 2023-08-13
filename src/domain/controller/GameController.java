package domain.controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import constants.UI;
import domain.EnchantedSphere;
import domain.NoblePhantasm;
import domain.ObstacleConfiguration;
import domain.Strategy;
import domain.ExplosiveParticle;
import domain.ExplosiveParticleList;
import domain.GameTime;
import domain.GiftBox;
import domain.GiftBoxList;
import domain.obstacle.Obstacle;
import domain.timertask.UpdateBoxTask;
import domain.timertask.UpdateExplosiveParticleTask;
import domain.timertask.UpdateObstaclePositions;
import domain.timertask.UpdateSphereTask;
import domain.validity.BuildingModeRequirement;
import domain.validity.ExcessObstacles;
import domain.validity.MinRequirements;
import ui.GroupPanel;
import ui.RunningModeFrame;
 
public class GameController {
	private static GameController instance;
	private GameTime gameTime;
	private NoblePhantasm phantasm;
	private EnchantedSphere ball;
	private TimerTask timerTask;
	private TimerTask timerTask2;
	private Timer timer;
	private Timer timer2;
	private ArrayList<BuildingModeRequirement> requirements = new ArrayList<BuildingModeRequirement>();
	private ArrayList<TimerTask> tasks = new ArrayList<TimerTask>();
	
	
	
	
	
	
	public void initializeGameTime() {
		gameTime = new GameTime();
	}
	
	
	public GameTime getGameTime() {
		return gameTime;
	}

	

	


	
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public TimerTask getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	private GameController() {	
    }

    public static GameController getInstance() {
    	
    	if (instance == null) {
        	instance = new GameController();
        }
        return instance;
    }	

    public void initializeAllTasks() {
    	initializeTimerTaskSphere();
    	initializeTimerTaskExplosiveParticle();
    	initializeTimerTaskGiftBox();
    	initializeTimerTaskObstacleMovement();
    }

	public void initializeGameObjects() {
    	phantasm = new NoblePhantasm((UI.RUNNING_MODE_FRAME_WIDTH - NoblePhantasm.width)/ 2, UI.RUNNING_MODE_FRAME_HEIGHT - 90,0);
    	ball = new EnchantedSphere(0, 0, (UI.RUNNING_MODE_FRAME_WIDTH - 16)/2, UI.RUNNING_MODE_FRAME_HEIGHT - (90 + EnchantedSphere.radius + 1));
    	ExplosiveParticleList.getInstance().initilizeList();
    	GiftBoxList.getInstance().initilizeList();
    	PlayerHandler.getInstance().initializeScoreAndLives();
    	initializeGameTime();
    }

	public void initializeTimerTaskSphere() {
		timer = new Timer(true);
		timerTask = new UpdateSphereTask(ball);   
        tasks.add(timerTask);
         //running timer task as daemon thread
         timer.scheduleAtFixedRate(timerTask, 0, 10);
         System.out.println(Thread.currentThread().getName()+" TimerTask started");
	}

    public void initializeTimerTaskExplosiveParticle() {
    	    timer2 = new Timer(true);
    		timerTask2 = new UpdateExplosiveParticleTask();   
    		tasks.add(timerTask2);
         
         //running timer task as daemon thread
        
         timer2.scheduleAtFixedRate(timerTask2, 0, 20);

    	

    	

		
 }
   
    public void initializeTimerTaskObstacleMovement() {
    	for(Obstacle o: ObstacleConfiguration.getInstance().getListOfObstacles()) {
			 timer2 = new Timer(true);
			 timerTask2 = new UpdateObstaclePositions(o);   
			 tasks.add(timerTask2);
		     
		     //running timer task as daemon thread
		    
		     timer2.scheduleAtFixedRate(timerTask2, 0, 20);
		   
		     //cancel after sometime
		     /*
		     try {
		         Thread.sleep(0);
		     } catch (InterruptedException e) {
		         e.printStackTrace();
		     }	
		     */
    	}
     
     
 }
      
    
    
    public void initializeTimerTaskGiftBox() {
    	    timer2 = new Timer(true);
    		timerTask2 = new UpdateBoxTask();   
    		tasks.add(timerTask2);
         
         //running timer task as daemon thread
        
         timer2.scheduleAtFixedRate(timerTask2, 0, 20);
         /*
         //cancel after sometime
         try {
             Thread.sleep(0);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         */
         
         }
		
	//initializes building mode requirements for obstacles
	public void initializeRequirements() {

		getRequirements().add(new BuildingModeRequirement(new MinRequirements()));
		getRequirements().add(new BuildingModeRequirement(new ExcessObstacles()));
		
	}
	
	


	public NoblePhantasm getPhantasm() {
		return phantasm;
	}

	public EnchantedSphere getBall() {
		return ball;
	}

	//checks if all the requirements are satisfied
	public boolean checkRequirements(int s, int f, int e, int g) {
		int numberOfPasses = 0;
		int counter = 0;

		//iterate through all requirements and execute the strategies
		for(BuildingModeRequirement req: getRequirements()) {
			boolean pass = req.executeStrategy(s,f,e,g);
			
			if (pass) { numberOfPasses += 1; }
			counter += 1;
		}
		//if all requirements are satisfied, return true
		if (numberOfPasses == counter) { return true; }
		else return false;
		
	}


	public ArrayList<BuildingModeRequirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<BuildingModeRequirement> requirements) {
		this.requirements = requirements;
	}
	
	public void cancelAllTasks() {
		for (TimerTask t: this.tasks) {
			t.cancel();
		}
	}

	public void setPhantasm(NoblePhantasm phantasm) {
		this.phantasm = phantasm;
	}
	


	
	
    
}