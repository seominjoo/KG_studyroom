package loginFeature;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public enum SignUpEnum {
	NAME("Name *"),
	PHONENUMBER("Phone Number *"),
	PASSWORD("Password"),
	PASSWORDCONFIRM("Password");
	
	JLabel label;
	JTextField text;
	JPasswordField blindPW;
	String labelName;

	SignUpEnum(String labelName) {
		this.label = new JLabel(labelName, label.CENTER);
		this.text = new JTextField(labelName, text.CENTER);
		this.blindPW = new JPasswordField(labelName);
		this.labelName = labelName;
	}
	
	
}
