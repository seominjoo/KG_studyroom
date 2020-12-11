package login.page;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.PhoneNumberEnum;
import login.clearText.ClearTextBackGround;
import login.clearText.PhoneNumberClearTextField;
import login.design.Conversion_image;
import login.design.EmptyPrice;
import login.design.Style;
import login.window.Main_ActionWindow;

public class AdminPage extends JPanel{
	public static JTextField admin_phone_number1;
	public static JTextField admin_phone_number2;
	public static JTextField admin_phone_number3;
	public static JPasswordField admin_loginpass;
	public static JTextField[] phoneTotal;

	
	public AdminPage() {
		this.setLayout(null);
		new Style(this);

		this.setLayout(null);
		new Style(this);

		// 관리자
		admin_phone_number1 = new JTextField("010");
		new Style(admin_phone_number1);
		admin_phone_number1.setBounds(145, 150, 55, 40);
		this.add(admin_phone_number1);

		JLabel hyphen1 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen1);
		hyphen1.setBounds(200, 150, 15, 40);
		this.add(hyphen1);

		admin_phone_number2 = new JTextField(4);
		new Style(admin_phone_number2);
		admin_phone_number2.setBounds(215, 150, 55, 40);
		this.add(admin_phone_number2);

		JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
		new Style(hyphen2);
		hyphen2.setBounds(200, 150, 15, 40);
		this.add(hyphen2);

		admin_phone_number3 = new JTextField(4);
		new Style(admin_phone_number3);
		admin_phone_number3.setBounds(285, 150, 55, 40);
		this.add(admin_phone_number3);
		this.add(admin_phone_number3);

		admin_loginpass = new JPasswordField("비밀번호");
		admin_loginpass.addMouseListener(new EmptyPrice(admin_loginpass));
		new Style(admin_loginpass);
		admin_loginpass.setBounds(145, 200, 195, 40);
		this.add(admin_loginpass);

		JButton admin = new JButton("관리자 로그인");
		new Style(admin);
		admin.setBounds(145, 250, 195, 40);
		admin.addActionListener(new Main_ActionWindow(admin));
		this.add(admin);

		JButton admin_find_PW1 = new JButton(new Conversion_image("image/PW찾기.png", 40, 40).imageicon_smooth);
		new Style(admin_find_PW1);
		admin_find_PW1.setText("관리자 비번찾기");
		admin_find_PW1.setBounds(413, 316, 50, 50);
		admin_find_PW1.addActionListener(new Main_ActionWindow(admin_find_PW1));
		this.add(admin_find_PW1);
		
		phoneTotal = new JTextField[] { admin_phone_number1, admin_phone_number2, admin_phone_number3 };
		// 전번 텍스트 마우스로 누를 때
		for (int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "관리자"));
			addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
		}
		
		admin_loginpass.addMouseListener(new EmptyPrice(admin_loginpass));
		
	}
	
}
