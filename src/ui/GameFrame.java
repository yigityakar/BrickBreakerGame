package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import domain.controller.NewGameHandler;
import domain.listeners.ValidityListener;
import domain.validity.ExcessObstacles;
import domain.validity.MinRequirements;
import panelMessages.MinRequirementError;
import panelMessages.ExcessObstacleError;

public class GameFrame extends JFrame implements ValidityListener{
	private BuildingModeUI editingPanel;
	private GroupPanel group;

	public GameFrame(){

		editingPanel= new BuildingModeUI();
		group = new GroupPanel();
		group.setPreferredSize(new Dimension(200, 1));	
		group.setSize(new Dimension(400, 200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(group, BorderLayout.EAST);     
		this.add(editingPanel, BorderLayout.CENTER);

		this.setVisible(true);      
		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);


		JButton buto = new JButton("Start New Game");
		this.add(buto,BorderLayout.SOUTH);
		buto.addActionListener(e -> {

			//check the user input of obstacle numbers for building mode requirements
			boolean passAll = NewGameHandler.getInstance().checkRequirements(GroupPanel.simple_num, GroupPanel.firm_num, GroupPanel.explosive_num, GroupPanel.gift_num);

			//if it passes all the requirements, go to running mode
			if(passAll) {
				this.remove(group);
				this.dispose();
				RunningModeFrame rmf = new RunningModeFrame();

			}
		});
	}

	@Override
	public void onValidityEvent(String message) {
		// TODO Auto-generated method stub

		if (message.equals("Minimum requirements not satisfied.")) {
			MinRequirementError error = new MinRequirementError();
			error.showMessage();
		}
		else if (message.equals("Maximum number of obstacles is 120.")) {
			ExcessObstacleError error = new ExcessObstacleError();
			error.showMessage();
		}
	}






















}



