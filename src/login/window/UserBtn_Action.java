package login.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import login.mainmenu.Time;
import login.mainmenu._00main;
import login.mainmenu._01start;
import login.mainmenu._02dayOrWeek;
import login.mainmenu._02dayRoom;
import login.mainmenu._03whatHour;
import login.mainmenu._05locker;
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
		// if문으로 1인석 정기권 룸 나눠야 될듯 

		
if (userBtn.getText().equals("미정")) { // 퇴실 페이지

			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "퇴실하기");
			MainPage.userToggle = "미정";

		} else if (userBtn.getText().equals("미정")) { // 퇴실 페이지
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "퇴실하기");
			MainPage.userToggle = "미정";

		} else if (userBtn.getText().equals("이전 화면")) { // 이전 페이지
			System.out.println("이전 화면 실행 현재 화면 : " + MainPage.userToggle);
			if (MainPage.userToggle.equals("예약페이지")) { // 좌석일때
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			} else if (MainPage.userToggle.equals("좌석이용권")
					|| MainPage.userToggle.equals("룸이용권")
					|| MainPage.userToggle.equals("사물함이용권")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			} else if (MainPage.userToggle.equals("이용권구매")
					|| MainPage.userToggle.equals("자리이동")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
			} else if (MainPage.userToggle.equals("좌석페이지")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "자리이동");
				MainPage.userToggle = "자리이동";
			} else if(MainPage.userToggle.equals("당일권가격표(좌석)")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			}  else if(MainPage.userToggle.equals("정기권가격표")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			}
			
			
		} else {
			// 페이지 준비중
			label1 = "[system] still in maintenance";
			label2 = "페이지 준비 중..";
			new SubWindow(label1, label2);
		}
	}

}
