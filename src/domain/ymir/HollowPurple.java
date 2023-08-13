package domain.ymir;

import java.util.Random;

import domain.ObstacleConfiguration;
import domain.ObstacleFactory;
import domain.obstacle.HollowObstacle;
import domain.obstacle.Obstacle;

public class HollowPurple implements YmirAbility{

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		System.out.println("Hollow Purple activated");
		
		//set count of hollow obstacles to 8
		int hollowCount = 8;
		Random random = new Random();
		while(hollowCount != 0) {
			
			//get a random position index
			int pos_index = random.nextInt(ObstacleConfiguration.getInstance().getPositions().length);
			
			boolean positionEmpty = false;
			
			//iterate over obstacles on screen
			for (Obstacle o: ObstacleConfiguration.getInstance().getListOfObstacles()) {
				//if there is an obstacle in the specified position, set positionEmpty false
				if (o.getX_position() == ObstacleConfiguration.getInstance().getPositions()[pos_index][0]) {
					positionEmpty = true;
					break;
				}
			}
			//if the position is empty, create a hollow obstacle
			if (positionEmpty) {
				Obstacle o = ObstacleFactory.getInstance().getHollowObstacle(
						ObstacleConfiguration.getInstance().getPositions()[pos_index][0], 
						ObstacleConfiguration.getInstance().getPositions()[pos_index][1]);
				o.setX_position(ObstacleConfiguration.getInstance().getPositions()[pos_index][0]);
				o.setY_position(ObstacleConfiguration.getInstance().getPositions()[pos_index][1]);

				ObstacleConfiguration.getInstance().addObstacle(o);
				hollowCount--;
				System.out.println("hollow obstacle created at location: " + o.getX_position() + o.getY_position());
			}
		}
	}
}
