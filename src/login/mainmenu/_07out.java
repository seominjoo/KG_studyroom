package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.Action;
import java.awt.GridLayout;

public class _07out extends JFrame {
	
	private JPanel contentPane;
	int num_seat;
	int room_seat;
	int room_seat1;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	public _07out() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,3, 0, 0));
		 
		JButton out_seat = new JButton("좌석 퇴실하기");
		contentPane.add(out_seat);
		
		JButton out_room = new JButton("룸 퇴실하기");
		contentPane.add(out_room);
		
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 Connection conn = DriverManager.getConnection(
			               "jdbc:oracle:thin:@localhost:1521/XEPDB1",
			               "hr",
			               "1234"
			               );
		out_seat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  
			 //로그인 된 회원의 좌석번호 확인
			sql = "SELECT seat_number,seat_type from person_info where login_state = 'On'";
			 
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
		         
				 while(rs.next()) { 
					 num_seat = rs.getInt("seat_number");
					  type = rs.getString("seat_type");
				 }
				 sql = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
			     pstmt = conn.prepareStatement(sql);
			     pstmt.setInt(1, num_seat);
			     int row3 = pstmt.executeUpdate();
			     if(num_seat==0) {
			    		String msg= "결제한 좌석이 없습니다";
						JOptionPane.showMessageDialog(null,msg); 
			     }else {
			     System.out.printf("%d번 좌석이 퇴실 되었습니다.(%d행 업데이트)\n",num_seat,row3);
			     
			     //정기이용권 사용자는 퇴실 시 좌석만 삭제
			     if(type.equals("정기 이용권")) {
			     sql = "update person_info set seat_number=null where login_state = 'On'";
			     pstmt = conn.prepareStatement(sql);
			     int row5 = pstmt.executeUpdate();
			     System.out.printf("(%d행 업데이트)\n",row5);	
			     }
			     //일일이용권 사용자는 만료시간 리셋
			     else { 
			     sql = "update person_info set seat_number=null,expiration_seat='20/01/01 00:00:00.000000000' where login_state = 'On'";
				 pstmt = conn.prepareStatement(sql);
				 int row5 = pstmt.executeUpdate();
				 System.out.printf("(%d행 업데이트)\n",row5);	
			     }
			   }
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}	
			}
		});   
		
		out_room.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
			 
				try {
					 
					 
			    	sql = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, _00main.room_chk);
					int row3 = pstmt.executeUpdate();
					  if(_00main.room_chk==0) {
				    		String msg= "결제한 좌석이 없습니다";
							JOptionPane.showMessageDialog(null,msg); 
				     }else {
					System.out.printf("%d번 룸이 퇴실 되었습니다.(%d행 업데이트)\n",_00main.room_chk,row3);
					
					sql = "update person_info set room_number=null,expiration_room='20/01/01 00:00:00.000000000' where login_state = 'On'";
					pstmt = conn.prepareStatement(sql);
					int row5 = pstmt.executeUpdate();
					System.out.printf("(%d행 업데이트)\n",row5);	
				     }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		
 
		
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
	public static void main(String[] args) {
		_07out frame = new _07out();
		frame.setVisible(true);
	}
}
