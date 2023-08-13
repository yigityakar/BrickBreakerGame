package domain;


import java.awt.Point;
import java.awt.Rectangle;

import domain.paths.DownFallPath;
public class DownwardPath implements DownFallPath {
		
	
	private int deltaY;
	private int deltaX;
	private int X;
	private int Y;
	
	public DownwardPath() {
		 deltaY=1;
		 deltaX=0;
		
	}
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	@Override
	public Point nextPosition() {
		// TODO Auto-generated method stub
		X= X + deltaX;
		Y= Y  +deltaY;
            return new Point((int) (X),(int)(Y));
	}

	@Override
	public Point currentPosition() {
		// TODO Auto-generated method stub
		return new Point((int)X, (int) Y);
	}

}
