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

import login.design.Style;
import login.loginDataBase.DBLoggedIn;
import login.page.LoginPage;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;
import login.window.Login_SwingTool;

public class ActionWindow extends JFrame implements ActionListener {

	JButton loginbtns;
	JPanel center_panel;
	JLabel comment1;
	JLabel comment2;
	JLabel comment3;

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
		center_panel = new JPanel(new GridLayout(3, 1, 0, -60));
		new Style(center_panel);
		comment1 = new JLabel("", JLabel.CENTER);
		comment2 = new JLabel("", JLabel.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton combtn = new JButton("Ȯ��");
		new Style(combtn);
		
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
			if (DBLoggedIn.person_name != null) {
				// �ִ�.
				if (DBLoggedIn.phone_number.equals(login_phonenumber) && DBLoggedIn.password.equals(login_password)) {
					// ��ȣ�� ����� ��ġ �ϸ�
					comment1.setText("ȸ����ȣ : " + DBLoggedIn.person_id);
					comment2.setText(DBLoggedIn.person_name + "�� ȯ���մϴ� !!");
					
					String update = "update person_info set login_state = 'On' "
							+ "where phone_number = '"+DBLoggedIn.phone_number+"' and pw = '"+DBLoggedIn.password+"'";
					System.out.println(update);
					DBLoggedIn db = new DBLoggedIn(update);
					

				} else {
					comment1.setText("�߸��� ��й�ȣ�Դϴ�.");
					comment2.setText("�ٽ� �Է� ���ּ���.");
				}

			} else {
				comment1.setText("�������� ���� ���̵��Դϴ�.");
				comment2.setText("ȸ������ ���ּ���.");
				combtn.setText("ȸ������");
			}

		} else if(loginbtns.getText().equals("ȸ������")){
			LoginPage.cards.show(LoginPage.page_panel,"ȸ������");
			
		}else {
			// ������ �غ���
			comment1.setText("[system] still in maintenance");
			comment2.setText("������ �غ� ��..");
			SwingToolsSubPage.initTestFrame(this);
			setLayout(new BorderLayout(10, 10));
		}


		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combtn.getText().equals("ȸ������")) {
					LoginPage.cards.show(LoginPage.page_panel,"ȸ������");
				}
				dispose();
				
			}
		});

		new Style(comment1);
		new Style(comment2);

		// �����Ӽ���

		// �����гο� �ڸ�Ʈ���̱�
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
