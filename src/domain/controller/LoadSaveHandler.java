package domain.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import constants.UI;
import domain.EnchantedSphere;
import domain.NoblePhantasm;
import domain.ObstacleConfiguration;
import domain.ObstacleFactory;
import domain.obstacle.Obstacle;
import domain.strategy.ItsMoving;
import domain.strategy.ItsNotMoving;
import ui.RunningModeFrame;

public class LoadSaveHandler {

	private static LoadSaveHandler instance;

	private int score;
	private int lives;
	private ArrayList<Obstacle> listOfSavedGameObstacles;

	private LoadSaveHandler() {

	}

	public static LoadSaveHandler getInstance() {
		if (instance == null) {
			instance = new LoadSaveHandler();
		}
		return instance;
	}

	public void loadGame(String path) {

		FileAccessor.getInstance().loadGame(path);
	}

	public void saveGame() {

		FileAccessor.getInstance().saveGame();
	}

	public String getPathSymbol() {

		return FileAccessor.getInstance().getPathSymbol();
	}

	public String isRegistered(String username, String password) {
		return FileAccessor.getInstance().isRegistered(username, password);
	}
	
	
	public String isAuthenticated(String username, String password) {
		return FileAccessor.getInstance().isAuthenticated(username, password);
	}
	
	
	

}
