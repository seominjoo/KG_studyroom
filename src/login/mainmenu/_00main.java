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
	public static DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 ");

	public _00main() {

		new Style(this);
		setLayout(null);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			// 로그인 된 회원번호 읽기
			String sql = "SELECT person_id from person_info where login_state = 'On'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row; 
			while (rs.next()) {
				id = rs.getInt("person_id"); 
				System.out.println("로그인 된 회원번호: " + id);
			}

			// 퇴실 시간이 지난 좌석 퇴실 처리
			sql = "SELECT seat_number, time_checkout FROM seat WHERE seat_statement ='사용 중'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int seat_out = rs.getInt("seat_number");
				Timestamp time_chk = rs.getTimestamp("time_checkout");
				if (LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='사용 가능',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' where Seat_Number= ?";
					PreparedStatement pstmt2 = conn.prepareStatement(change);
					pstmt2.setInt(1, seat_out);
					int row3 = pstmt2.executeUpdate();
				}
			}  
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
					sql = "update person_info set seat_number =0,expiration_seat='01/01/01 00:00:00.000000000',seat_type='없음' where login_state = 'On'";
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

			// 회원의 름 만료시간 가져오기 
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

		// 메뉴 버튼 4개
		JButton ticket_btn = new JButton("이용권구매");
		ticket_btn.setBounds(MainPage.w/2-230, MainPage.h/2-10, 220, 130);
		this.add(ticket_btn);

		JButton in_btn = new JButton("입실하기");
		in_btn.setBounds(MainPage.w/2-230, MainPage.h/2-160, 220, 130);
		this.add(in_btn);

		JButton move_btn = new JButton("자리이동");
		move_btn.setBounds(MainPage.w/2+10, MainPage.h/2-10, 220, 130);
		this.add(move_btn);

		JButton out_btn = new JButton("퇴실하기");
		out_btn.setBounds(MainPage.w/2+10, MainPage.h/2-160, 220, 130);
		this.add(out_btn);

		JButton chk_info = new JButton("마이 페이지");
		chk_info.setBounds(MainPage.w/2-230, MainPage.h/2-210, 120, 30);
		this.add(chk_info);

		new Style(ticket_btn);
		new Style(in_btn);
		new Style(move_btn);
		new Style(out_btn);
		new Style(chk_info);

		chk_info.addActionListener(new ActionListener() { // 마이페이지 알림창 
			@Override
			public void actionPerformed(ActionEvent e) {
				new _00myPage();
			}
		});

		ticket_btn.addActionListener(new ActionListener() { // 이용권 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("이용권구매", new _01start());  
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