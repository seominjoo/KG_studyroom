package login.mainmenu;

import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class _02dayOrWeek extends JPanel {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		_02dayOrWeek main = new _02dayOrWeek();
		frame.setBounds(0,0,683,562);
		frame.add(main);
		frame.setVisible(true);
	}

	public _02dayOrWeek() {
		 
		new Style(this);
		this.setLayout(null);

		//메뉴 버튼 3개
		JButton daily_btn = new JButton("당일 이용권"); 
		this.add(daily_btn);
		daily_btn.setBounds(MainPage.w/2-150, MainPage.h/2-190, 300, 100);

		JButton weekly_btn = new JButton("정기 이용권"); 
		this.add(weekly_btn);
		weekly_btn.setBounds(MainPage.w/2-150, MainPage.h/2-80, 300, 100);
		
		JButton back_btn = new JButton("이전 화면"); 
		this.add(back_btn);
		back_btn.setBounds(MainPage.w/2-150, MainPage.h/2+30, 300, 100);
		
		new Style(daily_btn);
		new Style(weekly_btn);
		new Style(back_btn);
		
		daily_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
 				MainPage.user_page_panel.add("당일권가격표(좌석)", new _03whatHour());
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "당일권가격표(좌석)");
				MainPage.userToggle = "당일권가격표(좌석)";
			}
		}); 

		weekly_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("정기권가격표", new _03whatWeek());
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "정기권가격표");
				MainPage.userToggle = "정기권가격표";
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