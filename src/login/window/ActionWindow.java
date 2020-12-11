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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.loginDataBase.DBLoggedIn;
import login.page.AdminPage;
import login.page.LoginPage;
import login.page.MainPage;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;
import login.window.Login_SwingTool;

public class ActionWindow extends JFrame implements ActionListener {

	JButton loginbtns;
	static JPanel center_panel;
	static JLabel comment1;
	static JLabel comment2;
	static JButton combtn;
	static JPanel panelInGrid3;

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;

		center_panel = new JPanel(new GridLayout(3, 1, 0, -60));
		new Style(center_panel);

		comment1 = new JLabel("", JLabel.CENTER);
		new Style(comment1);
		center_panel.add(comment1);

		comment2 = new JLabel("", JLabel.CENTER);
		new Style(comment2);
		center_panel.add(comment2);

		panelInGrid3 = new JPanel();
		panelInGrid3.setLayout(null);
		new Style(panelInGrid3);

		combtn = new JButton("확인");
		combtn.setBounds(121, 45, 110, 30);
		new Style(combtn);

		panelInGrid3.add(combtn);
		center_panel.add(panelInGrid3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name1 = null;
		String name2 = null;

		if (loginbtns.getText().equals("로그인")) {
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 0));
			String login_phonenumber = LoginPage.phone_number1.getText() + "-" + LoginPage.phone_number2.getText() + "-"
					+ LoginPage.phone_number3.getText();
			String login_password = String.valueOf(LoginPage.loginpass.getPassword());
			// 로그인 클릭 시
			// 닫기 하면 페이지를 넘길까?
			
			new DBLoggedIn(login_phonenumber, login_password);

			// 들고온 값이 디비에 있는지 확인
			System.out.println(login_phonenumber + ", " + login_password);
			System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

			if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(login_phonenumber)
					&& DBLoggedIn.password.equals(login_password)) {
				// 번호와 비번이 일치 하면
				name1 = "회원번호 : " + DBLoggedIn.person_id;
				name2 = DBLoggedIn.person_name + "님 환영합니다 !!";

				String update = "update person_info set login_state = 'On' " + "where phone_number = '"
						+ DBLoggedIn.phone_number + "' and pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

			} else {
				name1 = "가입하지 않은 아이디거나";
				name2 = "잘못된 비밀번호입니다.";
			}

		} else if (loginbtns.getText().equals("회원가입")) {
			MainPage.cards.show(MainPage.page_panel, "회원가입");
			MainPage.userToggle = "회원가입";

		} else if (loginbtns.getText().equals("비번찾기")) {
			MainPage.cards.show(MainPage.page_panel, "비번찾기");
			MainPage.userToggle = "비번찾기";
				
		} else if (loginbtns.getText().equals("관리자 비번찾기")) {
			MainPage.cards.show(MainPage.page_panel, "비번찾기");
			MainPage.userToggle = "관리자 비번찾기";
			
		} else if (loginbtns.getText().equals("관리자버튼")) {
			if (MainPage.userToggle.equals("메인") || MainPage.userToggle.equals("로그인")) {
				MainPage.cards.show(MainPage.page_panel, "관리자");
				MainPage.userToggle = "관리자";
			} else {
				MainPage.cards.show(MainPage.page_panel, "메인");
				System.out.println(MainPage.userToggle);
				MainPage.userToggle = "메인";
			}

		} else if (loginbtns.getText().contains("터치")) {
			MainPage.cards.show(MainPage.page_panel, "로그인");
			MainPage.userToggle = "로그인";
			System.out.println(MainPage.userToggle);
			
		} else if (loginbtns.getText().equals("관리자 로그인")) {
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 0));
			String admin_phonenumber = AdminPage.admin_phone_number1.getText() + "-"
					+ AdminPage.admin_phone_number2.getText() + "-" + AdminPage.admin_phone_number3.getText();
			String admin_password = String.valueOf(AdminPage.admin_loginpass.getPassword());
			// 로그인 클릭 시
			// 닫기 하면 페이지를 넘길까?
			
			new DBLoggedIn(admin_phonenumber, admin_password);

			// 들고온 값이 디비에 있는지 확인
			System.out.println(admin_phonenumber + ", " + admin_password);
			System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

			if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(admin_phonenumber)
					&& DBLoggedIn.password.equals(admin_password)) {
				// 번호와 비번이 일치 하면
				name1 = "회원번호 : " + DBLoggedIn.person_id;
				name2 = DBLoggedIn.person_name + "님 환영합니다 !!";

				String update = "update admin_info set admin_loginstate = 'On' " + "where admin_phonenumber = '"
						+ DBLoggedIn.phone_number + "' and admin_pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

			} else {
				name1 = "가입하지 않은 아이디거나";
				name2 = "잘못된 비밀번호입니다.";
			}

		} else {
			// 페이지 준비중
			name1 = "[system] still in maintenance";
			name2 = "페이지 준비 중..";
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 10));
		}
		LoginPage.phone_number1.setText("010");
		LoginPage.phone_number2.setText("");
		LoginPage.phone_number3.setText("");
		LoginPage.loginpass.setText("");

		comment1.setText(name1);
		comment2.setText(name2);

		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});

		// 프레임설정

		// 센터패널에 코멘트붙이기
		add(center_panel, BorderLayout.CENTER);

	}

}
