package login.page;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.design.Style;
import login.mainmenu._08reservation;
import login.mainmenu._09payment;
import login.mainmenu._10paycash;
import login.signUp.SignUpEnum;
import login.signUp.window.ResultWindow;

public class ManagementPage extends JPanel implements ActionListener {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JLabel title;
	JScrollPane scrollPane;
	JTable table;
	
	public ManagementPage() {
		setLayout(null);
		new Style(this);

		title = new JLabel("���� ����");
		new Style(title);
		title.setFont(new Font("���� ���", Font.BOLD, 18));
		title.setBounds(250, 10, 100, 100);
		add(title);

		scrollPane = new JScrollPane();
		new Style(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBounds(50, 100, 500, 400);
		add(scrollPane);
		
		String header[] = { "�����Ͻ�", "�̿�Ǹ�", "�繰��", "�������", "�����ݾ�" };
		String contents[][] = { { "��ȣ", _08reservation.number }
				
				
				
				 };

		DefaultTableModel model = new DefaultTableModel(contents, header);

		table = new JTable(model);
		new Style(table);
		table.setBounds(40, 104, 390, 245);
		table.setRowHeight(35);
		
		scrollPane.setViewportView(table);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("�������", new ManagementPage());
		MainPage.main_cards.show(MainPage.main_page_panel, "�������");
		MainPage.userToggle = "�������";

		try {
			// ���� �� ����ǥ�������� �Ÿ���
			// �̸�, �������, �޴���, ���,

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);
			//
			PreparedStatement read_PhoneNumber = conn.prepareStatement("SELECT phone_number FROM person_info");

			ResultSet rs = read_PhoneNumber.executeQuery();

			while (rs.next()) {
//				phoneNumber = rs.getString(1);
//				if (text.equals(phoneNumber)) {
//				
//					phoneNumber1.setText("");
//					phoneNumber2.setText("");
//					phoneNumber3.setText("");
//					samePhoneNumber = true;
				break;
//				}
			}

			if (rs != null)
				rs.close();
			if (read_PhoneNumber != null)
				read_PhoneNumber.close();

			if (conn != null)
				conn.close();

			System.out.println("����");

		} catch (SQLException e1) {
			// e1.printStackTrace();
			System.out.println(e1.toString());
			// new SignUpFailWindow(e1);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("[ojdbc] Ŭ���� ��ΰ� Ʋ�Ƚ��ϴ�.");
		}
	}

}
