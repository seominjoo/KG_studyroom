package loginFeature.findPW;

import javax.swing.JLabel;

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
