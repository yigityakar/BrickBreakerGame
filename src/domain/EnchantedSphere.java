package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import constants.UI;
import domain.NoblePhantasm;
import domain.ability.GameObject;
import domain.obstacle.Obstacle;

public class EnchantedSphere extends GameObject {

	BouncingMovingPath myPath;

	double deltaX, deltaY;
	double x, y;
	public static int radius = 16;
	private boolean isUnstoppable = false;
	private static final int LEFT_DIRECTION = 1;
	private static final int RIGHT_DIRECTION = 2;
	private static final int ABOVE_DIRECTION = 3;
	private static final int BOTTOM_DIRECTION = 4;

	Rectangle2D.Double ballRect;
	// Rectangle2D ballRect;
	Rectangle obj;
	Rectangle phantasm;
	// Shape phantasm;
	Shape np;
	Shape ballShape;

	ArrayList<Obstacle> listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();

	public EnchantedSphere(int deltaX, int deltaY, int x, int y) {

		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.x = x;
		this.y = y;
		ballRect = new Rectangle2D.Double(this.x, this.y, radius, radius);
		ballShape = new Rectangle2D.Double(this.x, this.y, radius, radius);
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);

	}

	public BouncingMovingPath getMyPath() {
		return myPath;
	}

	public void setMyPath(BouncingMovingPath myPath) {
		this.myPath = myPath;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);

	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point2D.Double pos = myPath.currentPosition();
		Ellipse2D.Double shape = new Ellipse2D.Double(pos.getX(), pos.getY(), radius, radius);

		g.setColor(Color.BLACK);

		// g.fillOval(pos.getX(), pos.getY(), radius, radius);
		g2d.fill(shape);

	}

	public void move() {
		Point2D.Double cpos = myPath.currentPosition();

		this.x = cpos.getX();
		this.y = cpos.getY();
		phantasm = new Rectangle(NoblePhantasm.x_coor, NoblePhantasm.y_coor, NoblePhantasm.width, NoblePhantasm.height);
		// ballRect = new Rectangle2D.Double(this.x,this.y,radius,radius);
		ballRect = new Rectangle2D.Double(this.x, this.y, radius, radius);
		// phantasm = new
		// Rectangle2D.Double(NoblePhantasm.x_coor,NoblePhantasm.y_coor,NoblePhantasm.width,NoblePhantasm.height);
		np = NoblePhantasm.shape;
		ballShape = new Rectangle2D.Double(this.x, this.y, radius, radius);

		// System.out.println("direction: " + NoblePhantasm.direction );

		Point2D.Double pos = myPath.nextPosition();

		int hitDirectoion = myPath.getHitDirection((int) pos.getX(), (int) pos.getY());

		if (!isUnstoppable()) {
			if (LEFT_DIRECTION == hitDirectoion) {
				this.deltaX = -this.deltaX;
				myPath = new BouncingMovingPath(pos.getX() - 4, pos.getY(), deltaX, deltaY, radius);

			} else if (RIGHT_DIRECTION == hitDirectoion) {
				this.deltaX = -this.deltaX;
				myPath = new BouncingMovingPath(pos.getX() + 4, pos.getY(), deltaX, deltaY, radius);

			} else if (ABOVE_DIRECTION == hitDirectoion) {
				this.deltaY = -this.deltaY;
				myPath = new BouncingMovingPath(pos.getX(), pos.getY() + 4, deltaX, deltaY, radius);

			} else if (BOTTOM_DIRECTION == hitDirectoion) {
				this.deltaY = -this.deltaY;
				myPath = new BouncingMovingPath(pos.getX(), pos.getY() - 4, deltaX, deltaY, radius);

			}
		}

		if (pos.getY() + 2 < 0) {
			this.deltaY = -deltaY;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		} else if (pos.getY() >= UI.RUNNING_MODE_FRAME_HEIGHT) {
			this.deltaY = 0;
			this.deltaX = 0;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), 0, 0, radius);

		} else if (np.intersects(ballShape.getBounds2D())) {

			DeltaCalculator deltaCalculator = new DeltaCalculator();
			this.deltaX = deltaCalculator.getDeltaX(this.deltaX, this.deltaY, NoblePhantasm.rot_measure);
			this.deltaY = -deltaCalculator.getDeltaY(this.deltaX, this.deltaY, NoblePhantasm.rot_measure);

			System.out.println("X " + this.deltaX);
			System.out.println("Y " + this.deltaY);

			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), this.deltaX, this.deltaY, radius);

		}

		else if (pos.getX() + 2 <= 0) {

			deltaX = -deltaX;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		} else if (pos.getX() + radius >= UI.RUNNING_MODE_FRAME_WIDTH) {

			deltaX = -deltaX;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		}
	}

	BufferedImage LoadImage(String fileName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public boolean isUnstoppable() {
		return isUnstoppable;
	}

	public void setUnstoppable(boolean isUnstoppable) {
		this.isUnstoppable = isUnstoppable;
	}

	@Override
	public void enhance() {
		// TODO Auto-generated method stub
		setUnstoppable(true);


	}

}
