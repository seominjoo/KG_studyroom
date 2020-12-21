package login.mainmenu;

import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class _02dayOrWeek extends JPanel {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		_02dayOrWeek main = new _02dayOrWeek();
		frame.setBounds(0,0,683,562);
		frame.add(main);
		frame.setVisible(true);
	}

	public _02dayOrWeek() {
		 
		new Style(this);
		this.setLayout(null);

		//�޴� ��ư 3��
		JButton daily_btn = new JButton("���� �̿��"); 
		this.add(daily_btn);
		daily_btn.setBounds(MainPage.w/2-150, MainPage.h/2-190, 300, 100);

		JButton weekly_btn = new JButton("���� �̿��"); 
		this.add(weekly_btn);
		weekly_btn.setBounds(MainPage.w/2-150, MainPage.h/2-80, 300, 100);
		
		JButton back_btn = new JButton("���� ȭ��"); 
		this.add(back_btn);
		back_btn.setBounds(MainPage.w/2-150, MainPage.h/2+30, 300, 100);
		
		new Style(daily_btn);
		new Style(weekly_btn);
		new Style(back_btn);
		
		daily_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
 				MainPage.user_page_panel.add("���ϱǰ���ǥ(�¼�)", new _03whatHour());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���ϱǰ���ǥ(�¼�)");
				MainPage.userToggle = "���ϱǰ���ǥ(�¼�)";
			}
		}); 

		weekly_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("����ǰ���ǥ", new _03whatWeek());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����ǰ���ǥ");
				MainPage.userToggle = "����ǰ���ǥ";
			}
		});

		back_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
 
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			}
		}); 
	} 
} 