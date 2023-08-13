package ui;

import javax.swing.JOptionPane;

public class HelpPanel {
	
	
	private static HelpPanel instance;
	
	
		private HelpPanel() {
		
		}
	
		public static HelpPanel getInstance() {
			if (instance == null) {
				instance = new HelpPanel();
			}
			return instance;
		}
	
		String message  = "Left arrow key moves noble phantasm to the left \n"
				+ "Right arrow key moves noble phantasm to the right\n"
				+ "A key rotates the noble phantasm to the left"
				+ "D key rotates the noble phantasm to the right \n"
				+ "Move the noble phantasm to start the game \n"
				+ "If the enchanted sphere get drop down you will lose one of your lives"
				+ "You have 3 lives, if you lose all your lives game will end\n";
	
	
		public void showHelpMessage() {
			JOptionPane.showMessageDialog(null, message, "How to Play", JOptionPane.DEFAULT_OPTION);
		}

	
	

}
