package studyroom.user.usermode;

import javax.swing.JPanel;

import studyroom.MainPage;
import studyroom.design.Style;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayRoom extends JPanel {

	public DayRoom() {

		new Style(this);
		this.setLayout(null);

		//�޴� ��ư 2��
		JButton daily_btn = new JButton("���� �̿��"); 
		this.add(daily_btn);
		new Style(daily_btn); 
		daily_btn.setBounds(MainPage.w/2-150, MainPage.h/2-140, 300, 100);

		JButton back_btn = new JButton("���� ȭ��"); 
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(MainPage.w/2-150, MainPage.h/2-10, 300, 100);

		daily_btn.addActionListener(new ActionListener() { //���ϱ� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add("���ϱǰ���ǥ(��)", new WhatHourRoom());
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���ϱǰ���ǥ(��)");
				MainPage.userToggle = "���ϱǰ���ǥ(��)";
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