package login.signUp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import login.BirthEnum;
import login.PhoneNumberEnum;
import login.YearMonthClick;
import login.clearText.ClearTextBackGround;
import login.clearText.ClearTextField;
import login.clearText.PhoneNumberClearTextField;
import login.signUp.window.ConsentContent;
import login.design.Style;
import login.page.LoginPage;
import login.page.MainPage;

public class SignUpPage extends JPanel {
	final int GRID = 8;

	static Map<JCheckBox, JButton> consent;

	static ImageIcon icon;
	static BufferedImage source;
	public static JComboBox<String> year;
	public static JComboBox<String> month;
	public static JComboBox<String> day;

	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;

	public static JTextField[] phoneTotal;

	static JPanel panelInGrid5_1;

	public static JLabel passAlert;
	public static JLabel passConfirmAlert;

	public SignUpPage() {
		setLayout(null);

		passAlert = new JLabel("");
		new Style(passAlert);
		passAlert.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		passAlert.setBounds(517, 294, 100, 25);
		add(passAlert);

		passConfirmAlert = new JLabel("", JLabel.LEFT);
		new Style(passConfirmAlert);
		passConfirmAlert.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		passConfirmAlert.setBounds(517, 364, 100, 25);
		add(passConfirmAlert);

		setLayout(new BorderLayout(80, 0));
		new Style(this);

		add(Style.getnewPanel(), BorderLayout.NORTH);
		add(Style.getnewPanel(), BorderLayout.WEST);
		add(Style.getnewPanel(), BorderLayout.EAST);
		add(Style.getnewPanel(), BorderLayout.SOUTH);

		JPanel grid = new JPanel(new GridLayout(GRID, 1, 0, 20));

		add(grid, BorderLayout.CENTER);
		new Style(grid);

		// 제목
		JLabel signup = new JLabel("회원가입", JLabel.CENTER);
		new Style(signup);
		signup.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		grid.add(signup);

		for (SignUpEnum value : SignUpEnum.values()) {

			JPanel gridInGrid = new JPanel(new GridLayout(1, 2, 0, 0));
			new Style(gridInGrid);

			JPanel nullPanelInGrid2_2 = new JPanel();
			nullPanelInGrid2_2.setLayout(null);
			new Style(nullPanelInGrid2_2);

			new Style(value.text, 4);
			value.text.setHorizontalAlignment(SwingConstants.LEFT);
			value.text.setBounds(18, 11, 149, 30);
			nullPanelInGrid2_2.add(value.text);

			// 비번, 비번 확인
			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM)) {
				panelInGrid5_1 = new JPanel();
				panelInGrid5_1.setLayout(null);
				new Style(panelInGrid5_1);

				JLabel passLabel = new JLabel(value.labelNameKor);
				new Style(passLabel);
				passLabel.setBounds(0, -11, 200, 50);

				if (value.equals(value.PASSWORD)) {

					JLabel passNoticement = new JLabel("※ 대소문자 & 숫자 각 1개 (8~12자리)");
					new Style(passNoticement);
					passNoticement.setFont(new Font("맑은 고딕", Font.BOLD, 10));
					passNoticement.setBounds(0, 19, 180, 25);
					panelInGrid5_1.add(passNoticement);

					value.PASSWORD.blindPW.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {

							if (Pattern.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,12}$",
									String.valueOf(value.PASSWORD.blindPW.getPassword()))) {
								passAlert.setText("사용가능");
								passAlert.setForeground(Color.decode("#9fda84"));
							} else if (String.valueOf(value.PASSWORD.blindPW.getPassword()).length() >= 1) {
								passAlert.setText("사용불가");
								passAlert.setForeground(Color.decode("#da9784"));
							} else {
								passAlert.setText("");
							}
						}
					});

				}

				panelInGrid5_1.add(passLabel);

				gridInGrid.add(panelInGrid5_1);

				JPanel nullPanelInGrid56_2 = new JPanel();
				nullPanelInGrid56_2.setLayout(null);
				new Style(nullPanelInGrid56_2);

				new Style(value.blindPW, 12);
				value.blindPW.setHorizontalAlignment(SwingConstants.LEFT);
				value.blindPW.setBounds(18, 0, 149, 30);

				nullPanelInGrid56_2.add(value.blindPW);
				gridInGrid.add(nullPanelInGrid56_2);
				grid.add(gridInGrid);

				value.PASSWORDCONFIRM.blindPW.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {

						if (String.valueOf(value.PASSWORD.blindPW.getPassword())
								.equals(String.valueOf(value.PASSWORDCONFIRM.blindPW.getPassword()))) {
							passConfirmAlert.setText("비밀번호 일치");
							passConfirmAlert.setForeground(Color.decode("#9fda84"));
						} else if (String.valueOf(value.PASSWORDCONFIRM.blindPW.getPassword()).equals("")) {
							passConfirmAlert.setText("");
						} else {
							passConfirmAlert.setText("비밀번호 불일치");
							passConfirmAlert.setForeground(Color.decode("#da9784"));
						}
					}
				});

				continue;
			}

			JLabel Label = new JLabel(value.labelNameKor);
			new Style(Label);
			gridInGrid.add(Label);

			// 생년월일
			if (value.equals(SignUpEnum.BIRTHDAY)) {
				JPanel panelInGrid2 = new JPanel();
				new Style(panelInGrid2);
				panelInGrid2.setLayout(null);

				year = new JComboBox<String>(BirthEnum.getYearTable());
				year.setBounds(18, 10, 65, 30);
				panelInGrid2.add(year);
				new Style(year);
				year.setSelectedItem("2000");

				month = new JComboBox<String>(BirthEnum.getMonthTable());
				month.setBounds(102, 10, 65, 30);
				panelInGrid2.add(month);
				new Style(month);

				day = new JComboBox<String>(BirthEnum.getDayTable());

				day.setBounds(186, 10, 65, 30);
				panelInGrid2.add(day);
				new Style(day);

				// 연도, 월 클릭
				year.addActionListener(new YearMonthClick("year", "회원가입"));
				month.addActionListener(new YearMonthClick("month", "회원가입"));

				gridInGrid.add(panelInGrid2);
				grid.add(gridInGrid);
				continue;
			}

			// 전화번호
			if (value.equals(SignUpEnum.PHONENUMBER)) {
				JPanel phoneNumber3Texts = new JPanel();
				phoneNumber3Texts.setLayout(null);
				new Style(phoneNumber3Texts);

				phone_number1 = new JTextField("010");
				new Style(phone_number1, 3);
				phone_number1.setBounds(18, 10, 65, 30);
				phoneNumber3Texts.add(phone_number1);

				JLabel str = new JLabel("-");
				str.setBounds(91, 10, 10, 30);
				new Style(str);
				phoneNumber3Texts.add(str);

				phone_number2 = new JTextField("");
				new Style(phone_number2, 4);
				phone_number2.setBounds(102, 10, 65, 30);
				phoneNumber3Texts.add(phone_number2);

				JLabel str2 = new JLabel("-");
				str2.setBounds(175, 10, 10, 30);
				new Style(str2);
				phoneNumber3Texts.add(str2);

				phone_number3 = new JTextField("");
				new Style(phone_number3, 4);
				phone_number3.setBounds(186, 10, 65, 30);
				phoneNumber3Texts.add(phone_number3);

				gridInGrid.add(phoneNumber3Texts);

				grid.add(gridInGrid);

				continue;
			}

			gridInGrid.add(nullPanelInGrid2_2);
			grid.add(gridInGrid);
			
		}

		// 텍스트를 마우스로 누를 때
		for (SignUpEnum value : SignUpEnum.values()) {
			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM))
				value.blindPW.addMouseListener(new ClearTextField(value));
			else
				value.text.addMouseListener(new ClearTextField(value));
		}
		// 약관 패널
		JPanel gridInGrid7 = new JPanel(new GridLayout(1, 2, 0, 2));
		new Style(gridInGrid7);
		consent = new HashMap<>();
		consent.put(new JCheckBox(" 서비스 이용 동의"), new JButton("약관보기"));

		int consentNum = 0;
		for (Entry<JCheckBox, JButton> kv : consent.entrySet()) {
			new Style(kv.getKey());

			new Style(kv.getValue());
			kv.getValue().setFont(new Font("맑은 고딕", Font.BOLD, 13));

			gridInGrid7.add(kv.getKey());

			JPanel gridInGrid72 = new JPanel();
			new Style(gridInGrid72);
			gridInGrid72.setLayout(null);
			gridInGrid7.add(gridInGrid72);
			kv.getValue().setBounds(67, 10, 100, 30);

			gridInGrid72.add(kv.getValue());

			// 약관 내용 보기
			consentNum++;
			kv.getValue().addActionListener(new ConsentContent(consentNum));
		}

		grid.add(gridInGrid7);

		// 승인,거절 패널

		int col = 4;
		JPanel gridInGrid8 = new JPanel(new GridLayout(1, col, 30, 10));
		new Style(gridInGrid8);

		JPanel nullPanelInGrid8_1 = new JPanel();
		nullPanelInGrid8_1.setLayout(null);
		new Style(nullPanelInGrid8_1);

		JButton s_Yes = new JButton("가입");
		s_Yes.setBounds(0, 0, 90, 30);

		JPanel nullPanelInGrid8_2 = new JPanel();
		nullPanelInGrid8_2.setLayout(null);
		new Style(nullPanelInGrid8_2);

		JButton s_No = new JButton("취소");
		s_No.setBounds(4, 0, 90, 30);

		for (int c = 0; c < col; c++) {
			if (c == 1) {
				nullPanelInGrid8_1.add(s_Yes);
				gridInGrid8.add(nullPanelInGrid8_1);
			} else if (c == 2) {
				nullPanelInGrid8_2.add(s_No);
				gridInGrid8.add(nullPanelInGrid8_2);
			} else
				gridInGrid8.add(new JLabel());
		}

		JButton[] yesNo = { s_Yes, s_No };
		for (int i = 0; i < yesNo.length; i++) {
			new Style(yesNo[i]);
		}

		grid.add(gridInGrid8);
		new Style(this);

		s_Yes.addMouseListener(new ClickSignUp());
		s_No.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "로그인");
				MainPage.userToggle = "로그인";

				for (SignUpEnum value : SignUpEnum.values()) {
					value.text.setText(value.labelName);
					
					value.blindPW.setText(value.labelName);
				}
				for (int i = 0; i < phoneTotal.length; i++) {
					phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
				}
				year.setSelectedItem("2000");
			}
		});

		phoneTotal = new JTextField[] { phone_number1, phone_number2, phone_number3 };
		// 전번 텍스트 마우스로 누를 때
		for (int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "회원가입"));
			addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
		}
		

	}
}



