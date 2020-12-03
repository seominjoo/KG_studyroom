package login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Login extends JFrame {

	public Login() {
		String path = "image/테스트이미지.jpg";
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image(path, 1000,562).imageicon_smooth);
		background.setSize(1000,562);
		login_panel.setOpaque(false); // 투명
		
		
		System.out.println(getSize());
		SwingTool_logo.initFrame(this);
		setSize(1000,590);
		add(login_panel);
		add(background);
	}


	public static void main(String[] args) throws IOException {

			new Login();

	}
}
