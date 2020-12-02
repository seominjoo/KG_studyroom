package loginFeature;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JFrame{

	SignUpEnum sign;
	final int GRID = 3;
	JPanel grid_panel = new JPanel(new GridLayout(GRID, 1, 0, 5));

	public SignUp() {
		
		for (SignUpEnum value : SignUpEnum.values()) {
//			JLabel label = value.label;
//			grid_panel.add(label);
			JTextField text = value.text;
			grid_panel.add(text);
		}
		
		SwingTools.initTestFrame(this);
		setLayout(new GridLayout(3, 3, 0, 0));
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(grid_panel);
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		
	}

	public static void main(String[] args) {
		new SignUp();
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (e.getX() > 150 && e.getX() < 300 && e.getY() > 0 && e.getY() < 30) {
////	            name.setText("");
//			repaint();
//		}
//	}
}
