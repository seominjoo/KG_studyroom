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
import login.findPW.FindPasswordPage;
import login.page.AdminPage;
import login.page.LoginPage;
import login.signUp.SignUpEnum;
import login.signUp.SignUpPage;

public class PhoneNumberClearTextField extends MouseAdapter {

	JTextField[] phoneTotal;
	JTextField phoneText;
	
	public PhoneNumberClearTextField(JTextField phoneText , String page) {
		this.phoneText = phoneText;
		if(page.equals("로그인")) {
			phoneTotal = LoginPage.phoneTotal;
		}
		else if(page.equals("회원가입")){
			phoneTotal = SignUpPage.phoneTotal;
		}
		else if(page.equals("비번찾기")) {
			phoneTotal = FindPasswordPage.phoneTotal;
		}
		else if(page.equals("관리자")) {
			phoneTotal = AdminPage.phoneTotal;
		}
	}

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
				LoginPage.loginpass.setText("비밀번호");
			}
			if(String.valueOf(AdminPage.admin_loginpass.getPassword()).equals("")) {
				AdminPage.admin_loginpass.setText("비밀번호");
			}
		}
		

		
		
//		int i = 0;
//		for (PhoneNumberEnum other : PhoneNumberEnum.values()) {
//			if (e.getButton() == MouseEvent.BUTTON1 && jtext[i].getText().equals(value.labelName)) {
//				jtext[i].setText("");
//			}
//			if (!(jtext[i].equals(jtext[i]) && jtext[i].getText().equals(""))) {
//				jtext[i].setText(value.labelName);
//			}
//			i++;
//			if (!other.equals(value) && other.text.getText().equals("")) {
//				other.text.setText(other.labelName);
//			}
//		}
//		for (SignUpEnum value : SignUpEnum.values()) {
//			if (String.valueOf(value.blindPW.getPassword()).equals(""))
//				value.blindPW.setText(value.labelName);
//			if (value.text.getText().equals(""))
//				value.text.setText(value.labelName);
//
//		}
	}
}
