package loginFeature;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public enum SignUpEnum {
	NAME("Name *","��          ��             ", 0),
	BIRTHDAY("Birthday *", "�� �� �� ��              ",1),
	PHONENUMBER("Phone Number *", "�� ȭ �� ȣ             ",2),
	PHONENUMBER1("010", "",3),
	PHONENUMBER2("", "",4),
	PHONENUMBER3("", "",5),
	PASSWORD("Password", "�� �� �� ȣ             ",6),
	PASSWORDCONFIRM("Password", "�� �� �� ȣ  Ȯ ��      ",7);
	
	
	JLabel label;
	JTextField text;
	JPasswordField blindPW;
	String labelName;
	String labelNameKor;
	public int index;
	
	SignUpEnum(String labelName, String labelNameKor, int index) {
		this.label = new JLabel(labelName, label.CENTER);
		this.text = new JTextField(labelName, text.CENTER);
		this.blindPW = new JPasswordField(labelName);
		this.labelName = labelName;
		this.labelNameKor = labelNameKor;
		this.index = index;
	}


	
	
}
