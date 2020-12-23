package login.clearText;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import login.PhoneNumberEnum;
import login.findPW.FindPasswordPageUser;
import login.page.AdminPage;
import login.page.LoginPage;
import login.signUp.SignUpEnum;
import login.signUp.SignUpPage;

public class PhoneNumberClearTextField extends MouseAdapter {

	JTextField[] phoneTotal;
	JTextField phoneText;
	
	public PhoneNumberClearTextField(JTextField phoneText , String page) {
		this.phoneText = phoneText;
		if(page.equals("�α���")) {
			phoneTotal = LoginPage.phoneTotal;
		}
		else if(page.equals("ȸ������")){
			phoneTotal = SignUpPage.phoneTotal;
		}
		else if(page.equals("���ã��")) {
			phoneTotal = FindPasswordPageUser.phoneTotal;
		}
		else if(page.equals("������")) {
			phoneTotal = AdminPage.phoneTotal;
		}
	}

	// ��ȭ��ȣ �ؽ�Ʈ ���� ������ �� �ش� �ؽ�Ʈ�� ""�� �ǰ�, ������ �ؽ�Ʈ�� �ʱ�ȭ�Ǵ� Ŭ����
	
	@Override
	public void mouseClicked(MouseEvent e) {

		for(int i = 0; i < phoneTotal.length; i++) {
			if(phoneText.equals(phoneTotal[i]) && phoneText.getText().equals(PhoneNumberEnum.values()[i].labelName)) {
				phoneText.setText("");
				for(SignUpEnum value : SignUpEnum.values()) {
					if(value.text.getText().equals("")) {
						value.text.setText(value.labelName);
						value.blindPW.setText(value.labelName);
					}
				}
			} else if(!(phoneText.equals(phoneTotal[i])) && phoneTotal[i].getText().equals("")) {
				phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
			}
			if(String.valueOf(LoginPage.loginpass.getPassword()).equals("")) {
				LoginPage.loginpass.setText("��й�ȣ");
			}
			if(String.valueOf(AdminPage.admin_loginpass.getPassword()).equals("")) {
				AdminPage.admin_loginpass.setText("��й�ȣ");
			}
		}
	}
}
