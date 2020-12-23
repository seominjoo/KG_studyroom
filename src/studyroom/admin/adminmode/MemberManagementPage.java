package studyroom.admin.adminmode;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import studyroom.MainPage;
import studyroom.design.Style;

public class MemberManagementPage extends JPanel implements ActionListener{

	JButton back;
	JButton num_member;
	JButton type;
	JButton current_user;

	static JScrollPane scrollPane;

	public static JComboBox<String> month;
	public static JComboBox<String> dayOrWeek;
	public static JComboBox<String> seat_room_locker;

	static JLabel totalmember;
	static JLabel newmember;


	public MemberManagementPage() {

		setLayout(null);
		new Style(this);

		totalmember = new JLabel();
		new Style(totalmember);
		totalmember.setBounds(80, 451, 150, 30);
		add(totalmember);

		newmember = new JLabel();
		new Style(newmember);
		newmember.setBounds(80, 481, 150, 30);
		add(newmember);

		scrollPane = new JScrollPane();
		new Style(scrollPane);

		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.setBounds(50, 85, 560, 350);
		add(scrollPane);

		month = new JComboBox<String>(new ManagementDate().monthTable2);
		month.setBounds(90, 40, 65, 30);
		add(month);
		new Style(month);

		dayOrWeek = new JComboBox<String>(new ManagementDate().typeTable);
		dayOrWeek.setBounds(255, 40, 80, 30);
		add(dayOrWeek);
		new Style(dayOrWeek);

		seat_room_locker = new JComboBox<String>(new ManagementDate().use_statusTable);
		seat_room_locker.setBounds(420, 40, 80, 30);
		add(seat_room_locker);
		new Style(seat_room_locker);

		num_member = new JButton("회원 수");
		new Style(num_member);
		num_member.setBounds(158, 40, 70, 30);
		add(num_member);

		num_member.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberDB(1); 
			}
		});

		type = new JButton("이용권");
		new Style(type);
		type.setBounds(338, 40, 60, 30);
		add(type);

		type.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberDB(2); 
			}
		});

		current_user = new JButton("이용 현황");
		new Style(current_user);
		current_user.setBounds(503, 40, 70, 30);
		add(current_user);

		current_user.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberDB(3); 
			}
		});

		back = new JButton("이전");
		new Style(back);
		back.setBounds(479, 481, 100, 30);
		add(back);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				MainPage.main_cards.show(MainPage.main_page_panel, "관리자메뉴");
				MainPage.userToggle = "관리자메뉴";
			}
		}); 
		new MemberDB(1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("회원관리", new MemberManagementPage());
		MainPage.main_cards.show(MainPage.main_page_panel, "회원관리");
		MainPage.userToggle = "회원관리";
	}
}