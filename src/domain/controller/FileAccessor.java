package domain.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import domain.validity.InputValidity;
import ui.GameOptionPanel;
import ui.RunningModeFrame;

public class FileAccessor {

	private static FileAccessor instance;

	private int score;
	private int lives;
	private ArrayList<Obstacle> listOfSavedGameObstacles;
	private InputValidity inputValidity;

	private FileAccessor() {
		inputValidity = new InputValidity();

	}

	public static FileAccessor getInstance() {
		if (instance == null) {
			instance = new FileAccessor();
		}
		return instance;
	}



	public String[] stringList(File[] flist) {

		String[] l = new String[flist.length];

		int count = 0;
		for (File f : flist) {




			l[count] = f.getName();
			count++;
		}

		return l;
	}

	public File[] gameList() {

		String pathSymbol = getPathSymbol();

		File folder = new File(
				System.getProperty("user.dir") + pathSymbol + "SaveFolder" + pathSymbol + PlayerHandler.getInstance().getPlayer().getUsername());
		File[] SaveList = folder.listFiles();


		File [] Saves = new File[SaveList.length-1];


		int c =0;
		for (File f : SaveList) {

			if(f.getName().equals("Empty.txt")) {
				continue;
			}

			Saves[c]= f;


			c++;
		}


		return Saves;

	}

