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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Style;
import login.mainmenu.Time;
import login.mainmenu._00myPage;

public class StoreBtnPage extends JFrame {

	static BufferedImage image;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	JButton out;
	JButton move;

	public StoreBtnPage() {
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
			String sql="";
			int id=0;
			String exp = "";
			String name ="";
			String type ="";
			Timestamp time = null;
			JLabel title = null;
			JLabel info = null;

			if (StoreManagementPage.type.equals("사물함")) {

				sql = "SELECT Person_Id, Person_Name, Expiration_locker FROM person_info WHERE locker_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.locker_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					time = rs.getTimestamp(3);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);			
				}
				
				title = new JLabel(StoreManagementPage.locker_number +"번 사물함 정보");
				info = new JLabel(
						"<html>회원번호 : "+id+"번"+
						"<br/>회원이름 : "+name+
						"<br/>만료기간 : "+exp
						);
				if (rs != null)
					rs.close();

			} else if (StoreManagementPage.type.equals("룸")) {
				sql = "SELECT Person_Id, Person_Name, Expiration_room, Seat_Type FROM person_info WHERE room_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.room_number); 
				ResultSet rs2 = pstmt.executeQuery(); 
				while(rs2.next()) { 
					id = rs2.getInt(1);
					name = rs2.getString(2);
					time = rs2.getTimestamp(3);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
					type = rs2.getString(4);
				}
				
				title = new JLabel(StoreManagementPage.room_number +"호 룸 정보");
				info = new JLabel(
						"<html>회원번호 : "+id+"번"+
						"<br/>회원이름 : "+name+
						"<br/>만료기간 : "+exp+
						"<br/>이용권 : "+type
						);
				
				if (rs2 != null)
					rs2.close();
				if (conn != null)
					conn.close();
				
			} else if (StoreManagementPage.type.equals("좌석")) {
				sql = "SELECT Person_Id, Person_Name, Expiration_seat, Seat_Type FROM person_info WHERE seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.seat_number); 
				ResultSet rs3 = pstmt.executeQuery(); 
				while(rs3.next()) { 
					id = rs3.getInt(1);
					name = rs3.getString(2);
					time = rs3.getTimestamp(3);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
					type = rs3.getString(4);
				}
				
				title = new JLabel(StoreManagementPage.seat_number +"번 좌석 정보");
				info = new JLabel(
						"<html>회원번호 : "+id+"번"+
						"<br/>회원이름 : "+name+
						"<br/>만료기간 : "+exp+
						"<br/>이용권 : "+type
						);
				System.out.println(id);
				if (rs3 != null)
					rs3.close();
				if (conn != null)
					conn.close();
			}
			
			title.setBounds(120,0,300,40);
			info.setBounds(20,0,300,190);
			title.setForeground(Color.white);
			info.setForeground(Color.white);
			add(title);
			add(info);
			
//			out = new JButton("퇴실");
//			move = new JButton("이동");
//			new Style(out);
//			new Style(move);
//			add(out);
//			add(move);
//			move.setBounds(50,200,100,50);
//			out.setBounds(180,200,100,50);
			
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setBounds(550, 300, 350, 220);
			setVisible(true);

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();

		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
