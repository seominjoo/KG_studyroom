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
import login.signUp.SignUpEnum;
import login.signUp.SignUpPage;

public class PhoneNumberClearTextField extends MouseAdapter {

	PhoneNumberEnum value;
	JTextField[] jtext;
	
	public PhoneNumberClearTextField(PhoneNumberEnum value, boolean flag) {
		this.value = value;
		if(flag) {
			jtext = SignUpPage.phoneTotal;
		}
		else {
			jtext = FindPasswordPage.phoneTotal;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		

		int i = 0;
		for (PhoneNumberEnum other : PhoneNumberEnum.values()) {
			if (e.getButton() == MouseEvent.BUTTON1 && jtext[i].getText().equals(value.labelName)) {
				jtext[i].setText("");
			}
			if (!(jtext[i].equals(jtext[i]) && jtext[i].getText().equals(""))) {
				jtext[i].setText(value.labelName);
			}
			i++;
			if (!other.equals(value) && other.text.getText().equals("")) {
				other.text.setText(other.labelName);
			}
		}
		for (SignUpEnum value : SignUpEnum.values()) {
			if (String.valueOf(value.blindPW.getPassword()).equals(""))
				value.blindPW.setText(value.labelName);
			if (value.text.getText().equals(""))
				value.text.setText(value.labelName);

		}
	}
}
