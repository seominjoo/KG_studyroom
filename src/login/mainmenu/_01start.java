package login.mainmenu;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;

import login.design.Style;
import login.page.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class _01start extends JPanel {

	public static Connection conn;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h시 m분 ");
	static Timestamp time_chk_seat;
	static ArrayList<String> pass_price = new ArrayList<>();
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		_01start main = new _01start();
//		frame.setBounds(0,0,683,562);
//		frame.add(main);
//		frame.setVisible(true);
//	}
	
	public _01start() {

		pass_price = new ArrayList<>();
		new Style(this);
		this.setLayout(null);

		// 메뉴 버튼 4개
		JButton seat_btn = new JButton("좌석 이용권");
		seat_btn.setBounds(MainPage.w/2-230, MainPage.h/2-160, 220, 130);
		this.add(seat_btn);

		JButton locker_btn = new JButton("사물함 이용권");
		locker_btn.setBounds(MainPage.w/2-230, MainPage.h/2-10, 220, 130);
		this.add(locker_btn);

		JButton room_btn = new JButton("룸 이용권");
		room_btn.setBounds(MainPage.w/2+10, MainPage.h/2-160, 220, 130);
		this.add(room_btn);

		JButton back_btn = new JButton("이전 화면");
		back_btn.setBounds(MainPage.w/2+10, MainPage.h/2-10, 220, 130);
		this.add(back_btn);
		
		new Style(seat_btn);
		new Style(locker_btn);
		new Style(room_btn);
		new Style(back_btn);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
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
//	                  System.out.println(pass_price.get(0));//락커 이용권 타입
//	                  System.out.println(pass_price.get(1));//락커 가격
//	                  System.out.println(pass_price.get(2));//seat_price_info 1행 타입
//	                  System.out.println(pass_price.get(3));//seat_price_info 1행 가격 
	         seat_btn.addActionListener(new ActionListener() { //좌석 이용권 페이지
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               System.out.println("좌석번호 현재"+_00main.seat_chk);
	               System.out.println("이용권 현재"+_00main.type);
	       
	                
	                if(_00main.seat_chk>0) {
	                        String msg= "결제한 좌석이 이미 존재합니다";
	                     JOptionPane.showMessageDialog(null,msg); 
	                     } else if(_00main.type.equals("정기 이용권")) {
	                      String msg= "정기 이용권 이용자는 입실을 이용하세요";
	                      JOptionPane.showMessageDialog(null,msg); 
	                   }else {
	                      MainPage.user_page_panel.add("좌석이용권", new _02dayOrWeek()); // 좌석 이용권 페이지
	                      MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
	                        MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
	                        MainPage.userToggle = "좌석이용권";
	                     }
	                  }
	         }); 
	         
	            room_btn.addActionListener(new ActionListener() { //룸 이용권 페이지
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                   try {//룸 만료시간이 안지나면 구매 불가 
	                     String sql = "SELECT room_number from person_info where login_state='On'";
	                     PreparedStatement pstmt = conn.prepareStatement(sql);
	                     ResultSet rs = pstmt.executeQuery();
	                     
	                      while(rs.next()) { 
	                            
	                         int room_chk = rs.getInt("room_number");
	                              if(room_chk>0) {
	                                 String msg= "결제한 룸이 이미 존재합니다";
	                              JOptionPane.showMessageDialog(null,msg); 
	                              }else {                     
	                                 MainPage.user_page_panel.add("룸이용권", new _02dayRoom());// 룸 이용권 페이지
	                                 MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
	                              MainPage.user_cards.show(MainPage.user_page_panel, "룸이용권");
	                              MainPage.userToggle = "룸이용권";
	                              }
	                           }
	                     
	                  } catch (SQLException e1) { 
	                     e1.printStackTrace();
	                  } 
	                }
	             });  
	          
	         locker_btn.addActionListener(new ActionListener() { //사물함 이용권 페이지
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {//사물함만료시간이 안지나면 구매 불가 
	                     String sql = "SELECT locker_number from person_info where login_state='On'";
	                     PreparedStatement pstmt = conn.prepareStatement(sql);
	                     ResultSet rs = pstmt.executeQuery();
	                     
	                      while(rs.next()) { 
	                            
	                              int locker_chk = rs.getInt("locker_number");
	                              if(locker_chk>0) {
	                                 String msg= "결제한 사물함이 이미 존재합니다";
	                              JOptionPane.showMessageDialog(null,msg); 
	                              }else {    
	                                 MainPage.user_page_panel.add("사물함이용권", new _05locker());
	                                 MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
	                              MainPage.user_cards.show(MainPage.user_page_panel, "사물함이용권");
	                              MainPage.userToggle = "사물함이용권";
	                              }
	                           }
	                     
	                  } catch (SQLException e1) { 
	                     e1.printStackTrace();
	                  }
	            }
	         });
	         
	           back_btn.addActionListener(new ActionListener() { //이전 페이지
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                   MainPage.user_page_panel.add("메인메뉴", new _00main()); // 메뉴페이지
	                   MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
	                  MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
	                  MainPage.userToggle = "메인메뉴";
	                }
	             });

	      } catch (ClassNotFoundException | SQLException e1) {
	         e1.printStackTrace();
	      }
	   }
	}