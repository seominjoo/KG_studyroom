package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._00main;
import login.mainmenu._01start;
import login.mainmenu._02dayOrWeek;
import login.mainmenu._02dayRoom;
import login.mainmenu._03whatHour;
import login.mainmenu._03whatHourRoom;
import login.mainmenu._03whatWeek;
import login.mainmenu._05locker;
import login.mainmenu._06move;
import login.mainmenu._06move_selectSeat;
import login.mainmenu._07in_selectSeat;
import login.mainmenu._07out;
import login.mainmenu._08reservation;
import login.mainmenu._09payment;
import login.signUp.SignUpPage;
import login.window.MainBtn_Action;
import login.window.Login_SwingTool;

public class MainPage extends JFrame implements Runnable {

	int x = new Conversion_image("image/���ȭ��.jpg", 4).x;
	int y = new Conversion_image("image/���ȭ��.jpg", 4).y;
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;

	Thread thread;
	JLabel clock;

	public static String userToggle;

	// �����ϱ� �ʿ��� �Ű�����
	public static LocalDateTime ss;
	public static int price;
	public static String seat_type;

	public MainPage() {

		userToggle = "����";
		// ���ȭ��
		JPanel fram_panel = new JPanel();
		new Style(fram_panel);
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		JLabel background = new JLabel(new Conversion_image("image/���ȭ��.jpg", 4).imageicon_smooth);
		background.setOpaque(false);
		background.setBounds(0, 0, x, y);

		// ������ ī�������� �г�
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(316, 0, 683, 562);

		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(316, 0, 683, 562);

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
//      user_page_panel.add("���θ޴�", new _00main()); // �޴�������
//      user_page_panel.add("�̿�Ǳ���", new _01start()); // �̿�Ǳ��� ������
//      user_page_panel.add("�¼��̿��", new _02dayOrWeek()); // �¼� �̿�� ������
//      user_page_panel.add("���̿��", new _02dayRoom());
//      user_page_panel.add("���ϱǰ���ǥ(�¼�)", new _03whatHour());
//      user_page_panel.add("����ǰ���ǥ", new _03whatWeek());
//      user_page_panel.add("���ϱǰ���ǥ(��)", new _03whatHourRoom());
//      user_page_panel.add("�繰���̿��", new _05locker());
//      user_page_panel.add("�ڸ��̵�", new _06move());
//      user_page_panel.add("�Խ�������", new _07in_seletSeat());
//      user_page_panel.add("���������", new _07out());
//      //user_page_panel.add("����������", new _08reservation(ss,price,seat_type));
//      user_page_panel.add("�ڸ�������", new _06move_selectSeat());
		// user_page_panel.add("����������", new _09payment(ss, price, seat_type));
//      user_page_panel.add("�����˸�â", new _10paycash(ss));

		// �������� Ȯ�� ��ư(or ����ٷΰ��� or�α׾ƿ�off)
//      user_page_panel.add("������", new _11receipt(ss, price));

		JButton changeUser = new JButton(new Conversion_image("image/������.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("�����ڹ�ư");
		changeUser.setBounds(5, 5, 40, 40);
		changeUser.addActionListener(new MainBtn_Action(changeUser));

		JButton logout = new JButton("�α׾ƿ�");
		new Style(logout);

		logout.setBounds(890, 0, 100, 30);
		logout.addActionListener(new MainBtn_Action(logout));
		background.add(logout);
		background.add(changeUser);
		fram_panel.add(background);

		add(main_page_panel);
		add(fram_panel);
		Login_SwingTool.initFrame(this);

		clock = new JLabel();
		clock.setHorizontalAlignment(JLabel.CENTER);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}

		add(clock);
		clock.setBounds(110, 0, 300, 30);
	}

	@Override
	public void run() {
//		while (true) {
//			Calendar cal = Calendar.getInstance();
//			String now = cal.get(Calendar.YEAR) + "�� " + 
//			(cal.get(Calendar.MONTH) + 1) + "�� " + cal.get(Calendar.DATE)
//					+ "�� " + cal.get(Calendar.HOUR) + "�� " 
//			+ cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND)
//					+ "�� ";
//			clock.setText(now);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public static void main(String[] args) {
		new MainPage();
	}
}