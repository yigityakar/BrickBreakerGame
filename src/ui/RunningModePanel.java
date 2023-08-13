package ui;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import constants.UI;
import domain.*;
import domain.ability.GameObject;
import domain.ability.PhantasmExpansion;
import domain.ability.UnstoppableSphere;
import domain.controller.GameController;
import domain.controller.PlayerHandler;
import domain.controller.YmirController;
import domain.controller.FileAccessor;
import domain.obstacle.Obstacle;

public class RunningModePanel extends JPanel implements KeyListener, ActionListener {
	private int delay = 4;
	
	ArrayList<Obstacle> listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();
	private Timer timer;
	public NoblePhantasm noblePhantasm;
	public EnchantedSphere ball;
	boolean firstt=true;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton saveButton;
	private JButton helpButton;
	boolean first = true;
	boolean second = false;
	int lives =10;	
	boolean padRight = false;
	boolean padLeft = false;
	boolean rotateRight = false;
	boolean rotateLeft = false;
	boolean rotateFromLeftToCenter = false;
	boolean rotateFromRightToCenter = false;

	
	public RunningModePanel() {
		
		// initialize game objects
        noblePhantasm = GameController.getInstance().getPhantasm();
        ball = GameController.getInstance().getBall();
        
        // initialize buttons
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setEnabled(false);
		add(saveButton);

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
        add(pauseButton);
        add(resumeButton);
        
        helpButton = new JButton("Help");
        helpButton.addActionListener(this);
        helpButton.setEnabled(false);
        add(helpButton);

	
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		//initialize timer
		timer = new Timer(delay, this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(padRight) {
			noblePhantasm.moveRight();
			NoblePhantasm.rot_measure = 0 ;
			rotateFromLeftToCenter = false;
			rotateFromRightToCenter = false;
			
		}
		
		if(padLeft) {
			noblePhantasm.moveLeft();
			//NoblePhantasm.rot_measure = 0 ;
			NoblePhantasm.rot_measure = 0;
			rotateFromLeftToCenter = false;
			rotateFromRightToCenter = false;
		}
		
		if(rotateRight) {
			noblePhantasm.rotateRight(Math.PI/180);;
			
		}
		//revert rotation
		if(rotateFromLeftToCenter ) {
			noblePhantasm.rotateRight(Math.PI/180);;
			//System.out.println("xxxxxxxxxxxxxxxxxxxxx");
			
		}
		
		
		if(rotateLeft) {
			noblePhantasm.rotateLeft(-Math.PI/180);
			
			
		}
		//revert rotation
		if(rotateFromRightToCenter ) {
			noblePhantasm.rotateLeft(-Math.PI/180);;
		}
		
		
		
		if(NoblePhantasm.rot_measure == 0 ) {
			
			rotateFromLeftToCenter = false;
			 rotateFromRightToCenter = false;
			 NoblePhantasm.right_rot_count = 0;
			 NoblePhantasm.left_rot_count = 0;
			 //System.out.println("LLLLLLLLLLLLLLLLLLLLL");
		
			
		}

		
		
		
		AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
		at.rotate(Math.toRadians(45));
		//BufferedImage paddle = LoadImage("src/powerup_increase.png");
		Graphics2D g2d= (Graphics2D) g;
		
		//g2d.drawImage(paddle,at,null);
		
		listOfObstacle = ObstacleConfiguration.getInstance().getListOfObstacles();   	
	
		
		//draw all the explosive particles
		for(ExplosiveParticle exp : ExplosiveParticleList.getInstance().getList()) {	
			
				g.setColor(Color.RED); 
				int xPositionParticle = exp.getX();
				int yPositionParticle = exp.getY();
				int radiusParticle =exp.getRadius();
				g.fillOval(xPositionParticle,yPositionParticle , radiusParticle, radiusParticle);   
			
		}
		
		//draw all the gift boxes
		for(GiftBox exp : GiftBoxList.getInstance().getList()) {	
				g.setColor(Color.GREEN); 
				int xPositionParticle = exp.getX();
				int yPositionParticle = exp.getY();
				int radiusParticle =exp.getRadius();
				g.fillOval(xPositionParticle,yPositionParticle , radiusParticle, radiusParticle);   	
		}

		try 
		{
		   GlobalMutex.getInstance().getSemaphore().acquire(1);
		   for(Obstacle ob: listOfObstacle) {		
			   drawObstacle(g,  ob ); 
				
				
			}
		} 
		catch (Exception e) 
		{
		// Logging
		}
		finally
		{
			GlobalMutex.getInstance().getSemaphore().release(1);
		}
		/*
		for(Obstacle ob: listOfObstacle) {
			
			
			ob.draw(g);
			 
			
			
		
			
		if(firstt) {	
		if(ob.isHasText()) {
			LifeWindow amountField = new LifeWindow(ob);
			ObstacleConfiguration.getInstance().addAlarmListener(amountField);
	     

		}
		
		}
		

		}
	
		//draw all the obstacles
		for(Obstacle ob: listOfObstacle) {
			ob.draw(g);
		
			if(firstt) {	
				if(ob.isHasText()) {
					LifeWindow amountField = new LifeWindow(ob);
					ObstacleConfiguration.getInstance().addAlarmListener(amountField);
				}
			}

		}
		*/
		
		firstt=false;
	
		g.setColor(Color.BLUE);
		if(ball.getDeltaX()==0 && ball.getDeltaY()==0  && !first) {
			
			GameController.getInstance().getGameTime().stopTime();
			GameController.getInstance().getGameTime().addCurrentTime();

			
			System.out.println("You lost one of your lives!!!!");
			timer.stop();
			PlayerHandler.getInstance().decreaseLife();
			ball.setDeltaX(0);
			ball.setDeltaY(0);
			ball.setX((UI.RUNNING_MODE_FRAME_WIDTH - EnchantedSphere.radius)/2);
			ball.setY(UI.RUNNING_MODE_FRAME_HEIGHT - (90 + EnchantedSphere.radius + 1));
			System.out.println("executed");
			noblePhantasm.setX_coor((UI.RUNNING_MODE_FRAME_WIDTH - UI.RUNNING_MODE_FRAME_WIDTH/10)/ 2);
			noblePhantasm.setY_coor(UI.RUNNING_MODE_FRAME_HEIGHT - 90);
			noblePhantasm.setDirection(0);
			
			
			
			if(PlayerHandler.getInstance().getLives()==0) {
				System.out.println("No lives left, GAME OVER!!!!");
				
				
			}
			else {
			
			System.out.println("Remaining lives: " + PlayerHandler.getInstance().getLives());
			second =true;	
			}
		}
		noblePhantasm.draw(g);
		ball.draw(g);
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		//right arrow pressed: move right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			padRight = true;
			
			if(first) {
				GameController.getInstance().getGameTime().startTime();
				
				GameController.getInstance().initializeAllTasks();
				YmirController.getInstance().initializeTimer();
				ball.setDeltaX(1);
				ball.setDeltaY(-1);
				timer.start();
				first=false;			
			}
			
			if(second) {
				GameController.getInstance().getGameTime().startTime();
				ball.setDeltaX(1);
				ball.setDeltaY(-1);
				timer.restart();
				second=false;
			}
			
			
			//noblePhantasm.moveRight();


		}
		// left arrow pressed: move left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			padLeft = true;
			
			if(first) {
				GameController.getInstance().getGameTime().startTime();
				GameController.getInstance().initializeAllTasks();
				YmirController.getInstance().initializeTimer();
				ball.setDeltaX(-1);
				ball.setDeltaY(-1);
				timer.start();
				first=false;
				
			}
			if(second) {
				GameController.getInstance().getGameTime().startTime();
				ball.setDeltaX(-1);
				ball.setDeltaY(-1);
				timer.restart();
				second=false;	
			}
			//noblePhantasm.moveLeft();

		}
		
