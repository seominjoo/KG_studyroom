package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.signUp.SignUpPage;
import login.swingTools.Login_SwingTool;
import login.swingTools.State;
import login.window.MainBtn_Action;

public class MainPage extends JFrame implements Runnable {

	public static int x = new Conversion_image("image/���ȭ��(����).jpg", 4).x; // ��ü ������ ���� ����
	public static int y = new Conversion_image("image/���ȭ��(����).jpg", 4).y; // ��ü ������ ���� ����
	public static int w = 683; // ���� �г� ���� ����(width)
	public static int h = 562; // ���� �г� ���� ����(height)
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;
	public static JPanel updateTable;
	public static CardLayout statecard;
	public static JLabel background;
	public static JPanel logout;
	public static CardLayout logoutcard;
	public static JButton logoutbtn;
	public static JButton empty;
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
		fram_panel.setBounds(0, 0, x, y);// x=1000, y=562
		// ��� �̹���
		background = new JLabel(new Conversion_image("image/���ȭ��(����).jpg", 4).imageicon_smooth);
		background.setOpaque(false);
		background.setBounds(0, 0, x, y);// x=1000, y=562

		// ���� ī�������� �г�
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(x - w, 0, w, h); // w=683, h=562

		// ����ڸ޴� ī�������� �г�
		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(x - w, 0, w, h); // w=683, h=562

		// �¼���Ȳ �г�
		updateTable = new JPanel();
		new Style(updateTable);
		statecard = new CardLayout();
		updateTable.setLayout(statecard);
		updateTable.setBounds(18, 190, 280, 50);
		updateTable.add(new State());

		// �α׾ƿ� �г�
		logout = new JPanel();
		new Style(logout);
		logoutcard = new CardLayout();
		logout.setLayout(logoutcard);
		logout.setBounds(x - 107, 5, 100, 30); // x=1000
		// ��ȭ��
		empty = new JButton();
		new Style(empty);
		empty.setBounds(0, 0, 100, 30);
		logout.add("1", empty);
		// �α׾ƿ���ư
		logoutbtn = new JButton("�α׾ƿ�");
		new Style(logoutbtn);
		logoutbtn.setBounds(0, 0, 90, 30);
		logoutbtn.setFont(new Font("���� ���", Font.BOLD, 13));
		logoutbtn.addActionListener(new MainBtn_Action(logoutbtn));
		logout.add("2", logoutbtn);

		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

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
//      user_page_panel.add("����������", new _08reservation(ss,price,seat_type));
//      user_page_panel.add("�ڸ�������", new _06move_selectSeat());
//		user_page_panel.add("����������", new _09payment(ss, price, seat_type));
//      user_page_panel.add("�����˸�â", new _10paycash(ss));

		// �������� Ȯ�� ��ư(or ����ٷΰ��� or�α׾ƿ�off)
//      user_page_panel.add("������", new _11receipt(ss, price));

		JButton changeUser = new JButton(new Conversion_image("image/������.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("�����ڹ�ư");
		changeUser.setBounds(5, 5, 40, 40);
		changeUser.addActionListener(new MainBtn_Action(changeUser));

		// ���� �ð�
		clock = new JLabel();
		new Style(clock);
		clock.setForeground(Color.decode("#dec5ae"));
		clock.setHorizontalAlignment(JLabel.CENTER);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		clock.setBounds(8, 150, 300, 30);

		background.add(logout);
		background.add(changeUser);
		background.add(clock);
		background.add(updateTable);

		fram_panel.add(background);

		Login_SwingTool.initFrame(this);
		add(main_page_panel);
		add(fram_panel);

	}

	@Override
	public void run() {
		while (true) {
			Calendar cal = Calendar.getInstance();
			String now = cal.get(Calendar.YEAR) + "�� " + (cal.get(Calendar.MONTH) + 1) + "�� " + cal.get(Calendar.DATE)
					+ "�� " + cal.get(Calendar.HOUR) + "�� " + cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND)
					+ "�� ";
			clock.setText(now);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MainPage();
	}
}