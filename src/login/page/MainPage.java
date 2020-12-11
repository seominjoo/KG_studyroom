package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._00main;
import login.mainmenu._01start;
import login.mainmenu._06move;
import login.mainmenu._07in_seletSeat;
import login.mainmenu._07out;
import login.signUp.SignUpPage;
import login.window.MainBtn_Action;
import login.window.Login_SwingTool;

public class MainPage extends JFrame {

	int x = new Conversion_image("image/�α���ȭ��.jpg", 5).x;
	int y = new Conversion_image("image/�α���ȭ��.jpg", 5).y;
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;

	public static String userToggle;
	
	public MainPage() {
		userToggle = "����";
		// ���ȭ��
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		JLabel background = new JLabel(new Conversion_image("image/�α���ȭ��.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);

		// ������ ī�������� �г�
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(1577 / 5, 0, 2423 / 5, 2250 / 5);
		
		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(1577 / 5, 0, 2423 / 5, 2250 / 5);
		
		JPanel main = new JPanel(new BorderLayout());
		new Style(main);
		JButton touch = new JButton("<html>��ġ�� �Ͽ�<br/>�̿����ּ���</html>");
		new Style(touch);
		touch.setBorder(null);
		touch.addActionListener(new MainBtn_Action(touch));
		main.add(touch);

		// ���� ������ �߰� �۾�
		main_page_panel.add("����", main);
		main_page_panel.add("�α���", new LoginPage());
		main_page_panel.add("����ڸ޴�", user_page_panel);
		main_page_panel.add("������", new AdminPage());
		main_page_panel.add("�����ڸ޴�", new AdminMenuPage());
		main_page_panel.add("ȸ������", new SignUpPage());
		main_page_panel.add("���ã��", new FindPasswordPageUser());
		
		// ����� �޴� ������ �߰� �۾�
		user_page_panel.add("����ڸ޴�", new _00main());
		user_page_panel.add("�̿�Ǳ���", new _01start());
		user_page_panel.add("�ڸ��̵�", new _06move());
		user_page_panel.add("�Խ��ϱ�", new _07in_seletSeat());
		user_page_panel.add("����ϱ�", new _07out());
//		user_page_panel.add("�ڸ��̵�", new _06move());
		
		
		
		
		
		

		JButton changeUser = new JButton(new Conversion_image("image/����.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("�����ڹ�ư");
		changeUser.setBounds(0, 0, 30, 30);
		changeUser.addActionListener(new MainBtn_Action(changeUser));
		background.add(changeUser);
		fram_panel.add(background);

		Login_SwingTool.initFrame(this);

		add(main_page_panel);
		add(fram_panel);
		Login_SwingTool.initFrame(this);
	}

	public static void main(String[] args) {
		new MainPage();
	}
}
