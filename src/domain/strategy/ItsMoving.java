package domain.strategy;

import java.util.Iterator;
import java.util.Random;

import constants.UI;
import domain.ObstacleConfiguration;
import domain.obstacle.Obstacle;
import domain.GlobalMutex;

public class ItsMoving implements Moving {

	private int x, y;
	private final int RIGHT_DIRECTION=1;
	private final int LEFT_DIRECTION=-1;
	private final int MOVING_RIGHT_LIMIT=45;
	private final int MOVING_LEFT_LIMIT=-45;
	int moveDirectionLocation;
	int collusionObstacleBias = 4;
	int collusionBoundriesBias = 8;
	
	
	public ItsMoving() {
		Random rand2 = new Random();
		rand2.nextInt(10);
		moveDirectionLocation = rand2.nextInt(35);
	}

	public void move(Obstacle ob) {
       
		try {
			GlobalMutex.getInstance().getSemaphore().acquire(1);
			  try {
				  if (moveDirectionLocation > 0) {
						if (moveDirectionLocation <  MOVING_RIGHT_LIMIT) {
							ob.setX_position(ob.getX_position() + 1);
							moveDirectionLocation++;

							if (ob.getWidth() + ob.getX_position() + collusionBoundriesBias == UI.RUNNING_MODE_FRAME_WIDTH)
								moveDirectionLocation = LEFT_DIRECTION;
			          
						
								
							for (Obstacle obstacle : ObstacleConfiguration.getInstance().getListOfObstacles()) {
								if (obstacle.getX_position() == collusionObstacleBias + ob.getX_position() + ob.getWidth()
										&& obstacle.getY_position() == ob.getY_position()) {
									moveDirectionLocation = LEFT_DIRECTION;
								}
							}
						} else {
							moveDirectionLocation = LEFT_DIRECTION;
						}
					} else if (moveDirectionLocation >  MOVING_LEFT_LIMIT) {

						ob.setX_position(ob.getX_position() - 1);
						moveDirectionLocation--;
						if (ob.getX_position() - collusionBoundriesBias == 0)
							moveDirectionLocation = RIGHT_DIRECTION;
						for (Obstacle obstacle : ObstacleConfiguration.getInstance().getListOfObstacles()) {
							if (obstacle.getX_position() + obstacle.getWidth() == ob.getX_position() - collusionObstacleBias
									&& obstacle.getY_position() == ob.getY_position()) {
								moveDirectionLocation = RIGHT_DIRECTION;
							}

						}

					} else {
						moveDirectionLocation = RIGHT_DIRECTION;

					}
			    // do something
			  } finally {
				  GlobalMutex.getInstance().getSemaphore().release(1);
			  }
			} catch(InterruptedException ie) {
			  // ...
			}
         
	}

}
