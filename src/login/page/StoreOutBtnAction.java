package login.page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreOutBtnAction {
	 
	public StoreOutBtnAction() {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;
			String sql ="";
			
			if (StoreManagementPage.type.equals("사물함")) {
				
				sql = "UPDATE locker SET Locker_Statement ='사용 가능',l_time_enter='01/01/01 00:00:00.000000000',l_time_checkout='01/01/01 00:00:00.000000000' WHERE Locker_Number=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.locker_number);
				int row = pstmt.executeUpdate(); 
				System.out.printf("locker %d행이 바뀌었습니다\n",row);

				sql = "UPDATE person_info SET locker_number=0 WHERE Locker_Number=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreMovePage.locker_move_number);
				int row2 = pstmt.executeUpdate(); 
				System.out.printf("person_info %d행이 바뀌었습니다.\n",row2);
		
				System.out.printf("%d번 사물함이 반납되었습니다.", StoreManagementPage.locker_number);
			
			} else if (StoreManagementPage.type.equals("룸")) {
			
				sql = "UPDATE seat SET Seat_Statement ='사용 가능',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' WHERE seat_Number=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.room_number);
				int row = pstmt.executeUpdate(); 
				System.out.printf("seat %d행이 바뀌었습니다\n",row);

				sql = "UPDATE person_info SET seat_number=0 WHERE seat_Number=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreMovePage.room_move_number);
				int row2 = pstmt.executeUpdate(); 
				System.out.printf("person_info %d행이 바뀌었습니다.\n",row2);
				
				System.out.printf("%d번 룸이 퇴실되었습니다.", StoreManagementPage.room_number);
				
			} else if (StoreManagementPage.type.equals("좌석")) {
			
				if (StoreBtnPage.seat_type.equals("일일 이용권")) {

					sql = "UPDATE seat AS s, person_info AS p"
							+ " SET s.Seat_Statement ='사용 가능',s.time_enter='01/01/01 00:00:00.000000000',s.time_checkout='01/01/01 00:00:00.000000000',"
							+ " p.seat_number=0, p.seat_type=0, expiration_seat ='01/01/01 00:00:00.000000000'"
							+ " WHERE s.Seat_Number=? AND p.Seat_Number=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, StoreManagementPage.seat_number);
					pstmt.setInt(2, StoreManagementPage.seat_number);
					int row = pstmt.executeUpdate(); 
					System.out.printf("%번 좌석이 퇴실되었습니다. (%d행이 변경되었습니다)\n",StoreManagementPage.seat_number, row);

					if (pstmt != null) pstmt.close();

				} else if (StoreBtnPage.seat_type.equals("정기 이용권")) {

					sql = "UPDATE seat AS s, person_info AS p"
							+ " SET s.Seat_Statement ='사용 가능',s.time_enter='01/01/01 00:00:00.000000000',s.time_checkout='01/01/01 00:00:00.000000000',"
							+ " p.seat_number=0"
							+ " WHERE s.Seat_Number=? AND p.Seat_Number=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, StoreManagementPage.seat_number);
					pstmt.setInt(2, StoreManagementPage.seat_number);
					int row = pstmt.executeUpdate(); 
					System.out.printf("%번 좌석이 퇴실되었습니다. (%d행이 변경되었습니다)\n",StoreManagementPage.seat_number, row);
				}
			}
			
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
