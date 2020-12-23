package studyroom.user.usermode;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import studyroom.design.Style;
import studyroom.user.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class WhatHourRoom extends JPanel {

	LocalDateTime time_now = LocalDateTime.now(); 
	 
	public WhatHourRoom() {
		
		setLayout(null);
		new Style(this);
		
		//�޴���ư 6��
		JButton btn_2hr= new JButton(Start.pass_price.get(18)+"("+Start.pass_price.get(19)+")");
		new Style(btn_2hr);
		btn_2hr.setBounds(MainPage.w/2-235, MainPage.h/2-150, 220, 80); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton(Start.pass_price.get(20)+"("+Start.pass_price.get(21)+")");
		new Style(btn_4hr);
		btn_4hr.setBounds(MainPage.w/2+5, MainPage.h/2-150, 220, 80); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton(Start.pass_price.get(22)+"("+Start.pass_price.get(23)+")");
		new Style(btn_6hr);
		btn_6hr.setBounds(MainPage.w/2-235, MainPage.h/2-60, 220, 80); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton(Start.pass_price.get(24)+"("+Start.pass_price.get(25)+")");
		new Style(btn_8hr);
		btn_8hr.setBounds(MainPage.w/2+5, MainPage.h/2-60, 220, 80); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton(Start.pass_price.get(26)+"("+Start.pass_price.get(27)+")");
		new Style(btn_12hr);
		btn_12hr.setBounds(MainPage.w/2-235, MainPage.h/2+30, 220, 80);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("���� ȭ��");
		new Style(btn_back);
		btn_back.setBounds(MainPage.w/2+5, MainPage.h/2+30, 220, 80);
		this.add(btn_back, btn_back);
		
		btn_2hr.addActionListener(new ActionListener() { //���� ������(2�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusHours(2), 
						Integer.parseInt(Start.pass_price.get(19)), Start.pass_price.get(18)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //���� ������(4�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusHours(4), 
						Integer.parseInt(Start.pass_price.get(21)), Start.pass_price.get(20)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //���� ������(6�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusHours(6), 
						Integer.parseInt(Start.pass_price.get(23)), Start.pass_price.get(22)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //���� ������(8�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusHours(8), 
						Integer.parseInt(Start.pass_price.get(25)), Start.pass_price.get(24)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //���� ������(12�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new Reservation(time_now, time_now.plusHours(12), 
						Integer.parseInt(Start.pass_price.get(27)), Start.pass_price.get(26)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���̿��");
				MainPage.userToggle = "���̿��";
			}
		});
	}
}

