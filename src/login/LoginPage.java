package login;

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

import login.design.Conversion_image;
import login.design.EmptyPrice;
import login.design.Style;
import login.window.ActionWindow;


public class LoginPage extends JFrame {
	
	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;
	public static JPasswordField loginpass;
	int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	int y = new Conversion_image("image/로그인화면.jpg", 5).y;
	

	//	70	231232	현태태	19831010	010-1111-1111	12345678	0			
	public LoginPage() {
		// 배경화면
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);
		
		JLabel background = new JLabel(new Conversion_image("image/로그인화면.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);
		fram_panel.add(background);
		
		// 낙엽쪽 카드페이지 패널
		JPanel page_panel = new JPanel(new CardLayout());
		new Style(page_panel);
		page_panel.setBounds(1577/5, 0, 2423/5,2250/5);
		add(page_panel);
		add(fram_panel);
		
		JPanel login_panel = new JPanel();
		login_panel.setLayout(null);
		new Style(login_panel);
		page_panel.add("로그인", login_panel);
	
		phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(155, 180, 40, 30);
		login_panel.add(phone_number1);
		
		JLabel hyphen = new JLabel("-", JLabel.CENTER);
		new Style(hyphen);
		hyphen.setBounds(195, 180, 10, 30);
		login_panel.add(hyphen);
		
		 phone_number2 = new JTextField(4);
		new Style(phone_number2);
		phone_number2.setBounds(205, 180, 45, 30);
		login_panel.add(phone_number2);
		
		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(250, 180, 10, 30);
		login_panel.add(hyphen2);
		
		 phone_number3 = new JTextField(4);
		new Style(phone_number3);
		phone_number3.setBounds(260, 180, 45, 30);
		login_panel.add(phone_number3);
		
		loginpass = new JPasswordField("비밀번호");
		loginpass.addMouseListener(new EmptyPrice(loginpass));
		new Style(loginpass);
		loginpass.setBounds(155, 220, 150, 30);
		login_panel.add(loginpass);
		
		JButton login = new JButton("로그인");
		new Style(login);
		login.setBounds(155, 260, 150, 30);
		login.addActionListener(new ActionWindow(login));
		login_panel.add(login);
		
		JButton find_PW = new JButton(new Conversion_image("image/PW찾기.png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setBounds(413, 316, 50, 50);
		find_PW.addActionListener(new ActionWindow(find_PW));
		login_panel.add(find_PW);
	
		JButton signup = new JButton(new Conversion_image("image/회원가입.png", 40	, 40).imageicon_smooth);
		new Style(signup);
		signup.setBounds(410, 365, 50, 50);
		signup.addActionListener(new ActionWindow(signup));
		login_panel.add(signup);
		
		JButton Poweroff = new JButton(new Conversion_image("image/전원.png", 30	, 30).imageicon_smooth);
		new Style(Poweroff);
		Poweroff.setBounds(0, 0, 30, 30);
		Poweroff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		background.add(Poweroff);
		
		Login_SwingTool.initFrame(this);
		
	}
	
	public static void main(String[] args) throws IOException {
			new LoginPage();
	}
}
