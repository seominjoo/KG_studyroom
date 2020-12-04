package loginFeature;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public enum SignUpEnum {
	NAME("Name *","��          ��             "),
	BIRTHDAY("Birthday *", "�� �� �� ��              "),
	PHONENUMBER("Phone Number *", "�� ȭ �� ȣ             "),
	PASSWORD("Password", "�� �� �� ȣ             "),
	PASSWORDCONFIRM("Password", "�� �� �� ȣ  Ȯ ��      ");
	
	
	JLabel label;
	JTextField text;
	JPasswordField blindPW;
	String labelName;
	String labelNameKor;

	SignUpEnum(String labelName, String labelNameKor) {
		this.label = new JLabel(labelName, label.CENTER);
		this.text = new JTextField(labelName, text.CENTER);
		this.blindPW = new JPasswordField(labelName);
		this.labelName = labelName;
		this.labelNameKor = labelNameKor;
	}


	
	
}
