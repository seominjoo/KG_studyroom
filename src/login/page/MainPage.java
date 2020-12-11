package login.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPage;
import login.signUp.SignUpPage;
import login.window.ActionWindow;
import login.window.Login_SwingTool;

public class MainPage extends JFrame {

	int x = new Conversion_image("image/�α���ȭ��.jpg", 5).x;
	int y = new Conversion_image("image/�α���ȭ��.jpg", 5).y;
	public static JPanel page_panel;
	public static CardLayout cards;

	public MainPage() {
		// ���ȭ��
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);

		JLabel background = new JLabel(new Conversion_image("image/�α���ȭ��.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);

		// ������ ī�������� �г�
		page_panel = new JPanel();
		cards = new CardLayout();
		page_panel.setLayout(cards);
		new Style(page_panel);
		page_panel.setBounds(1577 / 5, 0, 2423 / 5, 2250 / 5);
		
		JPanel main = new JPanel(new BorderLayout());
		new Style(main);
		JButton touch = new JButton("<html>��ġ�� �Ͽ�<br/>�̿����ּ���</html>");
		new Style(touch);
		touch.setBorder(null);
		touch.addActionListener(new ActionWindow(touch));
		main.add(touch);

		// ������ �߰� �۾�
		page_panel.add("����", main);
		page_panel.add("�α���", new LoginPage());
//				MainPage.page_panel.add("������", admin_panel);
		page_panel.add("ȸ������", new SignUpPage());
		page_panel.add("���ã��", new FindPasswordPage());

		JButton Poweroff = new JButton(new Conversion_image("image/����.png", 30, 30).imageicon_smooth);
		new Style(Poweroff);
		Poweroff.setText("������");
		Poweroff.setBounds(0, 0, 30, 30);
		Poweroff.addActionListener(new ActionWindow(Poweroff));
//		Poweroff.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
		background.add(Poweroff);
		fram_panel.add(background);

		Login_SwingTool.initFrame(this);

		add(page_panel);
		add(fram_panel);
		Login_SwingTool.initFrame(this);
	}

	public static void main(String[] args) {
		new MainPage();
	}
}
