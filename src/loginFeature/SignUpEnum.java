package loginFeature;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public enum SignUpEnum {
	NAME("Name *","��          ��             ", ""),
	BIRTHDAY("Birthday *", "�� �� �� ��              ", ""),
	PHONENUMBER("Phone Number *", "�� ȭ �� ȣ             ", ""),
	PASSWORD("Password", "�� �� �� ȣ             ", "12341234Aa!"),
	PASSWORDCONFIRM("Password", "�� �� �� ȣ  Ȯ ��      ", "12341234Aa!");
	
	
	JLabel label;
	public JTextField text;
	JPasswordField blindPW;
	String labelName;
	String labelNameKor;
	
	SignUpEnum(String labelName, String labelNameKor, String string) {
		this.label = new JLabel(labelName, label.CENTER);
		this.text = new JTextField(labelName, text.CENTER);
		this.blindPW = new JPasswordField(string);
		this.labelName = labelName;
		this.labelNameKor = labelNameKor;
	}


	
	
}
