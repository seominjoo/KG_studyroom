package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification extends JFrame implements ActionListener {

	Component com;

	public Notification(Component com) {
		this.com = com;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		SwingTool_logo.initFrame(this);
		setLayout(new BorderLayout());
		setBounds(250, 170, 300, 150);
		JPanel laynull = new JPanel();
		JLabel commenteng = new JLabel("[system] still in maintenance", JLabel.CENTER);
		commenteng.setBounds(x, y, width, height);
		JLabel commentkor = new JLabel("페이지 준비 중..", JLabel.CENTER);
		
		laynull.add(commenteng);
		laynull.add(commentkor);
		add(commentkor, BorderLayout.CENTER);
		
		JButton combtn = new JButton("닫기");
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(combtn, BorderLayout.SOUTH);
	}

}
