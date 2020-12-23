package login.mainmenu;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class _00main extends JPanel {

	public static int seat_chk;
	public static int room_chk;
	public static int locker_chk;

	public static Timestamp time_seat;
	public static Timestamp time_room;
	public static Timestamp time_locker;

	static int id;
	public static String type;
	public static DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� ");

	public _00main() {

		new Style(this);
		setLayout(null);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			// �α��� �� ȸ����ȣ �б�
			String sql = "SELECT person_id from person_info where login_state = 'On'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row; 
			while (rs.next()) {
				id = rs.getInt("person_id"); 
				System.out.println("�α��� �� ȸ����ȣ: " + id);
			}

			// ��� �ð��� ���� �¼� ��� ó��
			sql = "SELECT seat_number, time_checkout FROM seat WHERE seat_statement ='��� ��'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int seat_out = rs.getInt("seat_number");
				Timestamp time_chk = rs.getTimestamp("time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='��� ����',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' where Seat_Number= ?";
					PreparedStatement pstmt2 = conn.prepareStatement(change);
					pstmt2.setInt(1, seat_out);
					int row3 = pstmt2.executeUpdate();
				}
			}  
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
					sql = "update person_info set seat_number =0,expiration_seat='01/01/01 00:00:00.000000000',seat_type='����' where login_state = 'On'";
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

			// ȸ���� �� ����ð� �������� 
			sql = "select time_checkout from seat where seat_number = " + room_chk + "";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				time_room = rs.getTimestamp("time_checkout");
			}

			sql = "select l_time_checkout from locker where locker_number = " + locker_chk + "";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				time_locker = rs.getTimestamp("l_time_checkout");
			}

			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();

		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}

		// �޴� ��ư 4��
		JButton ticket_btn = new JButton("�̿�Ǳ���");
		ticket_btn.setBounds(MainPage.w/2-230, MainPage.h/2-10, 220, 130);
		this.add(ticket_btn);

		JButton in_btn = new JButton("�Խ��ϱ�");
		in_btn.setBounds(MainPage.w/2-230, MainPage.h/2-160, 220, 130);
		this.add(in_btn);

		JButton move_btn = new JButton("�ڸ��̵�");
		move_btn.setBounds(MainPage.w/2+10, MainPage.h/2-10, 220, 130);
		this.add(move_btn);

		JButton out_btn = new JButton("����ϱ�");
		out_btn.setBounds(MainPage.w/2+10, MainPage.h/2-160, 220, 130);
		this.add(out_btn);

		JButton chk_info = new JButton("���� ������");
		chk_info.setBounds(MainPage.w/2-230, MainPage.h/2-210, 120, 30);
		this.add(chk_info);

		new Style(ticket_btn);
		new Style(in_btn);
		new Style(move_btn);
		new Style(out_btn);
		new Style(chk_info);

		chk_info.addActionListener(new ActionListener() { // ���������� �˸�â 
			@Override
			public void actionPerformed(ActionEvent e) {
				new _00myPage();
			}
		});

		ticket_btn.addActionListener(new ActionListener() { // �̿�� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("�̿�Ǳ���", new _01start());  
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