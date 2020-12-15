package login.page;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import login.design.Style;
import login.mainmenu.Time;

public class MemberDB {

	JTable table;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");

	public MemberDB(int chk) {

		 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);
			String sql="";
			 

			if(chk==1) {
				sql="SELECT person_id,person_name,person_birth,phone_number,"
						+ "seat_number,expiration_seat,"
						+ "room_number,expiration_room,"
						+ "locker_number,expiration_locker,"
						+ "seat_type from person_info "; 

			}else if(chk==2) {
				if(MemberManagementPage.dayOrWeek.getSelectedItem().equals("전체")) {
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where seat_type != 'x'"; 
				}else if(MemberManagementPage.dayOrWeek.getSelectedItem().equals("정기")){
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where seat_type ='정기 이용권'"; 
				}else if(MemberManagementPage.dayOrWeek.getSelectedItem().equals("일일")){
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where seat_type ='일일 이용권'"; 
				}

			}else if(chk==3) {
				if(MemberManagementPage.seat_room_locker.getSelectedItem().equals("전체")) {
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where (seat_number is not null) or (room_number is not null) or (locker_number is not null)"; 
				}else if(MemberManagementPage.seat_room_locker.getSelectedItem().equals("좌석")) {
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where seat_number is not null"; 
				}else if(MemberManagementPage.seat_room_locker.getSelectedItem().equals("룸")) {
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where room_number is not null"; 
				}else if(MemberManagementPage.seat_room_locker.getSelectedItem().equals("사물함")) {
					sql="SELECT person_id,person_name,person_birth,phone_number,"
							+ "seat_number,expiration_seat,"
							+ "room_number,expiration_room,"
							+ "locker_number,expiration_locker,"
							+ "seat_type from person_info where locker_number is not null"; 
				}

			}
			
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			String header[] = {"회원 번호","성함", "생일", "폰 번호",
					"좌석", "좌석 만료 시간",
					"룸","룸 만료 시간",
					"사물함","사물함 만료 시간",
			"이용권 타입"};//11열

			int row=0;
			int i = 0;
			int sum = 0;

			while (rs.next()) {
				row++;
			}
			
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			String[][] contents = new String[row][header.length];
			 Timestamp time_chk=null;
			while (rs.next()) { 
				contents[i][0] = Integer.toString(rs.getInt(1));
				for (int j = 1; j < header.length; j++) {
					if (j == 4) {
						contents[i][j] = Integer.toString(rs.getInt(5));
					}else if(j==6) {
						contents[i][j] = Integer.toString(rs.getInt(7));
					}else if(j==8) {
						contents[i][j] = Integer.toString(rs.getInt(9));
					}else if(j==5) {
						time_chk = rs.getTimestamp(6);
						contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter);
					}else if(j==7) {
						time_chk = rs.getTimestamp(8);
						contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter);
					}else if(j==9) {
						time_chk = rs.getTimestamp(10);
						contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter);
					}else {
						contents[i][j] = rs.getString(j + 1);
					}
				} 
				i++;
			}
			MemberManagementPage.totalmember.setText("총 회원 수 : "+row);

			DefaultTableModel model = new DefaultTableModel(contents, header);

			table = new JTable(model);
			 
			table.getTableHeader().setOpaque(false);
			// table.setBounds(40, 104, 390, 245);
			table.setRowHeight(40);
			 
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(170);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(170);
			table.getColumnModel().getColumn(8).setPreferredWidth(50);
			table.getColumnModel().getColumn(9).setPreferredWidth(170);
			table.getColumnModel().getColumn(10).setPreferredWidth(90);

			JTableHeader headers = table.getTableHeader();
	         headers.setBackground(Color.darkGray);
	         headers.setForeground(Color.decode("#cfab8b"));
			
			new Style(table);
			MemberManagementPage.scrollPane.setViewportView(table);

		
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();

			if (conn != null)
				conn.close();
 
		} catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		} 
	}
}
