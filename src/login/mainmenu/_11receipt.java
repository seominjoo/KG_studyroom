package login.mainmenu;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import login.design.Style;
import login.page.MainPage;
import login.swingTools.State;

public class _11receipt extends JPanel {

	JTable table;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� a hh�� mm�� ss��");

	static String type;

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
		p2.setBounds(0, 0, 800, 600);
		p2.setLayout(null);
		add(p2);

		JLabel headTitle = new JLabel("<html><pre>KG STUDY</pre>");
		new Style(headTitle);
		headTitle.setBounds(290, 0, 440, 80);
		headTitle.setFont(new Font("Courier", Font.PLAIN, 35));
		p2.add(headTitle);

		JLabel smallTtile = new JLabel("KG STUDY");
		new Style(smallTtile);
		smallTtile.setFont(new Font("���� ���", Font.PLAIN, 11));
		smallTtile.setBounds(165, 80, 150, 20);
		p2.add(smallTtile);

		JLabel paidTime = new JLabel(_09payment.time_now.format(dateTimeFormatter).substring(0, 24));
		new Style(paidTime);
		paidTime.setFont(new Font("���� ���", Font.PLAIN, 11));
		paidTime.setBounds(386, 80, 250, 20);
		p2.add(paidTime);

		JLabel businessNum = new JLabel("����ڹ�ȣ : 1541600462");
		new Style(businessNum);
		businessNum.setFont(new Font("���� ���", Font.PLAIN, 11));
		businessNum.setBounds(165, 100, 170, 20);
		p2.add(businessNum);

		JLabel ceo = new JLabel("��ǥ : ������");
		new Style(ceo);
		ceo.setFont(new Font("���� ���", Font.PLAIN, 11));
		ceo.setBounds(165, 120, 170, 20);
		p2.add(ceo);

		JLabel address = new JLabel("���� ������ �������84�� 16 11, 12��");
		new Style(address);
		address.setFont(new Font("���� ���", Font.PLAIN, 11));
		address.setBounds(165, 140, 250, 20);
		p2.add(address);

		JLabel line1 = new JLabel("------------------------------------------------------------------------------");
		new Style(line1);
		line1.setFont(new Font("���� ���", Font.PLAIN, 11));
		line1.setBounds(165, 293, 390, 10);
		p2.add(line1);
		
		JLabel line2 = new JLabel("------------------------------------------------------------------------------");
		new Style(line2);
		line2.setFont(new Font("���� ���", Font.PLAIN, 11));
		line2.setBounds(165, 383, 390, 10);
		p2.add(line2);
		
		JLabel line3 = new JLabel("------------------------------------------------------------------------------");
		new Style(line3);
		line3.setFont(new Font("���� ���", Font.PLAIN, 11));
		line3.setBounds(165, 443, 390, 10);
		p2.add(line3);

		String header1[] = { "��ǰ��", "�ܰ�", "����", "�ݾ�" };
		String contents1[][] = { { "��ǰ��", "�ܰ�", "����", "�ݾ�" },
				{ _08reservation.type11, NumberFormat.getInstance().format(_08reservation.price), "1",
				NumberFormat.getInstance().format(_08reservation.price * Integer.parseInt("1")) } };

		DefaultTableModel model1 = new DefaultTableModel(contents1, header1);

		JTable priceTable = new JTable(model1);
		new Style(priceTable);
		priceTable.setShowGrid(false);
		priceTable.setShowHorizontalLines(true);
		priceTable.setBorder(null);
		priceTable.setBounds(165, 174, 390, 60);
		priceTable.setRowHeight(30);
		p2.add(priceTable);

//		JTableHeader priceHeader = priceTable.getTableHeader();
//		priceHeader.setOpaque(false);
//		priceHeader.setBounds(165, 164, 390, 30);
//		p2.add(priceHeader);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		centerRenderer.setOpaque(false);
		for (int i = 0; i < header1.length; i++) {
			priceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//			priceHeader.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		String header2[] = { "����", "����" };
		String contents2[][] = { { "��ȣ", _08reservation.number },
				{ "���(����) ���� �ð�", ss.format(dateTimeFormatter).substring(0, 24) },
				{ "���� �ݾ�", NumberFormat.getInstance().format((_08reservation.price)) },
				{ "���ް��ݾ�", NumberFormat.getInstance().format((int) (Math.ceil(_08reservation.price / 1.1))) },
				{ "�ΰ���", NumberFormat.getInstance()
						.format((int) (Math.floor(_08reservation.price - _08reservation.price / 1.1))) },
				{ "���� �ݾ�", NumberFormat.getInstance().format(price) },
				{ "�Ž��� ��", NumberFormat.getInstance().format(_10paycash.change) } };

		DefaultTableModel model2 = new DefaultTableModel(contents2, header2);

		table = new JTable(model2);
		new Style(table);
		table.setShowGrid(false);
		table.setBounds(165, 239, 390, 210);
		table.setRowHeight(30);
		table.setBorder(null);
		p2.add(table);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setOpaque(false);
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		
		JLabel payType = new JLabel("�ŷ����� : " + type);
		new Style(payType);
		payType.setBounds(165, 457, 130, 20);
		payType.setFont(new Font("���� ���", Font.PLAIN, 11));
		p2.add(payType);

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

			if (rs != null)
				rs.close();
			if (readPrice != null)
				readPrice.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
