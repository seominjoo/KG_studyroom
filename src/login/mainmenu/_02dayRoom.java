package login.mainmenu;

import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class _02dayRoom extends JPanel {
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		_02dayRoom main = new _02dayRoom();
//		frame.setBounds(0,0,683,562);
//		frame.add(main);
//		frame.setVisible(true);
//	}

	public _02dayRoom() {

		new Style(this);
		this.setLayout(null);
		
		//메뉴 버튼 2개
		JButton daily_btn = new JButton("당일 이용권"); 
		this.add(daily_btn);
		new Style(daily_btn); 
		daily_btn.setBounds(MainPage.w/2-150, MainPage.h/2-140, 300, 100);
		
		JButton back_btn = new JButton("이전 화면"); 
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(MainPage.w/2-150, MainPage.h/2-10, 300, 100);

		daily_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("당일권가격표(룸)", new _03whatHourRoom());
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "당일권가격표(룸)");
				MainPage.userToggle = "당일권가격표(룸)";
			}
		}); 

		back_btn.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) { 
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			}
		}); 
	} 
} 