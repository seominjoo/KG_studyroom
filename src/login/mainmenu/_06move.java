package login.mainmenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;

public class _06move extends JPanel {

//	private JPanel this;
	static int num_seat;
	static int num_room;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;

	public _06move() {
		
		new Style(this);
		setBounds(600, 150, 450, 300);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,3, 0, 0));
		
		JButton move_seat = new JButton("좌석 이동하기");
		this.add(move_seat);
		JButton move_room = new JButton("룸 이동하기");
		this.add(move_room);

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
						  setVisible(false);
				           _06move_selectSeat frame = new _06move_selectSeat();
				           frame.setVisible(true);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			});

			move_room.addActionListener(new ActionListener() {//룸
				@Override
				public void actionPerformed(ActionEvent e) { 

					//회원의 현재 룸번호 
					sql = "SELECT room_number FROM person_info "
							+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_room = rs.getInt("room_number");
						}
						if(num_room==0) {
							String msg= "결제한 룸이 없습니다";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("이동할 룸: "+num_seat+"호");
						  setVisible(false);
				           _06move_selectSeat frame = new _06move_selectSeat();
				           frame.setVisible(true);
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
		back_btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_00main frame = new _00main();
				frame.setVisible(true);
			}
		}); 
	}

}
