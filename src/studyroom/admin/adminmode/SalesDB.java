package studyroom.admin.adminmode;

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

import studyroom.design.Style;

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

			String header[] = {"결제일시", "회원번호", "이용권명", "사물함", "결제방식", "결제금액" };

			int realRow = 0;

			while (rs.next()) {
				realRow++;
			}

			String[][] contents = new String[realRow][header.length];


			int i = 0;
			int sum = 0;

			int week1 = 0;
			int week2 = 0;
			int week3 = 0;
			int week4 = 0;
			int week5 = 0;

			int[] weeks = { week1, week2, week3, week4, week5 };

			// 데이터가 존재 안 하는데 월매출 눌렀을 경우, 주간매출을 띄우되 0원으로
			if (realRow == 0 && maxCnt == 2) {
				for (int i2 = 0; i2 < weeks.length; i2++) {
					SalesManagementPage.weekTotal[i2].setText(
							(i2 + 1) + "주차 매출 : " + 0 + "원");
				}
			} 
			// 데이터가 존재 안 하는데 월매출이 아닌 다른걸 눌렀을 경우, 주간매출을 띄우지 않기
			else if(realRow == 0 && maxCnt != 2) {
				for (int i2 = 0; i2 < weeks.length; i2++) {
					SalesManagementPage.weekTotal[i2].setText("");
				}
			}
			
			// 그외 데이터가 존재할 때
			else {
				ResultSet rs1 = read_data.executeQuery();
				while (rs1.next()) {
					contents[i][0] = rs1.getString(1).substring(0, 19);
					for (int j = 1; j < header.length; j++) {
						contents[i][j] = rs1.getString(j + 1);

						if (j == 5) {
							sum += Integer.parseInt(rs1.getString(j + 1));
						}
					}

					// 데이터 존재할 때, 월매출 눌렀을 때의 주간매출 DB
					if (maxCnt == 2) {

						int single = Integer.parseInt(rs1.getString(1).substring(8, 10));

						if (single <= 7) {
							weeks[0] += Integer.parseInt(rs1.getString(6));
						} else if (single <= 14) {
							weeks[1] += Integer.parseInt(rs1.getString(6));
						} else if (single <= 21) {
							weeks[2] += Integer.parseInt(rs1.getString(6));
						} else if (single <= 28) {
							weeks[3] += Integer.parseInt(rs1.getString(6));
						} else {
							weeks[4] += Integer.parseInt(rs1.getString(6));
						}

						// 주간 매출 settext
						for (int i2 = 0; i2 < weeks.length; i2++) {
							SalesManagementPage.weekTotal[i2].setText(
									(i2 + 1) + "주차 매출 : " + NumberFormat.getInstance().format(weeks[i2]) + "원");
						}

					} 
					// 월매출이 아닌 다른걸 눌렀을 때 주간매출 안 보이게 하기
					else {
						for (int i2 = 0; i2 < SalesManagementPage.weekTotal.length; i2++) {
							SalesManagementPage.weekTotal[i2].setText("");
						}
					}
					i++;
				}

				if (rs1 != null)
					rs1.close();
			}
			// 총 매출
			SalesManagementPage.totalPayment.setText("총 매출 : " + NumberFormat.getInstance().format(sum) + "원");

			DefaultTableModel model = new DefaultTableModel(contents, header);

			// 매출관리 테이블
			String[] columns = {"결제일시", "회원번호", "이용권명", "사물함", "결제방식", "결제금액"};
			table = new JTable(model);
			new Style(table, columns);
			table.setRowHeight(35);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(130);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(4).setPreferredWidth(55);
			table.getColumnModel().getColumn(5).setPreferredWidth(55);
			
			SalesManagementPage.scrollPane.setViewportView(table);		
			
			if (rs != null)
				rs.close();
			if (read_data != null)
				read_data.close();		
			if (conn != null)
				conn.close();

			//System.out.println("성공");

		} catch (SQLException e1) {
			e1.printStackTrace();
			// System.out.println(e1.toString());
		} catch (ClassNotFoundException e1) {
			// e1.printStackTrace();
			//System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}
	}

}
