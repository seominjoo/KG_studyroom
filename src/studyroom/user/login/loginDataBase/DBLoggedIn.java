package studyroom.user.login.loginDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import studyroom.MainPage;
import studyroom.user.signUp.BirthEnum;
import studyroom.user.signUp.SignUpEnum;

public class DBLoggedIn {

	public static Integer person_id;
	public static String person_name;
	public static String phone_number;
	public static String password;
	public static Connection conn;
	String selectQuery;
	
	public DBLoggedIn() {

	}

	public DBLoggedIn(String query) {
		/*
		 * "INSERT INTO Person_Info " +
		 * "(Person_Id,Check_Time,Person_Name, person_birth, Phone_Number,PW,Total_Payment)"
		 * + " VALUES(SignUpSeq.nextval, ?, ?, ?, ?, ?, ?)"
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("[ojdbc] 성공적으로 로드되었습니다.");
			// 2. DriverManager 클래스를 통해 DB와의 연결을 수립
			// - DriverManager.getConnection() 메서드에
			// DB접속 주소와 아이디/패스워드를 전달하면 연결이 반환된다
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			conn.setAutoCommit(false);
			
			PreparedStatement insertPersonInfo = conn.prepareStatement(query);

			
			insertPersonInfo.executeUpdate();

			if (insertPersonInfo != null)
				insertPersonInfo.close();

			if (conn != null)
				conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	public DBLoggedIn(String login_phonenumber, String login_password) {
		
		if(MainPage.userToggle.equals("로그인")) {
			selectQuery = "SELECT Person_Id, Person_Name, Phone_Number, PW FROM Person_Info where Phone_Number = ?";
		}else if(MainPage.userToggle.equals("관리자")){
			selectQuery = "SELECT admin_Id, admin_Name, admin_PhoneNumber, admin_PW FROM admin_Info where admin_PhoneNumber = ?";
		}else {
			System.out.println("페이지 인식안됨 데이터 베이스");
		}
		
// 테스트
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DriverManager 클래스를 통해 DB와의 연결을 수립
			// - DriverManager.getConnection() 메서드에
			// DB접속 주소와 아이디/패스워드를 전달하면 연결이 반환된다
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			// 쿼리문
			// 로그인에 사용된 휴대폰번호, 비밀번호만 불러오기
			PreparedStatement findID;
			findID = conn.prepareStatement(selectQuery);

			findID.setString(1, login_phonenumber);

			// 결과
			ResultSet rs = findID.executeQuery();

			while (rs.next()) {
				person_id = rs.getInt(1);
				person_name = rs.getString(2);
				phone_number = rs.getString(3);
				password = rs.getString(4);
			}
			if (rs != null)
				rs.close();
			if (findID != null)
				findID.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
