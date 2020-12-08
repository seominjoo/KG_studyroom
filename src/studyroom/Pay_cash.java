package studyroom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JButton;

public class Pay_cash {

	private JFrame frame;//jk
	private JLabel lblNewLabel_1;
	private JTextField cash;
	LocalDateTime time_now = LocalDateTime.now();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pay_cash window = new Pay_cash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pay_cash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 200, 420, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 381, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		String price ="10000";

		JLabel lblNewLabel = new JLabel("���� �ݾ� : "+price+"��");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(57, 49, 236, 39);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("\uC785\uAE08 \uAE08\uC561");
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 18));
		lblNewLabel_1.setBounds(57, 98, 120, 39);
		panel.add(lblNewLabel_1);

		cash = new JTextField();
		cash.setBounds(173, 98, 120, 40);
		panel.add(cash);
		cash.setColumns(10);

		JButton pay_btn = new JButton("\uACB0\uC81C");
		pay_btn.setFont(new Font("���� ���", Font.BOLD, 15));
		pay_btn.setBounds(116, 181, 140, 39);
		panel.add(pay_btn);

		pay_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(price)<=Integer.parseInt(cash.getText())) {
					
					int change = Integer.parseInt(cash.getText())-Integer.parseInt(price);
					String msg= "�Ž��� �� : "+Integer.toString(change)+"��\n ���� �Ϸ�";
					JOptionPane.showMessageDialog(null,msg); 
				  
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:oracle:thin:@localhost:1521/XEPDB1",
								"hr",
								"1234"
								);
						PreparedStatement pstmt = null;




						for(int i=0;i<20;i++) {//(ī�� ���� ��ư ������)
							if( Select_seat.seats.get(i).isSelected()&&(Select_seat.seats.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
								Select_seat.seats.get(i).setEnabled(false);

								String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";

								pstmt = conn.prepareStatement(sql);

								pstmt.setInt(1, i+1);
								int row = pstmt.executeUpdate();

								//�Խ�/��ǽð� ����
								String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
								pstmt = conn.prepareStatement(sqlt1);
								pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
								pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
								pstmt.setInt(3, i+1);
								int rowt1 = pstmt.executeUpdate();

								System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
								System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt1);
							}
							if( Select_seat.lockers.get(i).isSelected()&&(Select_seat.lockers.get(i).isEnabled()==true)) {
								Select_seat.lockers.get(i).setEnabled(false);
								String sql3 = "update locker set Locker_Statement ='��� ��' where Locker_Number= ?";
								pstmt = conn.prepareStatement(sql3);
								pstmt.setInt(1, i+1);
								int row3 = pstmt.executeUpdate();

								String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
								pstmt = conn.prepareStatement(sqlt2);
								pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
								pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
								pstmt.setInt(3, i+1);
								int rowt2 = pstmt.executeUpdate();

								System.out.printf("%d�� �繰���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row3);
								System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt2);
							}
						}
						for(int i=0;i<4;i++) {
							if(Select_seat.room.get(i).isSelected()&&(Select_seat.room.get(i).isEnabled()==true)) {
								Select_seat.room.get(i).setEnabled(false);
								String sql2 = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
								pstmt = conn.prepareStatement(sql2);
								pstmt.setInt(1, i+101);
								int row2 = pstmt.executeUpdate();

								String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
								pstmt = conn.prepareStatement(sqlt3);
								pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
								pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
								pstmt.setInt(3, i+101);
								int rowt3 = pstmt.executeUpdate();

								System.out.printf("%dȣ ���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+101,row2);
								System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt3); 

								JOptionPane.showMessageDialog(null,"���� �Ϸ�");  
							}
							
							System.exit(0);
						} 
						if (pstmt != null) pstmt.close();
						if (conn != null) conn.close();
					} catch (ClassNotFoundException | SQLException e1) { 
						e1.printStackTrace();
					} 
 
					 
				}else {
					int change2 = Integer.parseInt(price)-Integer.parseInt(cash.getText()) ;
					String msg2= Integer.toString(change2)+"���� �����մϴ�";
					JOptionPane.showMessageDialog(null,msg2); 
				}

			}
		});


	}
}
