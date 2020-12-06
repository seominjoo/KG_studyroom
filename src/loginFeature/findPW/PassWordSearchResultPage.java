package loginFeature.findPW;

import javax.swing.JLabel;

public class PassWordSearchResultPage {

	public PassWordSearchResultPage(JLabel foundPW, String password) {
		if(ClickFindPasswordPage.password == null) {
			foundPW.setText("해당 전화번호는 존재하지 않습니다");
		}
		else {
			foundPW.setText("비밀 번호 : " + password);
		}
			
	}
	
}
