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
		// if������ 1�μ� ����� �� ������ �ɵ� 

		
if (userBtn.getText().equals("����")) { // ��� ������

			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����";

		} else if (userBtn.getText().equals("����")) { // ��� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����";

		} else if (userBtn.getText().equals("���� ȭ��")) { // ���� ������
			System.out.println("���� ȭ�� ���� ���� ȭ�� : " + MainPage.userToggle);
			if (MainPage.userToggle.equals("����������")) { // �¼��϶�
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			} else if (MainPage.userToggle.equals("�¼��̿��")
					|| MainPage.userToggle.equals("���̿��")
					|| MainPage.userToggle.equals("�繰���̿��")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			} else if (MainPage.userToggle.equals("�̿�Ǳ���")
					|| MainPage.userToggle.equals("�ڸ��̵�")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
				MainPage.userToggle = "���θ޴�";
			} else if (MainPage.userToggle.equals("�¼�������")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ��̵�");
				MainPage.userToggle = "�ڸ��̵�";
			} else if(MainPage.userToggle.equals("���ϱǰ���ǥ(�¼�)")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			}  else if(MainPage.userToggle.equals("����ǰ���ǥ")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			}
			
			
		} else {
			// ������ �غ���
			label1 = "[system] still in maintenance";
			label2 = "������ �غ� ��..";
			new SubWindow(label1, label2);
		}
	}

}
