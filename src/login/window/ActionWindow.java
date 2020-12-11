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

		combtn = new JButton("Ȯ��");
		combtn.setBounds(121, 45, 110, 30);
		new Style(combtn);

		panelInGrid3.add(combtn);
		center_panel.add(panelInGrid3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name1 = null;
		String name2 = null;

		if (loginbtns.getText().equals("�α���")) {
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 0));
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
				name1 = "ȸ����ȣ : " + DBLoggedIn.person_id;
				name2 = DBLoggedIn.person_name + "�� ȯ���մϴ� !!";

				String update = "update person_info set login_state = 'On' " + "where phone_number = '"
						+ DBLoggedIn.phone_number + "' and pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

			} else {
				name1 = "�������� ���� ���̵�ų�";
				name2 = "�߸��� ��й�ȣ�Դϴ�.";
			}

		} else if (loginbtns.getText().equals("ȸ������")) {
			MainPage.cards.show(MainPage.page_panel, "ȸ������");
			MainPage.userToggle = "ȸ������";

		} else if (loginbtns.getText().equals("���ã��")) {
			MainPage.cards.show(MainPage.page_panel, "���ã��");
			MainPage.userToggle = "���ã��";
				
		} else if (loginbtns.getText().equals("������ ���ã��")) {
			MainPage.cards.show(MainPage.page_panel, "���ã��");
			MainPage.userToggle = "������ ���ã��";
			
		} else if (loginbtns.getText().equals("�����ڹ�ư")) {
			if (MainPage.userToggle.equals("����") || MainPage.userToggle.equals("�α���")) {
				MainPage.cards.show(MainPage.page_panel, "������");
				MainPage.userToggle = "������";
			} else {
				MainPage.cards.show(MainPage.page_panel, "����");
				System.out.println(MainPage.userToggle);
				MainPage.userToggle = "����";
			}

		} else if (loginbtns.getText().contains("��ġ")) {
			MainPage.cards.show(MainPage.page_panel, "�α���");
			MainPage.userToggle = "�α���";
			System.out.println(MainPage.userToggle);
			
		} else if (loginbtns.getText().equals("������ �α���")) {
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 0));
			String admin_phonenumber = AdminPage.admin_phone_number1.getText() + "-"
					+ AdminPage.admin_phone_number2.getText() + "-" + AdminPage.admin_phone_number3.getText();
			String admin_password = String.valueOf(AdminPage.admin_loginpass.getPassword());
			// �α��� Ŭ�� ��
			// �ݱ� �ϸ� �������� �ѱ��?
			
			new DBLoggedIn(admin_phonenumber, admin_password);

			// ���� ���� ��� �ִ��� Ȯ��
			System.out.println(admin_phonenumber + ", " + admin_password);
			System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

			if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(admin_phonenumber)
					&& DBLoggedIn.password.equals(admin_password)) {
				// ��ȣ�� ����� ��ġ �ϸ�
				name1 = "ȸ����ȣ : " + DBLoggedIn.person_id;
				name2 = DBLoggedIn.person_name + "�� ȯ���մϴ� !!";

				String update = "update admin_info set admin_loginstate = 'On' " + "where admin_phonenumber = '"
						+ DBLoggedIn.phone_number + "' and admin_pw = '" + DBLoggedIn.password + "'";
				System.out.println(update);
				DBLoggedIn db = new DBLoggedIn(update);

			} else {
				name1 = "�������� ���� ���̵�ų�";
				name2 = "�߸��� ��й�ȣ�Դϴ�.";
			}

		} else {
			// ������ �غ���
			name1 = "[system] still in maintenance";
			name2 = "������ �غ� ��..";
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

		// �����Ӽ���

		// �����гο� �ڸ�Ʈ���̱�
		add(center_panel, BorderLayout.CENTER);

	}

}
