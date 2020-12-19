package login.mainmenu;

import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class _02dayRoom extends JPanel {

	public _02dayRoom() {

		new Style(this);
		setBounds(600, 150, 450, 300);
		this.setLayout(null);
		
		JButton daily_btn = new JButton("일일 이용권"); 
		this.add(daily_btn);
		new Style(daily_btn); 
		daily_btn.setBounds(140, 200, 300, 80);
		
		JButton back_btn = new JButton("이전 화면"); 
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(140, 300, 300, 80);

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