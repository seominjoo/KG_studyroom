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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class _03whatHour extends JPanel {

	public static LocalDateTime time_now = LocalDateTime.now(); 
	static ArrayList<String> pass_price = new ArrayList<>();
	public _03whatHour() {
		setLayout(null);
		new Style(this);
		
		//이용권 가격타입 db 불러오기 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			// 로그인된 회원번호 읽기
			String sql = "select * from seat_price_info,locker_price_info";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row=0;
			int loc=0;
			while (rs.next()) {
				if(loc==0) {
				pass_price.add(rs.getString(3));
				pass_price.add(Integer.toString(rs.getInt(4)));
				loc=1;
				}
				pass_price.add(rs.getString(1));
				pass_price.add(Integer.toString(rs.getInt(2)));
			}
			
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		 
//		System.out.println(pass_price.get(0));//락커 이용권 타입
//		System.out.println(pass_price.get(1));//락커 가격
//		System.out.println(pass_price.get(2));//seat_price_info 1행 타입
//		System.out.println(pass_price.get(3));//seat_price_info 1행 가격 
	 
		
		JButton btn_2hr= new JButton(pass_price.get(2)+"("+pass_price.get(3)+")");
		new Style(btn_2hr);
		btn_2hr.setBounds(90, 130, 188, 80); 
		this.add(btn_2hr);

		JButton btn_4hr = new JButton(pass_price.get(4)+"("+pass_price.get(5)+")");
		new Style(btn_4hr);
		btn_4hr.setBounds(303, 130, 193, 80); 
		this.add(btn_4hr);

		JButton btn_6hr = new JButton(pass_price.get(6)+"("+pass_price.get(7)+")");
		new Style(btn_6hr);
		btn_6hr.setBounds(90, 230, 188, 80); 
		this.add(btn_6hr);

		JButton btn_8hr = new JButton(pass_price.get(8)+"("+pass_price.get(9)+")");
		new Style(btn_8hr);
		btn_8hr.setBounds(303, 230, 193, 80); 
		this.add(btn_8hr);

		JButton btn_12hr = new JButton(pass_price.get(10)+"("+pass_price.get(11)+")");
		new Style(btn_12hr);
		btn_12hr.setBounds(90, 330, 188, 80);
		this.add(btn_12hr);
  
		JButton btn_back = new JButton("이전 화면");
		new Style(btn_back);
		btn_back.setBounds(303, 330, 193, 80);
		this.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new ActionListener() { //다음 페이지(2시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(2), Integer.parseInt(pass_price.get(3)), pass_price.get(2)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //다음 페이지(4시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(4), Integer.parseInt(pass_price.get(5)), pass_price.get(4)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //다음 페이지(6시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(6),Integer.parseInt(pass_price.get(7)), pass_price.get(6)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //다음 페이지(8시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(8), Integer.parseInt(pass_price.get(9)), pass_price.get(8)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //다음 페이지(12시간 이용권)
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.user_page_panel.add
				("예약페이지",new _08reservation(time_now.plusHours(12), Integer.parseInt(pass_price.get(11)), pass_price.get(10)));
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
				MainPage.userToggle = "예약페이지";
			}
		});

		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			}
		});
	}
}

