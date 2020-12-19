package login.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

public class _06move extends JPanel {

	static int num_seat;
	static int num_room;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	static int chk=0;
	
	public _06move() {
		
		new Style(this);
		setLayout(null);
		
		JButton move_seat = new JButton("좌석 이동하기");
		this.add(move_seat);
		new Style(move_seat);
		move_seat.setBounds(160,100,300,100);
		
		JButton move_room = new JButton("룸 이동하기");
		this.add(move_room);
		new Style(move_room);
		move_room.setBounds(160,220,300,100);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			
			move_seat.addActionListener(new ActionListener() {//1인석
				@Override
				public void actionPerformed(ActionEvent e) {
					chk=1;
					//회원의 현재 좌석번호 
					sql = "SELECT seat_number,room_number FROM person_info "
							+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_seat = rs.getInt("seat_number");
							num_room = rs.getInt("room_number");
						}if(num_seat==0) {
							String msg= "결제한 좌석이 없습니다";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("이동할 좌석: "+num_seat+"번");
						MainPage.user_page_panel.add("자리페이지", new _06move_selectSeat());
							MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
							MainPage.user_cards.show(MainPage.user_page_panel, "자리페이지");
							MainPage.userToggle = "자리페이지";
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			});

			move_room.addActionListener(new ActionListener() {//룸
				@Override
				public void actionPerformed(ActionEvent e) { 
					 chk=2;	
					//회원의 현재 룸번호 
					 sql = "SELECT seat_number,room_number FROM person_info "
								+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_seat = rs.getInt("seat_number");
							num_room = rs.getInt("room_number");
						}
						if(num_room==0) {
							String msg= "결제한 룸이 없습니다";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("이동할 룸: "+num_room+"호");
						MainPage.user_page_panel.add("자리페이지", new _06move_selectSeat());
						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.user_cards.show(MainPage.user_page_panel, "자리페이지");
						MainPage.userToggle = "자리페이지";
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		JButton back_btn = new JButton("이전 화면");
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(160,340,300,100);
		back_btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
			}
		}); 
	}
}
