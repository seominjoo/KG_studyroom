

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _07outMessage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Component com;

	public _07outMessage(Component com) {
		this.com = com;
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		JPanel laynull = new JPanel(new GridLayout(2, 0, 0, 0));
		new Style(laynull);	

		Date now = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("a K시 m분 s초");
		
		JLabel commenteng = new JLabel("[현재 시각] " + simple.format(now) , JLabel.CENTER);
		new Style(commenteng);
		JLabel commentkor = new JLabel("퇴실되셨습니다", JLabel.CENTER);
		new Style(commentkor);

		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10,10));
		setBounds(250, 170, 300, 150);

		laynull.add(commenteng);
		laynull.add(commentkor);

		JButton combtn = new JButton("닫기");
		new Style(combtn);
		combtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(new Style().getnewPanel(), BorderLayout.NORTH);
		add(laynull, BorderLayout.CENTER);
		add(combtn, BorderLayout.SOUTH);
	}

}
