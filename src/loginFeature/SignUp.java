package loginFeature;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JFrame implements MouseListener {

	SignUpEnum sign;

	public SignUp() {
		// sign.value.text;
		// sign.value.label;

		int view = 0;
		for (SignUpEnum value : SignUpEnum.values()) {
			JLabel label = value.label;
			JTextField text = value.text;

			label.setBounds(0, view, 500, 50);
			view += 50;
			text.setBounds(0, view, 500, 50);
			view += 50;
			add(text);
			add(label);
		}
		
		SwingTools.initTestFrame(this);
		
//		name = new JTextField();
//		name.setBounds(50, 50, 300, 50);
//		phoneNumber = new JTextField();
//		phoneNumber.setBounds(50,  150, 300, 50);
//		password = new JTextField();
//		password.setBounds(50,  250, 300, 50);
//		
//
//		add(name);
//
//		add(phoneNumber);
//		
//		add(password);
		
	}

	public static void main(String[] args) {
		new SignUp();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() > 150 && e.getX() < 300 && e.getY() > 0 && e.getY() < 30) {
//	            name.setText("");
			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
