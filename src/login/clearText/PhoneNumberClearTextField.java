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
import login.signUp.SignUpEnum;


public class PhoneNumberClearTextField extends MouseAdapter {
	
	PhoneNumberEnum value;
	
	public PhoneNumberClearTextField(PhoneNumberEnum value) {
		this.value = value;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && value.text.getText().contains(value.labelName)) {
			value.text.setText("");
			for(PhoneNumberEnum other : PhoneNumberEnum.values()) {
				if(!other.equals(value) && other.text.getText().equals("")) {
					other.text.setText(other.labelName);
				}
			}
			for(SignUpEnum value : SignUpEnum.values()) {
				if(String.valueOf(value.blindPW.getPassword()).equals(""))
					value.blindPW.setText(value.labelName);
				if(value.text.getText().equals(""))
					value.text.setText(value.labelName);
			}
		}

	}

}