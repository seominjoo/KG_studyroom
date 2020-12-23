package studyroom.user.usermode;

import javax.swing.JPanel;

import studyroom.MainPage;
import studyroom.design.Style;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayOrWeek extends JPanel {

	public DayOrWeek() {

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

		daily_btn.addActionListener(new ActionListener() { //���ϱ� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("���ϱǰ���ǥ(�¼�)", new WhatHour());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���ϱǰ���ǥ(�¼�)");
				MainPage.userToggle = "���ϱǰ���ǥ(�¼�)";
			}
		}); 

		weekly_btn.addActionListener(new ActionListener() { //����� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("����ǰ���ǥ", new WhatWeek());
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