package login.findPW;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import login.BirthEnum;
import login.PhoneNumberEnum;
import login.YearMonthClick;
import login.design.Style;
import login.page.LoginPage;
import login.clearText.ClearTextBackGround;
import login.clearText.PhoneNumberClearTextField;

public class FindPasswordPage extends JPanel {

	static String imagePath;
	static BufferedImage image;
	static ImageIcon icon;
	
	public FindPasswordPage() {

		JPanel background = this;
		setLayout(new BorderLayout(0, 30)) ;
		new Style(this);
		background.setBounds(0, 0, 2241 / 5, 2542 / 7);
		background.setOpaque(false);
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		background.add(blank,BorderLayout.NORTH);

		JPanel gridAll = new JPanel(new GridLayout(5, 1, 0, 0));
		background.add(gridAll, BorderLayout.CENTER);
		gridAll.setOpaque(false);

		JLabel title = new JLabel("비밀번호 찾기", JLabel.CENTER);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		title.setForeground(Color.decode("#cfab8b"));
		gridAll.add(title);

		JPanel grid2 = new JPanel();
		grid2.setOpaque(false);
		grid2.setLayout(null);
		
		JLabel birthLabel = new JLabel("생년 월일 : ");
		new Style(birthLabel);
		birthLabel.setBounds(60, -14, 200, 100);
		grid2.add(birthLabel);
		
		JComboBox<String> year = new JComboBox<String>(BirthEnum.getYearTable());
		new Style(year);
		year.setBounds(160, 22, 65, 30);
		JComboBox<String> month = new JComboBox<String>(BirthEnum.getMonthTable());
		new Style(month);
		month.setBounds(250, 22, 55, 30);
		JComboBox<String> day = new JComboBox<String>(BirthEnum.getDayTable());
		new Style(day);
		day.setBounds(330, 22, 55, 30);
		
		year.addActionListener(new YearMonthClick("year"));
		month.addActionListener(new YearMonthClick("month"));
		
		grid2.add(year);
		grid2.add(month);
		grid2.add(day);
		
		gridAll.add(grid2);
		
		JPanel grid3 = new JPanel();
		grid3.setOpaque(false);
		grid3.setLayout(null);

		JLabel phoneKor = new JLabel("전화 번호 : ");
		new Style(phoneKor);
		phoneKor.setBounds(85, 6, 100, 50);
		grid3.add(phoneKor);
		gridAll.add(grid3);

		JTextField phone_number1 = new JTextField();
		new Style(phone_number1, 3);
		phone_number1.setBounds(180, 18, 45, 30);
		grid3.add(phone_number1);

		JLabel str = new JLabel("-", JLabel.CENTER);
		str.setBounds(225, 18, 20, 30);
		new Style(str);
		grid3.add(str);

		JTextField phone_number2 = new JTextField();
		new Style(phone_number2, 4);
		phone_number2.setBounds(245, 18, 45, 30);
		grid3.add(phone_number2);

		JLabel str2 = new JLabel("-", JLabel.CENTER);
		str2.setBounds(290, 18, 20, 30);
		new Style(str2);
		grid3.add(str2);

		JTextField phone_number3 = new JTextField();
		new Style(phone_number3, 4);
		phone_number3.setBounds(310, 18, 45, 30);
		grid3.add(phone_number3);

		for(PhoneNumberEnum value : PhoneNumberEnum.values())
			value.text.addMouseListener(new PhoneNumberClearTextField(value));
		addMouseListener(new ClearTextBackGround());
		
		JPanel grid4 = new JPanel();
		grid4.setOpaque(false);
		grid4.setLayout(null);
		
		JLabel foundPW = new JLabel("", JLabel.CENTER);
		foundPW.setBounds(85, 0, 270, 50);
		grid4.add(foundPW);
		new Style(foundPW);
		
		gridAll.add(grid4);
		
		JPanel grid5 = new JPanel();
		grid5.setLayout(null);
		grid5.setOpaque(false);

		JButton find = new JButton("검색");
		JButton cancel = new JButton("취소");
		JButton[] findCancel = { find, cancel };
		int x = 120;
		for (int i = 0; i < findCancel.length; i++) {
			new Style(findCancel[i]);
			findCancel[i].setBounds(x, 0, 100, 50);
			grid5.add(findCancel[i]);
			x += 120;
		}
		
		find.addActionListener(new ClickFindPasswordPage(foundPW));
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage.cards.show(LoginPage.page_panel, "로그인");
			}
		});
		
		gridAll.add(grid5);

	}

	public static void main(String[] args) {
		new FindPasswordPage();
	}

}
