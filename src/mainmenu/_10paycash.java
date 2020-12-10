package mainmenu;

 

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

public class _10paycash {

	public static int change;
	private JFrame frame; 
	LocalDateTime time_now = LocalDateTime.now();
	 
	public _10paycash(LocalDateTime ss) {
		frame = new JFrame();
		frame.setBounds(600, 150, 420, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 381, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		String price =Integer.toString((_08reservation.price11));

		JLabel payment = new JLabel("결제 금액 : "+price+"원");
		payment.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		payment.setForeground(Color.BLACK);
		payment.setBounds(57, 49, 236, 39);
		panel.add(payment);

		JLabel deposit = new JLabel("입금 금액");
		deposit.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		deposit.setBounds(57, 98, 120, 39);
		panel.add(deposit);

		JTextField cash = new JTextField();
		cash.setBounds(173, 98, 120, 40);
		panel.add(cash);
		cash.setColumns(10);

		JButton pay_btn = new JButton("결제");
		pay_btn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pay_btn.setBounds(116, 181, 140, 39);
		panel.add(pay_btn);

		pay_btn.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(price)<=Integer.parseInt(cash.getText())) {
					
					change = Integer.parseInt(cash.getText())-Integer.parseInt(price);
					String msg= "결제 완료";
					JOptionPane.showMessageDialog(null,msg); 
				   
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:oracle:thin:@localhost:1521/XEPDB1",
								"hr",
								"1234"
								);
						PreparedStatement pstmt = null;

		for(int i=0;i<20;i++) {
			if( _08reservation.seats.get(i).isSelected()&&(_08reservation.seats.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크
				_08reservation.seats.get(i).setEnabled(false);

			String sql = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, i+1);
			int row = pstmt.executeUpdate();

			//입실/퇴실시간 저장
			String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
			pstmt = conn.prepareStatement(sqlt1);
			pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
			pstmt.setInt(3, i+1);
			int rowt1 = pstmt.executeUpdate();
			
			//결제테이블에 저장	
			String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql_pay);
			pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
			pstmt.setString(3, _08reservation.type11);
			pstmt.setString(4, "현금");
			pstmt.setInt(5,_08reservation.price11);
			int rowp = pstmt.executeUpdate();
			
			//회원info 테이블에 저장(좌석번호,사물함번호,입실)
			sql = "update person_info set seat_number=?,Expiration_seat=? where person_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i+1);
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
			pstmt.setInt(3, _00main.id);
			
			int row3 = pstmt.executeUpdate();
			 
			System.out.printf("%d번 자리가 예약되었습니다.(%d행 업데이트)\n", i+1,row);
			System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt1);
			System.out.printf("결제 기록이 업데이트되었습니다.(%d행 업데이트)\n",rowp);
			System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3);
		}
						
	}
		for(int i=0;i<4;i++) {
			 if(_08reservation.room.get(i).isSelected()&&(_08reservation.room.get(i).isEnabled()==true)) {
				 _08reservation.room.get(i).setEnabled(false);
				String sql2 = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, i+101);
				int row2 = pstmt.executeUpdate();

				String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
				pstmt = conn.prepareStatement(sqlt3);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
				pstmt.setInt(3, i+101);
				int rowt3 = pstmt.executeUpdate();
												

				String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql_pay);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
				pstmt.setString(3, _08reservation.type11);
				pstmt.setString(4, "현금");
				pstmt.setInt(5,_08reservation.price11);
				int rowp = pstmt.executeUpdate();

//				회원info 테이블에 저장(좌석번호,사물함번호,입실)
				sql2 = "update person_info set room_number=?,Expiration_room=? where person_id=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, i+1);
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
				pstmt.setInt(3, _00main.id);
				int row5 = pstmt.executeUpdate();
				
				System.out.printf("%d호 룸이 예약되었습니다.(%d행 업데이트)\n", i+101,row2);
				System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt3); 
				System.out.printf("결제 기록이 업데이트되었습니다.(%d행 업데이트)\n",rowp);
				System.out.printf("회원정보가 업데이트되었습니다.(%d행 업데이트)\n",row5);

											
		 }
	}
		for(int i=0;i<20;i++) { 
			 
			if( _08reservation.lockers.get(i).isSelected()&&(_08reservation.lockers.get(i).isEnabled()==true)) {
				_08reservation.lockers.get(i).setEnabled(false);
				String sql3 = "update locker set Locker_Statement ='사용 중' where Locker_Number= ?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, i+1);
				int row3 = pstmt.executeUpdate();
		
				String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
				pstmt = conn.prepareStatement(sqlt2);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setInt(3, i+1);
				int rowt2 = pstmt.executeUpdate();
								
				//결제테이블에 저장
				String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql_pay);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setString(3, "1달 이용권");
				pstmt.setString(4, "현금");
				pstmt.setInt(5,25000);
				int rowp = pstmt.executeUpdate();
				
				//회원info 테이블에 저장(좌석번호,사물함번호,입실)
				sql3 = "update person_info set locker_number=?,Expiration_locker=? where person_id=?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, i+101);
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setInt(3, _00main.id);
				int row1 = pstmt.executeUpdate();

				System.out.printf("%d번 사물함이 예약되었습니다.(%d행 업데이트)\n", i+1,row3);
				System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt2);
				System.out.printf("결제 기록이 업데이트되었습니다.(%d행 업데이트)\n",rowp);
 				System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row1);
				
				}
			}
			
		 new _11receipt(ss,Integer.parseInt(cash.getText()));
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
		frame.setVisible(false);
		 
		 
			} catch (ClassNotFoundException | SQLException e1) { 
				e1.printStackTrace();
			} 
  
		}else {
		int change2 = Integer.parseInt(price)-Integer.parseInt(cash.getText()) ;
		String msg2= Integer.toString(change2)+"원이 부족합니다";
		JOptionPane.showMessageDialog(null,msg2); 
			}
		}
	});
}
	public static void main(String[] args) {
	 
	}
}
