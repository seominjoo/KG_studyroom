package login.page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.design.Style;

public class SalesDB {

	JTable table;

	public SalesDB(String sql, int maxCnt) {
		
		System.out.println((String) SalesManagementPage.year.getSelectedItem().toString().substring(2,4));
		System.out.println((String) SalesManagementPage.month.getSelectedItem());
		System.out.println((String) SalesManagementPage.day.getSelectedItem());
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);

			PreparedStatement count = conn.prepareStatement("select count(*) from payment_record");

			ResultSet rs = count.executeQuery();
			int row = 0;
			while (rs.next()) {
				row = rs.getInt(1);
			}
			String header[] = { "결제일시", "이용권명", "사물함", "결제방식", "결제금액" };
			String[][] contents = new String[row][header.length];

			PreparedStatement read_data = conn.prepareStatement(sql);

			if (maxCnt == 1) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2,4));
			} else if (maxCnt == 2) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2,4));
				read_data.setString(2, (String) SalesManagementPage.month.getSelectedItem());
			} else if (maxCnt == 3) {
				read_data.setString(1, (String) SalesManagementPage.year.getSelectedItem().toString().substring(2,4));
				read_data.setString(2, (String) SalesManagementPage.month.getSelectedItem());
				read_data.setString(3, (String) SalesManagementPage.day.getSelectedItem());
			}

			//read_data.executeBatch();
			rs = read_data.executeQuery();

			int i = 0;
			while (rs.next()) {
				contents[i][0] = rs.getString(1).substring(0,19);
				for (int j = 1; j < header.length; j++) {
					contents[i][j] = rs.getString(j + 1);
				}
				i++;
			}

			DefaultTableModel model = new DefaultTableModel(contents, header);

			table = new JTable(model);
			new Style(table);
			table.getTableHeader().setOpaque(false);
			//table.setBounds(40, 104, 390, 245);
			table.setRowHeight(35);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);

			SalesManagementPage.scrollPane.setViewportView(table);

			if (count != null)
				count.close();
			if (rs != null)
				rs.close();
			if (read_data != null)
				read_data.close();

			if (conn != null)
				conn.close();

			System.out.println("성공");

		} catch (SQLException e1) {
			 e1.printStackTrace();
			//System.out.println(e1.toString());
		} catch (ClassNotFoundException e1) {
			// e1.printStackTrace();
			System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}
	}

}
