package login.mainmenu;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class _03whatHour extends JFrame {

	LocalDateTime time_now = LocalDateTime.now(); 
	private JPanel contentPane; 
	 
	public _03whatHour() {
		 
		
		JLabel label01 = new JLabel("<html>&emsp;&emsp;&emsp; &emsp;����ǥ");
		label01.setBounds(0,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		  
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 350);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		contentPane.add(label01);
		
		JButton btn_2hr= new JButton("2�ð� (3,000��)");
		btn_2hr.setBounds(5, 143, 208, 54); 
		contentPane.add(btn_2hr);

		JButton btn_4hr = new JButton("4�ð� (2,000��)");
		btn_4hr.setBounds(218, 143, 213, 54); 
		contentPane.add(btn_4hr);

		JButton btn_6hr = new JButton("6�ð� (2,000��)");
		btn_6hr.setBounds(5, 198, 208, 54); 
		contentPane.add(btn_6hr);

		JButton btn_8hr = new JButton("8�ð� (2,000��)");
		btn_8hr.setBounds(218, 198, 213, 54); 
		contentPane.add(btn_8hr);

		JButton btn_12hr = new JButton("12�ð� (2,000��)");
		btn_12hr.setBounds(5, 253, 208, 54);
		contentPane.add(btn_12hr);
  
		JButton btn_back = new JButton("���� ȭ��");
		btn_back.setBounds(218, 253, 213, 54);
		contentPane.add(btn_back, btn_back);
		

		btn_2hr.addActionListener(new ActionListener() { //���� ������(2�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusHours(2),3000,"2�ð� �̿�� (1�μ�)");
				frame.setVisible(true);
			}
		}); 

		btn_4hr.addActionListener(new ActionListener() { //���� ������(4�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusHours(4),4500,"4�ð� �̿�� (1�μ�)");
				frame.setVisible(true);
			}
		}); 

		btn_6hr.addActionListener(new ActionListener() { //���� ������(6�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusHours(6),6000,"6�ð� �̿�� (1�μ�)");
				frame.setVisible(true);
			}
		});

		btn_8hr.addActionListener(new ActionListener() { //���� ������(8�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusHours(8),7500,"8�ð� �̿�� (1�μ�)");
				frame.setVisible(true);
			}
		});

		btn_12hr.addActionListener(new ActionListener() { //���� ������(12�ð� �̿��)
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_08reservation frame = new _08reservation(time_now.plusHours(12),10000,"12�ð� �̿�� (1�μ�)");
				frame.setVisible(true);
			}
		});

		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_02dayOrWeek frame = new _02dayOrWeek();
				frame.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		_03whatHour frame = new _03whatHour();
		frame.setVisible(true);
	}
}

