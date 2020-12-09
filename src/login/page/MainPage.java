package login.page;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.window.Login_SwingTool;

public class MainPage extends JFrame {
	
	int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	int y = new Conversion_image("image/로그인화면.jpg", 5).y;
	public static JPanel page_panel;
	
	
	public MainPage() {
		// 배경화면
		JPanel fram_panel = new JPanel();
		fram_panel.setLayout(null);
		fram_panel.setBounds(0, 0, x, y);
		
		JLabel background = new JLabel(new Conversion_image("image/로그인화면.jpg", 5).imageicon_smooth);
		background.setBounds(0, 0, x, y);
		fram_panel.add(background);
		
		JButton Poweroff = new JButton(new Conversion_image("image/전원.png", 30	, 30).imageicon_smooth);
		new Style(Poweroff);
		Poweroff.setBounds(0, 0, 30, 30);
		Poweroff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		background.add(Poweroff);
		
		// 낙엽쪽 카드페이지 패널
		page_panel = new JPanel(new CardLayout());
		new Style(page_panel);
		page_panel.setBounds(1577/5, 0, 2423/5,2250/5);
		
		add("로그인", new LoginPage());
		add(fram_panel);
		Login_SwingTool.initFrame(this);
	}
	public static void main(String[] args) {
		new MainPage();
	}
}
