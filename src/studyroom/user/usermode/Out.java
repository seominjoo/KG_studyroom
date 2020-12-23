package studyroom.user.usermode;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import studyroom.design.Style;
import studyroom.swingTools.State;
import studyroom.user.MainPage;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Out extends JPanel {

	int num_seat;
	int room_seat;
	int room_seat1;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;

	public Out() {

		new Style(this);
		setLayout(null);

		//메뉴 버튼 3개
		JButton out_seat = new JButton("좌석 퇴실하기");
		this.add(out_seat);
		new Style(out_seat);
		out_seat.setBounds(MainPage.w/2-150, MainPage.h/2-190, 300, 100);

		JButton out_room = new JButton("룸 퇴실하기");
		this.add(out_room);
		new Style(out_room);
		out_room.setBounds(MainPage.w/2-150, MainPage.h/2-60, 300, 100);

		JButton back_btn = new JButton("이전 화면");
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(MainPage.w/2-150, MainPage.h/2+70, 300, 100);

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			out_seat.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// 로그인 된 회원의 좌석번호 확인
					sql = "SELECT seat_number,seat_type from person_info where login_state = 'On'";

					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							num_seat = rs.getInt("seat_number");
							type = rs.getString("seat_type");
						}
						sql = "update seat set Seat_Statement ='사용 가능',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' where Seat_Number= ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, num_seat);
						int row3 = pstmt.executeUpdate();
						if (num_seat == 0) {
							String msg = "결제한 좌석이 없습니다";
							JOptionPane.showMessageDialog(null, msg);
						} else {  
							// 정기이용권 사용자는 퇴실 시 좌석만 삭제
							if (type.equals("정기 이용권")) {
								sql = "update person_info set seat_number=0 where login_state = 'On'";
								pstmt = conn.prepareStatement(sql);
								int row5 = pstmt.executeUpdate();
							}
							// 당일이용권 사용자는 만료시간 리셋
							else {
								sql = "update person_info set seat_number=0,expiration_seat='01/01/01 00:00:00.000000000' ,seat_type='없음' where login_state = 'On'";
								pstmt = conn.prepareStatement(sql);
								int row5 = pstmt.executeUpdate();
							}
							String warning=String.format("%d번 좌석이 퇴실 되었습니다.",num_seat);
							JOptionPane.showMessageDialog(null,warning); 
							MainPage.updateTable.add(new State());
							MainPage.statecard.next(MainPage.updateTable);
							MainPage.user_page_panel.add("메인메뉴", new Mainmenu()); // 메뉴페이지
							MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
							MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
							MainPage.userToggle = "메인메뉴";
						}
					} catch (SQLException e1) { 
						e1.printStackTrace();
					}
				}

			});

			out_room.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					try { 
						sql = "update seat set Seat_Statement ='사용 가능',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' where Seat_Number=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, Mainmenu.room_chk);
						int row3 = pstmt.executeUpdate();
						if (Mainmenu.room_chk == 0) {
							String msg = "결제한 좌석이 없습니다";
							JOptionPane.showMessageDialog(null, msg);
						} else { 
							String warning=String.format("%d번 룸이 퇴실 되었습니다.",Mainmenu.room_chk);
							JOptionPane.showMessageDialog(null,warning); 
							sql = "update person_info set room_number=0 where login_state = 'On'";
							pstmt = conn.prepareStatement(sql);
							int row5 = pstmt.executeUpdate(); 
							MainPage.user_page_panel.add("메인메뉴", new Mainmenu()); // 메뉴페이지
							MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
							MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
							MainPage.userToggle = "메인메뉴";
						} 
						MainPage.updateTable.add(new State());
						MainPage.statecard.next(MainPage.updateTable);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		back_btn.addActionListener(new ActionListener() { // 이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("메인메뉴", new Mainmenu()); // 메뉴페이지
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
			}
		});
	}
}