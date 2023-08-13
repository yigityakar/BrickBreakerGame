package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.UI;
import domain.ObstacleConfiguration;
import domain.controller.ObstaclePositionSetter;
import domain.obstacle.Obstacle;


public class BuildingModeUI extends JPanel implements ActionListener,MouseListener, MouseMotionListener{
	private boolean first=true;
	public static int x;
	public static int y;
	Rectangle rect;
	int prex;
	int prey;
	boolean pressout = false;
	Obstacle obs;
	Rectangle intersect;
	ObstaclePositionSetter setter ;


	ArrayList<Obstacle> listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();	
	public BuildingModeUI() {

		JButton but = new JButton("Show Obstacles");
		this.add(but);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		but.addActionListener(e -> {
			
			
			
			repaint();
			
			
		});
	}
	
	public void paintComponent(Graphics g ) {
		listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();
        super.paintComponent(g);	
     
        for(Obstacle ob: listOfObstacle) {
        	
        	drawObstacle( g,  ob );
    		
    		
    			//buradaki first bug çözmek için eklendi, kısa süreli bir çözüm. düzeltilecek.
    			/*
    		if(first) {	
	    		if(ob.isHasText()) {
	    			LifeWindow amountField = new LifeWindow(ob);
	    			ObstacleConfiguration.getInstance().addAlarmListener(amountField);
	    	        this.add(amountField.getAmountField());
	
	    		}
        	}
        	first = false;

		*/
		 
		
		}
		
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		 
		int dx = e.getX() - x;
		 
		int dy = e.getY() - y;
		
		setter = new ObstaclePositionSetter(obs,obs.getX_position() + dx,obs.getY_position() + dy);
		setter.setPosition(obs.getX_position() + dx, obs.getY_position() + dy);
		
	
		
		
		
		
		x += dx;
		 
		y += dy;
		
		
	   repaint();
		 
		  }


	
	

	

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("buuuuu");
		
		x = e.getX();
		 
		y = e.getY();
		
     
		for(Obstacle ob: listOfObstacle) {
	      rect = new Rectangle(ob.getX_position(),ob.getY_position(),ob.getLength(),ob.getWidth());
	      if(rect.contains(x, y)) {
	    	
	    	  obs = ob;
	    	  prex = obs.getX_position();
	    	  prey = obs.getY_position();
	    	 
	      }
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("(((((((((((((((((((((((((((((((((((((");
		intersect = new Rectangle(obs.getX_position(),obs.getY_position(),obs.getLength(),obs.getWidth());
		
		for(Obstacle ob: listOfObstacle) {
			
		      rect = new Rectangle(ob.getX_position(),ob.getY_position(),ob.getLength(),ob.getWidth());
		      if(ob != obs) {
		    	  
		      if(Collision(rect,intersect)) {
		    	 
                  setter.setPosition(prex, prey);
		    	
		    	  
		      }
		      if(e.getX() + obs.getLength() > UI.RUNNING_MODE_FRAME_WIDTH || e.getX() - obs.getLength() < 0 || e.getY() + obs.getLength() > UI.RUNNING_MODE_FRAME_HEIGHT || e.getY() < 0) {
		    	  setter.setPosition(prex, prey);
		    	  
		    	
		      }
		      }
		     
			}
		repaint();
	}


	public boolean Collision (Rectangle rect1,Rectangle rect2) {
		
		if(rect1.intersects(rect2)) {
			return true;
		}else return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
public static void drawObstacle(Graphics g, Obstacle ob ) {
	
	int x_obst= ob.getX_position();
	int y_obst= ob.getY_position();
	int width_obst= ob.getWidth();
	int length_obst= ob.getLength();

	if(ob.getColor()=="blue") {
		g.setColor(Color.blue);
	}else if(ob.getColor()=="red") {
		g.setColor(Color.red);
	}else if(ob.getColor()=="yellow") {
		g.setColor(Color.yellow);
	}else if(ob.getColor()=="green") {
		g.setColor(Color.green);
	}
	if(ob.isIs_rectangle()) {
		g.fillRect(x_obst, y_obst, length_obst,width_obst);
		if(ob.getHasText()) {
			g.setColor(Color.DARK_GRAY);
			g.drawString(String.valueOf(ob.getLife()), x_obst+10, y_obst+15);
			
		}
	}else {
	g.fillOval(x_obst, y_obst, width_obst, width_obst);
	}
	
	
	
}


	
	
	
	
	
	

	
	
}
