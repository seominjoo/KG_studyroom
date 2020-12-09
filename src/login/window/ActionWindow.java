package login.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Style;
import login.loginDataBase.DataBase;
import login.page.LoginPage;
import login.signUp.SignUpPage;
import login.window.Login_SwingTool;

public class ActionWindow extends JFrame implements ActionListener {

	JButton loginbtns;
	JPanel center_panel;
	JLabel comment1;
	JLabel comment2;
	JLabel comment3;

	static String name = "서민주";

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
		center_panel = new JPanel(new GridLayout(2, 0, 0, 10));
		new Style(center_panel);
		comment1 = new JLabel("", JLabel.CENTER);
		comment2 = new JLabel("", JLabel.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (loginbtns.getText().equals("로그인")) {
			String login_phonenumber = LoginPage.phone_number1.getText() + "-" + LoginPage.phone_number2.getText() + "-"
					+ LoginPage.phone_number3.getText();
			String login_password = String.valueOf(LoginPage.loginpass.getPassword());
			// 로그인 클릭 시
			// 닫기 하면 페이지를 넘길까?
			new DataBase(login_phonenumber, login_password);

			// 들고온 값이 디비에 있는지 확인
			if (DataBase.person_name != null) {
				// 있다.
				if (DataBase.phone_number.equals(login_phonenumber) && DataBase.password.equals(login_password)) {
					// 번호와 비번이 일치 하면
					comment1.setText("회원번호 : " + DataBase.person_id);
					comment2.setText(DataBase.person_name + "님 환영합니다 !!");
//					new mainmenu();

				} else {
					comment1.setText("잘못된 비밀번호입니다.");
					comment2.setText("다시 입력 해주세요.");
				}

			} else {
				comment1.setText("가입하지 않은 아이디입니다.");
				comment2.setText("회원가입 해주세요.");
			}

		} else if(loginbtns.getText().equals("회원가입")){
			
			LoginPage.cards.show(LoginPage.page_panel,"회원가입");
		}else {
			// 페이지 준비중
			comment1.setText("[system] still in maintenance");
			comment2.setText("페이지 준비 중..");
		}

		JButton combtn = new JButton("확인");
		new Style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("여기가 될까? -- 가능");
				// new Selectpage();
			}
		});

		new Style(comment1);
		new Style(comment2);

		// 프레임설정
		Login_SwingTool.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10, 10));
		setBounds(250, 170, 300, 150);

		// 센터패널에 코멘트붙이기
		center_panel.add(comment1);
		center_panel.add(comment2);

		// 패널 정렬
		add(new Style().getnewPanel(), BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);

	}

}
