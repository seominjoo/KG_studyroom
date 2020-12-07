package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class _06moveSeatMessage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Component com;
	int Person_Id;

	public _06moveSeatMessage(Component com) {
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
		JLabel commentkor = new JLabel("이동되셨습니다", JLabel.CENTER);
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


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_06moveSeatMessage frame = new _06moveSeatMessage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public _06moveSeatMessage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
