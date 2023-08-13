package domain.controller;

import java.util.Timer;
import java.util.TimerTask;

import constants.UI;
import domain.EnchantedSphere;
import domain.ExplosiveParticle;
import domain.ExplosiveParticleList;
import domain.GiftBox;
import domain.GiftBoxList;
import domain.NoblePhantasm;
import ui.GameFrame;
import domain.obstacle.Obstacle;
import domain.timertask.UpdateBoxTask;
import domain.timertask.UpdateExplosiveParticleTask;
import domain.timertask.UpdateObstaclePositions;
import domain.timertask.UpdateSphereTask;
import domain.validity.BuildingModeRequirement;
import ui.RunningModeFrame;

public class NewGameHandler {
	private static NewGameHandler instance;

	private NoblePhantasm phantasm;
	private EnchantedSphere ball;


	private NewGameHandler() {	
    }

    public static NewGameHandler getInstance() {
    	
        if (instance == null) {
        	instance = new NewGameHandler();
        }
        return instance;
    }	
    

    public void initializeNewGame() {
    	GameController.getInstance().initializeGameObjects();
    }

    public void initializeRequirements() {
    	GameController.getInstance().initializeRequirements();
    }
    
    public boolean checkRequirements(int s, int f, int e, int g) {
    	return GameController.getInstance().checkRequirements(s,f,e,g);
    }
    


	public void subscribeToStrategies(GameFrame gameFrame) {
		
		//subscribes the game frame object to all of the building mode requirements' strategy objects
		for (BuildingModeRequirement r: GameController.getInstance().getRequirements()) {
			r.getStrategy().addValidityListener(gameFrame);
			
		}
	}

	public NoblePhantasm getPhantasm() {
		return phantasm;
	}

	public EnchantedSphere getBall() {
		return ball;

	}

	
    
}
