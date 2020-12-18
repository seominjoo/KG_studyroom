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

public class _03whatWeek extends JPanel {

	LocalDateTime time_now = LocalDateTime.now();

	public _03whatWeek() {
		setLayout(null);
		new Style(this);
	 
		//setBounds(600, 150, 450, 470);
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
 

		JButton btn_2wk = new JButton(_03whatHour.pass_price.get(12)+"("+_03whatHour.pass_price.get(13)+")"); 
		new Style(btn_2wk);
		btn_2wk.setBounds(150, 168, 250, 48); 
		this.add(btn_2wk);

		JButton btn_4wk = new JButton(_03whatHour.pass_price.get(14)+"("+_03whatHour.pass_price.get(15)+")"); 
		new Style(btn_4wk);
		btn_4wk.setBounds(150, 233, 250, 48); 
		this.add(btn_4wk);

		JButton btn_8wk = new JButton(_03whatHour.pass_price.get(16)+"("+_03whatHour.pass_price.get(17)+")");  
		new Style(btn_8wk);
		btn_8wk.setBounds(150, 303, 250, 48);
		this.add(btn_8wk);

		JButton btn_back = new JButton("이전 화면"); 
		new Style(btn_back);
		btn_back.setBounds(150, 373, 250, 48);
		this.add(btn_back);

		btn_2wk.addActionListener(new ActionListener() { //다음 페이지(2주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(2), 
						Integer.parseInt(_03whatHour.pass_price.get(13)), _03whatHour.pass_price.get(12)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_4wk.addActionListener(new ActionListener() { //다음 페이지(4주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(4), 
						Integer.parseInt(_03whatHour.pass_price.get(15)), _03whatHour.pass_price.get(14)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_8wk.addActionListener(new ActionListener() { //다음 페이지(8주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(8), 
						Integer.parseInt(_03whatHour.pass_price.get(17)), _03whatHour.pass_price.get(16)));
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
