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
		 
		new Style(this);
		JLabel label01 = new JLabel("<html>&emsp;&emsp;&emsp; &emsp;가격표");
		label01.setBounds(0,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		setBounds(600, 150, 450, 350);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		 
		this.add(label01);
		
		JButton btn_2hr= new JButton("2시간 (12,000원)");
		btn_2hr.setBounds(5, 143, 208, 54); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton("4시간 (18,000원)");
		btn_4hr.setBounds(218, 143, 213, 54); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton("6시간 (24,000원)");
		btn_6hr.setBounds(5, 198, 208, 54); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton("8시간 (30,000원)");
		btn_8hr.setBounds(218, 198, 213, 54); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton("12시간 (36,000원)");
		btn_12hr.setBounds(5, 253, 208, 54);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("이전 화면");
		btn_back.setBounds(218, 253, 213, 54);
		this.add(btn_back, btn_back);
		
		btn_2hr.addActionListener(new ActionListener() { //다음 페이지(2시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				_08reservation.time11 = time_now.plusHours(2);
				_08reservation.price11 = 12000;
				_08reservation.type11 = "2시간 이용권 (룸)";
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //다음 페이지(4시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				_08reservation.time11 = time_now.plusHours(4);
				_08reservation.price11 = 18000;
				_08reservation.type11 = "4시간 이용권 (룸)";
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //다음 페이지(6시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				_08reservation.time11 = time_now.plusHours(6);
				_08reservation.price11 = 24000;
				_08reservation.type11 = "6시간 이용권 (룸)";
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //다음 페이지(8시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				_08reservation.time11 = time_now.plusHours(8);
				_08reservation.price11 = 30000;
				_08reservation.type11 = "8시간 이용권 (룸)";
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //다음 페이지(12시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				_08reservation.time11 = time_now.plusHours(12);
				_08reservation.price11 = 36000;
				_08reservation.type11 = "12시간 이용권 (룸)";
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "룸이용권");
				MainPage.userToggle = "룸이용권";
			}
		});

//		btn_2hr.addActionListener(new ActionListener() { //다음 페이지(2시간 이용권)
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_08reservation frame = new _08reservation(time_now.plusHours(2),12000,"2시간 이용권 (룸)");
//				frame.setVisible(true);
//			}
//		}); 
//
//		btn_4hr.addActionListener(new ActionListener() { //다음 페이지(4시간 이용권)
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_08reservation frame = new _08reservation(time_now.plusHours(4),18000,"4시간 이용권 (룸)");
//				frame.setVisible(true);
//			}
//		}); 
//
//		btn_6hr.addActionListener(new ActionListener() { //다음 페이지(6시간 이용권)
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_08reservation frame = new _08reservation(time_now.plusHours(6),24000,"6시간 이용권 (룸)");
//				frame.setVisible(true);
//			}
//		});
//
//		btn_8hr.addActionListener(new ActionListener() { //다음 페이지(8시간 이용권)
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_08reservation frame = new _08reservation(time_now.plusHours(8),30000,"8시간 이용권 (룸)");
//				frame.setVisible(true);
//			}
//		});
//
//		btn_12hr.addActionListener(new ActionListener() { //다음 페이지(12시간 이용권)
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_08reservation frame = new _08reservation(time_now.plusHours(12),36000,"12시간 이용권 (룸)");
//				frame.setVisible(true);
//			}
//		});
//
//		btn_back.addActionListener(new ActionListener() { //이전 페이지
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				_02dayRoom frame = new _02dayRoom();
//				frame.setVisible(true);
//			}
//		});
	}

	public static void main(String[] args) {
		_03whatHourRoom frame = new _03whatHourRoom();
		frame.setVisible(true);
	}
}

