package studyroom.user.findPW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import studyroom.MainPage;
import studyroom.design.ClearTextBackGround;
import studyroom.design.PhoneNumberClearTextField;
import studyroom.design.Style;
import studyroom.user.login.LoginPage;
import studyroom.user.signUp.BirthEnum;
import studyroom.user.signUp.PhoneNumberEnum;
import studyroom.user.signUp.YearMonthClick;
import studyroom.window.MainBtn_Action;

public class FindPasswordPageUser extends JPanel {

	static String imagePath;
	static BufferedImage image;
	static ImageIcon icon;

	public static JComboBox<String> year;
	public static JComboBox<String> month;
	public static JComboBox<String> day;

	public static JTextField phone_number1;
	public static JTextField phone_number2;
	public static JTextField phone_number3;

	public static JTextField[] phoneTotal;

	// 비번찾기 페이지 클래스
	public FindPasswordPageUser() {

		JPanel background = this;
		setLayout(new BorderLayout(0, 70));
		new Style(this);
		background.setBounds(0, 0, 2241 / 5, 2542 / 7);
		background.setOpaque(false);
		background.add(Style.getnewPanel(), BorderLayout.NORTH);

		JPanel gridAll = new JPanel(new GridLayout(5, 1, 0, -150));
		background.add(gridAll, BorderLayout.CENTER);
		new Style(gridAll);

		JPanel panelInGrid1 = new JPanel();
		new Style(panelInGrid1);
		panelInGrid1.setLayout(null);
		gridAll.add(panelInGrid1);

		JLabel title = new JLabel("비밀번호 찾기", JLabel.CENTER);
		new Style(title);
		title.setBounds(245, 10, 200, 100);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		panelInGrid1.add(title);

		JPanel grid2 = new JPanel();
		new Style(grid2);
		grid2.setLayout(null);

		JLabel birthLabel = new JLabel("생년 월일 : ");
		new Style(birthLabel);
		birthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		birthLabel.setBounds(175, 5, 200, 100);
		grid2.add(birthLabel);

		year = new JComboBox<String>(BirthEnum.getYearTable());
		new Style(year);
		year.setBounds(275, 43, 65, 30);
		year.setSelectedItem("2000");

		month = new JComboBox<String>(BirthEnum.getMonthTable());
		new Style(month);
		month.setBounds(365, 43, 55, 30);
		day = new JComboBox<String>(BirthEnum.getDayTable());
		new Style(day);
		day.setBounds(445, 43, 55, 30);

		year.addActionListener(new YearMonthClick("year", "비번찾기"));
		month.addActionListener(new YearMonthClick("month", "비번찾기"));

		grid2.add(year);
		grid2.add(month);
		grid2.add(day);

		gridAll.add(grid2);

		JPanel grid3 = new JPanel();
		grid3.setOpaque(false);
		grid3.setLayout(null);

		JLabel phoneKor = new JLabel("전화 번호 : ");
		new Style(phoneKor);
		phoneKor.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		phoneKor.setBounds(200, 26, 100, 50);
		grid3.add(phoneKor);
		gridAll.add(grid3);

		phone_number1 = new JTextField("010");
		new Style(phone_number1, 3);
		phone_number1.setBounds(295, 38, 45, 30);
		grid3.add(phone_number1);

		JLabel str = new JLabel("-", JLabel.CENTER);
		str.setBounds(340, 38, 20, 30);
		new Style(str);
		grid3.add(str);

		phone_number2 = new JTextField();
		new Style(phone_number2, 4);
		phone_number2.setBounds(360, 38, 45, 30);
		grid3.add(phone_number2);

		JLabel str2 = new JLabel("-", JLabel.CENTER);
		str2.setBounds(405, 38, 20, 30);
		new Style(str2);
		grid3.add(str2);

		phone_number3 = new JTextField();
		new Style(phone_number3, 4);
		phone_number3.setBounds(425, 38, 45, 30);
		grid3.add(phone_number3);

		phoneTotal = new JTextField[] { phone_number1, phone_number2, phone_number3 };
		// 전화번호 텍스트 클릭했을 때 텍스트들 초기화
		for (int i = 0; i < phoneTotal.length; i++) {
			phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "비번찾기"));
		}

		addMouseListener(new ClearTextBackGround(phone_number1, PhoneNumberEnum.PHONENUMBER1));
		addMouseListener(new ClearTextBackGround(phone_number2, PhoneNumberEnum.PHONENUMBER2));
		addMouseListener(new ClearTextBackGround(phone_number3, PhoneNumberEnum.PHONENUMBER3));

		JPanel grid4 = new JPanel();
		grid4.setOpaque(false);
		grid4.setLayout(null);

		JLabel foundPW = new JLabel("",JLabel.CENTER);
		new Style(foundPW);
		foundPW.setBounds(205, 15, 270, 50);
		foundPW.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		grid4.add(foundPW);


		gridAll.add(grid4);

		JPanel grid5 = new JPanel();
		grid5.setLayout(null);
		grid5.setOpaque(false);

		JButton find = new JButton("검색");
		JButton cancel = new JButton("취소");
		JButton[] findCancel = { find, cancel };
		int x = 245;
		for (int i = 0; i < findCancel.length; i++) {
			new Style(findCancel[i]);
			findCancel[i].setBounds(x, 15, 70, 35);
			grid5.add(findCancel[i]);
			x += 120;
		}

		find.addActionListener(new ClickFindPasswordPage(foundPW));

		// 취소 눌렀을 때 관리자냐 사용자냐에 따라 알맞은 페이지로 back 및 pw찾기 페이지의 텍스트 초기화
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainPage.userToggle.equals("관리자 비번찾기")) {
					MainPage.main_cards.show(MainPage.main_page_panel, "관리자");
					MainPage.userToggle = "관리자";
				} else {
					MainPage.main_cards.show(MainPage.main_page_panel, "로그인");
					MainPage.userToggle = "로그인";
					year.setSelectedItem("2000");
					for (int i = 0; i < phoneTotal.length; i++) {
						phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
					}
					ClickFindPasswordPage.foundPW.setText("");
				}
			}
		});

		gridAll.add(grid5);

	}

}
