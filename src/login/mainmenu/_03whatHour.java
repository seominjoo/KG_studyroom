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

public class _03whatHour extends JPanel {

	public static LocalDateTime time_now = LocalDateTime.now(); 
	 
	public _03whatHour() {
		setLayout(null);
		new Style(this);
		
		JLabel label01 = new JLabel("����ǥ");
		new Style(label01);
		label01.setBounds(188,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		
		//setBounds(600, 150, 450, 350);
		
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));

		 
		this.add(label01);
		
		JButton btn_2hr= new JButton("2�ð� (3,000��)");
		new Style(btn_2hr);
		btn_2hr.setBounds(40, 138, 188, 48); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton("4�ð� (2,000��)");
		new Style(btn_4hr);
		btn_4hr.setBounds(253, 138, 193, 48); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton("6�ð� (2,000��)");
		new Style(btn_6hr);
		btn_6hr.setBounds(40, 203, 188, 48); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton("8�ð� (2,000��)");
		new Style(btn_8hr);
		btn_8hr.setBounds(253, 203, 193, 48); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton("12�ð� (2,000��)");
		new Style(btn_12hr);
		btn_12hr.setBounds(40, 273, 188, 48);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("���� ȭ��");
		new Style(btn_back);
		btn_back.setBounds(253, 273, 193, 48);
		this.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new ActionListener() { //���� ������(2�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(2), 3000, "2�ð� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //���� ������(4�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(4), 4500, "4�ð� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //���� ������(6�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(6), 6000, "6�ð� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //���� ������(8�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(8), 7500, "8�ð� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //���� ������(12�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(12), 10000, "12�ð� �̿�� (1�μ�)"));
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

