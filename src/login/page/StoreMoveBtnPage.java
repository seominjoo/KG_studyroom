package login.page;

import java.awt.Color;
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
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import login.design.Style;
import login.mainmenu.Time;
import login.mainmenu._00main;
import login.mainmenu._06move;

public class StoreMoveBtnPage extends JFrame {

	static BufferedImage image;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	JButton move;

	String sql_move ="";
	String type ="";
	
	int id=0;
	String exp = "";
	String name ="";
	String pn = "";
	Timestamp time = null;

	public StoreMoveBtnPage() {
		
		String sql="";			
		JLabel title = null;

		move = new JButton("확인");
		
		if (StoreManagementPage.type != StoreMovePage.type) {
			JOptionPane.showMessageDialog(null,"이동 불가한 선택입니다.");
		} else {
		
		try {
			
			image = ImageIO.read(new File("image/로고.png"));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			if (StoreManagementPage.type.equals("사물함")) {

				title = new JLabel(StoreMovePage.locker_move_number +"번 사물함으로 이동하시겠습니까?");
			
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							PreparedStatement pstmt = null;
							
							//seat 테이블에 이동한 사물함 <-> 원래 사물함
							String sql = "SELECT locker_statement, l_time_enter, l_time_checkout FROM locker WHERE locker_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.locker_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("locker_statement");
								Timestamp tc = rs.getTimestamp("l_time_enter");
								Timestamp tco = rs.getTimestamp("l_time_checkout");
								
								sql = "UPDATE locker SET locker_statement =?, l_time_enter=?, l_time_checkout=? WHERE Locker_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.locker_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE locker SET locker_statement='사용 가능', time_enter='01/01/01 00:00:00.000000000',"
									+ " time_checkout = '01/01/01 00:00:00.000000000' WHERE Locker_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row = pstmt.executeUpdate();
							
							//회원info 테이블에 좌석번호 업데이트
							sql = "UPDATE person_info SET locker_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.locker_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d번 자리로 이동되었습니다.(%d행 업데이트)\n", StoreMovePage.locker_move_number,row);
							System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
						JOptionPane.showMessageDialog(null, "이동되었습니다.");
						setVisible(false);
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";
					}
				});

			} else if (StoreManagementPage.type.equals("룸")) {

				title = new JLabel(StoreMovePage.room_move_number +"호 룸으로 이동하시겠습니까?");	
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
											
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							PreparedStatement pstmt = null;
							
							//seat 테이블에 이동한 룸 <-> 원래 룸
							String sql = "SELECT seat_statement, time_enter, time_checkout FROM seat WHERE seat_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.room_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("seat_statement");
								Timestamp tc = rs.getTimestamp("time_enter");
								Timestamp tco = rs.getTimestamp("time_checkout");
								
								sql = "UPDATE seat SET seat_statement =?, time_enter=?, time_checkout=? WHERE Seat_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.room_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE seat SET seat_statement='사용 가능', time_enter='01/01/01 00:00:00.000000000', "
									+ "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row = pstmt.executeUpdate();
							
							//회원info 테이블에 좌석번호 업데이트
							sql = "UPDATE person_info SET room_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.room_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d번 자리로 이동되었습니다.(%d행 업데이트)\n", StoreMovePage.room_move_number,row);
							System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "이동되었습니다.");
						setVisible(false);
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";						
					}
				});

			} else if (StoreManagementPage.type.equals("좌석")) {

				title = new JLabel(StoreMovePage.seat_move_number +"번 좌석으로 이동하시겠습니까?");

				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							PreparedStatement pstmt = null;
							
							//seat 테이블에 이동한 좌석 <-> 원래 좌석
							String sql = "SELECT seat_statement, time_enter, time_checkout FROM seat WHERE seat_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.seat_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("seat_statement");
								Timestamp tc = rs.getTimestamp("time_enter");
								Timestamp tco = rs.getTimestamp("time_checkout");
								
								sql = "UPDATE seat SET seat_statement =?, time_enter=?, time_checkout=? WHERE Seat_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.seat_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE seat SET seat_statement='사용 가능', time_enter='01/01/01 00:00:00.000000000', "
									+ "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row = pstmt.executeUpdate();
							
							//회원info 테이블에 좌석번호 업데이트
							sql = "UPDATE person_info SET seat_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.seat_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d번 자리로 이동되었습니다.(%d행 업데이트)\n", StoreMovePage.seat_move_number,row);
							System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
						JOptionPane.showMessageDialog(null, "이동되었습니다.");
						setVisible(false);
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";
					}
				});
			} 

			title.setBounds(70,10,300,80);
			title.setForeground(Color.white);
			add(title);

			new Style(move);
			move.setBorder(BorderFactory.createLineBorder(Color.white));
			move.setForeground(Color.white);
			add(move);
			move.setBounds(80,100,180,50);
			
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setBounds(550, 200, 350, 250);
			setVisible(true);


		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		}
	}
}


