

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class _06moveSeat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_06moveSeat frame = new _06moveSeat();
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
	public _06moveSeat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1 = new JButton("현재 좌석 보기");
		btnNewButton_1.setAction(action_1);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("이전 화면");
		btnNewButton.setAction(action);
		contentPane.add(btnNewButton);
	}

	private class SwingAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "이전 화면");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_01start frame = new _01start();
			frame.setVisible(true);
		}
	}
	private class SwingAction_1 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "현재 좌석 보기");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_06moveSeat2 frame = new _06moveSeat2();
			frame.setVisible(true);
		}
	}
}
