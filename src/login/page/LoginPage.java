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
import login.findPW.FindPasswordPage;
import login.signUp.SignUpPage;
import login.window.ActionWindow;
import login.window.Login_SwingTool;


public class LoginPage extends JFrame {
	
	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;
	public static JPasswordField loginpass;
	public static JPanel page_panel;
	public static CardLayout cards;
	int x = new Conversion_image("image/�α���ȭ��.jpg", 5).x;
	int y = new Conversion_image("image/�α���ȭ��.jpg", 5).y;
	
	public static JTextField[] phoneTotal;

	//	70	231232	������	19831010	010-1111-1111	12345678	0			
	public LoginPage() {
		// ���ȭ��
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		JLabel background = new JLabel(new Conversion_image("image/�α���ȭ��.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);
		fram_panel.add(background);
		
		// ������ ī�������� �г�
		page_panel = new JPanel();
		cards = new CardLayout();
		page_panel.setLayout(cards);
		new Style(page_panel);
		page_panel.setBounds(1577/5, 0, 2423/5,2250/5);
		add(page_panel);
		add(fram_panel);
		
		JPanel login_panel = new JPanel();
		login_panel.setLayout(null);
		new Style(login_panel);
		
		
		// ������ �߰� �۾� 
		page_panel.add("�α���", login_panel);
		page_panel.add("ȸ������", new SignUpPage());
		page_panel.add("���ã��", new FindPasswordPage());
	

		phone_number1 = new JTextField("010");
		new Style(phone_number1);
		phone_number1.setBounds(145, 150, 55, 40);
		login_panel.add(phone_number1);
		
		JLabel hyphen = new JLabel("-", JLabel.CENTER);
		new Style(hyphen);
		hyphen.setBounds(200, 150, 15, 40);
		login_panel.add(hyphen);
		
		 phone_number2 = new JTextField(4);
		new Style(phone_number2);
		phone_number2.setBounds(215, 150, 55, 40);
		login_panel.add(phone_number2);
		
		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(270, 150, 15, 40);
		login_panel.add(hyphen2);
		
		 phone_number3 = new JTextField(4);
		new Style(phone_number3);
		phone_number3.setBounds(285, 150, 55, 40);
		login_panel.add(phone_number3);
		
		loginpass = new JPasswordField("��й�ȣ");
		loginpass.addMouseListener(new EmptyPrice(loginpass));
		new Style(loginpass);
		loginpass.setBounds(145, 200, 195, 40);
		login_panel.add(loginpass);
		
		JButton login = new JButton("�α���");
		new Style(login);
		login.setBounds(145, 250, 195, 40);
		login.addActionListener(new ActionWindow(login));
		login_panel.add(login);
		
		JButton find_PW = new JButton(new Conversion_image("image/PWã��.png", 40, 40).imageicon_smooth);
		new Style(find_PW);
		find_PW.setText("���ã��");
		find_PW.setBounds(413, 316, 50, 50);
		find_PW.addActionListener(new ActionWindow(find_PW));
		login_panel.add(find_PW);
	
		JButton signup = new JButton(new Conversion_image("image/ȸ������.png", 40	, 40).imageicon_smooth);
		new Style(signup);
		signup.setText("ȸ������");
		signup.setBounds(410, 365, 50, 50);
		signup.addActionListener(new ActionWindow(signup));
		login_panel.add(signup);
		
		JButton Poweroff = new JButton(new Conversion_image("image/����.png", 30	, 30).imageicon_smooth);
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
		
		phoneTotal = new JTextField[] {phone_number1,phone_number2,phone_number3};
		// ���� �ؽ�Ʈ ���콺�� ���� ��
		for(int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField
					(phoneTotal[i], "�α���"));
			addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
		}

	}
	
	public static void main(String[] args) throws IOException {
			new LoginPage();
	}
}
