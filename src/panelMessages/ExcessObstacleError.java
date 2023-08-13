package panelMessages;

import javax.swing.JOptionPane;
import constants.UI;

public class ExcessObstacleError {
	
	

	String message  = "Max Number of Obstacles is " + UI.MAX_NUMBER_OF_OBSTACLES;
	
	
	public void showMessage() {
		JOptionPane.showMessageDialog(null, message, "Obstacle number exceed error", JOptionPane.WARNING_MESSAGE);
	}
	
	

}
