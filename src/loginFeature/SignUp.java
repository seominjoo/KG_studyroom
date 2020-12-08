package loginFeature;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.transform.Source;

public class SignUp extends JFrame {
	final int GRID = 8;
	JPanel card_panel = new JPanel(new CardLayout());
	JPanel grid_panel = new JPanel(new GridLayout(GRID, 1, 0, 30));

	static Map<JCheckBox, JButton> consent;
	static ImageIcon icon;
	static BufferedImage source;

	public static JComboBox<String> year;
	public static JComboBox<String> month;
	public static JComboBox<String> day;

	static {
		try {
			source = ImageIO.read(new File("C:\\Users\\Hyun\\Desktop\\자바SW개발자 양성과정 10월 현태환\\민짱.jpg"));

			icon = new ImageIcon(source.getScaledInstance(2241 / 4, 2542 / 3, Image.SCALE_SMOOTH));
			System.out.println(source.getHeight() + " " + source.getWidth());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SignUp() { // 생성자

		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				// Approach 1: Dispaly image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);
				// Approach 2: Scale image to size of component
				// Dimension d = getSize();
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);

			}
		};

		JLabel signup = new JLabel("회원가입", JLabel.CENTER); // 타이틀
		// setLayout(null)일때만 위치 사이즈 적용 가능
		signup.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		signup.setForeground(Color.decode("#cfab8b"));
		grid_panel.add(signup);

