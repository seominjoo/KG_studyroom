package studyroom.user.usermode;

import java.awt.Color;
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

import studyroom.MainPage;
import studyroom.design.Style;
import studyroom.swingTools.State;

public class Receipt extends JPanel {

	JTable table;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� a hh�� mm��");

	static String type;

	public Receipt(String type) {
		this.type = type;
	}

	public Receipt(LocalDateTime ss, int price) {

		// ���� �ǽð� �¼���Ȳ ������Ʈ
		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

		setLayout(null);
		new Style(this);
		setVisible(true);

		// ������ó�� ���̰� �ϱ� ���� �Ͼ� �г�
		JPanel p2 = new JPanel();
		p2.setBackground(Color.white);
		p2.setBounds(140, 40, 430, 517);
		p2.setLayout(null);
		add(p2);

		int x = 36;
		
		// �� ���� �����
		JLabel headTitle = new JLabel("<html><pre>KG STUDY</pre>");
		new Style(headTitle);
		headTitle.setBounds(162, -15, 360, 80);
		headTitle.setFont(new Font("���� ���", Font.PLAIN, 30));
		p2.add(headTitle);

		JLabel smallTtile = new JLabel("KG STUDY");
		new Style(smallTtile);
		smallTtile.setFont(new Font("���� ���", Font.PLAIN, 11));
		smallTtile.setBounds(x, 65, 150, 20);
		p2.add(smallTtile);

		JLabel paidTime = new JLabel(Payment.time_now.format(DateTimeFormatter.ofPattern("yyyy - MM - dd  HH : mm : ss")));
		new Style(paidTime);
		paidTime.setFont(new Font("���� ���", Font.PLAIN, 11));
		paidTime.setBounds(258, 65, 250, 20);
		p2.add(paidTime);

		JLabel businessNum = new JLabel("����ڹ�ȣ : 1541600462");
		new Style(businessNum);
		businessNum.setFont(new Font("���� ���", Font.PLAIN, 11));
		businessNum.setBounds(x, 85, 170, 20);
		p2.add(businessNum);

		JLabel ceo = new JLabel("��ǥ : ������");
		new Style(ceo);
		ceo.setFont(new Font("���� ���", Font.PLAIN, 11));
		ceo.setBounds(x, 105, 170, 20);
		p2.add(ceo);

		JLabel address = new JLabel("���� ������ �������84�� 16 11, 12��");
		new Style(address);
		address.setFont(new Font("���� ���", Font.PLAIN, 11));
		address.setBounds(x, 125, 250, 20);
		p2.add(address);

		JLabel line1 = new JLabel("------------------------------------------------------------------------");
		new Style(line1);
		line1.setFont(new Font("���� ���", Font.PLAIN, 11));
		line1.setBounds(x, 308, 360, 10);
		p2.add(line1);
		
		JLabel line2 = new JLabel("------------------------------------------------------------------------");
		new Style(line2);
		line2.setFont(new Font("���� ���", Font.PLAIN, 11));
		line2.setBounds(x, 398, 360, 10);
		p2.add(line2);
		
		JLabel line3 = new JLabel("------------------------------------------------------------------------");
		new Style(line3);
		line3.setFont(new Font("���� ���", Font.PLAIN, 11));
		line3.setBounds(x, 458, 360, 10);
		p2.add(line3);

		
		String header1[] = { "��ǰ��", "�ܰ�", "����", "�ݾ�" };
		String contents1[][] = { { "��ǰ��", "�ܰ�", "����", "�ݾ�" },
				{ Reservation.type11, NumberFormat.getInstance().format(Reservation.price), "1",
				NumberFormat.getInstance().format(Reservation.price * Integer.parseInt("1")) } };

		DefaultTableModel model1 = new DefaultTableModel(contents1, header1);

		JTable priceTable = new JTable(model1);
		new Style(priceTable);
		priceTable.setShowGrid(false);
		priceTable.setShowHorizontalLines(true);
		priceTable.setBorder(null);
		priceTable.setBounds(x, 159, 360, 60);
		priceTable.setRowHeight(30);
		priceTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		priceTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		priceTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		priceTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		p2.add(priceTable);


		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		centerRenderer.setOpaque(false);
		for (int i = 0; i < header1.length; i++) {
			priceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		String header2[] = { "����", "����" };
		String contents2[][] = { { "��ȣ", Reservation.number },
				{ "�Խ� �ð�", Payment.time_now.format(dateTimeFormatter)},
				{ "���(����) ���� �ð�", ss.format(dateTimeFormatter)},
				{ "���� �ݾ�", NumberFormat.getInstance().format((Reservation.price)) },
				{ "���ް��ݾ�", NumberFormat.getInstance().format((int) (Math.ceil(Reservation.price / 1.1))) },
				{ "�ΰ���", NumberFormat.getInstance()
						.format((int) (Math.floor(Reservation.price - Reservation.price / 1.1))) },
				{ "���� �ݾ�", NumberFormat.getInstance().format(price) },
				{ "�Ž��� ��", NumberFormat.getInstance().format(Paycash.change) } };

		DefaultTableModel model2 = new DefaultTableModel(contents2, header2);

		table = new JTable(model2);
		new Style(table);
		table.setShowGrid(false);
		table.setBounds(x, 224, 360, 240);
		table.setRowHeight(30);
		table.setBorder(null);
		p2.add(table);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setOpaque(false);
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		
		JLabel payType = new JLabel("�ŷ����� : " + type);
		new Style(payType);
		payType.setBounds(x, 472, 130, 20);
		payType.setFont(new Font("���� ���", Font.PLAIN, 11));
		p2.add(payType);
		// ��������� ���� ���̴� ������ ������
		
		// totalPayment�� person_info table�� �ֱ� ���� DB
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			PreparedStatement pstmt = null;

			PreparedStatement readPrice = conn
					.prepareStatement("SELECT payment FROM payment_record where person_id = ?");
			readPrice.setInt(1, Mainmenu.id);
			ResultSet rs = readPrice.executeQuery();

			int payment = 0;
			while (rs.next()) {
				payment = rs.getInt(1);
			}

			String total_payment = "update person_info set total_payment = total_payment + ? where person_id = ?";
			pstmt = conn.prepareStatement(total_payment);
			pstmt.setInt(1, payment);
			pstmt.setInt(2, Mainmenu.id);
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
