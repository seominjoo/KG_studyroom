package login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame {

	public Login() {
		String path = "image/테스트이미지.jpg";
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image(path, 624,373).imageicon_smooth);
		JTextField phone_number = new JTextField();
		phone_number.setBounds(200, 200, 100,20);
		JPasswordField login_pass = new JPasswordField();
		background.add(phone_number);
		
//		background.setSize(624,373);
//		login_panel.setOpaque(false); // 투명
		
		add(background);
		SwingTool_logo.initFrame(this);
		setSize(624,405);
	}


	public static void main(String[] args) throws IOException {

			new Login();

	}
}
