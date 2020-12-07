package mainmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class _01start extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_01start frame = new _01start();
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
	public _01start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{213, 213, 0};
		gbl_contentPane.rowHeights = new int[]{126, 126, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton_2 = new JButton("좌석 이용권");
		btnNewButton_2.setIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
		btnNewButton_2.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
		btnNewButton_2.setAction(action);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton = new JButton("사물함 이용권");
		btnNewButton.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/locker.png")));
		btnNewButton.setAction(action_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("자리 이동");
		btnNewButton_1.setIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
		btnNewButton_1.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/move.png")));
		btnNewButton_1.setAction(action_2);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("퇴실하기");
		btnNewButton_3.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/out.png")));
		btnNewButton_3.setAction(action_3);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 1;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SwingAction() {
			putValue(NAME, "좌석 이용권");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_02dayOrWeek frame = new _02dayOrWeek();
			frame.setVisible(true);
		}

	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "사물함 이용권");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_05locker frame = new _05locker();
			frame.setVisible(true);
			
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "자리 이동");
			putValue(SHORT_DESCRIPTION, "Some short description");
			Image img;
			   try {
			    img = ImageIO.read(new File("image/move.png"));
			    Image resizedImage = img.getScaledInstance(40, 40,
			      Image.SCALE_DEFAULT);
			    ImageIcon ic = new ImageIcon(resizedImage);
			    this.putValue(Action.SMALL_ICON, ic);
			   } catch (IOException e) {
			   }
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_06moveSeat frame = new _06moveSeat();
			frame.setVisible(true);
			
		}
	}
	
	private class SwingAction_3 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "퇴실하기");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_07out frame = new _07out();
			frame.setVisible(true);
		}
	}
}