		// A pressed: rotate left
		if(e.getKeyCode() == 65) {
			//System.out.println("A pressed");
			//noblePhantasm.rotateLeft(-Math.PI/9);
			rotateLeft = true;

		}
		// D pressed: rotate right
		if(e.getKeyCode() == 68) {
			//System.out.println("D pressed");
			//noblePhantasm.rotateRight(Math.PI/9);
			rotateRight = true;

		}
		// T pressed: phantasm expansion
		if(e.getKeyCode() == 84) {
			GameObject o = GameController.getInstance().getPhantasm();
			o = new PhantasmExpansion(o);
			o.enhance();
		}
		// U pressed: unstoppable enchanted sphere
		if(e.getKeyCode() == 85) {
			GameObject o = GameController.getInstance().getBall();
			o = new UnstoppableSphere(o);
			o.enhance();
		}
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			padRight =false;
			NoblePhantasm.direction=0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			NoblePhantasm.direction=0;
			padLeft =false;
			

		}
		
		// A pressed: rotation to left
		if(e.getKeyCode() == 65) {
			rotateLeft = false;
			rotateFromLeftToCenter = true;
			

			NoblePhantasm.right_rot_count = 0;
			NoblePhantasm.left_rot_count = 0;
			
		}
		// D pressed: rotation to right
		if(e.getKeyCode() == 68) {
			rotateRight = false;
			rotateFromRightToCenter = true;
			NoblePhantasm.right_rot_count = 0;
			NoblePhantasm.left_rot_count = 0;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//save the game
		if (e.getSource() == saveButton) {
			timer.stop();
			setFocusable(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			FileAccessor.getInstance().saveGame();

		}
		//pause the game, cancel all timer tasks
		if (e.getSource() == pauseButton) {
			GameController.getInstance().getGameTime().stopTime();
			GameController.getInstance().getGameTime().addCurrentTime();
			timer.stop();
			GameController.getInstance().cancelAllTasks();
			YmirController.getInstance().cancelTask();
			setFocusable(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			saveButton.setEnabled(true);
			helpButton.setEnabled(true);

		}
		//resume the game, reset all timer tasks
		if (e.getSource() == resumeButton) {
			GameController.getInstance().getGameTime().startTime();
			timer.restart();
			GameController.getInstance().initializeAllTasks();
			YmirController.getInstance().initializeTimer();
			setFocusable(true);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			helpButton.setEnabled(false);
			
		}
		
		if(e.getSource() == helpButton) {
			timer.stop();
			setFocusable(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			HelpPanel.getInstance().showHelpMessage();
		}


		repaint();
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
		}else if(ob.getColor()=="purple") {
			Color purple = new Color(203, 91, 252);
			g.setColor(purple);
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