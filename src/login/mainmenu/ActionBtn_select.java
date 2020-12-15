package login.mainmenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class ActionBtn_select extends JFrame implements ActionListener{



	JButton btn;
	int on=0;


	public ActionBtn_select(JButton btn) {

		this.btn = btn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(on++%2==0) {
			btn.setBackground(Color.orange);
			btn.setForeground(Color.BLACK); 
			btn.setSelected(true);
			System.out.println();
			 
		}else {
			btn.setBackground(Color.BLACK);
			btn.setForeground(Color.orange); 
			btn.setSelected(false);
			 
		}

	}
}
