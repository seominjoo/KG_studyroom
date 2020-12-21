package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.signUp.SignUpPage;
import login.swingTools.Login_SwingTool;
import login.swingTools.State;
import login.window.MainBtn_Action;

public class MainPage extends JFrame implements Runnable {

	public static int x = new Conversion_image("image/배경화면(누런).jpg", 4).x; // 전체 프레임 가로 길이
	public static int y = new Conversion_image("image/배경화면(누런).jpg", 4).y; // 전체 프레임 세로 길이
	public static int w = 683; // 메인 패널 가로 길이(width)
	public static int h = 562; // 메인 패널 세로 길이(height)
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;
	public static JPanel updateTable;
	public static CardLayout statecard;
	public static JLabel background;
	public static JPanel logout;
	public static CardLayout logoutcard;
	public static JButton logoutbtn;
	public static JButton empty;
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
		fram_panel.setBounds(0, 0, x, y);// x=1000, y=562
		// 배경 이미지
		background = new JLabel(new Conversion_image("image/배경화면(누런).jpg", 4).imageicon_smooth);
		background.setOpaque(false);
		background.setBounds(0, 0, x, y);// x=1000, y=562

		// 메인 카드페이지 패널
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(x - w, 0, w, h); // w=683, h=562

		// 사용자메뉴 카드페이지 패널
		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(x - w, 0, w, h); // w=683, h=562

		// 좌석현황 패널
		updateTable = new JPanel();
		new Style(updateTable);
		statecard = new CardLayout();
		updateTable.setLayout(statecard);
		updateTable.setBounds(18, 190, 280, 50);
		updateTable.add(new State());

		// 로그아웃 패널
		logout = new JPanel();
		new Style(logout);
		logoutcard = new CardLayout();
		logout.setLayout(logoutcard);
		logout.setBounds(x - 107, 5, 100, 30); // x=1000
		// 빈화면
		empty = new JButton();
		new Style(empty);
		empty.setBounds(0, 0, 100, 30);
		logout.add("1", empty);
		// 로그아웃버튼
		logoutbtn = new JButton("로그아웃");
		new Style(logoutbtn);
		logoutbtn.setBounds(0, 0, 90, 30);
		logoutbtn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		logoutbtn.addActionListener(new MainBtn_Action(logoutbtn));
		logout.add("2", logoutbtn);

		MainPage.updateTable.add(new State());
		MainPage.statecard.next(MainPage.updateTable);

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
//      user_page_panel.add("예약페이지", new _08reservation(ss,price,seat_type));
//      user_page_panel.add("자리페이지", new _06move_selectSeat());
//		user_page_panel.add("결제페이지", new _09payment(ss, price, seat_type));
//      user_page_panel.add("결제알림창", new _10paycash(ss));

		// 영수증에 확인 버튼(or 입장바로가기 or로그아웃off)
//      user_page_panel.add("영수증", new _11receipt(ss, price));

		JButton changeUser = new JButton(new Conversion_image("image/관리자.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("관리자버튼");
		changeUser.setBounds(5, 5, 40, 40);
		changeUser.addActionListener(new MainBtn_Action(changeUser));

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

		background.add(logout);
		background.add(changeUser);
		background.add(clock);
		background.add(updateTable);

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