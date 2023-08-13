package ui;


import javax.swing.JFrame;

import constants.UI;
import domain.EnchantedSphere;
import domain.controller.NewGameHandler;
import domain.timertask.UpdateSphereTask;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class RunningModeFrame extends JFrame {
	public EnchantedSphere ball;

	public RunningModeFrame() {
		
		NewGameHandler.getInstance().initializeNewGame();
		JFrame rmf = new JFrame();
		rmf.setSize(UI.RUNNING_MODE_FRAME_WIDTH, UI.RUNNING_MODE_FRAME_HEIGHT);
		rmf.setVisible(true);
		rmf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rmf.setLocationRelativeTo(null);
		RunningModePanel rmp = new RunningModePanel();
		rmf.add(rmp);

		rmf.setResizable(false);
		
		
		
		
		
	}
	
}


