package domain;

import domain.listeners.PositionListener;

public class Cannon {
//implements PositionListener{
	private int x_position;
	private int y_position;
	
	public Cannon() {
		
	}
	
	public int getX_position() {
		return x_position;
	}
	public void setX_position(int x_position) {
		this.x_position = x_position;
	}
	public int getY_position() {
		return y_position;
	}
	public void setY_position(int y_position) {
		this.y_position = y_position;
	}
	
	
	
	
	/*
	@Override
	public void onPositionEvent(NoblePhantasm phantasm) {
		// TODO Auto-generated method stub
		this.x_position = phantasm.getX_coor();
		this.y_position = phantasm.getY_coor();
	}
	
	*/

}
