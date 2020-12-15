package login.mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.page.MainPage;


import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class _02dayOrWeek extends JPanel {


	public _02dayOrWeek() {
		 
		new Style(this);
		setBounds(600, 150, 450, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 3, 0, 0));

		JButton daily_btn = new JButton("당일 이용권"); 
		this.add(daily_btn);

		JButton weekly_btn = new JButton("정기 이용권"); 
		this.add(weekly_btn);

		JButton back_btn = new JButton("이전 화면"); 
		this.add(back_btn);

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