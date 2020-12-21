package login.page;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import login.design.Style;
import login.mainmenu.Time;
import login.mainmenu._05locker;

public class PassChangeDB {

	JTable table;

	public PassChangeDB(int p,int k) { 


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);
		  
			if(k==0) { 
				String sql= "update seat_price_info set seat_price=? where seat_type=?" ;
				PreparedStatement pstm1 = conn.prepareStatement(sql);

				pstm1.setInt(1,Integer.parseInt(PassChange.text.get(0).getText()));
				pstm1.setString(2,PassChange.combobox.get(0).getSelectedItem().toString()); 
				int row = pstm1.executeUpdate();
				System.out.printf("%d행 업뎃\n",row);

				if (pstm1 != null)
					pstm1.close(); 
				 

			}else if(k==1) { 
				String sql= "update seat_price_info set seat_price=? where seat_type=?" ;
				PreparedStatement pstm1 = conn.prepareStatement(sql);

				pstm1.setInt(1,Integer.parseInt(PassChange.text.get(1).getText()));
				pstm1.setString(2,PassChange.combobox.get(1).getSelectedItem().toString()); 
				int row = pstm1.executeUpdate();
				System.out.printf("%d행 업뎃\n",row);
				if (pstm1 != null)
					pstm1.close(); 
				 
			}else if(k==2) { 
				String sql= "update seat_price_info set seat_price=? where seat_type=?" ;
				PreparedStatement pstm1 = conn.prepareStatement(sql);

				pstm1.setInt(1,Integer.parseInt(PassChange.text.get(2).getText()));
				pstm1.setString(2,PassChange.combobox.get(2).getSelectedItem().toString()); 
				int row = pstm1.executeUpdate();
				System.out.printf("%d행 업뎃\n",row);
				if (pstm1 != null)
					pstm1.close(); 
			 
			}else if(k==3) { 
				String sql= "update locker_price_info set locker_price=? where locker_type=?" ;
				PreparedStatement pstm1 = conn.prepareStatement(sql);
				
				pstm1.setInt(1,Integer.parseInt(PassChange.text.get(3).getText()));
				pstm1.setString(2,PassChange.combobox.get(3).getSelectedItem().toString()); 
				int row = pstm1.executeUpdate();
				System.out.printf("%d행 업뎃\n",row);
				if (pstm1 != null)
					pstm1.close(); 
			}
			if(p==1) { //가격표 현황 보이기(scroll 부분)
				String sql= "select * from seat_price_info,locker_price_info" ;

				PreparedStatement pstm = conn.prepareStatement(sql); 

				ResultSet rs = pstm.executeQuery(); 
				String header[] = {"자리 이용권","가격","사물함 이용권","가격"};//12열

				int row=0;
				int i = 0;
				int sum = 0;
				while (rs.next()) {
					row++;
				}
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();

				String[][] contents = new String[row][header.length];
				while (rs.next()) { 
					contents[i][0] = rs.getString(1);
					contents[i][1] = Integer.toString(rs.getInt(2));
					if(i==0) {
						contents[i][2] = rs.getString(3);
						contents[i][3] = Integer.toString(rs.getInt(4));
					}
					i++;
				} 

				DefaultTableModel model = new DefaultTableModel(contents, header);

				table = new JTable(model);

				table.getTableHeader().setOpaque(false);
				// table.setBounds(40, 104, 390, 245);
				table.setRowHeight(40);

				//	           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(60);
				table.getColumnModel().getColumn(2).setPreferredWidth(150);
				table.getColumnModel().getColumn(3).setPreferredWidth(60);

				JTableHeader headers = table.getTableHeader();
				headers.setBackground(Color.darkGray);
				headers.setForeground(Color.decode("#cfab8b"));

				new Style(table);
				PassChange.scroll.setViewportView(table);


				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();

			 
			}
			if (conn != null)
				conn.close();

		} catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
	}
}