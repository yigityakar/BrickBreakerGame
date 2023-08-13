package domain.controller;

import domain.player.Player;

public class PlayerHandler {
	private static PlayerHandler instance;
	private Player player;
	private boolean newPlayer;
	    
	private PlayerHandler() {	
	}
	
	public static PlayerHandler getInstance() {
        if (instance == null) {
        	instance = new PlayerHandler();
        }
        return instance;
    }

	public Player getPlayer() {
		return player;
	}

	public void initializePlayer(String username, String password) {
		Player p = new Player();
		p.setUsername(username);
		p.setPassword(password);
		this.player = p;
	}

	public boolean isNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(boolean newPlayer) {
		this.newPlayer = newPlayer;
	}
	
	public int getLives() {
		return player.getLives();
	}
	public void setLives(int lives) {
		player.setLives(lives);
	}
	
	public int getScore() {
		return player.getScore();
	}
	public void setScore(int score) {
		player.setScore(score);
	}
	
	public void initializeScoreAndLives() {
		player.setLives(3);
		player.setScore(0);
	}
	
	public void decreaseLife() {
		setLives(getLives() - 1);
	}
	
	public void increaseScore() {
		GameController.getInstance().getGameTime().stopTime();
		int additionalTime = GameController.getInstance().getGameTime().measureBetween();
		int additionalScore = 300 / (GameController.getInstance().getGameTime().getCurrentTime() + additionalTime);
		setScore(getScore() + additionalScore);
		System.out.println("score = " + getScore());
	}

	
	
	
	
	
	
    	
}

