package login.page;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.design.Style;

public class SalesDB {

	JTable table;

	public SalesDB(String sql, int maxCnt) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);

			PreparedStatement read_data = conn.prepareStatement(sql);

			if (maxCnt == 1) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2, 4));
			} else if (maxCnt == 2) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2, 4));
				read_data.setString(2, (String) SalesManagementPage.month.getSelectedItem());
			} else if (maxCnt == 3) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2, 4));
				read_data.setString(2, (String) SalesManagementPage.month.getSelectedItem());
				read_data.setString(3, (String) SalesManagementPage.day.getSelectedItem());
			}

			ResultSet rs = read_data.executeQuery();

			String header[] = { "결제일시", "이용권명", "사물함", "결제방식", "결제금액" };

			int realRow = 0;

			while (rs.next()) {
				realRow++;
			}

			String[][] contents = new String[realRow][header.length];

			// read_data.executeBatch();

			int i = 0;
			int sum = 0;

			int week1 = 0;
			int week2 = 0;
			int week3 = 0;
			int week4 = 0;
			int week5 = 0;

			ResultSet rs1 = read_data.executeQuery();
			while (rs1.next()) {
				contents[i][0] = rs1.getString(1).substring(0, 19);
				for (int j = 1; j < header.length; j++) {
					contents[i][j] = rs1.getString(j + 1);
					if (j == 4) {
						sum += Integer.parseInt(rs1.getString(j + 1));
					}
				}

				// 주간 매출 부분
				if (maxCnt == 2) {
					int single = Integer.parseInt(rs1.getString(1).substring(8, 10));
	
					if (single <= 7) {
						week1 += Integer.parseInt(rs1.getString(5));
					} else if (single <= 14) {
						week2 += Integer.parseInt(rs1.getString(5));
					} else if (single <= 21) {
						week3 += Integer.parseInt(rs1.getString(5));
					} else if (single <= 28) {
						week4 += Integer.parseInt(rs1.getString(5));
					} else {
						week5 += Integer.parseInt(rs1.getString(5));
					}

					SalesManagementPage.weekTotal[0]
							.setText(1 + "주차 매출 : " + NumberFormat.getInstance().format(week1) + "원");
					SalesManagementPage.weekTotal[1]
							.setText(2 + "주차 매출 : " + NumberFormat.getInstance().format(week2) + "원");
					SalesManagementPage.weekTotal[2]
							.setText(3 + "주차 매출 : " + NumberFormat.getInstance().format(week3) + "원");
					SalesManagementPage.weekTotal[3]
							.setText(4 + "주차 매출 : " + NumberFormat.getInstance().format(week4) + "원");
					SalesManagementPage.weekTotal[4]
							.setText(5 + "주차 매출 : " + NumberFormat.getInstance().format(week5) + "원");

				}
				i++;
			}

			SalesManagementPage.totalPayment.setText("총 매출 : " + NumberFormat.getInstance().format(sum) + "원");

			DefaultTableModel model = new DefaultTableModel(contents, header);

			table = new JTable(model);
			new Style(table);
			// table.setBounds(40, 104, 390, 245);
			table.setRowHeight(35);
			// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(90);
			table.getColumnModel().getColumn(3).setPreferredWidth(65);
			table.getColumnModel().getColumn(4).setPreferredWidth(65);

			SalesManagementPage.scrollPane.setViewportView(table);

			if (rs1 != null)
				rs1.close();
			if (rs != null)
				rs.close();
			if (read_data != null)
				read_data.close();

			if (conn != null)
				conn.close();

			System.out.println("성공");

		} catch (SQLException e1) {
			e1.printStackTrace();
			// System.out.println(e1.toString());
		} catch (ClassNotFoundException e1) {
			// e1.printStackTrace();
			System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}
	}

}
