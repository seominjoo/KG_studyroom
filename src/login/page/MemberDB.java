package login.page;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
	DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("M");
	LocalDate date = LocalDate.now();

	public MemberDB(int chk) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);

			String sql = "";

			if (chk == 1) {
				if (MemberManagementPage.month.getSelectedItem().equals("전체")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number,"
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where a.room_number=b.seat_number and a.locker_number = c.locker_number";

				} else {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number,"
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and substr(a.CHECK_TIME,1,2) = ?";
				}

			} else if (chk == 2) {
				if (MemberManagementPage.dayOrWeek.getSelectedItem().equals("전체")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.seat_type != '없음'";

				} else if (MemberManagementPage.dayOrWeek.getSelectedItem().equals("정기")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.seat_type ='정기 이용권'";

				} else if (MemberManagementPage.dayOrWeek.getSelectedItem().equals("당일")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.seat_type ='당일 이용권'";
				}

			} else if (chk == 3) {
				if (MemberManagementPage.seat_room_locker.getSelectedItem().equals("전체")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and ((a.seat_number !=0) or (a.room_number !=0) or (a.locker_number !=0))";

				} else if (MemberManagementPage.seat_room_locker.getSelectedItem().equals("좌석")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.seat_number !=0";

				} else if (MemberManagementPage.seat_room_locker.getSelectedItem().equals("룸")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.room_number !=0";

				} else if (MemberManagementPage.seat_room_locker.getSelectedItem().equals("사물함")) {
					sql = " SELECT distinct check_time,person_id,person_name,person_birth,phone_number, a.seat_number,expiration_seat ,a.room_number, "
							+ " b.time_checkout , a.locker_number ," + " c.l_time_checkout, seat_type  "
							+ " from person_info a, seat b ,locker c"
							+ " where (a.room_number=b.seat_number and a.locker_number = c.locker_number) and a.locker_number !=0";
				}
			}

			PreparedStatement pstm = conn.prepareStatement(sql);

			if (chk == 1) {
				if (!MemberManagementPage.month.getSelectedItem().equals("전체")) {
					pstm.setString(1, (String) MemberManagementPage.month.getSelectedItem());

				}
			}
			ResultSet rs = pstm.executeQuery();

			String header[] = { "가입 날짜","회원 번호", "성함", "생일", "폰 번호", "좌석", "좌석 만료 시간", "룸", "룸 만료 시간", "사물함", "사물함 만료 시간",
					"이용권 타입(좌석)"  };// 12열

			int row = 0;
			int i = 0;
			int sum = 0;
			int last_month = 0;
			while (rs.next()) {
				row++;
			}

			pstm = conn.prepareStatement(sql);
			if (chk == 1) {
				if (!MemberManagementPage.month.getSelectedItem().equals("전체")) {
					pstm.setString(1, (String) MemberManagementPage.month.getSelectedItem());
				}
			}
			rs = pstm.executeQuery();

			String[][] contents = new String[row][header.length];
			Timestamp time_chk = null;
			while (rs.next()) {
				contents[i][0] = rs.getString(1);
				contents[i][1] = Integer.toString(rs.getInt(2));
				for (int j = 2; j < header.length; j++) {
					if (j == 5) {
						if (rs.getInt(6) == 0) {
							contents[i][j] = "없음";
						} else {
							contents[i][j] = Integer.toString(rs.getInt(6));
						}
					} else if (j == 7) {
						if (rs.getInt(8) == 0) {
							contents[i][j] = "없음";
						} else {
							contents[i][j] = Integer.toString(rs.getInt(8));
						}
					} else if (j == 9) {
						if (rs.getInt(10) == 0) {
							contents[i][j] = "없음";
						} else {
							contents[i][j] = Integer.toString(rs.getInt(10));
						}
					} else if (j == 6) {
						if (rs.getTimestamp(7).toString().equals("2001-01-01 00:00:00.0")) {
							contents[i][j] = "";
						} else {
							time_chk = rs.getTimestamp(7);
							contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter)
									.substring(0, 23);
						}
					} else if (j == 8) {
						if (rs.getTimestamp(9).toString().contains("2001-01-01 00:00:00.0")) {
							contents[i][j] = "";
						} else {
							time_chk = rs.getTimestamp(9);
							contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter)
									.substring(0, 23);
						}
					} else if (j == 10) {
						if (rs.getTimestamp(11).toString().equals("2001-01-01 00:00:00.0")) {
							contents[i][j] = "";
						} else {
							time_chk = rs.getTimestamp(11);
							contents[i][j] = Time.TimeStampTOlocalDateTime(time_chk).format(dateTimeFormatter)
									.substring(0, 23);
						}
					} else {
						String mon = rs.getString(j + 1);
						contents[i][j] = mon;

						if (j == 11 && mon.substring(0, 2).equals(date.format(monthFormatter))) {
							last_month++;
						}

					}
				}
				i++;
			}
			MemberManagementPage.totalmember.setText("회원 수 : " + row);
			MemberManagementPage.newmember.setText("신규 회원 수 : " + last_month);

			DefaultTableModel model = new DefaultTableModel(contents, header);

			table = new JTable(model);

			table.getTableHeader().setOpaque(false);
			// table.setBounds(40, 104, 390, 245);
			table.setRowHeight(40);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(60);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(50);
			for(int k=0;k<row;k++) {
			if (contents[k][6].equals("")) {
				table.getColumnModel().getColumn(6).setPreferredWidth(90);
			} else {
				table.getColumnModel().getColumn(6).setPreferredWidth(170);
			}
			table.getColumnModel().getColumn(7).setPreferredWidth(50);
			if (contents[k][8].equals("")) {
				table.getColumnModel().getColumn(8).setPreferredWidth(90);
			} else {
				table.getColumnModel().getColumn(8).setPreferredWidth(170);
			}
			table.getColumnModel().getColumn(9).setPreferredWidth(50);
			if (contents[k][10].equals("")) {
				table.getColumnModel().getColumn(10).setPreferredWidth(100);
			} else {
				table.getColumnModel().getColumn(10).setPreferredWidth(170);
			}
			table.getColumnModel().getColumn(11).setPreferredWidth(120);
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			}
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