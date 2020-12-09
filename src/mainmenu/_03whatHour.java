package mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class _03whatHour extends JFrame {

	/**
	 * 
	 */
	LocalDateTime time_now = LocalDateTime.now();
	private static final long serialVersionUID = 1L;
	int Person_Id;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_03whatHour frame = new _03whatHour();
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
	public _03whatHour() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{213, 213, 0};
		gbl_contentPane.rowHeights = new int[]{84, 84, 84, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton_2 = new JButton("2�ð� (3,000��)");
		btnNewButton_2.setAction(action);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton = new JButton("4�ð� (4,500��)");
		btnNewButton.setAction(action_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("6�ð� (6,000��)");
		btnNewButton_1.setAction(action_2);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("8�ð� (7,500��)");
		btnNewButton_4.setAction(action_3);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 1;
		contentPane.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("12�ð� (10,000��)");
		btnNewButton_3.setAction(action_4);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 2;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("���� ȭ��");
		btnNewButton_5.setAction(action_5);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 2;
		contentPane.add(btnNewButton_5, gbc_btnNewButton_5);
	}

	private class SwingAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "2�ð� (3,000��)");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_08selectSeat frame = new _08selectSeat(time_now.plusHours(2),3000,"2�ð� �̿��");
			frame.setVisible(true);
			 
		}
	}
	
	private class SwingAction_1 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "4�ð� (4,500��)");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_08selectSeat frame = new _08selectSeat(time_now.plusHours(4),4500,"4�ð� �̿��");
			frame.setVisible(true);
		 
		}
	}
	private class SwingAction_2 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "6�ð� (6,000��)");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_08selectSeat frame = new _08selectSeat(time_now.plusHours(6),6000,"6�ð� �̿��");
			frame.setVisible(true);
			 
		}
	}
	private class SwingAction_3 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "8�ð� (7,500��)");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_08selectSeat frame = new _08selectSeat(time_now.plusHours(8),7500,"8�ð� �̿��");
			frame.setVisible(true);
		 
		}
	}
	private class SwingAction_4 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_4() {
			putValue(NAME, "12�ð� (10,000��)");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_08selectSeat frame = new _08selectSeat(time_now.plusHours(12),10000,"12�ð� �̿��");
			frame.setVisible(true);
		 
		}
		
	}
	private class SwingAction_5 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_5() {
			putValue(NAME, "���� ȭ��");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_02dayOrWeek frame = new _02dayOrWeek();
			frame.setVisible(true);
		}
	}
}
