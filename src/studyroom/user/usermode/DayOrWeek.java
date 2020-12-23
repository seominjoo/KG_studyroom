package studyroom.user.usermode;

import javax.swing.JPanel;

import studyroom.MainPage;
import studyroom.design.Style;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayOrWeek extends JPanel {

	public DayOrWeek() {

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

		daily_btn.addActionListener(new ActionListener() { //일일권 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("당일권가격표(좌석)", new WhatHour());
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "당일권가격표(좌석)");
				MainPage.userToggle = "당일권가격표(좌석)";
			}
		}); 

		weekly_btn.addActionListener(new ActionListener() { //정기권 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("정기권가격표", new WhatWeek());
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