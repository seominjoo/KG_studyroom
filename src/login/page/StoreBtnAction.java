package login.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreBtnAction implements ActionListener{

	int g=0;
	String type;
	
	public StoreBtnAction (int g, String type) {
		this.type = type;
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		StoreManagementPage.seat_number=0;
		StoreManagementPage.room_number=0;
		StoreManagementPage.locker_number=0;
		
		StoreManagementPage.seat_number=g+1;
		StoreManagementPage.room_number=g+101;
		StoreManagementPage.locker_number=g+1;
		
		StoreManagementPage.type=type;
		
		new StoreBtnPage();	
	}
}
