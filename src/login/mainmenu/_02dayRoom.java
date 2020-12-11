package login.mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;

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

public class _02dayRoom extends JPanel {


	public _02dayRoom() {

		new Style(this);
		setBounds(600, 150, 450, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 2, 0, 0));

		JButton daily_btn = new JButton("일일 이용권"); 
		this.add(daily_btn);

		JButton back_btn = new JButton("이전 화면"); 
		this.add(back_btn);

		
		daily_btn.addActionListener(new ActionListener() { //다음 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_03whatHourRoom frame = new _03whatHourRoom();
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
		_02dayRoom frame = new _02dayRoom();
		frame.setVisible(true);
	}
} 