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
	static String name = "������";
	
	
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

		if (loginbtns.getText().equals("�α���")) {
			// �α��� Ŭ�� ��
			// �ݱ� �ϸ� �������� �ѱ��?
			new DateBase(login_phonenumber, login_password);
			
			commenteng = new JLabel("[system] Welcome !!", JLabel.CENTER);
			new Style(commenteng);
			commentkor = new JLabel(name+"�� ȯ���մϴ� !!", JLabel.CENTER);
			new Style(commentkor);
			
		} else {
			// ������ �غ��� 
			commenteng = new JLabel("[system] still in maintenance", JLabel.CENTER);
			new Style(commenteng);
			commentkor = new JLabel("������ �غ� ��..", JLabel.CENTER);
			new Style(commentkor);
		}

		JButton combtn = new JButton("�ݱ�");
		new Style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("���Ⱑ �ɱ�? -- ����");
				new Selectpage();
			}
		});
		
		// �����Ӽ���
		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10, 10));
		setBounds(250, 170, 300, 150);

		// �����гο� �ڸ�Ʈ���̱�
		center_panel.add(commenteng);
		center_panel.add(commentkor);

		// �г� ����
		add(new Style().getnewPanel(), BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);

	}

}