		for (SignUpEnum value : SignUpEnum.values()) {

			JTextField text = value.text; // 텍스트생성
			value.text.setOpaque(false);
			// text.setFocusTraversalKeysEnabled(false);
			JPanel panelOfPanel = new JPanel(new GridLayout(1, 2, 0, 0));
			panelOfPanel.setOpaque(false);
			new Style(text, 3);
			text.setHorizontalAlignment(SwingConstants.LEFT);
			text.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			text.setForeground(Color.decode("#cfab8b"));
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));


			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM)) {
				JLabel jpassLabel = new JLabel(value.labelNameKor);
				jpassLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
				jpassLabel.setForeground(Color.decode("#cfab8b"));
				value.blindPW.setOpaque(false);
				value.blindPW.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
				value.blindPW.setForeground(Color.decode("#cfab8b"));
				value.blindPW.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
				// 이제 즐거운 디비 시간~ 오잉...오잉..
				panelOfPanel.add(jpassLabel);
				panelOfPanel.add(value.blindPW);
				grid_panel.add(panelOfPanel);
				continue;
			}

			JLabel passLabel = new JLabel(value.labelNameKor);
			passLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
			passLabel.setForeground(Color.decode("#cfab8b"));
			panelOfPanel.add(passLabel);

			if (value.equals(SignUpEnum.BIRTHDAY)) {
				JPanel panelInGrid2 = new JPanel();
				panelInGrid2.setLayout(null);
				panelInGrid2.setOpaque(false);

				int firstYear = 1930;
				int lastYear = LocalDate.now().getYear();
				String[] yearTable = new String[lastYear - firstYear + 1];
				for (int i = 0; i <= lastYear - 1930; i++) {
					yearTable[i] = (firstYear + "").format("%d", firstYear);
					firstYear++;
				}
				year = new JComboBox<>(yearTable);
				year.setBounds(0, 23, 65, 30);
				panelInGrid2.add(year);
				year.setOpaque(false); // 배경 투명
				year.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				year.setForeground(Color.decode("#cfab8b"));
				year.setSelectedItem("2000");
				new Style(year);
				
				int lastMonth = 12;
				String[] monthTable = new String[lastMonth+1];
				for (int i = 1; i < monthTable.length; i++) {
					monthTable[i] = (i + "").format("%02d", i);
				}
				month = new JComboBox<>(monthTable);
				month.setBounds(77, 23, 60, 30);
				panelInGrid2.add(month);
				month.setOpaque(false); // 배경 투명
				month.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				month.setForeground(Color.decode("#cfab8b"));
				new Style(month);
	

				String[] dayTable = new String[32];
				for (int i = 1; i < dayTable.length; i++) {
					dayTable[i] = (i + "").format("%02d", i);
				}
				
				day = new JComboBox<>(dayTable);
				
				
				int[] monthFordayCount = { 4, 6, 9, 11};
				day.setBounds(149, 23, 60, 30);
				panelInGrid2.add(day);
				day.setOpaque(false); // 배경 투명
				day.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				day.setForeground(Color.decode("#cfab8b"));
				new Style(day);
				System.out.println(month.getSelectedItem());
				
				
				year.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						month.setSelectedItem("01");
						day.setSelectedItem("01");
					}
				});
				
				
				month.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int yearselect = Integer.parseInt((String) year.getSelectedItem());
						int monthselect = Integer.parseInt((String) month.getSelectedItem());
						System.out.println(yearselect+", "+monthselect);
						DefaultComboBoxModel<String> realDay = new DefaultComboBoxModel<>(dayTable);
						day.setModel(realDay);
						
						if (monthselect == 2) {
							if(yearselect % 4 == 0) {
								day.removeItem("30");
								day.removeItem("31");
							}else {
								day.removeItem("29");
								day.removeItem("30");
								day.removeItem("31");
							}
						}
						else {
							for (int i : monthFordayCount) {
								if(i == monthselect) {
									day.removeItem("31");
									break;
								}
							
							}
						}
						
					}
				});

				panelOfPanel.add(panelInGrid2);
				grid_panel.add(panelOfPanel);
				continue;
			}

			if (value.equals(SignUpEnum.PHONENUMBER)) {
				JPanel phoneNumber3Texts = new JPanel();
				phoneNumber3Texts.setLayout(null);
				phoneNumber3Texts.setOpaque(false); // 배경 투명
				// phoneNumber3Texts.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
				// // 테두리?
				// phoneNumber3Texts.setForeground(Color.decode("#cfab8b"));

				JTextField phone_number1 = PhoneNumberEnum.PHONENUMBER1.text;
				phone_number1.setOpaque(false); // 배경 투명
				phone_number1.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				phone_number1.setHorizontalAlignment(SwingConstants.CENTER);
				phone_number1.setForeground(Color.decode("#cfab8b"));
				phone_number1.setBounds(0, 23, 65, 30);
				phoneNumber3Texts.add(phone_number1);

				JLabel str = new JLabel("-", JLabel.CENTER);
				str.setBounds(66, 21, 10, 30);
				str.setForeground(Color.decode("#cfab8b"));
				phoneNumber3Texts.add(str);

				JTextField phone_number2 = PhoneNumberEnum.PHONENUMBER2.text;
				phone_number2.setOpaque(false); // 배경 투명
				phone_number2.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				phone_number2.setHorizontalAlignment(SwingConstants.CENTER);
				phone_number2.setForeground(Color.decode("#cfab8b"));
				phone_number2.setBounds(77, 23, 60, 30);
				phoneNumber3Texts.add(phone_number2);

				JLabel str2 = new JLabel("-", JLabel.CENTER);
				str2.setBounds(138, 21, 10, 30);
				str2.setForeground(Color.decode("#cfab8b"));
				phoneNumber3Texts.add(str2);
			
				JTextField phone_number3 = PhoneNumberEnum.PHONENUMBER3.text;
				phone_number3.setOpaque(false); // 배경 투명
				phone_number3.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				phone_number3.setHorizontalAlignment(SwingConstants.CENTER);
				phone_number3.setForeground(Color.decode("#cfab8b"));
				phone_number3.setBounds(149, 23, 60, 30);
				phoneNumber3Texts.add(phone_number3);

				panelOfPanel.add(phoneNumber3Texts);

				grid_panel.add(panelOfPanel);

				for (PhoneNumberEnum phoneValue : PhoneNumberEnum.values())
					phoneValue.text.addMouseListener(new PhoneNumberClearTextField(phoneValue));

				continue;
			}

			panelOfPanel.add(text);
			grid_panel.add(panelOfPanel);
		}

