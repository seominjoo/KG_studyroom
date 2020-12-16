package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._00main;
import login.mainmenu._02dayOrWeek;
import login.mainmenu._02dayRoom;
import login.mainmenu._03whatHour;
import login.mainmenu._03whatHourRoom;
import login.mainmenu._03whatWeek;
import login.mainmenu._05locker;
import login.mainmenu._06move;
import login.mainmenu._06move_selectSeat;
import login.mainmenu._07in_selectSeat;
import login.mainmenu._07out;
import login.mainmenu._08reservation;
import login.mainmenu._09payment;
import login.mainmenu._10paycash;
import login.signUp.SignUpPage;
import login.swingTools.Login_SwingTool;
import login.window.MainBtn_Action;

public class MainPage extends JFrame implements Runnable {

	public static int x = new Conversion_image("image/배경화면(누런).jpg", 4).x;
	public static int y = new Conversion_image("image/배경화면(누런).jpg", 4).y;
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;
	public static int count_seat;
	public static int count_room;
	public static int count_locker;
	public static JLabel background;
	public static JTable table;
	Thread thread;
	JLabel clock;

	public static String userToggle;

	// 예약하기 필요한 매개변수
	public static LocalDateTime ss;
	public static int price;
	public static String seat_type;

	public MainPage() {

		userToggle = "메인";
		// 배경화면
		JPanel fram_panel = new JPanel();
		new Style(fram_panel);
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		background = new JLabel(new Conversion_image("image/배경화면(누런).jpg", 4).imageicon_smooth);
		background.setOpaque(false);
		background.setBounds(0, 0, x, y);

		// 낙엽쪽 카드페이지 패널
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(316, 0, 683, 562);

		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(316, 0, 683, 562);

		JPanel main = new JPanel(new BorderLayout());
		new Style(main);
		JButton touch = new JButton("<html>터치를 하여<br/>이용해주세요</html>");
		new Style(touch);
		touch.setBorder(null);
		touch.addActionListener(new MainBtn_Action(touch));
		main.add(touch);

		// 메인 페이지 추가 작업
		main_page_panel.add("메인", main);
		main_page_panel.add("로그인", new LoginPage());
		main_page_panel.add("사용자메뉴", user_page_panel);
		main_page_panel.add("관리자", new AdminPage());
		main_page_panel.add("관리자메뉴", new AdminMenuPage());
		main_page_panel.add("회원가입", new SignUpPage());
		main_page_panel.add("비번찾기", new FindPasswordPageUser());

		// 사용자 메뉴 페이지 추가 작업
//      user_page_panel.add("메인메뉴", new _00main()); // 메뉴페이지
//      user_page_panel.add("이용권구매", new _01start()); // 이용권구매 페이지
//      user_page_panel.add("좌석이용권", new _02dayOrWeek()); // 좌석 이용권 페이지
//      user_page_panel.add("룸이용권", new _02dayRoom());
//      user_page_panel.add("당일권가격표(좌석)", new _03whatHour());
//      user_page_panel.add("정기권가격표", new _03whatWeek());
//      user_page_panel.add("당일권가격표(룸)", new _03whatHourRoom());
//      user_page_panel.add("사물함이용권", new _05locker());
//      user_page_panel.add("자리이동", new _06move());
//      user_page_panel.add("입실페이지", new _07in_seletSeat());
//      user_page_panel.add("퇴실페이지", new _07out());
//      //user_page_panel.add("예약페이지", new _08reservation(ss,price,seat_type));
//      user_page_panel.add("자리페이지", new _06move_selectSeat());
		// user_page_panel.add("결제페이지", new _09payment(ss, price, seat_type));
//      user_page_panel.add("결제알림창", new _10paycash(ss));

		// 영수증에 확인 버튼(or 입장바로가기 or로그아웃off)
//      user_page_panel.add("영수증", new _11receipt(ss, price));

		JButton changeUser = new JButton(new Conversion_image("image/관리자.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("관리자버튼");
		changeUser.setBounds(5, 5, 40, 40);
		changeUser.addActionListener(new MainBtn_Action(changeUser));

		JButton logout = new JButton("로그아웃");
		new Style(logout);

		logout.setBounds(890, 0, 100, 30);
		logout.addActionListener(new MainBtn_Action(logout));

		// 현재 시간
		clock = new JLabel();
		new Style(clock);
		clock.setForeground(Color.decode("#dec5ae"));
		clock.setHorizontalAlignment(JLabel.CENTER);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		clock.setBounds(8, 150, 300, 30);

		// 사용중인 좌석 수 확인
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
			String sql4 = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstmt = conn.prepareStatement(sql4);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sn = rs.getInt("seat_number");
				if (sn <= 20) {
					count_seat++;
				} else if (sn >= 101) {
					count_room++;
				}
			}

			// 사용중인 사물함 수 확인
			String sql5 = "select locker_number from locker where locker_statement='사용 중'";
			pstmt = conn.prepareStatement(sql5);
			ResultSet rs5 = pstmt.executeQuery();

			while (rs.next()) {
				int sn = rs.getInt("locker_number");
				count_locker++;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		// 스터디룸 상황표
		String header[] = { "1인석", "스터디룸", "사물함" };
		String contents[][] = { { "<html>사용중 1인석<br/>&emsp;&emsp;" + Integer.toString(count_seat) + " / 20",
				"<html>사용중 스터디룸<br/>&emsp;&emsp;&emsp;" + Integer.toString(count_room) + " / 4",
				"<html>사용중 사물함<br/>&emsp;&emsp;" + Integer.toString(count_locker) + " / 20", } };

		DefaultTableModel model = new DefaultTableModel(contents, header);
		MainPage.table = new JTable(model);

		MainPage.table.setBounds(18, 190, 280, 50);
		MainPage.table.setRowHeight(50);

		// 테두리
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		MainPage.table.setBorder(border);

		// 상황표 글씨 중앙 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		MainPage.table.getColumn("1인석").setCellRenderer(celAlignCenter);
		MainPage.table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
		MainPage.table.getColumn("사물함").setCellRenderer(celAlignCenter);

		// 디자인 적용
		new Style(celAlignCenter);
		celAlignCenter.setForeground(Color.decode("#dec5ae"));
		new Style(MainPage.table);
		MainPage.table.setGridColor(Color.decode("#dec5ae")); // 테이블 내부 선 색
		MainPage.table.setBorder(BorderFactory.createLineBorder(Color.decode("#dec5ae")));

		background.add(logout);
		background.add(changeUser);
		background.add(clock);
		background.add(table);

		fram_panel.add(background);

		Login_SwingTool.initFrame(this);
		add(main_page_panel);
		add(fram_panel);
	}

	@Override
	public void run() {
		while (true) {
			Calendar cal = Calendar.getInstance();
			String now = cal.get(Calendar.YEAR) + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DATE)
					+ "일 " + cal.get(Calendar.HOUR) + "시 " + cal.get(Calendar.MINUTE) + "분 " + cal.get(Calendar.SECOND)
					+ "초 ";
			clock.setText(now);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MainPage();
	}
}