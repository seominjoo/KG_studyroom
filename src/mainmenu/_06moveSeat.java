package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class _06moveSeat extends JFrame {

	
	private JPanel contentPane;
	 
	public _06moveSeat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton check_seat_btn = new JButton("���� �¼� ����"); 
		contentPane.add(check_seat_btn);
		
		JButton btn_back = new JButton("���� ȭ��"); 
		contentPane.add(btn_back);
		
		check_seat_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				_06moveSeat2 frame = new _06moveSeat2();
				frame.setVisible(true);
			}
		});
		
		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				_01start frame = new _01start();
				frame.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		_06moveSeat frame = new _06moveSeat();
		frame.setVisible(true);
	}
}
