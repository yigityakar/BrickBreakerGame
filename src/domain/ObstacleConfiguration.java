package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import domain.obstacle.*;
import domain.strategy.ItsMoving;
import domain.strategy.ItsNotMoving;
import domain.controller.PlayerHandler;
import domain.listeners.LifeListener;

public class ObstacleConfiguration {

	private static ObstacleConfiguration instance;

	final int L = 10;

	List<LifeListener> listeners = new ArrayList<>();

	private int[] numberOfObstacles;
	private ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();
	private int[][] positions;
	private int[] orderOfObjects;

	private static int simple;
	private static int firm;
	private static int explosive;
	private static int gift;

	private ObstacleConfiguration() {

	}

	public static ObstacleConfiguration getInstance() {
		if (instance == null) {
			instance = new ObstacleConfiguration();
		}
		return instance;
	}

	public void addAlarmListener(LifeListener lis) {
		listeners.add(lis);
	}

	public void hitHappened(Obstacle ob) {
		ob.setLife(ob.getLife() - 1);
		if (ob.getLife() <= 0) {
			listOfObstacles.remove(ob);
			PlayerHandler.getInstance().increaseScore();
		}
	}

	public void publishHitEvent(Obstacle ob) {
		for (LifeListener l : listeners)
			l.onHitEvent();
	}

	public void initializeObstacleConfiguration(int simple, int firm, int explosive, int gift) {

		this.simple = simple;
		this.firm = firm;
		this.explosive = explosive;
		this.gift = gift;

		listOfObstacles = new ArrayList<Obstacle>();
		positions = setPositionList();
		numberOfObstacles = new int[4];
		numberOfObstacles[0] = simple;
		numberOfObstacles[1] = firm;
		numberOfObstacles[2] = explosive;
		numberOfObstacles[3] = gift;

		orderOfObjects = setObstacleOrder(simple, firm, explosive, gift);

		initializeObstacles();
	}

	public int[][] setPositionList() {

		int[][] positions = new int[250][2];
		int count = 1;
		int cor1 = 0;
		int cor2 = 0;
		int delta1 = 40;
		int delta2 = 35;

		for (int x = 0; x < 250; x++) {

			positions[x][0] = cor1;
			positions[x][1] = cor2;

			if (count % 20 == 0 && count != 0) {
				cor2 += delta2;
				cor1 = 0;
			} else {

				cor1 += delta1;
			}
			count++;

		}
		return positions;

	}

	public int[] setObstacleOrder(int simple, int firm, int explosive, int gift) {

		int total = simple + firm + explosive + gift;

		int count = 0;

		int[] list = new int[total];

		Random rand = new Random();

		while (count != total) {
			int index = rand.nextInt(4);

			if (numberOfObstacles[index] != 0) {
				list[count] = index;
				numberOfObstacles[index] = numberOfObstacles[index] - 1;
				count++;
			}

		}

		return list;
	}

	public void initializeObstacles() {
		Random rand = new Random();
		int length = orderOfObjects.length;
		Obstacle obstacle;
		int count = 0;

		while (count < length) {

			int pos_index = rand.nextInt(positions.length);

			if (positions[pos_index][0] != -1) {

				switch (orderOfObjects[count]) {
				case 0:
					obstacle = ObstacleFactory.getInstance().getSimpleObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 1:
					obstacle = ObstacleFactory.getInstance().getFirmObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 2:
					obstacle = ObstacleFactory.getInstance().getExplosiveObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 3:
					obstacle = ObstacleFactory.getInstance().getGiftObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				}
				positions[pos_index][0] = -1;
				positions[pos_index][1] = -1;
				count++;

			}

		}

	}

	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] positions) {
		this.positions = positions;
	}

	public int getSimple() {
		return simple;
	}

	public void setSimple(int simple) {
		this.simple = simple;
	}

	public int getFirm() {
		return firm;
	}

	public void setFirm(int firm) {
		this.firm = firm;
	}

	public int getExplosive() {
		return explosive;
	}

	public void setExplosive(int explosive) {
		this.explosive = explosive;
	}

	public int getGift() {
		return gift;
	}

	public void setGift(int gift) {
		this.gift = gift;
	}

	public void setListOfObstacles(ArrayList<Obstacle> listOfObstacles) {
		this.listOfObstacles = listOfObstacles;
	}

	public ArrayList<Obstacle> getListOfObstacles() {
		return listOfObstacles;
	}

	public void addObstacle(Obstacle o) {
		this.listOfObstacles.add(o);
		
	}

}
