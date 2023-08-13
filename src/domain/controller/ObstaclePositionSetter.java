package domain.controller;

import java.util.ArrayList;

import domain.ObstacleConfiguration;
import domain.obstacle.Obstacle;


public class ObstaclePositionSetter {
	
	ArrayList<Obstacle> listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();	
	Obstacle obs;
	int x_pos;
	int y_pos;
	public ObstaclePositionSetter(Obstacle obs , int x_pos , int y_pos){
		this.obs = obs;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		
	}
	
	public void setPosition(int x , int y) {
		this.obs.setX_position(x);
		this.obs.setY_position(y);
	}
	
	

}
