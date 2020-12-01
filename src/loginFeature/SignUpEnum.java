package loginFeature;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;

public enum SignUpEnum {
	NAME("Name"),
	PHONENUMBER("Phone Number"),
	PASSWORD("Password");
	
	JLabel label;
	JTextField text;
	String labelName;

	SignUpEnum(String labelName) {
		this.label = new JLabel(labelName, JLabel.CENTER);
		this.text = new JTextField(JTextField.CENTER);
		this.labelName = labelName;
	}
	
	
}
