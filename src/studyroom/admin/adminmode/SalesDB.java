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

			String header[] = {"�����Ͻ�", "ȸ����ȣ", "�̿�Ǹ�", "�繰��", "�������", "�����ݾ�" };

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

			// �����Ͱ� ���� �� �ϴµ� ������ ������ ���, �ְ������� ���� 0������
			if (realRow == 0 && maxCnt == 2) {
				for (int i2 = 0; i2 < weeks.length; i2++) {
					SalesManagementPage.weekTotal[i2].setText(
							(i2 + 1) + "���� ���� : " + 0 + "��");
				}
			} 
			// �����Ͱ� ���� �� �ϴµ� �������� �ƴ� �ٸ��� ������ ���, �ְ������� ����� �ʱ�
			else if(realRow == 0 && maxCnt != 2) {
				for (int i2 = 0; i2 < weeks.length; i2++) {
					SalesManagementPage.weekTotal[i2].setText("");
				}
			}
			
			// �׿� �����Ͱ� ������ ��
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

					// ������ ������ ��, ������ ������ ���� �ְ����� DB
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

						// �ְ� ���� settext
						for (int i2 = 0; i2 < weeks.length; i2++) {
							SalesManagementPage.weekTotal[i2].setText(
									(i2 + 1) + "���� ���� : " + NumberFormat.getInstance().format(weeks[i2]) + "��");
						}

					} 
					// �������� �ƴ� �ٸ��� ������ �� �ְ����� �� ���̰� �ϱ�
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
			// �� ����
			SalesManagementPage.totalPayment.setText("�� ���� : " + NumberFormat.getInstance().format(sum) + "��");

			DefaultTableModel model = new DefaultTableModel(contents, header);

			// ������� ���̺�
			String[] columns = {"�����Ͻ�", "ȸ����ȣ", "�̿�Ǹ�", "�繰��", "�������", "�����ݾ�"};
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

			//System.out.println("����");

		} catch (SQLException e1) {
			e1.printStackTrace();
			// System.out.println(e1.toString());
		} catch (ClassNotFoundException e1) {
			// e1.printStackTrace();
			//System.out.println("[ojdbc] Ŭ���� ��ΰ� Ʋ�Ƚ��ϴ�.");
		}
	}

}
