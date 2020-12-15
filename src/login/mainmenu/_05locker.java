package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.page.MainPage;


import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.Action;
import java.awt.GridLayout;

public class _05locker extends JPanel { 
	
	LocalDateTime time_now = LocalDateTime.now(); 
	public _05locker() {
		new Style(this);
		setBounds(600, 150, 450, 300);
		setLayout(null);
		
		JButton check_locker_btn = new JButton("이용 가능한 사물함 보기"); 
		this.add(check_locker_btn);
		check_locker_btn.setBounds(140, 200, 300, 80);
		JButton btn_back = new JButton("이전 화면"); 
		this.add(btn_back);
		btn_back.setBounds(140, 300, 300, 80);
		new Style(check_locker_btn);
		new Style(btn_back);
		
		check_locker_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusMonths(1),25000,"1달 이용권 (사물함)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});
		
		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			}
		});
	}

}
