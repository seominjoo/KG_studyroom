package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.Action;
import java.awt.GridLayout;

public class _05locker extends JFrame { 
	
	private JPanel contentPane; 
	LocalDateTime time_now = LocalDateTime.now(); 
	public _05locker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton check_locker_btn = new JButton("�̿� ������ �繰�� ����"); 
		contentPane.add(check_locker_btn);
		
		JButton btn_back = new JButton("���� ȭ��"); 
		contentPane.add(btn_back);
		
		check_locker_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new _08reservation(time_now.plusMonths(1),25000,"1�� �̿�� (�繰��)"); 
			}
		});
		
		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_01start frame = new _01start();
				frame.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		_05locker frame = new _05locker();
		frame.setVisible(true);
	}
}
