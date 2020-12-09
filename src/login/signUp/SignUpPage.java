package login.signUp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
import login.swingTools.SwingToolsMainPage;

public class SignUpPage extends JFrame {
	final int GRID = 8;
	JPanel card_panel = new JPanel(new CardLayout());
	JPanel grid_panel = new JPanel(new GridLayout(GRID, 1, 0, 30));

	static Map<JCheckBox, JButton> consent;
	static ImageIcon icon;
	static BufferedImage source;

	static {
		try {
			source = ImageIO.read(new File("image/테스트이미지1.jpg"));
			icon = new ImageIcon(source.getScaledInstance(2241 / 4, 2542 / 3, Image.SCALE_SMOOTH));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SignUpPage() {
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		// �젣紐�
		JLabel signup = new JLabel("�쉶�썝媛��엯", JLabel.CENTER);
		new Style(signup);
		signup.setFont(new Font("留묒� 怨좊뵓", Font.BOLD, 30));
		grid_panel.add(signup);

		for (SignUpEnum value : SignUpEnum.values()) {
			
			// text.setFocusTraversalKeysEnabled(false);
			JPanel gridInGrid = new JPanel(new GridLayout(1, 2, 0, 0));
			new Style(gridInGrid);
			new Style(value.text, 3);
			value.text.setHorizontalAlignment(SwingConstants.LEFT);

			// 鍮꾨쾲, 鍮꾨쾲 �솗�씤
			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM)) {
				JLabel passLabel = new JLabel(value.labelNameKor);
				new Style(passLabel);
				new Style(value.blindPW, 12);
				value.blindPW.setHorizontalAlignment(SwingConstants.LEFT);
				gridInGrid.add(passLabel);
				gridInGrid.add(value.blindPW);
				grid_panel.add(gridInGrid);
				continue;
			}

			JLabel Label = new JLabel(value.labelNameKor);
			new Style(Label);
			gridInGrid.add(Label);

			// �깮�뀈�썡�씪
			if (value.equals(SignUpEnum.BIRTHDAY)) {
				JPanel panelInGrid2 = new JPanel();
				new Style(panelInGrid2);
				panelInGrid2.setLayout(null);
				
				JComboBox<String> year = BirthEnum.YEAR.birthComboBoxYear;
				year.setBounds(0, 23, 65, 30);
				panelInGrid2.add(year);
				new Style(year);
				
				JComboBox<String> month = BirthEnum.MONTH.birthComboBoxMonth;
				month.setBounds(77, 23, 60, 30);
				panelInGrid2.add(month);
				new Style(month);
				
				JComboBox<String> day = BirthEnum.DAY.birthComboBoxDay;

				day.setBounds(149, 23, 60, 30);
				panelInGrid2.add(day);
				new Style(day);

				// �뿰�룄, �썡 �겢由�
				year.addActionListener(new YearMonthClick("year"));
				month.addActionListener(new YearMonthClick("month"));

				gridInGrid.add(panelInGrid2);
				grid_panel.add(gridInGrid);
				continue;
			}

			// �쟾�솕踰덊샇
			if (value.equals(SignUpEnum.PHONENUMBER)) {
				JPanel phoneNumber3Texts = new JPanel();
				phoneNumber3Texts.setLayout(null);
				new Style(phoneNumber3Texts);

				JTextField phone_number1 = PhoneNumberEnum.PHONENUMBER1.text;
				new Style(phone_number1, 3);
				phone_number1.setBounds(0, 23, 65, 30);
				phoneNumber3Texts.add(phone_number1);

				JLabel str = new JLabel("-");
				str.setBounds(68, 21, 10, 30);
				new Style(str);
				phoneNumber3Texts.add(str);

				JTextField phone_number2 = PhoneNumberEnum.PHONENUMBER2.text;
				new Style(phone_number2, 4);
				phone_number2.setBounds(77, 23, 60, 30);
				phoneNumber3Texts.add(phone_number2);

				JLabel str2 = new JLabel("-");
				str2.setBounds(140, 21, 10, 30);
				new Style(str2);
				phoneNumber3Texts.add(str2);

				JTextField phone_number3 = PhoneNumberEnum.PHONENUMBER3.text;
				new Style(phone_number3, 4);
				phone_number3.setBounds(149, 23, 60, 30);
				phoneNumber3Texts.add(phone_number3);

				gridInGrid.add(phoneNumber3Texts);

				grid_panel.add(gridInGrid);

				continue;
			}

			gridInGrid.add(value.text);
			grid_panel.add(gridInGrid);
		}

		// �뀓�뒪�듃瑜� 留덉슦�뒪濡� �늻瑜� �븣
		for (SignUpEnum value : SignUpEnum.values()) {
			if (value.equals(value.PASSWORD) || value.equals(value.PASSWORDCONFIRM))
				value.blindPW.addMouseListener(new ClearTextField(value));
			else
				value.text.addMouseListener(new ClearTextField(value));
		}
		// �쟾踰� �뀓�뒪�듃 留덉슦�뒪濡� �늻瑜� �븣
		for (PhoneNumberEnum phoneValue : PhoneNumberEnum.values())
			phoneValue.text.addMouseListener(new PhoneNumberClearTextField(phoneValue));

		// �빟愿� �뙣�꼸
		JPanel gridInGrid7 = new JPanel(new GridLayout(2, 2, 0, 2));
		new Style(gridInGrid7);
		consent = new HashMap<>();
		consent.put(new JCheckBox(" �꽌鍮꾩뒪 �씠�슜 �룞�쓽"), new JButton("�빟愿�蹂닿린"));
		consent.put(new JCheckBox(" �궗�슜�젙蹂� �룞�쓽"), new JButton("�빟愿�蹂닿린"));

		int consentNum = 0;
		for (Entry<JCheckBox, JButton> kv : consent.entrySet()) {
			new Style(kv.getKey());
			
			new Style(kv.getValue());
			kv.getValue().setFont(new Font("留묒� 怨좊뵓", Font.BOLD, 17));
			
			gridInGrid7.add(kv.getKey());
			gridInGrid7.add(kv.getValue());
			
			// �빟愿� �궡�슜 蹂닿린
			consentNum++;
			kv.getValue().addActionListener(new ConsentContent(consentNum));
		}

		grid_panel.add(gridInGrid7);

		// �듅�씤,嫄곗젅 �뙣�꼸
		int row = 2;
		int col = 4;

		JPanel gridInGrid8 = new JPanel(new GridLayout(row, col, 30, 10));
		new Style(gridInGrid8);
		
		JButton s_Yes = new JButton("媛��엯");
		JButton s_No = new JButton("痍⑥냼");

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (r == 0 && c == 1)
					gridInGrid8.add(s_Yes);
				else if ((r == 0 && c == 2))
					gridInGrid8.add(s_No);
				else
					gridInGrid8.add(new JLabel());
			}
		}

		JButton[] yesNo = { s_Yes, s_No };
		for (int i = 0; i < yesNo.length; i++) {
			new Style(yesNo[i]);
		}

		grid_panel.add(gridInGrid8);
		new Style(grid_panel);
		
		card_panel.add("Signup", grid_panel);
		new Style(card_panel);

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

		background.setLayout(new BorderLayout(60, 10));
		background.add(Style.getnewPanel(), BorderLayout.NORTH);
		background.add(Style.getnewPanel(), BorderLayout.EAST);
		background.add(card_panel, BorderLayout.CENTER);
		background.add(Style.getnewPanel(), BorderLayout.WEST);
		background.add(Style.getnewPanel(), BorderLayout.SOUTH);

		// 諛곌꼍 �닃���쓣 �븣 �뀓�뒪�듃 珥덇린�솕
		addMouseListener(new ClearTextBackGround());
	}

	public static void main(String[] args) {
		new SignUpPage();
	}

}
