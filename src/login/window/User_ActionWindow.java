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

public class User_ActionWindow implements ActionListener {

	JButton menuBtns;

	public User_ActionWindow(JButton menuBtns) {
		this.menuBtns = menuBtns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (menuBtns.getText().equals("로그인")) {
			
		} else if (menuBtns.getText().equals("회원가입")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "회원가입");
			MainPage.userToggle = "회원가입";

		} else if (menuBtns.getText().equals("비번찾기")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "비번찾기");
			MainPage.userToggle = "비번찾기";
				
		} else if (menuBtns.getText().equals("관리자 비번찾기")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "비번찾기");
			MainPage.userToggle = "관리자 비번찾기";
			
		} else if (menuBtns.getText().equals("관리자버튼")) {
			if (MainPage.userToggle.equals("메인") || MainPage.userToggle.equals("로그인")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "관리자");
				MainPage.userToggle = "관리자";
			} else {
				MainPage.main_cards.show(MainPage.main_page_panel, "메인");
				System.out.println(MainPage.userToggle);
				MainPage.userToggle = "메인";
			}

		} else if (menuBtns.getText().contains("터치")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "로그인");
			MainPage.userToggle = "로그인";
			System.out.println(MainPage.userToggle);
			
		} else if (menuBtns.getText().equals("관리자 로그인")) {
			
		} else {
			
		}
		
	}

}
