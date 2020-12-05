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
	PASSWORD("Password", "�� �� �� ȣ             ",3),
	PASSWORDCONFIRM("Password", "�� �� �� ȣ  Ȯ ��      ",4);
	
	
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
