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

			if (_00main.seat_chk > 0) {
				String msg = "이미 좌석이 있습니다.";
				JOptionPane.showMessageDialog(null, msg);
			} else if (_00main.type.equals("정기 이용권")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "입실하기");
				MainPage.userToggle = "입실하기";
			} else {
				String msg1 = "정기 이용권 이용자만 가능합니다";
				JOptionPane.showMessageDialog(null, msg1);
			}
		}else if (userBtn.getText().equals("일일 이용권")) { // 다음 페이지(일일이용권)
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "일일권가격표");
				MainPage.userToggle = "일일권가격표";

		} else if (userBtn.getText().equals("2시간 (3,000원)")) { // 다음 페이지(2시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 3000;
			MainPage.seat_type = "2시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("4시간 (2,000원)")) { // 다음 페이지(4시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(4);
			MainPage.price = 4500;
			MainPage.seat_type = "4시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("6시간 (2,000원)")) { // 다음 페이지(6시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(6);
			MainPage.price = 6000;
			MainPage.seat_type = "6시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("8시간 (2,000원)")) { // 다음 페이지(8시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(8);
			MainPage.price = 7500;
			MainPage.seat_type = "8시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("12시간 (2,000원)")) { // 다음 페이지(12시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(12);
			MainPage.price = 10000;
			MainPage.seat_type = "12시간 이용권 (1인석)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} 
		else if (userBtn.getText().equals("정기 이용권")) { // 다음 페이지(정기이용권)
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "정기권가격표");
			MainPage.userToggle = "정기권가격표";

		} else if (userBtn.getText().equals("좌석 이용권")) { // 1인석 페이지
			if (_00main.seat_chk > 0) {
				String msg = "결제한 좌석이 이미 존재합니다";
				JOptionPane.showMessageDialog(null, msg);
			} else if (_00main.type.equals("정기 이용권")) {
				String msg = "정기 이용권 이용자는 입실을 이용하세요";
				JOptionPane.showMessageDialog(null, msg);
			} else {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "좌석이용권");
				MainPage.userToggle = "좌석이용권";
			}

		} else if (userBtn.getText().equals("사물함 이용권")) { // 퇴실 페이지
			try {// 사물함만료시간이 안지나면 구매 불가
				String sql = "SELECT expiration_locker from person_info where login_state='On'";
				PreparedStatement pstmt = _01start.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {

					Timestamp time_chk = rs.getTimestamp("expiration_locker");
					if (LocalDateTime.now().isBefore(Time.TimeStampTOlocalDateTime(time_chk))) {
						String msg = "결제한 사물함이 이미 존재합니다";
						JOptionPane.showMessageDialog(null, msg);
					} else {
						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.user_cards.show(MainPage.user_page_panel, "사물함이용권");
						MainPage.userToggle = "사물함이용권";
					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (userBtn.getText().equals("룸 이용권")) { // 룸이용 페이지
			try {// 룸만료시간이 안지나면 구매 불가
				String sql = "SELECT expiration_room from person_info where login_state='On'";
				PreparedStatement pstmt = _01start.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					Timestamp time_chk = rs.getTimestamp("expiration_room");
					if (LocalDateTime.now().isBefore(Time.TimeStampTOlocalDateTime(time_chk))) {
						String msg = "결제한 룸이 이미 존재합니다";
						JOptionPane.showMessageDialog(null, msg);
					} else {

						MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
						MainPage.user_cards.show(MainPage.user_page_panel, "룸이용권");
						MainPage.userToggle = "룸이용권";
					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (userBtn.getText().equals("2시간 (12,000원)")) { // 다음 페이지(2시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 12000;
			MainPage.seat_type = "2시간 이용권 (룸)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("4시간 (18,000원)")) { // 다음 페이지(4시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(4);
			MainPage.price = 18000;
			MainPage.seat_type = "4시간 이용권 (룸)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("6시간 (24,000원)")) { // 다음 페이지(6시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(6);
			MainPage.price = 24000;
			MainPage.seat_type = "6시간 이용권 (룸)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("8시간 (30,000원)")) { // 다음 페이지(8시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(8);
			MainPage.price = 30000;
			MainPage.seat_type = "8시간 이용권 (룸)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("12시간 (36,000원)")) { // 다음 페이지(12시간 이용권)
			MainPage.ss = _03whatHour.time_now.plusHours(12);
			MainPage.price = 36000;
			MainPage.seat_type = "12시간 이용권 (룸)";
			MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
			MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
			MainPage.userToggle = "예약페이지";

		} else if (userBtn.getText().equals("미정")) { // 퇴실 페이지

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
			} else if (MainPage.userToggle.equals("좌석이용권")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			} else if (MainPage.userToggle.equals("이용권구매")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
			} else if (MainPage.userToggle.equals("이용권구매")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
			} else if (MainPage.userToggle.equals("룸이용권")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
				MainPage.userToggle = "이용권구매";
			}
		} else {
			// 페이지 준비중
			label1 = "[system] still in maintenance";
			label2 = "페이지 준비 중..";
			new SubWindow(label1, label2);
		}
	}

}
