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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.BirthEnum;
import login.YearMonthClick;
import login.design.Style;
import login.mainmenu._08reservation;
import login.mainmenu._09payment;
import login.mainmenu._10paycash;
import login.signUp.SignUpEnum;
import login.signUp.window.ResultWindow;

public class SalesManagementPage extends JPanel implements ActionListener {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	JLabel title;
	JScrollPane scrollPane;
	JTable table;
	
	public static JComboBox<String> year;
	public static JComboBox<String> month;
	public static JComboBox<String> day;
	
	public SalesManagementPage() {
		setLayout(null);
		new Style(this);

		title = new JLabel("매출 관리");
		new Style(title);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		title.setBounds(250, 10, 100, 100);
		add(title);

		scrollPane = new JScrollPane();
		new Style(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBounds(50, 100, 500, 400);
		add(scrollPane);
		
		year = new JComboBox<String>(new ManagementDate().yearTable);
		year.setBounds(0, 3, 65, 30);
		add(year);
		new Style(year);
		year.setSelectedItem("2020");

		month = new JComboBox<String>(new ManagementDate().monthTable);
		month.setBounds(84, 3, 50, 30);
		add(month);
		new Style(month);

		day = new JComboBox<String>(new ManagementDate().dayTable);
		day.setBounds(152, 3, 50, 30);
		add(day);
		new Style(day);

		// 연도, 월 클릭
		year.addActionListener(new YearMonthClick("year", "매출관리"));
		month.addActionListener(new YearMonthClick("month", "매출관리"));

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);
			
			PreparedStatement count = conn.prepareStatement
					("select count(*) from payment_record");
			
			ResultSet rs = count.executeQuery();
			int row = 0;
			while(rs.next()) {
				row = rs.getInt(1);
			}
			String header[] = { "결제일시", "이용권명", "사물함", "결제방식", "결제금액" };
			String[][] contents = new String[row][header.length];
			
			PreparedStatement read_data = conn.prepareStatement
					("SELECT paid_time,seat_type,locker_type,pay_method,payment"
							+ " FROM payment_record order by paid_time");

			rs = read_data.executeQuery();
			
			int i = 0;
			while (rs.next()) {
				for(int j = 0; j < header.length; j++) {
					contents[i][j] = rs.getString(j+1);
				}
				i++;
			}
			
			DefaultTableModel model = new DefaultTableModel(contents, header);

			table = new JTable(model);
			new Style(table);
			table.setBounds(40, 104, 390, 245);
			table.setRowHeight(35);
			
			scrollPane.setViewportView(table);
			
			if (rs != null)
				rs.close();
			if (read_data != null)
				read_data.close();

			if (conn != null)
				conn.close();

			System.out.println("성공");

		} catch (SQLException e1) {
			// e1.printStackTrace();
			System.out.println(e1.toString());
			// new SignUpFailWindow(e1);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("매출관리", new SalesManagementPage());
		MainPage.main_cards.show(MainPage.main_page_panel, "매출관리");
		MainPage.userToggle = "매출관리";

	}

}
