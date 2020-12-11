package login.mainmenu;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import login.window.UserBtn_Action;

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
		 
		
		JLabel label01 = new JLabel("<html>&emsp;&emsp;&emsp; &emsp;����ǥ");
		label01.setBounds(0,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		
		setBounds(600, 150, 450, 350);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		 
		this.add(label01);
		
		JButton btn_2hr= new JButton("2�ð� (3,000��)");
		btn_2hr.setBounds(5, 143, 208, 54); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton("4�ð� (2,000��)");
		btn_4hr.setBounds(218, 143, 213, 54); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton("6�ð� (2,000��)");
		btn_6hr.setBounds(5, 198, 208, 54); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton("8�ð� (2,000��)");
		btn_8hr.setBounds(218, 198, 213, 54); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton("12�ð� (2,000��)");
		btn_12hr.setBounds(5, 253, 208, 54);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("���� ȭ��");
		btn_back.setBounds(218, 253, 213, 54);
		this.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new UserBtn_Action(btn_2hr)); //���� ������(2�ð� �̿��)

		btn_4hr.addActionListener(new UserBtn_Action(btn_4hr)); //���� ������(4�ð� �̿��)
		
		btn_6hr.addActionListener(new UserBtn_Action(btn_6hr)); //���� ������(6�ð� �̿��)
			
		btn_8hr.addActionListener(new UserBtn_Action(btn_8hr)); //���� ������(8�ð� �̿��)
			
		btn_12hr.addActionListener(new UserBtn_Action(btn_12hr)); //���� ������(12�ð� �̿��)	

		btn_back.addActionListener(new UserBtn_Action(btn_back)); //���� ������
	}
}

