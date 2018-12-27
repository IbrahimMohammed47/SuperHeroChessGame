package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.game.Game;

public class KeyboardManager implements KeyListener{

	
	
private Game g ;
int keyCode ;
	
	public KeyboardManager(Game g){
		g = this.g ;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	public int getPressedKey() {
		return keyCode ;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keyCode = 0 ;
	}

}
