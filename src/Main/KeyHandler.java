package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT) {
			gp.RIGHT_PRESSED = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			gp.LEFT_PRESSED = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT) {
			gp.RIGHT_PRESSED = false;
		}
		if(code == KeyEvent.VK_LEFT) {
			gp.LEFT_PRESSED = false;
		}
	}
	
}
