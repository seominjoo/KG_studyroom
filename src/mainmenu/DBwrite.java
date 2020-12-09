package mainmenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBwrite {

	public DBwrite(String query) {

		query = "SELECT Locker_Number,l_time_checkout FROM locker "
				+ "WHERE Locker_Statement='»ç¿ë Áß'";
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
	 
	}
}
