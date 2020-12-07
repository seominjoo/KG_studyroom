package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DateBase {

	public static Integer person_id;
	public static String person_name;
	static String phone_number;
	static String password;

	public DateBase(String login_phonenumber, String login_password) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("[ojdbc] 성공적으로 로드되었습니다.");
			// 2. DriverManager 클래스를 통해 DB와의 연결을 수립
			// - DriverManager.getConnection() 메서드에
			// DB접속 주소와 아이디/패스워드를 전달하면 연결이 반환된다
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "123");
			conn.setAutoCommit(false);
			
			// 쿼리문
			// 로그인에 사용된 휴대폰번호, 비밀번호만 불러오기
			PreparedStatement findID = conn.prepareStatement(
					"SELECT Person_Id, Person_Name, Phone_Number, PW FROM Person_Info where Phone_Number = ?"
					);
			
			findID.setString(1, login_phonenumber);
			// 결과
			ResultSet rs = findID.executeQuery();

			while (rs.next()) {
				person_id = rs.getInt(1);
				person_name = rs.getString(2);
				phone_number = rs.getString(3);
				password = rs.getString(4);
			}

			rs.close();
			findID.close();
			conn.close();

			System.out.println(person_name + person_id + password + "d" + login_phonenumber + login_password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
