package studyroom.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import studyroom.design.Style;
import studyroom.swingTools.SwingToolsSubPage;
import studyroom.user.MainPage;
import studyroom.user.usermode.Mainmenu;

public class SubWindow extends JFrame {

	static JPanel center_panel;
	static JLabel comment1;
	static JLabel comment2;
	static JButton combtn;
	static JPanel panelInGrid3;

	// �� �α��� ������ �˸�â
	public SubWindow(String label1, String label2) {
		String nextcard = "";
		new SubWindow(label1, label2, nextcard);
	}

	public SubWindow(String label1, String label2, String nextcard) {

		SwingToolsSubPage.initTestFrame(this);
		new Style(this);
		setLayout(new BorderLayout(10, 0));

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

		comment1.setText(label1);
		comment2.setText(label2);

		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (nextcard.equals("����ڸ޴�")) {
					MainPage.user_page_panel.add("���θ޴�", new Mainmenu()); // �޴�������
					MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
					MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
					MainPage.userToggle = "���θ޴�";
					// �α��� �� ī�巹�̾ƿ� ǥ�� �Ǵ� ������
					MainPage.logoutcard.show(MainPage.logout, "2");
					MainPage.extendcard.show(MainPage.extend, "2");
					MainPage.homecard.show(MainPage.home, "2");
					MainPage.changeUsercard.show(MainPage.changeUser, "2");

				} else if (nextcard.equals("�����ڸ޴�")) {
					MainPage.main_cards.show(MainPage.main_page_panel, "�����ڸ޴�");
					MainPage.userToggle = "�����ڸ޴�";
					// �α��� �� ī�巹�̾ƿ� ǥ�� �Ǵ� ������
					MainPage.logoutcard.show(MainPage.logout, "2");
					MainPage.extendcard.show(MainPage.extend, "2");
					MainPage.changeUsercard.show(MainPage.changeUser, "2");
				}
				dispose();

			}
		});

		// �����Ӽ���

		// �����гο� �ڸ�Ʈ���̱�
		add(center_panel, BorderLayout.CENTER);

	}

}