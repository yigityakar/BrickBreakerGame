package panelMessages;

import javax.swing.JOptionPane;
import constants.UI;
import domain.validity.ExcessObstacles;

public class MinRequirementError {
	
	
	String message  = "Minimum Requirements : \n"
			+ "Simple Obstacle : 75 \n"
			+ "Firm Obstacle : 10\n"
			+ "Explosive Obstacle : 5 \n"
			+ "Gift Obstacle : 10\n"
			+ "Max Obstacle Number : "+UI.MAX_NUMBER_OF_OBSTACLES +"\n";
	
	
	public void showMessage() {
		JOptionPane.showMessageDialog(null, message, "Minimum requirements not satisfied", JOptionPane.WARNING_MESSAGE);
	}
	
	
	
	

}
