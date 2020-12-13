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

public class MainBtn_Action implements ActionListener {

	JButton loginbtns;
	String nextcard = "";

	public MainBtn_Action(JButton loginbtns) {
		this.loginbtns = loginbtns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label1 = null;
		String label2 = null;

		if (loginbtns.getText().equals("ȸ������")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "ȸ������");
			MainPage.userToggle = "ȸ������";
			SignUpPage.passAlert.setText(""); // ȸ������ ��� �˸� �ʱ�ȭ
			SignUpPage.passConfirmAlert.setText(""); // ȸ������ ���Ȯ�� �˸� �ʱ�ȭ

		} else if (loginbtns.getText().equals("���ã��")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "���ã��");
			MainPage.userToggle = "���ã��";

		} else if (loginbtns.getText().equals("������ ���ã��")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "���ã��");
			MainPage.userToggle = "������ ���ã��";

		} else if (loginbtns.getText().equals("�����ڹ�ư")) {
			// ������ ��ư
			if (MainPage.userToggle.equals("����") || MainPage.userToggle.equals("�α���")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "������");
				MainPage.userToggle = "������";
			} else {
				// ùȭ��
				MainPage.main_cards.show(MainPage.main_page_panel, "����");
				MainPage.userToggle = "����";
			}

		} else if (loginbtns.getText().contains("��ġ")) {
			MainPage.main_cards.show(MainPage.main_page_panel, "�α���");
			MainPage.userToggle = "�α���";

		} else if (loginbtns.getText().equals("������ �α���")) {
			String admin_phonenumber = AdminPage.admin_phone_number1.getText() + "-"
					+ AdminPage.admin_phone_number2.getText() + "-" + AdminPage.admin_phone_number3.getText();
			String admin_password = String.valueOf(AdminPage.admin_loginpass.getPassword());

			// ������ �α��� Ŭ�� ��
			new DBLoggedIn(admin_phonenumber, admin_password);

			// ���� ���� ��� �ִ��� Ȯ��
			System.out.println(admin_phonenumber + ", " + admin_password);
			System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

			if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(admin_phonenumber)
					&& DBLoggedIn.password.equals(admin_password)) {
				// ��ȣ�� ����� ��ġ �ϸ�
				label1 = "ȸ����ȣ : " + DBLoggedIn.person_id;
				label2 = DBLoggedIn.person_name + "�� ȯ���մϴ� !!";

				String update = "update admin_info set admin_loginstate = 'On' " + "where admin_phonenumber = '"
						+ DBLoggedIn.phone_number + "' and admin_pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

				// ��ư �̸�
				nextcard = "�����ڸ޴�";
				new SubWindow(label1, label2, nextcard);

			} else {
				label1 = "�������� ���� ���̵�ų�";
				label2 = "�߸��� ��й�ȣ�Դϴ�.";
				new SubWindow(label1, label2);
			}

		} else if (loginbtns.getText().equals("�α���")) {
			MainPage.userToggle = "�α���";

			String login_phonenumber = LoginPage.phone_number1.getText() + "-" + LoginPage.phone_number2.getText() + "-"
					+ LoginPage.phone_number3.getText();
			String login_password = String.valueOf(LoginPage.loginpass.getPassword());
			// �α��� Ŭ�� ��
			// �ݱ� �ϸ� �������� �ѱ��?

			new DBLoggedIn(login_phonenumber, login_password);

			// ���� ���� ��� �ִ��� Ȯ��
			System.out.println(login_phonenumber + ", " + login_password);
			System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

			if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(login_phonenumber)
					&& DBLoggedIn.password.equals(login_password)) {
				// ��ȣ�� ����� ��ġ �ϸ�
				label1 = "ȸ����ȣ : " + DBLoggedIn.person_id;
				label2 = DBLoggedIn.person_name + "�� ȯ���մϴ� !!";

				String update = "update person_info set login_state = 'On' " + "where phone_number = '"
						+ DBLoggedIn.phone_number + "' and pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

				// ��ư �̸�
				nextcard = "����ڸ޴�";
				new SubWindow(label1, label2, nextcard);

			} else {
				label1 = "�������� ���� ���̵�ų�";
				label2 = "�߸��� ��й�ȣ�Դϴ�.";
				new SubWindow(label1, label2);
			}

		} else {
			
			// ������ �غ���
			
			label1 = "[system] still in maintenance";
			label2 = "������ �غ� ��..";
			new SubWindow(label1, label2);
		}

		//�α��� ���� �ʱ�ȭ
//		LoginPage.phone_number1.setText("010");
//		LoginPage.phone_number2.setText("");
//		LoginPage.phone_number3.setText("");
//		LoginPage.loginpass.setText("");
		AdminPage.admin_phone_number1.setText("010");
		AdminPage.admin_phone_number2.setText("");
		AdminPage.admin_phone_number3.setText("");
		AdminPage.admin_loginpass.setText("");
	}

}
