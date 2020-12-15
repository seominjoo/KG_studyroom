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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Style;
import login.mainmenu._00myPage;

public class StoreBtnPage extends JFrame {

	JButton back;
	static BufferedImage image;

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
			String name ="";
			String type ="";
			Timestamp time = null;
			JLabel info = null;

			if (StoreManagementPage.number>20 && StoreManagementPage.number<100) {
				StoreManagementPage.number-=20;
				sql = "SELECT Person_Id, Person_Name, Expiration_locker"
						+ " FROM person_info WHERE locker_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt("Person_Id");
					name = rs.getString("Person_Name");
					time = rs.getTimestamp("Expiration_locker");
				}

				info = new JLabel(
						"<html>회원번호 : "+id+"번"+
						"<br/>회원이름 : "+name+
						"<br/>만료기간 : "+time
						);

			} else {
				sql = "SELECT Person_Id, Person_Name, Expiration_seat, Seat_Type"
						+ " FROM person_info WHERE seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt("Person_Id");
					name = rs.getString("Person_Name");
					type = rs.getString("Seat_Type");
					time = rs.getTimestamp("Expiration_seat");
				}
				
				info = new JLabel(
						"<html>회원번호 : "+id+"번"+
						"<br/>회원이름 : "+name+
						"<br/>만료기간 : "+time+
						"<br/>이용권 : "+type
						);
			}

			new Style(info);
			add(info);
			info.setBounds(20,0,400,190);
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(550, 300, 450, 220);
			setVisible(true);

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			
			StoreManagementPage.number = 0;

		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new StoreBtnPage();
	}

}
