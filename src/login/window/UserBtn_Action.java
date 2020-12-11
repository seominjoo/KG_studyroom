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

public class UserBtn_Action implements ActionListener {

	JButton menuBtns;

	public UserBtn_Action(JButton menuBtns) {
		this.menuBtns = menuBtns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label1 = null;
		String label2 = null;

		if (menuBtns.getText().equals("회원가입")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "회원가입");
			MainPage.userToggle = "회원가입";

		} else if (menuBtns.getText().equals("비번찾기")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "비번찾기");
			MainPage.userToggle = "비번찾기";

		} else {
			// 페이지 준비중
			label1 = "[system] still in maintenance";
			label2 = "페이지 준비 중..";
			new SubWindow(label1, label2);
		}
	}

}
