package studyroom.admin.adminmode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import studyroom.MainPage;
import studyroom.design.Style;

public class StoreMovePage extends JPanel implements ActionListener {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JLabel title;
	JLabel clock;
	JButton back;
	JButton info;
	String time_checkout;
	Thread thread;

	JMenuBar bar = new JMenuBar();
	JMenu menu = new JMenu("��ȣ�� ���� ��� ����");
	JMenu s = new JMenu("1�μ�");
	JMenu r = new JMenu("��");   
	JMenu l = new JMenu("�繰��");

	public static int seat_move_number;
	public static int room_move_number;
	public static int locker_move_number;
	public static String type;
	static LocalDateTime time_now = LocalDateTime.now();

	int a=0;
	int d=0;
	int e=0; 
	int f=0; 
	int g=0;
	int c=0;
	int q=0;
	int count = 0;

	ArrayList<JButton> seats_btn = new ArrayList<>();
	{
		for(int i=0;i<20;i++) {
			seats_btn.add(new JButton());
			seats_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "�¼�"));
			seats_btn.get(i).setEnabled(true);
		}
	}
	ArrayList<JButton> room_btn = new ArrayList<>(); 
	{
		for(int i=0;i<4;i++) {//0~3
			room_btn.add(new JButton());      
			room_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "��"));
			room_btn.get(i).setEnabled(true);
		}
	}
	ArrayList<JButton> locker_btn = new ArrayList<>(); 
	{
		for(int i=0;i<20;i++) {
			locker_btn.add(new JButton()); 
			locker_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "�繰��"));
			locker_btn.get(i).setEnabled(true);
		}
	}

	ArrayList<JMenuItem> seat = new ArrayList<>(); 
	{
		for(int i=0; i<20; ++i) {
			seat.add(new JMenuItem(Integer.toString(i+1)+"��"));
			seat.get(i).addActionListener(new StoreMoveBtnAction(i, "�¼�"));
			seat.get(i).setEnabled(true);
			s.add(seat.get(i));
		}
	}

	ArrayList<JMenuItem> room = new ArrayList<>(); 
	{
		for(int i=0; i<4; ++i) {
			room.add(new JMenuItem(Integer.toString(i+101)+"ȣ"));
			room.get(i).addActionListener(new StoreMoveBtnAction(i, "��"));
			room.get(i).setEnabled(true);
			r.add(room.get(i));
		}
	}

	ArrayList<JMenuItem> locker = new ArrayList<>(); 
	{
		for(int i=0; i<20; ++i) {
			locker.add(new JMenuItem(Integer.toString(i+1)+"��"));
			locker.get(i).addActionListener(new StoreMoveBtnAction(i, "�繰��"));
			locker.get(i).setEnabled(true);
			l.add(locker.get(i));
		}
	}

	public StoreMovePage() {

		this.setLayout(new BorderLayout(0,20));
		new Style(this);

		JPanel top = new JPanel();
		top.add(bar);
		top.setBounds(85, 0, 130, 27);
		new Style(top);
		add(top);
		add(Style.getnewPanel(), BorderLayout.NORTH);

		menu.add(s);
		menu.add(r);
		menu.add(l);
		bar.add(menu);

		JPanel c = new JPanel();
		add(c, BorderLayout.CENTER);
		c.setLayout(null);
		new Style(c);

		JLabel label01 = new JLabel("1�μ�");
		label01.setBounds(43,50,50,30);
		new Style(label01); 
		label01.setFont(new Font("���� ���", Font.BOLD, 12));
		c.add(label01);

		JLabel label02 = new JLabel("��");
		label02.setBounds(60,215,50,30);
		new Style(label02);
		label02.setFont(new Font("���� ���", Font.BOLD, 13));
		c.add(label02);

		JLabel label03 = new JLabel("�繰��");
		label03.setBounds(60,320,50,30);
		new Style(label03);
		label03.setFont(new Font("���� ���", Font.BOLD, 12));
		c.add(label03);

		JLabel label04 = new JLabel("�ްԽ�");
		label04.setOpaque(true); 
		label04.setBorder(BorderFactory.createLineBorder(Color.gray));
		label04.setBackground(Color.decode("#241614"));
		label04.setForeground(Color.decode("#a8877d"));
		label04.setBounds(315,225,180,85);
		label04.setFont(new Font("���� ���", Font.BOLD, 15));
		label04.setHorizontalAlignment(JLabel.CENTER);
		c.add(label04);

		JLabel label05 = new JLabel("��� ����");
		label05.setOpaque(true);
		label05.setBackground(Color.decode("#241614"));
		label05.setForeground(Color.orange);
		label05.setHorizontalAlignment(JLabel.CENTER);
		label05.setBounds(90,435,177,30);
		c.add(label05);

		JLabel label06 = new JLabel("��� ��");
		label06.setOpaque(true);
		label06.setBackground(Color.decode("#241614"));
		label06.setForeground(Color.gray);
		label06.setHorizontalAlignment(JLabel.CENTER);
		label06.setBounds(90,470,177,30);
		c.add(label06);

		info = new JButton("���� ���̺�");
		info.addActionListener(new StoreDBPage());
		info.setBounds(465,435,140,65);
		new Style(info);
		c.add(info);

		back = new JButton("���� ȭ��");
		new Style(back);
		back.setBounds(315,435,140,65);
		c.add(back);

		for(int i=0;i<3;i++) {
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(85+f,10,60,60); 
			f+=60;  
		}
		for(int i=3;i<6;i++) {
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(135+f,10,60,60); 
			f+=60;  
		}
		for(int i=6;i<11;i++) {
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(185+f,10+a,60,60); 
			a+=60;
		}
		for(int i=11; i<14;i++) {
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(85+g,70,60,60);
			g+=60;  
		}
		for(int i=14; i<17;i++) {
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(135+g,70,60,60);
			g+=60;  
		}
		g-=180;
		for(int i=17; i<20;i++) { 
			seats_btn.get(i).setBackground(Color.decode("#241614"));
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(135+g,160,60,60);
			g+=60;  
		}
		for(int i=0;i<2;i++) {
			room_btn.get(i).setBackground(Color.decode("#241614"));
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(85+e,160,90,75);
			e+=90; 
		}
		for(int i=2;i<4;i++) {
			room_btn.get(i).setBackground(Color.decode("#241614"));
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(85+d,235,90,75);
			d+=90; 
		}
		for(int i=0;i<10;i++) {
			locker_btn.get(i).setBackground(Color.decode("#241614"));
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(50+q,350,60,30);
			q+=60;
		}
		d=0;
		for(int i=10; i<20;i++) { 
			locker_btn.get(i).setBackground(Color.decode("#241614"));
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(50+d,380,60,30);
			d+=60;   
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			String sql = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 

			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					seats_btn.get(sn-1).setEnabled(false);
				}else if (sn>100) {
					room_btn.get(sn-101).setEnabled(false);
				}
			}

			sql = "select locker_number from locker where locker_statement='��� ��'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while(rs.next()) {
				int sn = rs.getInt("locker_number");
				locker_btn.get(sn-1).setEnabled(false);
			}

			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();

		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}      

		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_page_panel.add("����������̺�", new StoreDBPage());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.main_cards.show(MainPage.main_page_panel, "����������̺�");
				MainPage.userToggle = "����������̺�";
			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "�����ڸ޴�");
				MainPage.userToggle = "�����ڸ޴�";
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("�������-�̵�", new StoreMovePage());
		MainPage.main_cards.show(MainPage.main_page_panel, "�������-�̵�");
		MainPage.userToggle = "�������-�̵�";
	}
}