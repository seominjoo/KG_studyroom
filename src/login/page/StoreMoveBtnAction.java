package login.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class StoreMoveBtnAction implements ActionListener{

	int g=0;
	String type;
	
	public StoreMoveBtnAction (int g, String type) {
		this.type = type;
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		StoreMovePage.seat_move_number=0;
		StoreMovePage.room_move_number=0;
		StoreMovePage.locker_move_number=0;
		
		StoreMovePage.seat_move_number=g+1;
		StoreMovePage.room_move_number=g+101;
		StoreMovePage.locker_move_number=g+1;
		
		StoreMovePage.type=type;
		
		new StoreMoveBtnPage();	
		
	}
}

