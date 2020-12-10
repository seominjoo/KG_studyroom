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
import login.signUp.SignUpPage;

public class ClearTextField extends MouseAdapter {
	
	SignUpEnum value;
	
	public ClearTextField(SignUpEnum value) {
		this.value = value;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && value.text.getText().contains(value.labelName)) {
			value.text.setText("");
			value.blindPW.setText("");
			for(int i = 0; i < SignUpPage.phoneTotal.length; i++) {
				if(SignUpPage.phoneTotal[i].getText().equals("")) {
					SignUpPage.phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
				}
			}
			for(SignUpEnum other : SignUpEnum.values()) {
				if(!other.equals(value) && (other.text.getText().equals("") 
						&& String.valueOf(other.blindPW.getPassword()).equals(""))) {
					other.text.setText(other.labelName);
					other.blindPW.setText(other.labelName);
				}
			}
			
		}

	}

}
