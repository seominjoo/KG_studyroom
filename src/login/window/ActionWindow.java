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
	static JPanel center_panel;
	static JLabel comment1;
	static JLabel comment2;
	static JPanel panelInGrid3;
	JLabel comment3;
	static String name1 = "";
	static String name2 = "";

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
		
	}
	public ActionWindow() {
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
		//panelInGrid3.add(combtn);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton combtn = new JButton("Ȯ��");
		new Style(combtn);
		combtn.setBounds(121, 45, 110, 30);
		
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
					name1 = "ȸ����ȣ : " + DBLoggedIn.person_id;
					name2 = DBLoggedIn.person_name + "�� ȯ���մϴ� !!";
					
					String update = "update person_info set login_state = 'On' "
							+ "where phone_number = '"+DBLoggedIn.phone_number+"' and pw = '"+DBLoggedIn.password+"'";
					System.out.println(update);
					DBLoggedIn db = new DBLoggedIn(update);
					

				} else {
					name1 = "�߸��� ��й�ȣ�Դϴ�.";
					name2 = "�ٽ� �Է� ���ּ���.";
				}

			} else {
				name1 = "�������� ���� ���̵��Դϴ�.";
				name2 = "ȸ������ ���ּ���.";
				combtn.setText("ȸ������");
			}

		} else if(loginbtns.getText().equals("ȸ������")){
			LoginPage.cards.show(LoginPage.page_panel,"ȸ������");
			
		}
		else if(loginbtns.getText().equals("���ã��")) {
			LoginPage.cards.show(LoginPage.page_panel, "���ã��");
		}
		else {
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
		
		
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combtn.getText().equals("ȸ������")) {
					LoginPage.cards.show(LoginPage.page_panel,"ȸ������");
				}
				dispose();
				
			}
		});


		// �����Ӽ���

		// �����гο� �ڸ�Ʈ���̱�
		new ActionWindow();		
		
	
		add(center_panel, BorderLayout.CENTER);

	}

}
