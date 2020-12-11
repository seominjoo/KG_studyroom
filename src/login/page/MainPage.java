package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._00main;
import login.mainmenu._01start;
import login.mainmenu._02dayOrWeek;
import login.mainmenu._02dayRoom;
import login.mainmenu._03whatHour;
import login.mainmenu._03whatHourRoom;
import login.mainmenu._03whatWeek;
import login.mainmenu._05locker;
import login.mainmenu._06move;
import login.mainmenu._07in_seletSeat;
import login.mainmenu._07out;
import login.mainmenu._08reservation;
import login.signUp.SignUpPage;
import login.window.MainBtn_Action;
import login.window.Login_SwingTool;

public class MainPage extends JFrame {

	int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	int y = new Conversion_image("image/로그인화면.jpg", 5).y;
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;

	public static String userToggle;
	
	// 예약하기 필요한 매개변수
	public static LocalDateTime ss;
	public static int price;
	public static String seat_type;
	
	public MainPage() {
		userToggle = "메인";
		// 배경화면
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		JLabel background = new JLabel(new Conversion_image("image/로그인화면.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);

		// 낙엽쪽 카드페이지 패널
		main_page_panel = new JPanel();
		main_cards = new CardLayout();
		main_page_panel.setLayout(main_cards);
		new Style(main_page_panel);
		main_page_panel.setBounds(1577 / 5, 0, 2423 / 5, 2250 / 5);
		
		user_page_panel = new JPanel();
		user_cards = new CardLayout();
		user_page_panel.setLayout(user_cards);
		new Style(user_page_panel);
		user_page_panel.setBounds(1577 / 5, 0, 2423 / 5, 2250 / 5);
		
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
		user_page_panel.add("메인메뉴", new _00main()); // 메뉴페이지
		user_page_panel.add("이용권구매", new _01start()); // 이용권구매 페이지
		user_page_panel.add("좌석이용권", new _02dayOrWeek()); // 좌석 이용권 페이지
		user_page_panel.add("룸이용권", new _02dayRoom());
		user_page_panel.add("일일권가격표(좌석)", new _03whatHour());
		user_page_panel.add("정기권가격표", new _03whatWeek());
		user_page_panel.add("일일권가격표(룸)", new _03whatHourRoom());
		user_page_panel.add("사물함이용권", new _05locker());
		user_page_panel.add("자리이동", new _06move());
		user_page_panel.add("입실하기", new _07in_seletSeat());
		user_page_panel.add("퇴실하기", new _07out());
		user_page_panel.add("예약페이지", new _08reservation(ss, price, seat_type));
		
		// 미처리
//		user_page_panel.add("좌석페이지", new _06move_selectSeat());
//		user_page_panel.add("결제페이지", new _09payment());
//		user_page_panel.add("결제알림창", new _10paycash());
		// 영수증에 확인 버튼(or 입장바로가기 or로그아웃off)
//		user_page_panel.add("영수증", new _11receipt());
		
		

		JButton changeUser = new JButton(new Conversion_image("image/전원.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("관리자버튼");
		changeUser.setBounds(0, 0, 30, 30);
		changeUser.addActionListener(new MainBtn_Action(changeUser));
		background.add(changeUser);
		fram_panel.add(background);

		Login_SwingTool.initFrame(this);

		add(main_page_panel);
		add(fram_panel);
		Login_SwingTool.initFrame(this);
	}

	public static void main(String[] args) {
		new MainPage();
	}
}
