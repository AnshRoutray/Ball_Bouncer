package Main;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Ball Bouncer");
		GamePanel gamePanel = new GamePanel();
		
		window.add(gamePanel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGame();
	}
}
