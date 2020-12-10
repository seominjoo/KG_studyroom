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
			for(SignUpEnum other : SignUpEnum.values()) {
				if(!other.equals(value) && (other.text.getText().equals("") 
						&& String.valueOf(other.blindPW.getPassword()).equals(""))) {
					other.text.setText(other.labelName);
					other.blindPW.setText(other.labelName);
				}
			}
//			for(PhoneNumberEnum value : PhoneNumberEnum.values()) {
//				if(value.text.getText().equals("")) {
//					value.text.setText(value.labelName);
//				}				
//			}
		}

	}

}
