package login.mainmenu;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.page.MainPage;


import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class _03whatHour extends JPanel {

	public static LocalDateTime time_now = LocalDateTime.now(); 
	 
	public _03whatHour() {
		setLayout(null);
		new Style(this);
		
		JLabel label01 = new JLabel("가격표");
		new Style(label01);
		label01.setBounds(188,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		
		//setBounds(600, 150, 450, 350);
		
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));

		 
		this.add(label01);
		
		JButton btn_2hr= new JButton("2시간 (3,000원)");
		new Style(btn_2hr);
		btn_2hr.setBounds(40, 138, 188, 48); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton("4시간 (2,000원)");
		new Style(btn_4hr);
		btn_4hr.setBounds(253, 138, 193, 48); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton("6시간 (2,000원)");
		new Style(btn_6hr);
		btn_6hr.setBounds(40, 203, 188, 48); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton("8시간 (2,000원)");
		new Style(btn_8hr);
		btn_8hr.setBounds(253, 203, 193, 48); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton("12시간 (2,000원)");
		new Style(btn_12hr);
		btn_12hr.setBounds(40, 273, 188, 48);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("이전 화면");
		new Style(btn_back);
		btn_back.setBounds(253, 273, 193, 48);
		this.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new ActionListener() { //다음 페이지(2시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(2), 3000, "2시간 이용권 (1인석)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //다음 페이지(4시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(4), 4500, "4시간 이용권 (1인석)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //다음 페이지(6시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(6), 6000, "6시간 이용권 (1인석)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //다음 페이지(8시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(8), 7500, "8시간 이용권 (1인석)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //다음 페이지(12시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(12), 10000, "12시간 이용권 (1인석)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			}
		});
	}
}

