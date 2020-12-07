

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
		SimpleDateFormat simple = new SimpleDateFormat("a K�� m�� s��");
		
		JLabel commenteng = new JLabel("[���� �ð�] " + simple.format(now) , JLabel.CENTER);
		new Style(commenteng);
		JLabel commentkor = new JLabel("��ǵǼ̽��ϴ�", JLabel.CENTER);
		new Style(commentkor);

		SwingTool_logo.initFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(10,10));
		setBounds(250, 170, 300, 150);

		laynull.add(commenteng);
		laynull.add(commentkor);

		JButton combtn = new JButton("�ݱ�");
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
