package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DateBase {

	
	String login_phonenumber;
	String login_password;

	public DateBase(String login_phonenumber, String login_password) {
		this.login_phonenumber = login_phonenumber;
		this.login_password = login_password;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("[ojdbc] 성공적으로 로드되었습니다.");
			// 2. DriverManager 클래스를 통해 DB와의 연결을 수립
			// - DriverManager.getConnection() 메서드에
			// DB접속 주소와 아이디/패스워드를 전달하면 연결이 반환된다
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "123");
			// 쿼리문
			PreparedStatement pstmt = conn.prepareStatement("SELECT Person_Name, Phone_Number, PW FROM Person_Info");

			conn.setAutoCommit(false);
			
			// 결과
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String phonenumber = rs.getString(2);
				String password = rs.getString(3);
				System.out.println(name + phonenumber + password + "d" + this.login_phonenumber + this.login_password);
			}

			rs.close();
			pstmt.close();
			conn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DateBase("서민주", "444444");
	}
}
