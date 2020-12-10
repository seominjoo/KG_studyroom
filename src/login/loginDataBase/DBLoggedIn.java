package login.loginDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import login.BirthEnum;
import login.signUp.SignUpEnum;

public class DBLoggedIn {

	public static Integer person_id;
	public static String person_name;
	public static String phone_number;
	public static String password;
	public static Connection conn;

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
			System.out.println("[ojdbc] ���������� �ε�Ǿ����ϴ�.");
			// 2. DriverManager Ŭ������ ���� DB���� ������ ����
			// - DriverManager.getConnection() �޼��忡
			// DB���� �ּҿ� ���̵�/�н����带 �����ϸ� ������ ��ȯ�ȴ�
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
// �׽�Ʈ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("[ojdbc] ���������� �ε�Ǿ����ϴ�.");
			// 2. DriverManager Ŭ������ ���� DB���� ������ ����
			// - DriverManager.getConnection() �޼��忡
			// DB���� �ּҿ� ���̵�/�н����带 �����ϸ� ������ ��ȯ�ȴ�
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			// ������
			// �α��ο� ���� �޴�����ȣ, ��й�ȣ�� �ҷ�����
			PreparedStatement findID;
			findID = conn.prepareStatement(
					"SELECT Person_Id, Person_Name, Phone_Number, PW FROM Person_Info where Phone_Number = ?");

			findID.setString(1, login_phonenumber);

			// ���
			ResultSet rs = findID.executeQuery();

			while (rs.next()) {
				person_id = rs.getInt(1);
				person_name = rs.getString(2);
				phone_number = rs.getString(3);
				password = rs.getString(4);
			}
			System.out.println(person_id +", "+ person_name +", "+ phone_number +", "+  password);
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
