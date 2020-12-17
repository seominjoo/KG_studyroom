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
//	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
//	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h시 m분 ");

	public static int seat_chk;
	public static int locker_chk;
	public static int room_chk;
	public static Timestamp time_seat;
	public static Timestamp time_room;
	public static Timestamp time_locker;

	static int id;
	public static String type;
	public static DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 ");

	public _00main() {

		new Style(this);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			// 로그인된 회원번호 읽기
			String sql = "SELECT person_id from person_info where login_state = 'On'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row;

			while (rs.next()) {
				id = rs.getInt("person_id");

				System.out.println("로그인 된 회원번호: " + id);
			}

			// 퇴실 시간이 지난 좌석 퇴실 처리
			sql = "SELECT seat_number, time_checkout FROM seat " + "WHERE seat_statement ='사용 중'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int seat_out = rs.getInt("seat_number");
				Timestamp time_chk = rs.getTimestamp("time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmt2 = conn.prepareStatement(change);
					pstmt2.setInt(1, seat_out);
					int row3 = pstmt2.executeUpdate();
				}
			}

			System.out.println("db실행");
			// 퇴실시간(만료시간) 지나면 회원정보 만료시간 리셋
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

			// 만료 시간이 지난 사물함 만료 처리
			sql = "SELECT Locker_Number,l_time_checkout FROM locker " + "WHERE Locker_Statement='사용 중'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int locker_chk = rs.getInt("Locker_Number");
				Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='사용 가능',l_time_enter='01/01/01 00:00:00.000000000',"
							+ "l_time_checkout='01/01/01 00:00:00.000000000' where Locker_Number= ?";
					PreparedStatement pstmt3 = conn.prepareStatement(change2);
					pstmt3.setInt(1, locker_chk);
					int row4 = pstmt3.executeUpdate();
				}
			}
			System.out.println(room_chk);
			// 회원의 름 만료시간 가져오기
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

		// 메뉴 버튼 4개
		JButton ticket_btn = new JButton("이용권구매");
		ticket_btn.setBounds(130, 150, 208, 121);
		this.add(ticket_btn);

		JButton in_btn = new JButton("입실하기");
		in_btn.setBounds(348, 150, 213, 121);
		this.add(in_btn);

		JButton move_btn = new JButton("자리이동");
		move_btn.setBounds(130, 281, 208, 126);
		this.add(move_btn);

		JButton out_btn = new JButton("퇴실하기");
		out_btn.setBounds(348, 281, 213, 126);
		this.add(out_btn);

		JButton chk_info = new JButton("마이 페이지");
		chk_info.setBounds(130, 110, 120, 30);
		this.add(chk_info);

		new Style(ticket_btn);
		new Style(in_btn);
		new Style(move_btn);
		new Style(out_btn);
		new Style(chk_info);

//		// 스터디룸 상황표
//		String header[] = { "1인석", "스터디룸", "사물함", "현재시간" };
//		String contents[][] = { { "<html>사용중 1인석<br/>&emsp;&emsp;" + Integer.toString(count_seat) + " / 20",
//				"<html>사용중 스터디룸<br/>&emsp;&emsp;&emsp;" + Integer.toString(count_room) + " / 4",
//				"<html>사용중 사물함<br/>&emsp;&emsp;" + Integer.toString(count_locker) + " / 20",
//				"<html>&emsp;&nbsp;&nbsp;&nbsp;현재시간<br/>" + LocalDate.now().format(date) + "<br/>&nbsp;&nbsp;&nbsp;"
//						+ LocalTime.now().format(time) } };
//
//		DefaultTableModel model = new DefaultTableModel(contents, header);
//		table = new JTable(model);
//
//		table.setBounds(80, 80, 437, 80);
//		table.setRowHeight(80);
//
//		// 테두리
//		Color color = UIManager.getColor("Table.gridColor");
//		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
//		table.setBorder(border);
//		
//		// 상황표 글씨 중앙 정렬
//		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
//		table.getColumn("1인석").setCellRenderer(celAlignCenter);
//		table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
//		table.getColumn("사물함").setCellRenderer(celAlignCenter);
//		table.getColumn("현재시간").setCellRenderer(celAlignCenter);
//		
//		new Style(celAlignCenter);
//		new Style(table);
//		table.setFont(new Font("맑은 고딕", Font.BOLD, 11));
//		this.add(table);
//		

		chk_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new _00myPage();

			}
		});
		ticket_btn.addActionListener(new ActionListener() { // 이용권 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("이용권구매", new _01start()); // 이용권구매 페이지
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			}
		});

		move_btn.addActionListener(new ActionListener() { // 자리이동 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((seat_chk > 0 && seat_chk < 21) || (room_chk >= 101 && room_chk <= 104)) {
					MainPage.user_page_panel.add("자리이동", new _06move());
					MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
					MainPage.user_cards.show(MainPage.user_page_panel, "자리이동");
					MainPage.userToggle = "자리이동";

				} else {
					String msg = "사용 중인 좌석이 없습니다.";
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});

		out_btn.addActionListener(new ActionListener() { // 퇴실 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("퇴실페이지", new _07out());
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "퇴실페이지");
				MainPage.userToggle = "퇴실페이지";
			}
		});

		in_btn.addActionListener(new ActionListener() { // 입실 페이지(정기이용권 이용자)

			@Override
			public void actionPerformed(ActionEvent e) {
				if (seat_chk > 0) {
					String msg = "이미 좌석이 있습니다.";
					JOptionPane.showMessageDialog(null, msg);
				} else if (type.equals("정기 이용권")) {
					MainPage.user_page_panel.add("입실페이지", new _07in_selectSeat());
					MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
					MainPage.user_cards.show(MainPage.user_page_panel, "입실페이지");
					MainPage.userToggle = "입실페이지";
				} else {
					String msg1 = "정기 이용권 이용자만 가능합니다";
					JOptionPane.showMessageDialog(null, msg1);
				}
			}
		});

	}

}
