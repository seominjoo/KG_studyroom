package loginFeature;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUp extends JFrame implements MouseListener{

	JTextField name;
	
	public SignUp() {
		
	

		
		name = new JTextField("입력");
		name.setBounds(50, 50, 400, 50);
		

		
		// KeyAdapter : KeyListener를 전부 구현하지 않아도 되도록 추상클래스로 바꿔놓은 클래스



		add(name);

		SwingTools.initTestFrame(this);
	}
	
	
	
	public static void main(String[] args) {
		new SignUp();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		 if( e.getX() > 150 && e.getX() < 300 && e.getY() > 0 && e.getY() < 30)
	        {   
	            name.setText("");
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
