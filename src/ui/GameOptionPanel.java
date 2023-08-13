package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.UI;
import domain.ObstacleConfiguration;
import domain.controller.FileAccessor;
import domain.controller.NewGameHandler;
import domain.controller.PlayerHandler;

public class GameOptionPanel extends JPanel implements ActionListener {
	private JButton newgameb;
	private JButton loadgameb;
	private JLabel header;
	private JFrame frame;

	public GameOptionPanel(){
		
		//initialize the buttons
		newgameb = new JButton("New Game");
		newgameb.addActionListener(this);
		
		loadgameb = new JButton("Load Game");
		loadgameb.addActionListener(this);
		if(PlayerHandler.getInstance().isNewPlayer()) {
			
			
			loadgameb.setEnabled(false);
		}
        
        //initialize the label
		header = new JLabel("Choose a game option to continue");
        
		//create the layout
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        		  .addGap(100)
        		  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        				  .addComponent(header)
        				  .addComponent(newgameb)
        				  .addComponent(loadgameb))	  
        	      .addGap(100)
        	           	
        );
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        		  .addGap(100)
				  .addComponent(header)
				  .addComponent(newgameb)
				  .addComponent(loadgameb)	  
        );
        this.setLayout(layout);

        //create a frame, set its content pane
		frame = new JFrame();
		frame.setContentPane(this);
		frame.setSize(UI.LOGIN_FRAME_WIDTH, UI.LOGIN_FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }


	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
        
		if (e.getSource() == newgameb) {
			frame.dispose();
			GameFrame gframe = new GameFrame();
			NewGameHandler.getInstance().initializeRequirements();
			NewGameHandler.getInstance().subscribeToStrategies(gframe);
			
        }
		if (e.getSource() == loadgameb) {
			frame.dispose();

			SavedGamesPanel panel = new SavedGamesPanel();

			
			
			
			
			
			
			
			
			
        	
        }
	        
		
		
			
      
	}
	    
}

	
