package login.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.text.Format;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.loginDataBase.DBLoggedIn;
import login.mainmenu._00main;
import login.mainmenu._03whatHour;
import login.mainmenu._06move;
import login.mainmenu._07in_seletSeat;
import login.mainmenu._07out;
import login.mainmenu._08reservation;
import login.page.AdminPage;
import login.page.LoginPage;
import login.page.MainPage;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;
import login.window.Login_SwingTool;

public class UserBtn_Action implements ActionListener {

	JButton userBtn;

	public UserBtn_Action(JButton userBtn) {
		this.userBtn = userBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label1 = null;
		String label2 = null;

		if (userBtn.getText().equals("이용권구매")) { // 이용권 페이지
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
			MainPage.userToggle = "이용권구매";

		} else if (userBtn.getText().equals("자리이동")) { // 자리이동 페이지
			if ((_00main.seat_chk > 0 && _00main.seat_chk < 21)
					|| (_00main.room_chk >= 101 && _00main.room_chk <= 104)) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "자리이동");
				MainPage.userToggle = "자리이동";
			} else {
				String msg = "사용 중인 좌석이 없습니다.";
				JOptionPane.showMessageDialog(null, msg);
			}

		} else if (userBtn.getText().equals("퇴실하기")) { // 퇴실 페이지
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "퇴실하기");
			MainPage.userToggle = "퇴실하기";
			
		} else if (userBtn.getText().equals("입실하기")) { // 입실 페이지(정기이용권 이용자)
			     
		          if(_00main.seat_chk>0) {
		        		 String msg= "이미 좌석이 있습니다.";
						 JOptionPane.showMessageDialog(null,msg); 
		        	 } else if(_00main.type.equals("정기 이용권")) {
		        		 MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
		     			MainPage.user_cards.show(MainPage.user_page_panel, "입실하기");
		     			MainPage.userToggle = "입실하기";
		             }else {
		             String msg1= "정기 이용권 이용자만 가능합니다";
		        	 JOptionPane.showMessageDialog(null,msg1);
		      }
			
		} else if (userBtn.getText().equals("2시간 (3,000원)")) { // 퇴실 페이지
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 3000;
			MainPage.seat_type = "2시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약하기");
			MainPage.userToggle = "예약하기";
			
		} else if (userBtn.getText().equals("퇴실하기")) { // 퇴실 페이지
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "퇴실하기");
			MainPage.userToggle = "퇴실하기";
			
		} else {
			// 페이지 준비중
			label1 = "[system] still in maintenance";
			label2 = "페이지 준비 중..";
			new SubWindow(label1, label2);
		}
	}

}
