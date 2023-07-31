package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	public int screenWidth = 800;
	public int screenHeight = 500;
	public int FPS = 100;
	public int ballChangeX = 4;
	public int ballChangeY = -4;
	public int SCORE = 0;
	public double deflectorSpeed = 4;
	public boolean RIGHT_PRESSED = false;
	public boolean LEFT_PRESSED = false;
	public boolean LOST = false;
	public Thread gameThread;
	public KeyHandler keyH = new KeyHandler(this);
	public Rectangle deflector = new Rectangle(screenWidth / 2, screenHeight  - 100, 50, 10);
	public Ellipse2D.Double ball = new Ellipse2D.Double(screenWidth / 2 + 20, screenHeight - 125,
			 10, 10);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		this.setBackground(Color.cyan);
		this.setVisible(true);
	}
	public void setUpGame() {
		
	}
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if(drawCount >= FPS) {
				SCORE++;
				drawCount = 0;
			}
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
		}
	}
	public void update() {
		
		//updating balls position
		
		ball.x = ball.x + ballChangeX;
		ball.y = ball.y + ballChangeY;
		
		if(RIGHT_PRESSED == true && LEFT_PRESSED == false && deflector.x <= screenWidth - 55) {
			deflector.x += deflectorSpeed;
		}
		if(LEFT_PRESSED == true && RIGHT_PRESSED == false && deflector.x >= 6) {
			deflector.x -= deflectorSpeed;
		}
		if(ball.x >= screenWidth || ball.x <= 6) {
			ballChangeX = -ballChangeX;
		}
		if(ball.y <= 6) {
			ballChangeY = -ballChangeY;
		}
		if(ball.x <= deflector.x + deflector.width && ball.x >= deflector.x && 
				ball.y >= deflector.y - 6 && ball.y <= deflector.y + 6) {
			ballChangeY = -ballChangeY;
			ballChangeX = ballChangeX -(int)Math.random() * 3 + 1;
		}
		if(ball.y >= screenHeight) {
			LOST = true;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font("Sans Serif", Font.BOLD, 20));
		g2.setColor(Color.orange);
		g2.fill(deflector);
		g2.setColor(Color.blue);
		g2.fill(ball);
		g2.setColor(Color.blue);
		g2.drawString("Score: " + SCORE, 30, 30);
		if(LOST) {
			g2.setColor(Color.red);
			g2.drawString("YOU LOST :(", screenWidth / 2 - 50, screenHeight / 2 - 5);
			gameThread.stop();
		}
	}

}
