package login.clearText;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import login.PhoneNumberEnum;
import login.signUp.SignUpEnum;

public class ClearTextBackGround extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		for (SignUpEnum value : SignUpEnum.values()) {
			if (value.text.getText().equals(""))
				value.text.setText(value.labelName);
			if (String.valueOf(value.blindPW.getPassword()).equals(""))
				value.blindPW.setText(value.labelName);
		}
		for (PhoneNumberEnum value : PhoneNumberEnum.values()) {
			if (value.text.getText().equals(""))
				value.text.setText(value.labelName);
		}
	}
	
}
