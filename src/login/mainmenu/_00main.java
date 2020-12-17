package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import login.design.Style;
import login.page.MainPage;
//import swing.btn.actions.RandomBtnAction;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.ImageIcon;

public class _00main extends JPanel {

//	JTable table;
//	public static int count_seat;
//	public static int count_room;
//	public static int count_locker;
//	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy�� M�� d��");
//	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h�� m�� ");

	public static int seat_chk;
	public static int locker_chk;
	public static int room_chk;
	public static Timestamp time_seat;
	public static Timestamp time_room;
	public static Timestamp time_locker;

	static int id;
	public static String type;
	public static DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� ");

	public _00main() {

		new Style(this);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			// �α��ε� ȸ����ȣ �б�
			String sql = "SELECT person_id from person_info where login_state = 'On'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row;

			while (rs.next()) {
				id = rs.getInt("person_id");

				System.out.println("�α��� �� ȸ����ȣ: " + id);
			}

			// ��� �ð��� ���� �¼� ��� ó��
			sql = "SELECT seat_number, time_checkout FROM seat " + "WHERE seat_statement ='��� ��'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int seat_out = rs.getInt("seat_number");
				Timestamp time_chk = rs.getTimestamp("time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmt2 = conn.prepareStatement(change);
					pstmt2.setInt(1, seat_out);
					int row3 = pstmt2.executeUpdate();
				}
			}

			System.out.println("db����");
			// ��ǽð�(����ð�) ������ ȸ������ ����ð� ����
			sql = "SELECT seat_number,room_number,locker_number,expiration_seat,seat_type "
					+ "FROM person_info WHERE login_state = 'On'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				seat_chk = rs.getInt("seat_number");
				room_chk = rs.getInt("room_number");
				locker_chk = rs.getInt("locker_number");
				time_seat = rs.getTimestamp("expiration_seat");
				type = rs.getString("seat_type");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_seat))) {
					sql = "update person_info set seat_number =null,expiration_seat='01/01/01 00:00:00.000000000',seat_type='x' where login_state = 'On'";
					pstmt = conn.prepareStatement(sql);
					row = pstmt.executeUpdate();
				}
			}

			// ���� �ð��� ���� �繰�� ���� ó��
			sql = "SELECT Locker_Number,l_time_checkout FROM locker " + "WHERE Locker_Statement='��� ��'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int locker_chk = rs.getInt("Locker_Number");
				Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='��� ����',l_time_enter='01/01/01 00:00:00.000000000',"
							+ "l_time_checkout='01/01/01 00:00:00.000000000' where Locker_Number= ?";
					PreparedStatement pstmt3 = conn.prepareStatement(change2);
					pstmt3.setInt(1, locker_chk);
					int row4 = pstmt3.executeUpdate();
				}
			}
			System.out.println(room_chk);
			// ȸ���� �� ����ð� ��������
			if (room_chk == 0) {
				time_room = Timestamp.valueOf("0001-01-01 00:00:00");
			} else {
				sql = "select time_checkout from seat where seat_number = " + room_chk + "";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					time_room = rs.getTimestamp("time_checkout");
				}
			}
			if (locker_chk == 0) {
				time_locker = Timestamp.valueOf("0001-01-01 00:00:00");
			} else {
				sql = "select l_time_checkout from locker where locker_number = " + locker_chk + "";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					time_locker = rs.getTimestamp("l_time_checkout");
				}
			}

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		// �޴� ��ư 4��
		JButton ticket_btn = new JButton("�̿�Ǳ���");
		ticket_btn.setBounds(130, 150, 208, 121);
		this.add(ticket_btn);

		JButton in_btn = new JButton("�Խ��ϱ�");
		in_btn.setBounds(348, 150, 213, 121);
		this.add(in_btn);

		JButton move_btn = new JButton("�ڸ��̵�");
		move_btn.setBounds(130, 281, 208, 126);
		this.add(move_btn);

		JButton out_btn = new JButton("����ϱ�");
		out_btn.setBounds(348, 281, 213, 126);
		this.add(out_btn);

		JButton chk_info = new JButton("���� ������");
		chk_info.setBounds(130, 110, 120, 30);
		this.add(chk_info);

		new Style(ticket_btn);
		new Style(in_btn);
		new Style(move_btn);
		new Style(out_btn);
		new Style(chk_info);

//		// ���͵�� ��Ȳǥ
//		String header[] = { "1�μ�", "���͵��", "�繰��", "����ð�" };
//		String contents[][] = { { "<html>����� 1�μ�<br/>&emsp;&emsp;" + Integer.toString(count_seat) + " / 20",
//				"<html>����� ���͵��<br/>&emsp;&emsp;&emsp;" + Integer.toString(count_room) + " / 4",
//				"<html>����� �繰��<br/>&emsp;&emsp;" + Integer.toString(count_locker) + " / 20",
//				"<html>&emsp;&nbsp;&nbsp;&nbsp;����ð�<br/>" + LocalDate.now().format(date) + "<br/>&nbsp;&nbsp;&nbsp;"
//						+ LocalTime.now().format(time) } };
//
//		DefaultTableModel model = new DefaultTableModel(contents, header);
//		table = new JTable(model);
//
//		table.setBounds(80, 80, 437, 80);
//		table.setRowHeight(80);
//
//		// �׵θ�
//		Color color = UIManager.getColor("Table.gridColor");
//		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
//		table.setBorder(border);
//		
//		// ��Ȳǥ �۾� �߾� ����
//		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
//		table.getColumn("1�μ�").setCellRenderer(celAlignCenter);
//		table.getColumn("���͵��").setCellRenderer(celAlignCenter);
//		table.getColumn("�繰��").setCellRenderer(celAlignCenter);
//		table.getColumn("����ð�").setCellRenderer(celAlignCenter);
//		
//		new Style(celAlignCenter);
//		new Style(table);
//		table.setFont(new Font("���� ���", Font.BOLD, 11));
//		this.add(table);
//		

		chk_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new _00myPage();

			}
		});
		ticket_btn.addActionListener(new ActionListener() { // �̿�� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("�̿�Ǳ���", new _01start()); // �̿�Ǳ��� ������
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			}
		});

		move_btn.addActionListener(new ActionListener() { // �ڸ��̵� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((seat_chk > 0 && seat_chk < 21) || (room_chk >= 101 && room_chk <= 104)) {
					MainPage.user_page_panel.add("�ڸ��̵�", new _06move());
					MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
					MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ��̵�");
					MainPage.userToggle = "�ڸ��̵�";

				} else {
					String msg = "��� ���� �¼��� �����ϴ�.";
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});

		out_btn.addActionListener(new ActionListener() { // ��� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("���������", new _07out());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���������");
				MainPage.userToggle = "���������";
			}
		});

		in_btn.addActionListener(new ActionListener() { // �Խ� ������(�����̿�� �̿���)

			@Override
			public void actionPerformed(ActionEvent e) {
				if (seat_chk > 0) {
					String msg = "�̹� �¼��� �ֽ��ϴ�.";
					JOptionPane.showMessageDialog(null, msg);
				} else if (type.equals("���� �̿��")) {
					MainPage.user_page_panel.add("�Խ�������", new _07in_selectSeat());
					MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
					MainPage.user_cards.show(MainPage.user_page_panel, "�Խ�������");
					MainPage.userToggle = "�Խ�������";
				} else {
					String msg1 = "���� �̿�� �̿��ڸ� �����մϴ�";
					JOptionPane.showMessageDialog(null, msg1);
				}
			}
		});

	}

}
