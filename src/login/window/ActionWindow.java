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
import login.DateBase;
import login.LoginPage;
import login.SwingTool_logo;

public class ActionWindow extends JFrame implements ActionListener {

	JButton loginbtns;
	JPanel center_panel;
	JLabel commenteng;
	JLabel commentkor;
	
	static String login_phonenumber;
	static String login_password;
	static String name = "서민주";
	
	
	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
	}
	
	public static void setLogin_phonenumber(String login_phonenumber) {
		ActionWindow.login_phonenumber = login_phonenumber;
	}
	public static void setLogin_password(String login_password) {
		ActionWindow.login_password = login_password;
	}
	public static String getLogin_password() {
		return login_password;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		center_panel = new JPanel(new GridLayout(2, 0, 0, 0));
		new Style(center_panel);

		if (loginbtns.getText().equals("로그인")) {
			// 로그인 클릭 시
			// 닫기 하면 페이지를 넘길까?
			new DateBase(login_phonenumber, login_password);
			
			commenteng = new JLabel("[system] Welcome !!", JLabel.CENTER);
			new Style(commenteng);
			commentkor = new JLabel(name+"님 환영합니다 !!", JLabel.CENTER);
			new Style(commentkor);
			
		} else {
			// 페이지 준비중 
			commenteng = new JLabel("[system] still in maintenance", JLabel.CENTER);
			new Style(commenteng);
			commentkor = new JLabel("페이지 준비 중..", JLabel.CENTER);
			new Style(commentkor);
		}

		JButton combtn = new JButton("닫기");
		new Style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("여기가 될까? -- 가능");
				new Selectpage();
			}
		});
		
		// 프레임설정
		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10, 10));
		setBounds(250, 170, 300, 150);

		// 센터패널에 코멘트붙이기
		center_panel.add(commenteng);
		center_panel.add(commentkor);

		// 패널 정렬
		add(new Style().getnewPanel(), BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);

	}

}
