package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

		
		JPanel laynull = new JPanel(new GridLayout(2, 0, 0, 0));
		new style(laynull);
		JLabel commenteng = new JLabel("[system] still in maintenance", JLabel.CENTER);
		new style(commenteng);
		JLabel commentkor = new JLabel("페이지 준비 중..", JLabel.CENTER);
		new style(commentkor);
		
		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10,10));
		setBounds(250, 170, 300, 150);
		
		laynull.add(commenteng);
		laynull.add(commentkor);
		
		JButton combtn = new JButton("닫기");
		new style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(new style().getnewPanel(), BorderLayout.NORTH);
		add(laynull, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);
	}

}
