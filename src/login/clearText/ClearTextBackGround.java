package login.clearText;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import login.PhoneNumberEnum;
import login.findPW.FindPasswordPage;
import login.signUp.SignUpEnum;

public class ClearTextBackGround extends MouseAdapter {

	
	JTextField phonenum;
	PhoneNumberEnum value;

	public ClearTextBackGround(JTextField phonenum, PhoneNumberEnum value) {
		this.phonenum = phonenum;
		this.value = value;
	}

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
			
	}

}
