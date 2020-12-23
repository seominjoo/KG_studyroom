package studyroom.user.usermode;

import javax.swing.JPanel;

import studyroom.design.Style;
import studyroom.user.MainPage;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class Locker extends JPanel { 
	
	LocalDateTime time_now = LocalDateTime.now(); 
	
	public Locker() {
		
		new Style(this);
		setLayout(null);
		
		//�޴� ��ư 2��
		JButton check_locker_btn = new JButton(Start.pass_price.get(0)+"("+Start.pass_price.get(1)+")"); 
		this.add(check_locker_btn);
		new Style(check_locker_btn);
		check_locker_btn.setBounds(MainPage.w/2-150, MainPage.h/2-140, 300, 100);
		
		JButton btn_back = new JButton("���� ȭ��"); 
		this.add(btn_back);
		new Style(btn_back);
		btn_back.setBounds(MainPage.w/2-150, MainPage.h/2-10, 300, 100);
	
		check_locker_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusMonths(1),
						Integer.parseInt(Start.pass_price.get(1)), Start.pass_price.get(0)));
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
