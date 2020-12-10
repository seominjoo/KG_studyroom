package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class _07in extends JFrame {

	private static final long serialVersionUID = 1L;

	int Person_Id=70;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_07in frame = new _07in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public _07in() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton in_btn = new JButton("지금 입실하기");
		contentPane.add(in_btn);
		in_btn.addActionListener(new _07inMessage(in_btn)); //알림창
		
		JButton back_btn = new JButton("이전 화면");
		contentPane.add(back_btn);

		back_btn.addActionListener(new ActionListener() { //이전 페이지
	          @Override
	          public void actionPerformed(ActionEvent e) {
	           setVisible(false);
	           _00main frame = new _00main();
	           frame.setVisible(true);
	          }
	       }); 
	}
}
