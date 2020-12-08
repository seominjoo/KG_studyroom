package mainmenu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class _09paymentLocker {

	private JFrame frame;
	private JTable table;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_09paymentLocker window = new _09paymentLocker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 
	/**
	 * Initialize the contents of the frame.
	 */
	  _09paymentLocker() {
		frame = new JFrame();
		frame.setSize(750,500);
		frame.setLocation(1050,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setVisible(true);

		JPanel p2 = new JPanel();
		p2.setBounds(12, 10, 706, 453);
		frame.getContentPane().add(p2);
		p2.setLayout(null);

		time_checkout = time_now.plusMonths(1).format(dateTimeFormatter);

		String header[] = {"����","����"};
		String contents[][]= {
				{"���� ��ȣ",_05selectLocker.number},
				{"��� ���� �ð�",time_now.format(dateTimeFormatter)},
				{"��� ���� �ð�",time_checkout}, 
				{"���� �ݾ�","25,000"}
		};


		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(135, 104, 437, 200);
		table.setRowHeight(40);
		p2.add(table);
		
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		p2.add(table);
		
		JRadioButton card_btn = new JRadioButton("ī��");
		card_btn.setBounds(361, 332, 121, 23);
		p2.add(card_btn);

		JRadioButton cash_btn = new JRadioButton("����");
		cash_btn.setBounds(200, 332, 121, 23);
		p2.add(cash_btn);
		
		ButtonGroup group = new ButtonGroup();
		group.add(cash_btn);
		group.add(card_btn);

		JButton back_btn = new JButton("���ư���");
		back_btn.setBounds(200, 381, 121, 42);
		p2.add(back_btn);

		JButton pay_btn = new JButton("�����ϱ�");
		pay_btn.setBounds(361, 381, 121, 42);
		p2.add(pay_btn);

		pay_btn.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521/XEPDB1",
							"hr",
							"1234"
							);
					PreparedStatement pstmt = null;

			 
					if(cash_btn.isSelected()) {// ���ݰ���
						new _12paycash_locker();
						 
					}
					 
					
					if(card_btn.isSelected()==true) {//ī�� ����
					int result= JOptionPane.showConfirmDialog(null, "ī�带 �����ϼ���","Message",JOptionPane.YES_NO_OPTION);
						if(result==JOptionPane.CLOSED_OPTION) {
							
						}else if(result==JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null,"���");//��� �޼���
						}else {
					for(int i=0;i<20;i++) {//(���� �Ϸ� ��ư ������)
						
						
						if( _05selectLocker.lockers.get(i).isSelected()&&(_05selectLocker.lockers.get(i).isEnabled()==true)) {
							_05selectLocker.lockers.get(i).setEnabled(false);
							String sql3 = "update locker set Locker_Statement ='��� ��' where Locker_Number= ?";
							pstmt = conn.prepareStatement(sql3);
							pstmt.setInt(1, i+1);
							int row3 = pstmt.executeUpdate();

							String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
							pstmt = conn.prepareStatement(sqlt2);
							pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
							pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
							pstmt.setInt(3, i+1);
							int rowt2 = pstmt.executeUpdate();

							System.out.printf("%d�� �繰���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row3);
							System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt2);
						}
					}
						}
					}
					
					frame.setVisible(false);

					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (ClassNotFoundException | SQLException e1) { 
					e1.printStackTrace();
				} 

			}
		});

		back_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new _05selectLocker();

			}
		});


	}
}