	public void loadGame(String path) {

		listOfSavedGameObstacles = new ArrayList<Obstacle>();

		try {
			boolean oc = true;
			int score = 0;
			int lives = 5;
			File savedGame = new File(path);
			Scanner loadScanner = new Scanner(savedGame);

			while (loadScanner.hasNextLine()) {

				String a = loadScanner.nextLine();

				while (!a.equals("OBJECTS END") && oc) {

					int x = Integer.parseInt(a.split(" ")[0]);
					int y = Integer.parseInt(a.split(" ")[1]);
					String type = a.split(" ")[3];
					String moving = a.split(" ")[4];
					int live = Integer.parseInt(a.split(" ")[5]);
					Obstacle obstacle = new Obstacle();
					switch (type) {
					case "domain.obstacle.SimpleObstacle":
						obstacle = ObstacleFactory.getInstance().getSimpleObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {
							obstacle.setIs_moving(true);
							obstacle.setMoving(new ItsMoving());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new ItsNotMoving());


						}

						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.obstacle.FirmObstacle":
						obstacle = ObstacleFactory.getInstance().getFirmObstacle(x, y);
						obstacle.setLife(live);

						//Random rand2 = new Random();
						//obstacle.setLife(rand2.nextInt(5) + 1);
						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new ItsMoving());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new ItsNotMoving());

						}
						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.obstacle.ExplosiveObstacle":

						obstacle=ObstacleFactory.getInstance().getExplosiveObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new ItsMoving());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new ItsNotMoving());

						}

						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.obstacle.GiftObstacle":

						obstacle= ObstacleFactory.getInstance().getGiftObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new ItsMoving());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new ItsNotMoving());

						}
						listOfSavedGameObstacles.add(obstacle);
						break;
					}

					// System.out.println(b);
					a = loadScanner.nextLine();
					if (a.equals("OBJECTS END")) {
						a = loadScanner.nextLine();
						oc = false;
						break;
					}

				}

				if (a.equals("SCORE START")) {
					// System.out.println("HERE");
					PlayerHandler.getInstance().getPlayer().setScore(Integer.parseInt(loadScanner.nextLine()));

				}

				if (a.equals("LIVES START")) {
					// System.out.println("HERE");
					PlayerHandler.getInstance().getPlayer().setLives(Integer.parseInt(loadScanner.nextLine()));

				}

			}

			System.out.println(score);
			System.out.println(lives);

			loadScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		ObstacleConfiguration.getInstance().setListOfObstacles(listOfSavedGameObstacles);

	}

	public void saveGame() {

		String username = PlayerHandler.getInstance().getPlayer().getUsername();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("-dd-MM-yyyy-HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();

		String date = dtf.format(now);
		String savename = username + date + ".txt";
		String pathSymbol = getPathSymbol();

		String s = System.getProperty("user.dir") + pathSymbol +"SaveFolder" + pathSymbol + username + pathSymbol + savename;
		File file = new File(s);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			PrintWriter pwriter = new PrintWriter(writer);
			System.out.println(file.getName());

			for (Obstacle o : ObstacleConfiguration.getInstance().getListOfObstacles()) {
				pwriter.print(o.getX_position() + " ");
				pwriter.print(o.getY_position() + " ");
				pwriter.print(o.getClass() + " ");
				pwriter.print(o.getIs_moving() + " ");
				pwriter.print(o.getLife());
				pwriter.println();

			}

			pwriter.println("OBJECTS END");

			pwriter.println("SCORE START");
			pwriter.println(1200);
			pwriter.println("SCORE END");

			pwriter.println("LIVES START");
			pwriter.println(PlayerHandler.getInstance().getPlayer().getLives());
			pwriter.println("LIVES END");

			pwriter.close();
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String getPathSymbol() {
		String symbol;
		if (System.getProperty("user.dir").startsWith("/")) {
			symbol = "/";
		} else {
			symbol = "\\";

		}
		return symbol;

	}

	public String isRegistered(String username,String password) {
		if(!inputValidity.isValidCredential(username, password)) {

			return "invalid";

		}
		else {
			PlayerHandler.getInstance().initializePlayer(username, password);
			PlayerHandler.getInstance().setNewPlayer(true);

			String symbol = LoadSaveHandler.getInstance().getPathSymbol();

			String path = System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + username;

			File f1 = new File(path);

			// if username does not exist

			if (!f1.exists()) {

				f1.mkdir();


				try {
					File empty = new File(f1 + symbol + "Empty.txt");
					FileWriter w2 = new FileWriter(empty);
					PrintWriter p2 = new PrintWriter(w2);
					p2.print("This is an automatically generated empty file to avoid vanishing folders by git");
					p2.close();
					w2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				File f2 = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "Userlist.txt");

				FileWriter writer;
				try {
					writer = new FileWriter(f2, true);
					PrintWriter pwriter = new PrintWriter(writer);

					pwriter.println(username + " " + password);

					writer.close();
					pwriter.close();
					return "RegisterSuccessfull";
					//frame.dispose();
					//GameOptionPanel gop = new GameOptionPanel();

				} catch (IOException e1) {
					return "FileNotFound";
					// TODO Auto-generated catch block

				}

			} else {

				return "User Already Exists";
			}
		}
	}





	public String isAuthenticated(String username, String password) {
		if(!inputValidity.isValidCredential(username, password)) {

			return "invalid";

		}
		else {
			String symbol = getPathSymbol();
			File userList = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "Userlist.txt");




			try {
				Scanner loginScanner = new Scanner(userList);
				while (loginScanner.hasNextLine()) {
					String line = loginScanner.nextLine();
					String name = line.split(" ")[0];
					String pass = line.split(" ")[1];

					if (name.equals(username)) {

						if (pass.equals(password)) {

							PlayerHandler.getInstance().initializePlayer(username, password);


							String path = System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + username;
							File folder = new File(path);
							File[] SaveList = folder.listFiles();

							if(SaveList.length==1) {

								PlayerHandler.getInstance().setNewPlayer(true);
							}
							else {
								PlayerHandler.getInstance().setNewPlayer(false);


							}

							return "Login Successfull";
							//frame.dispose();
							//GameOptionPanel gop = new GameOptionPanel();
							//userFound=true;


						} else {
							return "Invalid Password";

						}

					}
				}

				return "User Not Found";

			} catch (FileNotFoundException e1) {
				return "FILE NOT FOUND";
				// TODO Auto-generated catch block
			}
		}
	}














}
