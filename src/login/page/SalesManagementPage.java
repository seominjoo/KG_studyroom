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

import javax.swing.JButton;
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
	static JScrollPane scrollPane;
	//JTable table;
	
	public static JComboBox<String> year;
	public static JComboBox<String> month;
	public static JComboBox<String> day;
	
	JButton yearTotal;
	JButton monthTotal;
	JButton dayTotal;
	
	JButton back;
	static JLabel totalPayment;
	
	public SalesManagementPage() {
		setLayout(null);
		new Style(this);

//		title = new JLabel("매출 관리");
//		new Style(title);
//		title.setFont(new Font("맑은 고딕", Font.BOLD, 18));
//		title.setBounds(250, 10, 100, 100);
//		add(title);

		back = new JButton("이전");
		new Style(back);
		back.setBounds(410, 488, 100, 50);
		add(back);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//MainPage.main_page_panel.add("관리자메뉴", new AdminMenuPage());
				MainPage.main_cards.show(MainPage.main_page_panel, "관리자메뉴");
				MainPage.userToggle = "관리자메뉴";
			}
		});
		
		totalPayment = new JLabel();
		new Style(totalPayment);
		totalPayment.setBounds(100, 458, 200, 100);
		add(totalPayment);
		
		scrollPane = new JScrollPane();
		new Style(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBounds(50, 85, 500, 380);
		add(scrollPane);
		
		year = new JComboBox<String>(new ManagementDate().yearTable);
		year.setBounds(100, 30, 65, 30);
		add(year);
		new Style(year);
		year.setSelectedItem("2020");

		month = new JComboBox<String>(new ManagementDate().monthTable);
		month.setBounds(245, 30, 50, 30);
		add(month);
		new Style(month);

		day = new JComboBox<String>(new ManagementDate().dayTable);
		day.setBounds(375, 30, 50, 30);
		add(day);
		new Style(day);

		// 연도, 월 클릭
		year.addActionListener(new YearMonthClick("year", "매출관리"));
		month.addActionListener(new YearMonthClick("month", "매출관리"));

		yearTotal = new JButton("연매출");
		new Style(yearTotal);
		yearTotal.setBounds(175, 30, 50, 30);
		add(yearTotal);
		
		yearTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SalesDB("SELECT paid_time,seat_type,locker_type,pay_method,payment"
						+ " FROM payment_record where substr(paid_time,1,2) = ? order by paid_time", 1);
			}
		});
		
		monthTotal = new JButton("월매출");
		new Style(monthTotal);
		monthTotal.setBounds(305, 30, 50, 30);
		add(monthTotal);
		
		monthTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SalesDB("SELECT paid_time,seat_type,locker_type,pay_method,payment"
						+ " FROM payment_record where substr(paid_time,1,2) = ? " 
						+ "and substr(paid_time,4,2) = ? order by paid_time", 2);
			}
		});
		
		dayTotal = new JButton("일매출");
		new Style(dayTotal);
		dayTotal.setBounds(435, 30, 50, 30);
		add(dayTotal);

		dayTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SalesDB("SELECT paid_time,seat_type,locker_type,pay_method,payment"
						+ " FROM payment_record where substr(paid_time,1,2) = ? " 
						+ "and substr(paid_time,4,2) = ? "
						+ "and substr(paid_time,7,2) = ? order by paid_time", 3);
			}
		});
		
		
		new SalesDB("SELECT paid_time,seat_type,locker_type,pay_method,payment"
			+ " FROM payment_record order by paid_time", 0);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainPage.main_page_panel.add("매출관리", new SalesManagementPage());
		MainPage.main_cards.show(MainPage.main_page_panel, "매출관리");
		MainPage.userToggle = "매출관리";

	}

}
