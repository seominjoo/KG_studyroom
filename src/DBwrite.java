

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBwrite {

	public DBwrite(String query) {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");		
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);

			conn.setAutoCommit(false);
	
			PreparedStatement pstmt1 = conn.prepareStatement(query); 

			if (pstmt1 != null) pstmt1.close();
			if (conn != null) conn.close();

			System.out.println("���� ��");
		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ���� �� ã��");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		System.out.println(query +"�� ����Ǿ����ϴ�");
		
	}
}
