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
import login.page.LoginPage;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;
import login.window.Login_SwingTool;

public class ActionWindow extends JFrame implements ActionListener {

	JButton loginbtns;
	JPanel center_panel;
	static JLabel comment1;
	static JLabel comment2;
	JLabel comment3;
	static String name1 = "";
	static String name2 = "";

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton combtn = new JButton("확인");
		new Style(combtn);
		
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
			if (DBLoggedIn.person_name != null) {
				// 있다.
				if (DBLoggedIn.phone_number.equals(login_phonenumber) && DBLoggedIn.password.equals(login_password)) {
					// 번호와 비번이 일치 하면
					name1 = "회원번호 : " + DBLoggedIn.person_id;
					name2 = DBLoggedIn.person_name + "님 환영합니다 !!";
					
					String update = "update person_info set login_state = 'On' "
							+ "where phone_number = '"+DBLoggedIn.phone_number+"' and pw = '"+DBLoggedIn.password+"'";
					System.out.println(update);
					DBLoggedIn db = new DBLoggedIn(update);
					

				} else {
					name1 = "잘못된 비밀번호입니다.";
					name2 = "다시 입력 해주세요.";
				}

			} else {
				name1 = "가입하지 않은 아이디입니다.";
				name2 = "회원가입 해주세요.";
				combtn.setText("회원가입");
			}

		} else if(loginbtns.getText().equals("회원가입")){
			LoginPage.cards.show(LoginPage.page_panel,"회원가입");
			
		}
		else if(loginbtns.getText().equals("비번찾기")) {
			LoginPage.cards.show(LoginPage.page_panel, "비번찾기");
		}
		else {
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
		
		
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combtn.getText().equals("회원가입")) {
					LoginPage.cards.show(LoginPage.page_panel,"회원가입");
				}
				dispose();
				
			}
		});


		// 프레임설정

		// 센터패널에 코멘트붙이기
		center_panel = new JPanel(new GridLayout(3, 1, 0, -60));
		new Style(center_panel);
		
		comment1 = new JLabel(name1, JLabel.CENTER);
		comment2 = new JLabel(name2, JLabel.CENTER);
		new Style(comment1);
		new Style(comment2);
		
		center_panel.add(comment1);
		center_panel.add(comment2);
		
		
		JPanel panelInGrid3 = new JPanel();
		center_panel.add(panelInGrid3);
		new Style(panelInGrid3);
		panelInGrid3.setLayout(null);
		combtn.setBounds(121, 45, 110, 30);
		panelInGrid3.add(combtn);
		
		add(center_panel, BorderLayout.CENTER);

	}

}
