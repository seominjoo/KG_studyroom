package loginFeature;

import javax.swing.JTextField;

public enum PhoneNumberEnum {

	PHONENUMBER1("010"),
	PHONENUMBER2(""),
	PHONENUMBER3(""),;

	String labelName;
	public JTextField text;
	
	PhoneNumberEnum(String labelName) {
		this.labelName = labelName;
		this.text = new JTextField(labelName);
	}
	
	
	
}
