package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

import login.PhoneNumberEnum;
import login.clearText.ClearTextBackGround;
import login.clearText.PhoneNumberClearTextField;
import login.design.Conversion_image;
import login.design.EmptyPrice;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.signUp.SignUpPage;
import login.window.Main_ActionWindow;
import login.window.Login_SwingTool;

public class LoginPage extends JPanel {

	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;
	public static JPasswordField loginpass;
	public static JTextField phone_number4;
	public static JTextField phone_number5;
	public static JTextField phone_number6;
	public static JPasswordField admin_loginpass1;
	
	int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	int y = new Conversion_image("image/로그인화면.jpg", 5).y;

	public static JTextField[] phoneTotal;

	// 70 231232 현태태 19831010 010-1111-1111 12345678 0
	public LoginPage() {
		

		this.setLayout(null);
		new Style(this);

		JPanel admin_panel = new JPanel();
		admin_panel.setLayout(null);
		new Style(admin_panel);

		phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(145, 150, 55, 40);
		this.add(phone_number1);

		JLabel hyphen1 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen1);
		hyphen1.setBounds(200, 150, 15, 40);
		this.add(hyphen1);

		phone_number2 = new JTextField(4);
		new Style(phone_number2);
		phone_number2.setBounds(215, 150, 55, 40);
		this.add(phone_number2);

		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(270, 150, 15, 40);
		this.add(hyphen2);

		phone_number3 = new JTextField(4);
		new Style(phone_number3);
		phone_number3.setBounds(285, 150, 55, 40);
		this.add(phone_number3);
		
		loginpass = new JPasswordField("비밀번호");
		loginpass.addMouseListener(new EmptyPrice(loginpass));
		new Style(loginpass);
		loginpass.setBounds(145, 200, 195, 40);
		this.add(loginpass);
		
		JButton login = new JButton("로그인");
		new Style(login);
		login.setBounds(145, 250, 195, 40);
		login.addActionListener(new Main_ActionWindow(login));
		this.add(login);
		
		JButton find_PW = new JButton(new Conversion_image("image/PW찾기.png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setText("비번찾기");
		find_PW.setBounds(413, 316, 50, 50);
		find_PW.addActionListener(new Main_ActionWindow(find_PW));
		this.add(find_PW);
		
		JButton signup = new JButton(new Conversion_image("image/회원가입.png", 40, 40).imageicon_smooth);
		new Style(signup);
		signup.setText("회원가입");
		signup.setBounds(410, 365, 50, 50);
		signup.addActionListener(new Main_ActionWindow(signup));
		this.add(signup);

		phoneTotal = new JTextField[] { phone_number1, phone_number2, phone_number3 };
		// 전번 텍스트 마우스로 누를 때
		for (int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "로그인"));
			addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
		}

	}


}
