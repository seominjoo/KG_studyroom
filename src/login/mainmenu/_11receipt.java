package login.mainmenu;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.design.Style;
import login.page.MainPage;
import login.swingTools.State;

public class _11receipt extends JPanel {

	JTable table;
	   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� a hh�� mm�� ss��");

	public _11receipt(LocalDateTime ss, int price) {
		
		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

		setLayout(null);
		new Style(this);
		setVisible(true);

		JLabel label01 = new JLabel("<html><pre>������</pre>");
		new Style(label01);
		label01.setBounds(183, 0, 440, 80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));

		JPanel p2 = new JPanel();
		new Style(p2);
		p2.setBounds(70, 100, 693, 453);
		add(p2);
		p2.setLayout(null);
		p2.add(label01);

		String header[] = { "����", "����" };
		String contents[][] = { { "��ȣ", _08reservation.number },
				{ "���� �ð�", _09payment.time_now.format(dateTimeFormatter).substring(0, 24) },
				{ "���(����) ���� �ð�", ss.format(dateTimeFormatter).substring(0, 24) }, { "�̿��", _08reservation.type11 },
				{ "���� �ݾ�", Integer.toString((_08reservation.price)) + "��" }, { "���� �ݾ�", Integer.toString(price) + "��" },
				{ "�Ž��� ��", Integer.toString(_10paycash.change) + "��" } };

		DefaultTableModel model = new DefaultTableModel(contents, header);

		table = new JTable(model);
		new Style(table);
		table.setBounds(40, 104, 390, 245);
		table.setRowHeight(35);
		p2.add(table);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			PreparedStatement pstmt = null;
		  
			PreparedStatement readPrice = conn
					.prepareStatement("SELECT payment FROM payment_record where person_id = ?");
			readPrice.setInt(1, _00main.id);
			ResultSet rs = readPrice.executeQuery();

			int payment = 0;
			while (rs.next()) {
				payment = rs.getInt(1);
			}

			String total_payment = "update person_info set total_payment = total_payment + ? where person_id = ?";
			pstmt = conn.prepareStatement(total_payment);
			pstmt.setInt(1, payment);
			pstmt.setInt(2, _00main.id);
			int rowtp = pstmt.executeUpdate();

			if (rs != null) rs.close();
			if (readPrice != null) readPrice.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
