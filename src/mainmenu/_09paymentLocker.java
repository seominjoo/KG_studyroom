package mainmenu;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class _09paymentLocker {

	private JFrame frame;
	private JTable table;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
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
	 */
	public _09paymentLocker() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		time_checkout = time_now.plusHours(2).format(dateTimeFormatter);

		String header[] = {"결제","정보"};
		String contents[][]= {
				{"예약 번호",_05selectLocker.number},
				{"입실 시간",time_now.format(dateTimeFormatter)},
				{"퇴실 예정 시간",time_checkout}, 
				{"결제 금액",""}
		};


		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(135, 104, 437, 200);
		table.setRowHeight(40);
		p2.add(table);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("카드");
		rdbtnNewRadioButton.setBounds(361, 332, 121, 23);
		p2.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("현금");
		rdbtnNewRadioButton_1.setBounds(200, 332, 121, 23);
		p2.add(rdbtnNewRadioButton_1);

		JButton back_btn = new JButton("돌아가기");
		back_btn.setBounds(200, 381, 121, 42);
		p2.add(back_btn);

		JButton pay_btn = new JButton("결제하기");
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

					String msg="결제가 완료되었습니다";
					JOptionPane.showMessageDialog(null, msg);

					for(int i=0;i<20;i++) {//(예약 완료 버튼 누를시)
						if( _08selectSeat.seats.get(i).isSelected()&&(_08selectSeat.seats.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크
							_08selectSeat.seats.get(i).setEnabled(false);

							String sql = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";

							pstmt = conn.prepareStatement(sql);

							pstmt.setInt(1, i+1);
							int row = pstmt.executeUpdate();

							//입실/퇴실시간 저장
							String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
							pstmt = conn.prepareStatement(sqlt1);
							pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
							pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
							pstmt.setInt(3, i+1);
							int rowt1 = pstmt.executeUpdate();

							System.out.printf("%d번 자리가 예약되었습니다.(%d행 업데이트)\n", i+1,row);
							System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt1);
						}
						if( _05selectLocker.lockers.get(i).isSelected()&&(_05selectLocker.lockers.get(i).isEnabled()==true)) {
							_05selectLocker.lockers.get(i).setEnabled(false);
							String sql3 = "update locker set Locker_Statement ='사용 중' where Locker_Number= ?";
							pstmt = conn.prepareStatement(sql3);
							pstmt.setInt(1, i+1);
							int row3 = pstmt.executeUpdate();

							String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
							pstmt = conn.prepareStatement(sqlt2);
							pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
							pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
							pstmt.setInt(3, i+1);
							int rowt2 = pstmt.executeUpdate();

							System.out.printf("%d번 사물함이 예약되었습니다.(%d행 업데이트)\n", i+1,row3);
							System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt2);
						}
					}
					for(int i=0;i<4;i++) {
						if(_08selectSeat.room.get(i).isSelected()&&(_08selectSeat.room.get(i).isEnabled()==true)) {
							_08selectSeat.room.get(i).setEnabled(false);
							String sql2 = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, i+101);
							int row2 = pstmt.executeUpdate();

							String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
							pstmt = conn.prepareStatement(sqlt3);
							pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
							pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMinutes(10)));
							pstmt.setInt(3, i+101);
							int rowt3 = pstmt.executeUpdate();

							System.out.printf("%d호 룸이 예약되었습니다.(%d행 업데이트)\n", i+101,row2);
							System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt3); 
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
