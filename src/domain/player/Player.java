package domain.player;

import java.util.ArrayList;

import domain.obstacle.Obstacle;

public class Player {
	
	private String username;
	private String password;
	
	//players saved game variables
	private int score;
	private int lives;
	private ArrayList<Obstacle> listOfSavedGameObstacles;
	
	
	public Player() {
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public ArrayList<Obstacle> getListOfSavedGameObstacles() {
		return listOfSavedGameObstacles;
	}
	public void setListOfSavedGameObstacles(ArrayList<Obstacle> listOfSavedGameObstacles) {
		this.listOfSavedGameObstacles = listOfSavedGameObstacles;
	}
	
	
	
	
	
}
