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

public class _03whatHourRoom extends JPanel {

	LocalDateTime time_now = LocalDateTime.now(); 
	 
	public _03whatHourRoom() {
		setLayout(null);
		new Style(this);
		
		JLabel label01 = new JLabel("����ǥ");
		new Style(label01);
		label01.setBounds(188,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		//setBounds(600, 150, 450, 350);
		
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.add(label01);
		
		JButton btn_2hr= new JButton(_01start.pass_price.get(18)+"("+_01start.pass_price.get(19)+")");
		new Style(btn_2hr);
		btn_2hr.setBounds(90, 130, 188, 80); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton(_01start.pass_price.get(20)+"("+_01start.pass_price.get(21)+")");
		new Style(btn_4hr);
		btn_4hr.setBounds(303, 130, 193, 80); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton(_01start.pass_price.get(22)+"("+_01start.pass_price.get(23)+")");
		new Style(btn_6hr);
		btn_6hr.setBounds(90, 230, 188, 80); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton(_01start.pass_price.get(24)+"("+_01start.pass_price.get(25)+")");
		new Style(btn_8hr);
		btn_8hr.setBounds(303, 230, 193, 80); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton(_01start.pass_price.get(26)+"("+_01start.pass_price.get(27)+")");
		new Style(btn_12hr);
		btn_12hr.setBounds(90, 330, 188, 80);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("���� ȭ��");
		new Style(btn_back);
		btn_back.setBounds(303, 330, 193, 80);
		this.add(btn_back, btn_back);
		
		btn_2hr.addActionListener(new ActionListener() { //���� ������(2�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(2), 
						Integer.parseInt(_01start.pass_price.get(19)), _01start.pass_price.get(18)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //���� ������(4�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(4), 
						Integer.parseInt(_01start.pass_price.get(21)), _01start.pass_price.get(20)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //���� ������(6�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(6), 
						Integer.parseInt(_01start.pass_price.get(23)), _01start.pass_price.get(22)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //���� ������(8�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(8), 
						Integer.parseInt(_01start.pass_price.get(25)), _01start.pass_price.get(24)));
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //���� ������(12�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("����������",new _08reservation(time_now.plusHours(12), 
						Integer.parseInt(_01start.pass_price.get(27)), _01start.pass_price.get(26)));
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

