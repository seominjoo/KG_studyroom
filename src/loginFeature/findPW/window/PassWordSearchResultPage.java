package loginFeature.findPW.window;

import javax.swing.JLabel;

import loginFeature.findPW.ClickFindPasswordPage;

public class PassWordSearchResultPage {

	public PassWordSearchResultPage(JLabel foundPW, String password) {
		if(ClickFindPasswordPage.password == null) {
			foundPW.setText("�ش� ��ȭ��ȣ�� �������� �ʽ��ϴ�");
		}
		else {
			foundPW.setText("��� ��ȣ : " + password);
		}
			
	}
	
}
