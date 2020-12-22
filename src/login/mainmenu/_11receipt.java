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

	   String type;
	   
	   public _11receipt(String type) {
		this.type = type;
	}
	   
	public _11receipt(LocalDateTime ss, int price) {
		
		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

		setLayout(null);
		new Style(this);
		setVisible(true);

		JPanel p2 = new JPanel();
		new Style(p2);
		p2.setBounds(70, 100, 693, 453);
		p2.setLayout(null);
		add(p2);

		JLabel headTitle = new JLabel("<html><pre>MEMO STUDY</pre>");
		new Style(headTitle);
		headTitle.setBounds(200, 0, 440, 80);
		headTitle.setFont(new Font("Courier", Font.PLAIN, 35));
		p2.add(headTitle);
		
		JLabel smallTtile = new JLabel("MEMO STUDY");
		new Style(smallTtile);
		smallTtile.setBounds(150, 60, 150, 20);
		p2.add(smallTtile);
		
		JLabel paidTime = new JLabel(_09payment.time_now.format(dateTimeFormatter).substring(0, 24));
		new Style(paidTime);
		paidTime.setBounds(250, 60, 220, 20);
		p2.add(paidTime);
		
		JLabel businessNum = new JLabel("����ڹ�ȣ:1541600462");
		new Style(businessNum);
		businessNum.setBounds(150, 80, 170, 20);
		p2.add(businessNum);

		JLabel ceo = new JLabel("��ǥ:������");
		new Style(ceo);
		ceo.setBounds(150, 100, 170, 20);
		p2.add(ceo);
		
		JLabel address = new JLabel("�������� ������ ���̿� �ִ� ���̼� 3�� �ǹ� ��򰡿�");
		new Style(address);
		address.setBounds(150, 140, 250, 20);
		p2.add(address);
		
		String header1[] = { "��ǰ��", "�ܰ�", "����", "�ݾ�" };
		String contents1[][] = { { _08reservation.type11, Integer.toString((_08reservation.price)),
			"1", String.valueOf(_08reservation.price * Integer.parseInt("1"))}
				};
		
		DefaultTableModel model1 = new DefaultTableModel(contents1, header1);
		
		JTable priceTable = new JTable(model1);
		new Style(priceTable);
		priceTable.setBounds(57, 104, 390, 50);
		priceTable.setRowHeight(35);
		p2.add(priceTable);
		
		String header2[] = { "����", "����" };
		String contents2[][] = { { "ȸ�� ��ȣ", _08reservation.number },
				 { "���(����) ���� �ð�", ss.format(dateTimeFormatter).substring(0, 24) },
				{ "���� �ݾ�", Integer.toString((_08reservation.price)) }, 
				{ "���ް��ݾ�", String.valueOf(_08reservation.price / 1.1) },
				{ "�ΰ���", String.valueOf(_08reservation.price - _08reservation.price / 1.1) },
				{ "���� �ݾ�", Integer.toString(price)  },
				{ "�Ž��� ��", Integer.toString(_10paycash.change) + "��" } };

		DefaultTableModel model2 = new DefaultTableModel(contents2, header2);

		table = new JTable(model2);
		new Style(table);
		table.setBounds(57, 154, 390, 270);
		table.setRowHeight(35);
		p2.add(table);
		
		JLabel payType = new JLabel("");

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
