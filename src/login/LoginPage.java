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

import login.design.Conversion_image;
import login.design.EmptyPrice;
import login.design.Style;
import login.window.ActionWindow;


public class LoginPage extends JFrame {
	
	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;
	public static JPasswordField loginpass;
	

	//	70	231232	현태태	19831010	010-1111-1111	12345678	0			
	public LoginPage() {
		// 디자인
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image("image/테스트이미지.jpg", 624,373).imageicon_smooth);
		background.setBounds(0, 0, 624, 373);
	
		 phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(340, 137, 40, 30);
		background.add(phone_number1);
		
		JLabel hyphen = new JLabel("-", JLabel.CENTER);
		new Style(hyphen);
		hyphen.setBounds(380, 137, 10, 30);
		background.add(hyphen);
		
		 phone_number2 = new JTextField(4);
		new Style(phone_number2);
		phone_number2.setBounds(390, 137, 45, 30);
		background.add(phone_number2);
		
		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(435, 137, 10, 30);
		background.add(hyphen2);
		
		 phone_number3 = new JTextField(4);
		new Style(phone_number3);
		phone_number3.setBounds(445, 137, 45, 30);
		background.add(phone_number3);
		
		loginpass = new JPasswordField("비밀번호");
		loginpass.addMouseListener(new EmptyPrice(loginpass));
		new Style(loginpass);
		loginpass.setBounds(340, 177, 150, 30);
		background.add(loginpass);
		
		JButton login = new JButton("로그인");
		new Style(login);
		login.setBounds(340, 217, 150, 30);
		login.addActionListener(new ActionWindow(login));
		background.add(login);
		
		JButton find_PW = new JButton(new Conversion_image("image/PW찾기.png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setBounds(545, 247, 50, 50);
		find_PW.addActionListener(new ActionWindow(find_PW));
		background.add(find_PW);
	
		JButton signup = new JButton(new Conversion_image("image/회원가입.png", 40	, 40).imageicon_smooth);
		new Style(signup);
		signup.setBounds(543, 292, 50, 50);
		signup.addActionListener(new ActionWindow(signup));
		background.add(signup);
		
		JButton Poweroff = new JButton(new Conversion_image("image/전원.png", 40	, 40).imageicon_smooth);
		new Style(Poweroff);
		Poweroff.setBounds(0, 0, 50, 50);
		Poweroff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		background.add(Poweroff);
		
		SwingTool_logo.initFrame(this);
		add(background);
		setSize(624,405);
	}
	
	public static void main(String[] args) throws IOException {
			new LoginPage();
	}
}
