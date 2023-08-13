/***********************************************************
 * 
 * Obstacle: superclass for all the obstacles in the game 
 * 
<<<<<<< HEAD
 * NOTES	  - draw() and hit()
 * 			  - fields: private or protected
 * 			  - AWT imports should be removed.
=======
 * BAKILMALI  - draw() and hit()
 * 			  - field'lar private/protected 
 * 				 - bunlari olabildigince private yapalim
 * 			  - AWT importlari kaldirilacak
>>>>>>> obstacle-update
 * 
 ***********************************************************/

package domain.obstacle;

import domain.strategy.Moving;

public class Obstacle {

	protected int life;
	protected int x_position;
	protected int y_position;
	protected int length;
	protected int width;
	protected boolean is_rectangle;
	protected boolean collusionOccured;
	protected boolean hasParticle;
	protected boolean drawable;
	protected boolean hasGiftBox;
	protected boolean is_moving;
	protected boolean hasText;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	protected String color;
	private Moving moving;

	// constructors
	public Obstacle() {
	}

	// getters and setters
	public boolean getIs_moving() {
		return is_moving;
	}

	public void setIs_moving(boolean is_moving) {
		this.is_moving = is_moving;
	}

	public boolean getHasGiftBox() {
		return hasGiftBox;
	}

	public void setHasGiftBox(boolean hasGiftBox) {
		this.hasGiftBox = hasGiftBox;
	}

	public boolean isDrawable() {
		return drawable;
	}

	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}

	public boolean getHasParticle() {
		return hasParticle;
	}

	public void setHasParticle(boolean hasParticle) {
		this.hasParticle = hasParticle;
	}

	public void setHasText(boolean hasText) {
		this.hasText = hasText;
	}

	public boolean getHasText() {
		return hasText;
	}

	public boolean getCollusionOccured() {
		return collusionOccured;
	}

	public void setCollusionOccured(boolean isHit) {
		this.collusionOccured = isHit;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isIs_rectangle() {
		return is_rectangle;
	}

	public void setIs_rectangle(boolean is_rectangle) {
		this.is_rectangle = is_rectangle;
	}

	public void tryToMove(Obstacle ob) {
		this.moving.move(ob);
	}

	public void setMoving(Moving newMowing) {
		moving = newMowing;
	}

}
