package login.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Style;
import login.mainmenu._00main;
import login.page.MainPage;
import login.swingTools.SwingToolsSubPage;

public class SubWindow extends JFrame {

	static JPanel center_panel;
	static JLabel comment1;
	static JLabel comment2;
	static JButton combtn;
	static JPanel panelInGrid3;
	
	
	public SubWindow(String label1, String label2) {
		String nextcard = "";
		new SubWindow(label1, label2, nextcard);	
	}

	public SubWindow(String label1, String label2, String nextcard) {
		
		SwingToolsSubPage.initTestFrame(this);
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
					MainPage.user_page_panel.add("���θ޴�", new _00main()); // �޴�������
					MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
					MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
					MainPage.userToggle = "���θ޴�";
				} else if (nextcard.equals("�����ڸ޴�")) {
					MainPage.main_cards.show(MainPage.main_page_panel, "�����ڸ޴�");
					MainPage.userToggle = "�����ڸ޴�";
				}
				dispose();

			}
		});

		// �����Ӽ���

		// �����гο� �ڸ�Ʈ���̱�
		add(center_panel, BorderLayout.CENTER);

	}

}
