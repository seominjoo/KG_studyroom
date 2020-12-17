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

public class StoreBtnPage extends JFrame {

	static BufferedImage image;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	JButton out;
	JButton move;

	String sql_out ="";
	String type ="";

	public StoreBtnPage() {
		
		String sql="";	
		String sql2="";
		int id=0;
		String exp = "";
		String name ="";
		String pn = "";
		Timestamp time = null;
		JLabel title = null;
		JLabel info = null;
		out = new JButton("퇴실");
		move = new JButton("이동");

		
		try {
			
			image = ImageIO.read(new File("image/로고.png"));
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;
			
			if (StoreManagementPage.type.equals("사물함")) {
				
				sql = "SELECT Person_Id, Person_Name, Phone_Number, l_time_checkout FROM person_info, locker WHERE locker.locker_number =?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.locker_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					pn = rs.getString(3);
					time = rs.getTimestamp(4);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}
				
				title = new JLabel(StoreManagementPage.locker_number +"번 사물함 정보");
				info = new JLabel(
								"<html>회원번호 : "+id+"번"+
								"<br/>회원이름 : "+name+
								"<br/>핸드폰번호 : "+pn+
								"<br/>만료기간 : "+exp
						);

				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							sql_out = "UPDATE locker SET Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null WHERE Locker_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row = pstmt.executeUpdate(); 
							
							sql_out = "UPDATE person_info SET locker_number=null WHERE Locker_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row2 = pstmt.executeUpdate(); 
							
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
							System.out.printf("%d번 사물함이 반납되었습니다. (locker %d행, person_info %d행이 변경되었습니다)\n",StoreManagementPage.locker_number, row, row2);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, StoreManagementPage.locker_number+"번 사물함이 반납되었습니다");
						setVisible(false);
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {					
						int result = JOptionPane.showConfirmDialog(null, "이동할 사물함을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
						MainPage.userToggle = "매장관리-이동";
						}
					}
				});

			} else if (StoreManagementPage.type.equals("룸")) {
				
				sql = "SELECT Person_Id, Person_Name, seat_type, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.room_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					type = rs.getString(3);
					pn = rs.getString(4);
					time = rs.getTimestamp(5);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}

				title = new JLabel(StoreManagementPage.room_number +"호 룸 정보");
				info = new JLabel(
								"<html>회원번호 : "+id+"번"+
								"<br/>회원이름 : "+name+
								"<br/>핸드폰번호 : "+pn+
								"<br/>만료기간 : "+exp+
								"<br/>이용권 : "+type
						);
				
				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_out = "UPDATE seat SET Seat_Statement ='사용 가능',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row = pstmt.executeUpdate(); 
							
							sql_out = "UPDATE person_info SET room_number=null, Seat_Type ='x' WHERE Room_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row2 = pstmt.executeUpdate(); 
							
							System.out.printf("%d호 룸이 퇴실되었습니다. (seat %d행, person_info %d행이 변경되었습니다)\n",StoreManagementPage.room_number, row, row2);
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, StoreManagementPage.room_number+ "호 룸이 퇴실되었습니다");
						setVisible(false);
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "이동할 룸을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
						MainPage.userToggle = "매장관리-이동";
						}
					}
				});

			} else if (StoreManagementPage.type.equals("좌석")) {

				sql = "SELECT Person_Id, Person_Name, seat_type, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.seat_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					type = rs.getString(3);
					pn = rs.getString(4);
					time = rs.getTimestamp(5);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}

				title = new JLabel(StoreManagementPage.seat_number +"번 좌석 정보");
				info = new JLabel(
								"<html>회원번호 : "+id+"번"+
								"<br/>회원이름 : "+name+
								"<br/>핸드폰번호 : "+pn+
								"<br/>만료기간 : "+exp+
								"<br/>이용권 : "+type
						);
				
				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_out = "UPDATE seat SET Seat_Statement ='사용 가능',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row = pstmt.executeUpdate(); 
							System.out.printf("%d번 좌석이 퇴실되었습니다.(seat %d행이 변경되었습니다.)\n",StoreManagementPage.seat_number, row);
							
							if (type.equals("일일 이용권")) {
							sql_out = "UPDATE person_info SET seat_number=null, Seat_Type ='x' WHERE Seat_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row2 = pstmt.executeUpdate(); 
							System.out.printf("person_info %d행이 변경되었습니다.\n", row2);
							}
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, StoreManagementPage.seat_number+ "번 좌석이 퇴실되었습니다");
						setVisible(false);
						MainPage.main_page_panel.add("메인", new MainPage());
						MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
						MainPage.userToggle = "매장관리";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "이동할 좌석을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
						MainPage.userToggle = "매장관리-이동";
						}
					}
				});	
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} 

			title.setBounds(120,10,300,40);
			info.setBounds(20,0,300,190);
			title.setForeground(Color.white);
			info.setForeground(Color.white);
			add(title);
			add(info);

			new Style(out);
			new Style(move);
			out.setBorder(BorderFactory.createLineBorder(Color.white));
			out.setForeground(Color.white);
			move.setBorder(BorderFactory.createLineBorder(Color.white));
			move.setForeground(Color.white);
			add(out);
			add(move);
			move.setBounds(50,180,100,50);
			out.setBounds(180,180,100,50);
			
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setBounds(550, 300, 350, 300);
			setVisible(true);

			if (conn != null) conn.close();

		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}

