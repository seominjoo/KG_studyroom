package login.mainmenu;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		JLabel label01 = new JLabel("<html>&emsp;&emsp;&emsp; &emsp;가격표");
		label01.setBounds(0,0,420,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		
		setBounds(600, 150, 450, 470);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		this.add(label01);

		JButton btn_2wk = new JButton("2주 (90,000원)");  
		btn_2wk.setBounds(5, 166, 208, 121);
		this.add(btn_2wk);

		JButton btn_4wk = new JButton("4주 (130,000원)"); 
		btn_4wk.setBounds(218, 166, 213, 121);
		this.add(btn_4wk);

		JButton btn_8wk = new JButton("8주 (250,000원)");  
		btn_8wk.setBounds(5, 297, 208, 126);
		this.add(btn_8wk);

		JButton btn_back = new JButton("이전 화면");  
		btn_back.setBounds(218, 297, 213, 126);
		this.add(btn_back);

		btn_2wk.addActionListener(new ActionListener() { //다음 페이지(2주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusWeeks(2),90000,"2주 이용권");
				frame.setVisible(true);
			}
		});

		btn_4wk.addActionListener(new ActionListener() { //다음 페이지(4주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusWeeks(4),130000,"4주 이용권");
				frame.setVisible(true);
			}
		});

		btn_8wk.addActionListener(new ActionListener() { //다음 페이지(8주 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusWeeks(8),250000,"8주 이용권");
				frame.setVisible(true);
			}
		});

		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_02dayOrWeek frame = new _02dayOrWeek();
				frame.setVisible(true);
			}
		});
	} 

	public static void main(String[] args) {
		_03whatWeek frame = new _03whatWeek();
		frame.setVisible(true);
	}
}
