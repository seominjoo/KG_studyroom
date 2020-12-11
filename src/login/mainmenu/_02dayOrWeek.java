package login.mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.window.UserBtn_Action;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class _02dayOrWeek extends JPanel {


	public _02dayOrWeek() {
		 
		new Style(this);
		setBounds(600, 150, 450, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 3, 0, 0));

		JButton daily_btn = new JButton("���� �̿��"); 
		this.add(daily_btn);

		JButton weekly_btn = new JButton("���� �̿��"); 
		this.add(weekly_btn);

		JButton back_btn = new JButton("���� ȭ��"); 
		this.add(back_btn);

		
		daily_btn.addActionListener(new UserBtn_Action(daily_btn)); //���� ������(�����̿��)

		weekly_btn.addActionListener(new UserBtn_Action(daily_btn)); //���� ������(�����̿��)

		back_btn.addActionListener(new UserBtn_Action(daily_btn)); //���� ������

	} 
} 