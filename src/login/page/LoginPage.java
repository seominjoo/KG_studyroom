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
import login.swingTools.Login_SwingTool;
import login.swingTools.State;
import login.window.MainBtn_Action;

public class LoginPage extends JPanel {

	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;
	public static JPasswordField loginpass;
	public static JTextField phone_number4;
	public static JTextField phone_number5;
	public static JTextField phone_number6;
	public static JPasswordField admin_loginpass1;

	public static JTextField[] phoneTotal;
	
	// 683, 562
	public LoginPage() {
		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

		this.setLayout(null);
		new Style(this);

		JPanel admin_panel = new JPanel();
		admin_panel.setLayout(null);
		new Style(admin_panel);

		phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(245, 200, 55, 40);
		this.add(phone_number1);

		JLabel hyphen1 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen1);
		hyphen1.setBounds(300, 200, 15, 40);
		this.add(hyphen1);

		phone_number2 = new JTextField("1143");
		new Style(phone_number2);
		phone_number2.setBounds(315, 200, 55, 40);
		this.add(phone_number2);

		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(370, 200, 15, 40);
		this.add(hyphen2);

		phone_number3 = new JTextField("1111");
		new Style(phone_number3);
		phone_number3.setBounds(385, 200, 55, 40);
		this.add(phone_number3);

		loginpass = new JPasswordField("12345678");
		loginpass.addMouseListener(new EmptyPrice(loginpass));
		new Style(loginpass);
		loginpass.setBounds(245, 250, 195, 40);
		this.add(loginpass);

		JButton login = new JButton("로그인");
		new Style(login);
		login.setBounds(245, 300, 195, 40);
		login.addActionListener(new MainBtn_Action(login));
		this.add(login);

		JButton find_PW = new JButton(new Conversion_image("image/PW찾기(진한).png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setText("비번찾기");
		find_PW.setBounds(622, 450, 50, 50);
		find_PW.addActionListener(new MainBtn_Action(find_PW));
		this.add(find_PW);

		JButton signup = new JButton(new Conversion_image("image/회원가입(진한).png", 40, 40).imageicon_smooth);
		new Style(signup);
		signup.setText("회원가입");
		signup.setBounds(620, 500, 50, 50);
		signup.addActionListener(new MainBtn_Action(signup));
		this.add(signup);

		phoneTotal = new JTextField[] { phone_number1, phone_number2, phone_number3 };
		// 전번 텍스트 마우스로 누를 때
		for (int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "로그인"));
			addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
		}

	}

}