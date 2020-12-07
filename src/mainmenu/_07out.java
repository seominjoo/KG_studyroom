package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Action;
import java.awt.GridLayout;

public class _07out extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int Person_Id;
	
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
					_07out frame = new _07out();
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
	public _07out() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1 = new JButton("지금 퇴실하기");
		btnNewButton_1.setAction(action_1);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new _07outMessage(btnNewButton_1));
		
		JButton btnNewButton = new JButton("이전 화면");
		btnNewButton.setAction(action);
		contentPane.add(btnNewButton);
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "지금 퇴실하기");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Date now = new Date();
			SimpleDateFormat simple = new SimpleDateFormat("a K시 m분 s초");
			String time = simple.format(now);
			new DBwrite("INSERT Payment_Record(Exit_Time)"
					+ " VALUES (" + time + ")"
					+ "WHERE Person_Id =" + Integer.toString(Person_Id) + ";");
			new DBwrite("INSERT Person_Info(Person_Statement)"
					+ " VALUES ('퇴실')"
					+ "WHERE Person_Id =" + Integer.toString(Person_Id) + ";");
			
		}
	}
}
