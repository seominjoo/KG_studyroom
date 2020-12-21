package login.mainmenu;

import javax.swing.JPanel;
import javax.swing.JButton;

import login.design.Style;
import login.page.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class _03whatWeek extends JPanel {

	LocalDateTime time_now = LocalDateTime.now();

	public _03whatWeek() {
		
		setLayout(null);
		new Style(this);

		//�޴� ��ư 4��
		JButton btn_2wk = new JButton(_01start.pass_price.get(12)+"("+_01start.pass_price.get(13)+")"); 
		new Style(btn_2wk);
		btn_2wk.setBounds(MainPage.w/2-230, MainPage.h/2-160, 220, 130);
		this.add(btn_2wk);

		JButton btn_4wk = new JButton(_01start.pass_price.get(14)+"("+_01start.pass_price.get(15)+")"); 
		new Style(btn_4wk);
		btn_4wk.setBounds(MainPage.w/2+10, MainPage.h/2-160, 220, 130);
		this.add(btn_4wk);

		JButton btn_8wk = new JButton(_01start.pass_price.get(16)+"("+_01start.pass_price.get(17)+")");  
		new Style(btn_8wk);
		btn_8wk.setBounds(MainPage.w/2-230, MainPage.h/2-10, 220, 130);
		this.add(btn_8wk);

		JButton btn_back = new JButton("���� ȭ��"); 
		new Style(btn_back);
		btn_back.setBounds(MainPage.w/2+10, MainPage.h/2-10, 220, 130);
		this.add(btn_back);

		btn_2wk.addActionListener(new ActionListener() { //���� ������(2�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(2), 
						Integer.parseInt(_01start.pass_price.get(13)), _01start.pass_price.get(12)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_4wk.addActionListener(new ActionListener() { //���� ������(4�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(4), 
						Integer.parseInt(_01start.pass_price.get(15)),_01start.pass_price.get(14)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_8wk.addActionListener(new ActionListener() { //���� ������(8�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(8), 
						Integer.parseInt(_01start.pass_price.get(17)),_01start.pass_price.get(16)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			}
		});
	} 
}
