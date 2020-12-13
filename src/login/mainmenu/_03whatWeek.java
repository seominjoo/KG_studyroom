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

public class _03whatWeek extends JPanel {

	LocalDateTime time_now = LocalDateTime.now();

	public _03whatWeek() {
		setLayout(null);
		new Style(this);
		
		JLabel label01 = new JLabel("����ǥ");
		new Style(label01);
		label01.setBounds(188,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		
		//setBounds(600, 150, 450, 470);
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.add(label01);

		JButton btn_2wk = new JButton("2�� (90,000��)"); 
		new Style(btn_2wk);
		btn_2wk.setBounds(40, 138, 188, 48); 
		this.add(btn_2wk);

		JButton btn_4wk = new JButton("4�� (130,000��)"); 
		new Style(btn_4wk);
		btn_4wk.setBounds(253, 138, 193, 48); 
		this.add(btn_4wk);

		JButton btn_8wk = new JButton("8�� (250,000��)");  
		new Style(btn_8wk);
		btn_8wk.setBounds(40, 273, 188, 48);
		this.add(btn_8wk);

		JButton btn_back = new JButton("���� ȭ��"); 
		new Style(btn_back);
		btn_back.setBounds(253, 273, 193, 48);
		this.add(btn_back);

		btn_2wk.addActionListener(new ActionListener() { //���� ������(2�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(2), 90000, "2�� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_4wk.addActionListener(new ActionListener() { //���� ������(4�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(4), 130000, "4�� �̿�� (1�μ�)"));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_8wk.addActionListener(new ActionListener() { //���� ������(8�� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(8), 250000, "8�� �̿�� (1�μ�)"));
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
