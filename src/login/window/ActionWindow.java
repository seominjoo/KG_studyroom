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
	JLabel comment1;
	JLabel comment2;
	JLabel comment3;

	static String name = "������";

	public ActionWindow(JButton loginbtns) {
		this.loginbtns = loginbtns;
		center_panel = new JPanel(new GridLayout(2, 0, 0, 10));
		new Style(center_panel);
		comment1 = new JLabel("", JLabel.CENTER);
		comment2 = new JLabel("", JLabel.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (loginbtns.getText().equals("�α���")) {
			String login_phonenumber = LoginPage.phone_number1.getText() + "-" + LoginPage.phone_number2.getText() + "-"
					+ LoginPage.phone_number3.getText();
			String login_password = String.valueOf(LoginPage.loginpass.getPassword());
			// �α��� Ŭ�� ��
			// �ݱ� �ϸ� �������� �ѱ��?
			new DateBase(login_phonenumber, login_password);

			// ����� ���� ��� �ִ��� Ȯ��
			if (DateBase.person_name != null) {
				// �ִ�.
				if (DateBase.phone_number.equals(login_phonenumber) && DateBase.password.equals(login_password)) {
					// ��ȣ�� ����� ��ġ �ϸ�
					comment1.setText("ȸ����ȣ : " + DateBase.person_id);
					comment2.setText(DateBase.person_name + "�� ȯ���մϴ� !!");

				} else {
					comment1.setText("�߸��� ��й�ȣ�Դϴ�.");
					comment2.setText("�ٽ� �Է� ���ּ���.");
				}

			} else {
				comment1.setText("�������� ���� ���̵��Դϴ�.");
				comment2.setText("ȸ������ ���ּ���.");
			}

		} else {
			// ������ �غ���
			comment1.setText("[system] still in maintenance");
			comment2.setText("������ �غ� ��..");
		}

		JButton combtn = new JButton("Ȯ��");
		new Style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("���Ⱑ �ɱ�? -- ����");
				// new Selectpage();
			}
		});

		new Style(comment1);
		new Style(comment2);

		// �����Ӽ���
		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10, 10));
		setBounds(250, 170, 300, 150);

		// �����гο� �ڸ�Ʈ���̱�
		center_panel.add(comment1);
		center_panel.add(comment2);

		// �г� ����
		add(new Style().getnewPanel(), BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);

	}

}