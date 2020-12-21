package login.page;

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

import login.design.Style;

public class StoreMovePage extends JPanel implements ActionListener {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JLabel title;
	JLabel clock;
	JButton back;
	JButton info;
	Thread thread;
	
	JMenuBar bar = new JMenuBar();
	JMenu menu = new JMenu("                      ��ȣ�� ���� �¼� / �� / �繰�� ��� ����                    ");
	JMenu s = new JMenu("1�μ�");
	JMenu r = new JMenu("��");	
	JMenu l = new JMenu("�繰��");
	public static int seat_move_number;
	public static int room_move_number;
	public static int locker_move_number;
	public static String type;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;

	int a=0;
	int d=0;
	int e=0; 
	int f=0; 
	int g=0;
	int c=0;
	int q=0;
	int count = 0;

	ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
	{
		for(int i=0;i<20;i++) {
			seats_btn.add(new JButton());
			seats_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "�¼�"));
		}
	}
	ArrayList<JButton> room_btn = new ArrayList<>(); //101~104ȣ (��) ��ư
	{
		for(int i=0;i<4;i++) {//0~3
			room_btn.add(new JButton());		
			room_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "��"));
		}
	}
	ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20�� �繰�� ��ư
	{
		for(int i=0;i<20;i++) {
			locker_btn.add(new JButton()); 
			locker_btn.get(i).addActionListener(new StoreMoveBtnAction(i, "�繰��"));
		}
	}
	
	ArrayList<JMenuItem> seat = new ArrayList<>(); //1~20�� �¼� (1�μ�) �޴� ��ư
	{
	for(int i=0; i<20; ++i) {
		seat.add(new JMenuItem(Integer.toString(i+1)+"��"));
		seat.get(i).addActionListener(new StoreMoveBtnAction(i, "�¼�"));
		s.add(seat.get(i));
	}
	}
	
	ArrayList<JMenuItem> room = new ArrayList<>(); //101~104ȣ (��) �޴� ��ư
	{
	for(int i=0; i<4; ++i) {
		room.add(new JMenuItem(Integer.toString(i+101)+"ȣ"));
		room.get(i).addActionListener(new StoreMoveBtnAction(i, "��"));
		r.add(room.get(i));
	}
	}

	ArrayList<JMenuItem> locker = new ArrayList<>(); //1~20�� �繰�� �޴� ��ư
	{
	for(int i=0; i<20; ++i) {
		locker.add(new JMenuItem(Integer.toString(i+1)+"��"));
		locker.get(i).addActionListener(new StoreMoveBtnAction(i, "�繰��"));
		l.add(locker.get(i));
	}
	}
	
	public StoreMovePage() {

		this.setLayout(new BorderLayout());
		new Style(this);
			
		menu.add(s);
		menu.add(r);
		menu.add(l);
		bar.add(menu);
		
		JPanel top = new JPanel();
		top.add(bar);
		new Style(top);
		add(top, BorderLayout.NORTH);
		
		JPanel c = new JPanel();
		add(c, BorderLayout.CENTER);
		c.setLayout(null);
		new Style(c);

		JLabel label03 = new JLabel("�繰��");
		label03.setBounds(65,320,50,30);
		label03.setFont(new Font("���� ����", Font.BOLD, 10));
		c.add(label03);
		new Style(label03);

		JLabel label04 = new JLabel("�ްԽ�");
		label04.setOpaque(true); 
		label04.setBorder(BorderFactory.createLineBorder(Color.gray));
		label04.setBackground(Color.black);
		label04.setForeground(Color.decode("#5590cf"));
		label04.setBounds(300,225,180,85);
		label04.setFont(new Font("���� ����", Font.BOLD, 15));
		label04.setHorizontalAlignment(JLabel.CENTER);
		c.add(label04);

		JLabel label05 = new JLabel("��� ����");
		label05.setOpaque(true);
		label05.setBackground(Color.black);
		label05.setForeground(Color.orange);
		label05.setHorizontalAlignment(JLabel.CENTER);
		label05.setBounds(90,435,100,30);
		c.add(label05);

		JLabel label06 = new JLabel("��� ��");
		label06.setOpaque(true);
		label06.setBackground(Color.black);
		label06.setForeground(Color.gray);
		label06.setHorizontalAlignment(JLabel.CENTER);
		label06.setBounds(90,470,100,30);
		c.add(label06);

		for(int i=0;i<3;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(80+f,10,60,60); 
			f+=60;  
		}
		for(int i=3;i<6;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(120+f,10,60,60); 
			f+=60;  
		}
		for(int i=6;i<11;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(170+f,10+a,60,60); 
			a+=60;
		}
		for(int i=11; i<14;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(80+g,70,60,60);
			g+=60;  
		}
		for(int i=14; i<17;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(120+g,70,60,60);
			g+=60;  
		}
		g-=180;
		for(int i=17; i<20;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(120+g,160,60,60);
			g+=60;  
		}
		for(int i=0;i<2;i++) {//0~3 �� ��ư ��ġ ����
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(80+e,160,90,75);
			e+=90; 
		}
		for(int i=2;i<4;i++) {//0~3 �� ��ư ��ġ ����
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(80+d,235,90,75);
			d+=90; 
		}
		for(int i=0;i<10;i++) {// �繰�� ��ư ��ġ ����
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(50+q,350,60,30);
			q+=60;
		}
		d=0;
		for(int i=10; i<20;i++) { // �繰�� ��ư ��ġ ����
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(50+d,380,60,30);
			d+=60;   
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);

			//������� �¼�
			String sql = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
			System.out.print("����� �ڸ� : ");

			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					System.out.printf("%d�� ",sn); 
					seats_btn.get(sn-1).setForeground(Color.gray);
					seats_btn.get(sn-1).setEnabled(false);
				}else if (sn>100) {
					System.out.printf("[%dȣ] ",sn); 
					room_btn.get(sn-101).setForeground(Color.gray);
					room_btn.get(sn-1).setEnabled(false);
				}
			}

			// ������� �繰��
			sql = "select locker_number from locker where locker_statement='��� ��'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			System.out.println();
			System.out.printf("����� �繰�� : ");
			while(rs.next()) {
				int sn = rs.getInt("locker_number");
				System.out.printf("%d�� ",sn);
				locker_btn.get(sn-1).setForeground(Color.gray);
				locker_btn.get(sn-1).setEnabled(false);
			}

			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();

		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}		
		
		info = new JButton("���� ���� ���̺�");
		info.addActionListener(new StoreDBPage());
		info.setBounds(230,435,220,65);
		new Style(info);
		c.add(info);
		
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_page_panel.add("����������̺�", new StoreDBPage());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.main_cards.show(MainPage.main_page_panel, "����������̺�");
				MainPage.userToggle = "����������̺�";
			}
		});
		
		back = new JButton("����");
		new Style(back);
		back.setBounds(480,435,120,65);
		c.add(back);

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