//		 텍스트를 마우스로 누를 때
		for (SignUpEnum value : SignUpEnum.values()) {
			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM))
				value.blindPW.addMouseListener(new ClearTextField(value));
			else
				value.text.addMouseListener(new ClearTextField(value));
		}

		// 약관 패널
		JPanel p1 = new JPanel(new GridLayout(2, 2, 0, 2));
		p1.setOpaque(false);
		consent = new HashMap<>();
		consent.put(new JCheckBox(" 서비스 이용 동의"), new JButton("약관보기"));
		consent.put(new JCheckBox(" 사용정보 동의"), new JButton("약관보기"));

		int consentNum = 0;
		for (Entry<JCheckBox, JButton> kv : consent.entrySet()) {
			kv.getKey().setForeground(Color.decode("#cfab8b"));
			p1.add(kv.getKey());
			kv.getValue().setBackground(Color.white);
			p1.add(kv.getValue());
			kv.getKey().setOpaque(false);
			kv.getKey().setFont(new Font("맑은 고딕", Font.BOLD, 17));
//	        login.setContentAreaFilled(false);
			kv.getValue().setOpaque(false); // 배경 투명
			kv.getValue().setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
			kv.getValue().setForeground(Color.decode("#cfab8b"));
			kv.getValue().setFocusPainted(false);
			kv.getValue().setFocusable(false);
			kv.getValue().setFont(new Font("맑은 고딕", Font.BOLD, 17));
			kv.getValue().setBackground(Color.decode("#cfab8b"));
			// 약관 내용 보기
			consentNum++;
				kv.getValue().addActionListener(new ConsentContent(consentNum));
		}

		grid_panel.add(p1);

		// 승인,거절 패널
		int row = 2;
		int col = 4;

		JPanel p2 = new JPanel(new GridLayout(row, col, 30, 10));
		JButton s_Yes = new JButton("가입");
		JButton s_No = new JButton("취소");

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (r == 0 && c == 1)
					p2.add(s_Yes);
				else if ((r == 0 && c == 2))
					p2.add(s_No);
				else
					p2.add(new JLabel());
			}
		}
		p2.setOpaque(false);

		JButton[] yesNo = { s_Yes, s_No };
		for (int i = 0; i < yesNo.length; i++) {
			yesNo[i].setBackground(Color.decode("#cfab8b"));
			yesNo[i].setFocusable(false);
			yesNo[i].setOpaque(false); // 배경 투명
			yesNo[i].setFocusPainted(false);
			yesNo[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
			yesNo[i].setForeground(Color.decode("#cfab8b"));
			yesNo[i].setFont(new Font("맑은 고딕", Font.BOLD, 17));
		}

		grid_panel.add(p2);

		// 가입 버튼 누를 떄
		card_panel.add("Signup", grid_panel);

		grid_panel.setOpaque(false);
		card_panel.setOpaque(false);

		s_Yes.addMouseListener(new ClickSignUp());
		s_No.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		SwingToolsMainPage.initTestFrame(this);
		background.setBounds(0, 0, 2241 / 4, 2542 / 3);
		add(background);

		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		JPanel p4 = new JPanel();
		p4.setOpaque(false);
		JPanel p5 = new JPanel();
		p5.setOpaque(false);
		JPanel p6 = new JPanel();
		p6.setOpaque(false);

		background.setLayout(new BorderLayout(60, 10));
		background.add(p3, BorderLayout.NORTH);
		background.add(p4, BorderLayout.EAST);
		background.add(card_panel, BorderLayout.CENTER);
		background.add(p5, BorderLayout.WEST);
		background.add(p6, BorderLayout.SOUTH);

		// 바깥쪽 눌렀을 때 기본 값 만들어버리기
		addMouseListener(new ClearTextBackGround());

		this.setBackground(Color.black);
	}

	public static void main(String[] args) {
		new SignUp();
	}

}
