package studyroom.design;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import studyroom.admin.AdminPage;
import studyroom.user.findPW.FindPasswordPageUser;
import studyroom.user.login.LoginPage;
import studyroom.user.signUp.PhoneNumberEnum;
import studyroom.user.signUp.SignUpEnum;

public class ClearTextBackGround extends MouseAdapter {

	
	JTextField phonenum;
	PhoneNumberEnum value;

	public ClearTextBackGround(JTextField phonenum, PhoneNumberEnum value) {
		this.phonenum = phonenum;
		this.value = value;
	}
	
	// 텍스트 밖을 눌렀을 때 텍스트 안을 초기화하는 클래스
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for (SignUpEnum value : SignUpEnum.values()) {
			if (value.text.getText().equals(""))
				value.text.setText(value.labelName);
			if (String.valueOf(value.blindPW.getPassword()).equals(""))
				value.blindPW.setText(value.labelName);
		}
			if (e.getButton() == MouseEvent.BUTTON1 && phonenum.getText().equals(""))
				phonenum.setText(value.labelName);
			if(e.getButton() == MouseEvent.BUTTON1 && String.valueOf(LoginPage.loginpass.getPassword()).equals("")) {
				LoginPage.loginpass.setText("");
			}
			if(e.getButton() == MouseEvent.BUTTON1 && String.valueOf(AdminPage.admin_loginpass.getPassword()).equals("")) {
				AdminPage.admin_loginpass.setText("");
			}
	}

}
