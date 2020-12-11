package login.findPW.window;

import javax.swing.JLabel;

import login.findPW.*;

public class PassWordSearchResultPage {

	public PassWordSearchResultPage(JLabel foundPW, String password) {
		if(password.equals("")) {
			foundPW.setText("해당 전화번호는 존재하지 않습니다");
		}
		else {
			foundPW.setText("비밀 번호 : " + password);
		}
			
	}
	
}
