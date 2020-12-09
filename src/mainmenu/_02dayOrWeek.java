package mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class _02dayOrWeek extends JFrame {

	private JPanel contentPane;

	public _02dayOrWeek() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));

		JButton daily_btn = new JButton("일일 이용권"); 
		contentPane.add(daily_btn);

		JButton weekly_btn = new JButton("정기 이용권"); 
		contentPane.add(weekly_btn);

		JButton back_btn = new JButton("이전 화면"); 
		contentPane.add(back_btn);

		daily_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_03whatHour frame = new _03whatHour();
				frame.setVisible(true);
			}
		}); 

		weekly_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_04whatWeek frame = new _04whatWeek();
				frame.setVisible(true);
			}
		});

		back_btn.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_01start frame = new _01start();
				frame.setVisible(true);
			}
		}); 

	} 
	public static void main(String[] args) {
		_02dayOrWeek frame = new _02dayOrWeek();
		frame.setVisible(true);
	}
} 