package loginFeature;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class LimitJtextField implements KeyListener{

	private JTextField text;
	
	public LimitJtextField(JTextField text) {
		this.text = text;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		JTextField src = (JTextField) e.getSource();
		if(src.getText().length() >= 4)
			e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
