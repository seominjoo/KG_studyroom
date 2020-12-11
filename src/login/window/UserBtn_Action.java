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

		if (userBtn.getText().equals("�̿�Ǳ���")) { // �̿�� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
			MainPage.userToggle = "�̿�Ǳ���";

		} else if (userBtn.getText().equals("�ڸ��̵�")) { // �ڸ��̵� ������
			if ((_00main.seat_chk > 0 && _00main.seat_chk < 21)
					|| (_00main.room_chk >= 101 && _00main.room_chk <= 104)) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ��̵�");
				MainPage.userToggle = "�ڸ��̵�";
			} else {
				String msg = "��� ���� �¼��� �����ϴ�.";
				JOptionPane.showMessageDialog(null, msg);
			}

		} else if (userBtn.getText().equals("����ϱ�")) { // ��� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����ϱ�";
			
		} else if (userBtn.getText().equals("�Խ��ϱ�")) { // �Խ� ������(�����̿�� �̿���)
			     
		          if(_00main.seat_chk>0) {
		        		 String msg= "�̹� �¼��� �ֽ��ϴ�.";
						 JOptionPane.showMessageDialog(null,msg); 
		        	 } else if(_00main.type.equals("���� �̿��")) {
		        		 MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
		     			MainPage.user_cards.show(MainPage.user_page_panel, "�Խ��ϱ�");
		     			MainPage.userToggle = "�Խ��ϱ�";
		             }else {
		             String msg1= "���� �̿�� �̿��ڸ� �����մϴ�";
		        	 JOptionPane.showMessageDialog(null,msg1);
		      }
			
		} else if (userBtn.getText().equals("2�ð� (3,000��)")) { // ��� ������
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 3000;
			MainPage.seat_type = "2�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "�����ϱ�");
			MainPage.userToggle = "�����ϱ�";
			
		} else if (userBtn.getText().equals("����ϱ�")) { // ��� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����ϱ�";
			
		} else {
			// ������ �غ���
			label1 = "[system] still in maintenance";
			label2 = "������ �غ� ��..";
			new SubWindow(label1, label2);
		}
	}

}
