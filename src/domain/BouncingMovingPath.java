package domain;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import domain.controller.NewGameHandler;
import domain.obstacle.Obstacle;
import domain.paths.EnchantedPath;

public class BouncingMovingPath implements EnchantedPath {

	Rectangle ballRect;
	Rectangle obj;
	Rectangle phantasm;
	Shape np;
	Shape ballShape;
	int radius;
	private static final int LEFT_DIRECTION = 1;
	private static final int RIGHT_DIRECTION = 2;
	private static final int ABOVE_DIRECTION = 3;
	private static final int BOTTOM_DIRECTION = 4;
	ArrayList<Obstacle> listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();

	double currentX, currentY;
	// This makes the first step 0
	double deltaX, deltaY;

	public BouncingMovingPath(double X1, double Y1, double deltaX, double deltaY, int radius) {
		currentX = X1;
		currentY = Y1;
		this.radius = radius;
		this.deltaX = deltaX;
		this.deltaY = deltaY;

		phantasm = new Rectangle(NoblePhantasm.x_coor, NoblePhantasm.y_coor, NoblePhantasm.width, NoblePhantasm.height);
		ballRect = new Rectangle((int) currentX, (int) currentY, radius, radius);
		np = NoblePhantasm.shape;
		ballShape = new Rectangle2D.Double((int) currentX, (int) currentY, radius, radius);

	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public int getHitDirection(int x_p, int y_p) {
		// TODO Auto-generated method stub
		for (Obstacle ob : listOfObstacle) {
			obj = new Rectangle(ob.getX_position(), ob.getY_position(), ob.getLength(), ob.getWidth());
			if (ballRect.intersects(obj)) {
				if (currentX + radius - 1 <= obj.x || currentX + 1 >= obj.x + obj.width) {
					if (ob.getHasParticle() == true) {
						ExplosiveParticleList.getInstance().addParticles(ob);
					}
					if (ob.getHasGiftBox() == true) {
						GiftBoxList.getInstance().addGiftBox(ob);
					}
					ObstacleConfiguration.getInstance().hitHappened(ob);
					if (currentX + radius - 1 <= obj.x) {
						return LEFT_DIRECTION;
					} else {
						return RIGHT_DIRECTION;
					}
				} else {
					if (ob.getHasParticle() == true) {
						ExplosiveParticleList.getInstance().addParticles(ob);
					}
					if (ob.getHasGiftBox() == true) {
						GiftBoxList.getInstance().addGiftBox(ob);
					}
					ObstacleConfiguration.getInstance().hitHappened(ob);
					if (currentY > obj.y) {
						return ABOVE_DIRECTION;
					} else {
						return BOTTOM_DIRECTION;
					}
				}

			}
		}
		return 0;
	}

	@Override
	public Point2D.Double nextPosition() {

		currentX = currentX + deltaX;
		currentY = currentY + deltaY;
		ballRect = new Rectangle((int) currentX, (int) currentY, radius, radius);
		return new Point2D.Double((currentX + deltaX), (currentY + deltaY));

	}

	@Override
	public Point2D.Double currentPosition() {

		return new Point2D.Double(currentX, currentY);
	}

}
