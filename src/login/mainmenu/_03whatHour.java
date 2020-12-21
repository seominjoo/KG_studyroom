package login.mainmenu;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import login.design.Style;
import login.page.MainPage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class _03whatHour extends JPanel {

	public static LocalDateTime time_now = LocalDateTime.now(); 

	public _03whatHour() {
		
		setLayout(null);
		new Style(this);
		 
		JButton btn_2hr= new JButton(_01start.pass_price.get(2)+"("+_01start.pass_price.get(3)+")");
		new Style(btn_2hr);
		btn_2hr.setBounds(MainPage.w/2-210, MainPage.h/2-150, 200, 80); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton(_01start.pass_price.get(4)+"("+_01start.pass_price.get(5)+")");
		new Style(btn_4hr);
		btn_4hr.setBounds(MainPage.w/2+10, MainPage.h/2-150, 200, 80); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton(_01start.pass_price.get(6)+"("+_01start.pass_price.get(7)+")");
		new Style(btn_6hr);
		btn_6hr.setBounds(MainPage.w/2-210, MainPage.h/2-60, 200, 80); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton(_01start.pass_price.get(8)+"("+_01start.pass_price.get(9)+")");
		new Style(btn_8hr);
		btn_8hr.setBounds(MainPage.w/2+10, MainPage.h/2-60, 200, 80); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton(_01start.pass_price.get(10)+"("+_01start.pass_price.get(11)+")");
		new Style(btn_12hr);
		btn_12hr.setBounds(MainPage.w/2-210, MainPage.h/2+30, 200, 80);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("이전 화면");
		new Style(btn_back);
		btn_back.setBounds(MainPage.w/2+10, MainPage.h/2+30, 200, 80);
		this.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new ActionListener() { //다음 페이지(2시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(2), Integer.parseInt(_01start.pass_price.get(3)), _01start.pass_price.get(2)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //다음 페이지(4시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(4), Integer.parseInt(_01start.pass_price.get(5)), _01start.pass_price.get(4)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //다음 페이지(6시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(6),Integer.parseInt(_01start.pass_price.get(7)), _01start.pass_price.get(6)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //다음 페이지(8시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(8), Integer.parseInt(_01start.pass_price.get(9)), _01start.pass_price.get(8)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //다음 페이지(12시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(12), Integer.parseInt(_01start.pass_price.get(11)), _01start.pass_price.get(10)));
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

