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
			System.out.println("[ojdbc] ���������� �ε�Ǿ����ϴ�.");
			// 2. DriverManager Ŭ������ ���� DB���� ������ ����
			// - DriverManager.getConnection() �޼��忡
			// DB���� �ּҿ� ���̵�/�н����带 �����ϸ� ������ ��ȯ�ȴ�
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "123");
			// ������
			PreparedStatement pstmt = conn.prepareStatement("SELECT Person_Name, Phone_Number, PW FROM Person_Info");

			conn.setAutoCommit(false);
			
			// ���
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
		new DateBase("������", "444444");
	}
}
