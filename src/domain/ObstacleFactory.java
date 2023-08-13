package domain;

import java.util.Random;

import domain.obstacle.*;
import domain.strategy.ItsMoving;
import domain.strategy.ItsNotMoving;

public class ObstacleFactory {

	private static ObstacleFactory instance;

	private ObstacleFactory() {
	}

	public static ObstacleFactory getInstance() {
		if (instance == null) {
			instance = new ObstacleFactory();
		}
		return instance;
	}

	public Obstacle getSimpleObstacle(int x_position, int y_position) {

		SimpleObstacle simpleObstacle = new SimpleObstacle();
		simpleObstacle.setX_position(x_position);
		simpleObstacle.setY_position(y_position);

		Random rand3 = new Random();
		if (rand3.nextInt(2) + 1 == 2) {
			simpleObstacle.setMoving(new ItsMoving());
			simpleObstacle.setIs_moving(true);
		} else {
			simpleObstacle.setMoving(new ItsNotMoving());
			simpleObstacle.setIs_moving(false);

		}
		return simpleObstacle;
	}

	public Obstacle getFirmObstacle(int x_position, int y_position) {

		FirmObstacle firmObstacle = new FirmObstacle();
		firmObstacle.setX_position(x_position);
		firmObstacle.setY_position(y_position);
		Random rand4 = new Random();
		if (rand4.nextInt(2) + 1 == 2) {
			firmObstacle.setMoving(new ItsMoving());
			firmObstacle.setIs_moving(true);

		} else {
			firmObstacle.setMoving(new ItsNotMoving());
			firmObstacle.setIs_moving(false);

		}
		return firmObstacle;
	}

	public Obstacle getExplosiveObstacle(int x_position, int y_position) {

		ExplosiveObstacle explosiveObstacle = new ExplosiveObstacle();
		explosiveObstacle.setX_position(x_position);
		explosiveObstacle.setY_position(y_position);

		Random rand5 = new Random();
		if (rand5.nextInt(2) + 1 == 2) {
			explosiveObstacle.setMoving(new ItsMoving());
			explosiveObstacle.setIs_moving(true);

		} else {
			explosiveObstacle.setIs_moving(false);

			explosiveObstacle.setMoving(new ItsNotMoving());
		}

		return explosiveObstacle;
	}

	public Obstacle getGiftObstacle(int x_position, int y_position) {

		GiftObstacle giftObstacle = new GiftObstacle();
		giftObstacle.setX_position(x_position);
		giftObstacle.setY_position(y_position);
		giftObstacle.setMoving(new ItsNotMoving());
		giftObstacle.setIs_moving(false);

		return giftObstacle;
	}
	public Obstacle getHollowObstacle(int x_position, int y_position) {

		HollowObstacle hollowObstacle = new HollowObstacle();
		hollowObstacle.setX_position(x_position);
		hollowObstacle.setY_position(y_position);

		Random rand3 = new Random();
		if (rand3.nextInt(2) + 1 == 2) {
			hollowObstacle.setMoving(new ItsMoving());
			hollowObstacle.setIs_moving(true);
		} else {
			hollowObstacle.setMoving(new ItsNotMoving());
			hollowObstacle.setIs_moving(false);

		}
		return hollowObstacle;
	}
}
