package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.mainmenu._00main;
import login.signUp.SignUpPage;
import login.window.Main_ActionWindow;
import login.window.Login_SwingTool;

public class MainPage extends JFrame {

	int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	int y = new Conversion_image("image/로그인화면.jpg", 5).y;
	public static JPanel main_page_panel;
	public static CardLayout main_cards;
	public static JPanel user_page_panel;
	public static CardLayout user_cards;

	public static String userToggle;
	
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
		touch.addActionListener(new Main_ActionWindow(touch));
		main.add(touch);

		// 메인 페이지 추가 작업
		main_page_panel.add("메인", main);
		main_page_panel.add("로그인", new LoginPage());
		main_page_panel.add("사용자메뉴", new _00main());
		main_page_panel.add("관리자", new AdminPage());
		main_page_panel.add("관리자메뉴", new AdminMenuPage());
		main_page_panel.add("회원가입", new SignUpPage());
		main_page_panel.add("비번찾기", new FindPasswordPageUser());
		
		// 사용자 메뉴 페이지 추가 작업
//		user_page_panel.add("사용자메뉴", new _00main());

		JButton changeUser = new JButton(new Conversion_image("image/전원.png", 30, 30).imageicon_smooth);
		new Style(changeUser);
		changeUser.setText("관리자버튼");
		changeUser.setBounds(0, 0, 30, 30);
		changeUser.addActionListener(new Main_ActionWindow(changeUser));
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
