package login.mainmenu;

import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class _05locker extends JPanel { 
	
	LocalDateTime time_now = LocalDateTime.now(); 
	
	public _05locker() {
		
		new Style(this);
		setLayout(null);
		
		JButton check_locker_btn = new JButton(_01start.pass_price.get(0)+"("+_01start.pass_price.get(1)+")"); 
		this.add(check_locker_btn);
		new Style(check_locker_btn);
		check_locker_btn.setBounds(140, 200, 300, 80);
		
		JButton btn_back = new JButton("���� ȭ��"); 
		this.add(btn_back);
		new Style(btn_back);
		btn_back.setBounds(140, 300, 300, 80);
	
		check_locker_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusMonths(1),
						Integer.parseInt(_01start.pass_price.get(1)), _01start.pass_price.get(0)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});
		
		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			}
		});
	}
}
