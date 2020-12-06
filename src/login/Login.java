package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Login extends JFrame {

	public Login() {
		String path = "image/테스트이미지.jpg";
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image(path, 624,373).imageicon_smooth);
		background.setBounds(0, 0, 624, 373);
	
		JTextField phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(340, 137, 40, 30);
		background.add(phone_number1);
		
		JLabel hyphen = new JLabel("-", JLabel.CENTER);
		new Style(hyphen);
		hyphen.setBounds(380, 137, 10, 30);
		background.add(hyphen);
		
		JTextField phone_number2 = new JTextField();
		new Style(phone_number2);
		phone_number2.setBounds(390, 137, 45, 30);
		background.add(phone_number2);
		
		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(435, 137, 10, 30);
		background.add(hyphen2);
		
		JTextField phone_number3 = new JTextField();
		new Style(phone_number3);
		phone_number3.setBounds(445, 137, 45, 30);
		background.add(phone_number3);
		
		JPasswordField logpass = new JPasswordField("비밀번호");
		new Style(logpass);
		logpass.setBounds(340, 177, 150, 30);
		background.add(logpass);
		
		JButton login = new JButton("로그인");
		new Style(login);
		login.setBounds(340, 217, 150, 30);
		login.addActionListener(new Notification(login));
		background.add(login);
		
		JButton find_PW = new JButton(new Conversion_image("image/PW찾기.png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setBounds(545, 247, 50, 50);
		find_PW.addActionListener(new Notification(find_PW));
		background.add(find_PW);
	
		JButton Signup = new JButton(new Conversion_image("image/회원가입.png", 40	, 40).imageicon_smooth);
		new Style(Signup);
		Signup.setBounds(543, 292, 50, 50);
		Signup.addActionListener(new Notification(Signup));
		background.add(Signup);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("x : " + e.getX() + " Y : " + e.getY());
				}
			}
		});
//		login_panel.setOpaque(false); // 투명
		
		SwingTool_logo.initFrame(this);
		add(background);
		setSize(624,405);
	}


	public static void main(String[] args) throws IOException {
			new Login();
	}
}
