package login.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._01start;

public class StoreManagementPage extends JPanel implements ActionListener {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JLabel title;
	JLabel clock;
	JButton back;
	JButton info;
	Thread thread;
	
	JMenuBar bar = new JMenuBar();
	JMenu menu = new JMenu("                        ��ȣ�� ���� �¼� / �� / �繰�� ��� ����                    ");
	JMenu s = new JMenu("1�μ�");
	JMenu r = new JMenu("��");	
	JMenu l = new JMenu("�繰��");
	public static int seat_number;
	public static int room_number;
	public static int locker_number;
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

	static ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
	{
		for(int i=0;i<20;i++) {
			seats_btn.add(new JButton());
			seats_btn.get(i).addActionListener(new StoreBtnAction(i, "�¼�"));
		}
	}
	static ArrayList<JButton> room_btn = new ArrayList<>(); //101~104ȣ (��) ��ư
	{
		for(int i=0;i<4;i++) {//0~3
			room_btn.add(new JButton());		
			room_btn.get(i).addActionListener(new StoreBtnAction(i, "��"));
		}
	}
	static ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20�� �繰�� ��ư
	{
		for(int i=0;i<20;i++) {
			locker_btn.add(new JButton()); 
			locker_btn.get(i).addActionListener(new StoreBtnAction(i, "�繰��"));
		}
	}
	
	static ArrayList<JMenuItem> seat = new ArrayList(); //1~20�� �¼� (1�μ�) �޴� ��ư
	{
	for(int i=0; i<20; ++i) {
		seat.add(new JMenuItem(Integer.toString(i+1)+"��"));
		s.add(seat.get(i));
		seat.get(i).addActionListener(new StoreBtnAction(i, "�¼�"));
	}
	}
	
	static ArrayList<JMenuItem> room = new ArrayList(); //101~104ȣ (��) �޴� ��ư
	{
	for(int i=0; i<4; ++i) {
		room.add(new JMenuItem(Integer.toString(i+101)+"ȣ"));
		r.add(room.get(i));
		room.get(i).addActionListener(new StoreBtnAction(i, "��"));
	}
	}

	static ArrayList<JMenuItem> locker = new ArrayList(); //1~20�� �繰�� �޴� ��ư
	{
	for(int i=0; i<20; ++i) {
		locker.add(new JMenuItem(Integer.toString(i+1)+"��"));
		l.add(locker.get(i));
		locker.get(i).addActionListener(new StoreBtnAction(i, "�繰��"));
	}
	}
	
	public StoreManagementPage() {

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
		label03.setBounds(15,320,50,30);
		label03.setFont(new Font("���� ���", Font.BOLD, 10));
		c.add(label03);
		new Style(label03);

		JLabel label04 = new JLabel("�ްԽ�");
		label04.setOpaque(true); 
		label04.setBorder(BorderFactory.createLineBorder(Color.gray));
		label04.setBackground(Color.black);
		label04.setForeground(Color.decode("#5590cf"));
		label04.setBounds(250,225,180,85);
		label04.setFont(new Font("���� ���", Font.BOLD, 15));
		label04.setHorizontalAlignment(JLabel.CENTER);
		c.add(label04);

		JLabel label05 = new JLabel("��� ����");
		label05.setOpaque(true);
		label05.setBackground(Color.black);
		label05.setForeground(Color.orange);
		label05.setHorizontalAlignment(JLabel.CENTER);
		label05.setBounds(40,435,100,30);
		c.add(label05);

		JLabel label06 = new JLabel("��� ��");
		label06.setOpaque(true);
		label06.setBackground(Color.black);
		label06.setForeground(Color.gray);
		label06.setHorizontalAlignment(JLabel.CENTER);
		label06.setBounds(40,470,100,30);
		c.add(label06);

		for(int i=0;i<3;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(30+f,10,60,60); 
			f+=60;  
		}
		for(int i=3;i<6;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(70+f,10,60,60); 
			f+=60;  
		}
		for(int i=6;i<11;i++) {// 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(120+f,10+a,60,60); 
			a+=60;
		}
		for(int i=11; i<14;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(30+g,70,60,60);
			g+=60;  
		}
		for(int i=14; i<17;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(70+g,70,60,60);
			g+=60;  
		}
		g-=180;
		for(int i=17; i<20;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			c.add(seats_btn.get(i));
			seats_btn.get(i).setBounds(70+g,160,60,60);
			g+=60;  
		}
		for(int i=0;i<2;i++) {//0~3 �� ��ư ��ġ ����
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(30+e,160,90,75);
			e+=90; 
		}
		for(int i=2;i<4;i++) {//0~3 �� ��ư ��ġ ����
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			c.add(room_btn.get(i));
			room_btn.get(i).setBounds(30+d,235,90,75);
			d+=90; 
		}
		for(int i=0;i<10;i++) {// �繰�� ��ư ��ġ ����
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(10+q,350,60,30);
			q+=55;
		}
		d=0;
		for(int i=10; i<20;i++) { // �繰�� ��ư ��ġ ����
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			c.add(locker_btn.get(i));
			locker_btn.get(i).setBounds(10+d,380,60,30);
			d+=55;   
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
				}else if (sn>100) {
					System.out.printf("[%dȣ] ",sn); 
					room_btn.get(sn-101).setForeground(Color.gray);
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
			}

			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();

		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}		

		info = new JButton("���� ���� ���̺�");
		info.addActionListener(new StoreDBPage());
		info.setBounds(180,435,220,65);
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
		back.setBounds(430,435,120,65);
		c.add(back);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.main_cards.show(MainPage.main_page_panel, "�����ڸ޴�");
				MainPage.userToggle = "�����ڸ޴�";
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("�������", new StoreManagementPage());
		MainPage.main_cards.show(MainPage.main_page_panel, "�������");
		MainPage.userToggle = "�������";
	}

}
