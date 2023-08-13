package domain;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.UI;
import domain.ability.GameObject;
import ui.RunningModeFrame;

public class NoblePhantasm extends GameObject{
	public static int width = UI.RUNNING_MODE_FRAME_WIDTH/10;
	public static int height = 20;
	public static int x_coor;
	public static int y_coor;
	public static int direction;
	//public static Rectangle rect; 
	public static Shape shape ;
	public static Shape rotated;
	public static int left_rot_count = 0;
	public static int right_rot_count = 0;
	AffineTransform at;
	AffineTransform img;
	public static int rot_measure = 0;
	
	
	//public int move_speed = width/2;
	public int move_speed = 3;
	
	
	
	
	public NoblePhantasm(int x ,int y,int direction) {
		NoblePhantasm.x_coor = x;
		NoblePhantasm.y_coor = y;
		NoblePhantasm.direction=direction;
		//rect = new Rectangle(NoblePhantasm.x_coor,NoblePhantasm.y_coor,width,height);
		shape = new Rectangle2D.Double(x_coor, y_coor, width, height);
		
		
		
	}  
	
	public void draw(Graphics g) {
		//g.fillRect(x_coor, y_coor, width, height);
		//AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
		//at.rotate(Math.toRadians(45));
		//BufferedImage paddle = LoadImage("src/powerup_increase.png");
		Graphics2D g2d= (Graphics2D) g;
		
		
		//g2d.drawImage(paddle,at,null);
		//AffineTransform at = AffineTransform.getRotateInstance(Math.PI/2, x_coor + width/2,y_coor+ height/2 -10);
		 //rotated =  at.createTransformedShape(shape);
		
		//BufferedImage paddle = LoadImage("src/powerup_increase.png");
		g2d.setColor(Color.BLUE);
	      g2d.fill(shape);
		 
	      
	     
		//g2d.draw(rotated);
	   // g2d.drawImage(paddle,shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, null);
		
		
		
		
		//g2d.drawImage(paddle, x_coor, y_coor, width, height, null);
		
		//g2d.drawImage(paddle,rect.x, rect.y, rect.width, rect.height, null);
		
		//g2d.rotate(Math.toRadians(45), x_coor, y_coor);
		//g2d.drawImage(paddle,rect.x, rect.y, rect.width, rect.height, null);
		
		
	}
	
	
	public void moveLeft() {
		if(x_coor<0 ) {
			x_coor = 0;
			//rect = new Rectangle(NoblePhantasm.x_coor,NoblePhantasm.y_coor,width,height);
			
			shape = new Rectangle2D.Double(x_coor, y_coor, width, height);
			left_rot_count = 0;
			right_rot_count = 0;
			
			direction=-1;
			
		}else {
			
			x_coor = x_coor - move_speed;
			//rect = new Rectangle(NoblePhantasm.x_coor,NoblePhantasm.y_coor,width,height);
			
			//y_coor = rotated.getBounds().y;
			shape = new Rectangle2D.Double(x_coor, y_coor, width, height);
			
			//rotated.getBounds().x = x_coor;
			//rotated.getBounds().y = y_coor;
			left_rot_count = 0;
			right_rot_count = 0;

			direction=-1;

	}
	}
	
	public void moveRight() {
		if(x_coor > UI.RUNNING_MODE_FRAME_WIDTH - width) {
			x_coor = UI.RUNNING_MODE_FRAME_WIDTH - width;
			//rect = new Rectangle(NoblePhantasm.x_coor,NoblePhantasm.y_coor,width,height);
			shape = new Rectangle2D.Double(x_coor, y_coor, width, height);
			left_rot_count = 0;
			right_rot_count = 0;

			direction=1;

		}else {
			
			x_coor = x_coor + move_speed;
			//rect = new Rectangle(NoblePhantasm.x_coor,NoblePhantasm.y_coor,width,height);
			shape = new Rectangle2D.Double(x_coor, y_coor, width, height);
			left_rot_count = 0;
			right_rot_count = 0;

			direction=1;
			
			
		}
		 
	}
	
	public void rotateRight(double angle) {
		/*
		if(right_rot_count == 2) {
			System.out.println("/////////////////////////111111111111111////////////////////////");
			left_rot_count = -2;
		}
		*/
       if(right_rot_count < 45) {
    	    at = AffineTransform.getRotateInstance(angle, x_coor + width/2,y_coor+ height/2 );
    	    
  		 shape =  at.createTransformedShape(shape);
  		
  		 right_rot_count ++;
  		 rot_measure++;
  		 //System.out.println("rot_measure right : " + rot_measure );
       }
		
		
		//g2d.drawImage(paddle,at,null);
		
		
		
	}
	
	public void rotateLeft(double angle) {
		/*
		if(left_rot_count == 2) {
			System.out.println("///////////////////////2222222222222222222//////////////////////////");
			right_rot_count= -2;
		}
		*/
		if(left_rot_count < 45) {
			 at = AffineTransform.getRotateInstance(angle, x_coor + width/2,y_coor+ height/2 );
			 shape =  at.createTransformedShape(shape);
			 		
			// shape = rotated;
			 left_rot_count ++;
			rot_measure--;
			//System.out.println("LLLLLLLLLLLLLLLLLLLLL");
		}
		
	}
	
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int widthNew) {
		this.width = widthNew;
		shape = new Rectangle2D.Double(x_coor, y_coor, widthNew, height);;
	}

	

	public int getX_coor() {
		return x_coor;
	}

	public void setX_coor(int x_coor) {
		this.x_coor = x_coor;
	}

	public int getY_coor() {
		return y_coor;
	}

	public void setY_coor(int y_coor) {
		this.y_coor = y_coor;
	}

	public static int getDirection() {
		return direction;
	}

	public static void setDirection(int direction) {
		NoblePhantasm.direction = direction;
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

	@Override
	public void enhance() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
