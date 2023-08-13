package domain.timertask;

import java.util.TimerTask;

import domain.obstacle.Obstacle;

public class UpdateObstaclePositions  extends TimerTask {

	Obstacle  exp;
	  
    public UpdateObstaclePositions(Obstacle exp) {
    	 this.exp= exp;
    }
    @Override
    public void run() {
        // update ball position
    
    	exp.tryToMove(exp);
    	  
    
    }

}