package login.mainmenu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class _07in_seletSeat extends JFrame implements ActionListener{
 
	 
	static boolean selected = false;
	static ArrayList<JCheckBox> seats = new ArrayList<>(); //1~20번 좌석 (1인석)
	{
		for(int i=1;i<=20;i++) {
			seats.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> room = new ArrayList<>(); //101~104호 (룸)
	{
		for(int i=101;i<=104;i++) {
			room.add(new JCheckBox(i+"호",selected));
		}
	}
	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20번 사물함
	{
		for(int i=1;i<=20;i++) {
			lockers.add(new JCheckBox(i+"",selected));
		}
	}

	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	JLabel label_msg;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	JPanel p1 = new JPanel();
	 
	
	_07in_seletSeat() {
		 
		JButton OK;
		JButton back;
		JLabel label = new JLabel("1인석");
		JLabel label02 = new JLabel("룸");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	 
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label_msg); 
		p1.add(label02);
		
		JLabel label03 = new JLabel("사물함");
		label_msg = new JLabel("");
		label03.setBounds(10,175,50,30);
		label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label_msg); 
		
		for(int i=0; i<10;i++) { // 1인석 체크박스 위치 설정
			seats.get(i).setBounds(20+a,40,40,30);
			p1.add(seats.get(i));
			a+=40;
			
		}
		for(int i=10; i<20;i++) {// 1인석 체크박스 위치 설정
			seats.get(i).setBounds(20+b,70,40,30);
			p1.add(seats.get(i));
			b+=40;
		}
		 
		for(int i=0; i<4;i++) {// 룸 체크박스 위치 설정
			room.get(i).setBounds(20+e,140,100,30);
			p1.add(room.get(i));
			e+=100;
			room.get(i).setEnabled(false); 
		}
		for(int i=0; i<10;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(20+c,200,40,30);
			p1.add(lockers.get(i));
			c+=40;
			lockers.get(i).setEnabled(false);
			
		}
		for(int i=10; i<20;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(60+d,230,40,30);
			p1.add(lockers.get(i));
			d+=40;
			lockers.get(i).setEnabled(false);
			
		}
		
		
		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				for(int i=0;i<20;i++) {
					seats.get(i).setSelected(false);
				} 
				_01start frame = new _01start();
				frame.setVisible(true);
				
			}
		};
		
		back = new JButton("이전 화면");
		back.setBounds(180,380,100,50);
		p1.add(back);
		back.addActionListener(back_btn);
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
		
			//사용중인 좌석이면 체크박스 체크 및 비활성화 
			String sql = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
			 
			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					System.out.printf("%d번 ",sn); 
					seats.get(sn-1).setSelected(true);
					seats.get(sn-1).setEnabled(false);
				}else if (sn>=101) {
					System.out.printf("[%d호] ",sn); 
					room.get(sn-101).setSelected(true);
					room.get(sn-101).setEnabled(false);
				}
			}
			 	
						// 사용중인 사물함이면 체크박스 체크 및 비활성화 
						sql = "select locker_number from locker where locker_statement='사용 중'";
						pstm = conn.prepareStatement(sql);
						rs = pstm.executeQuery();
						System.out.println();
						System.out.printf("예약된 사물함 : ");
						while(rs.next()) {
							int sn = rs.getInt("locker_number");
							System.out.printf("%d번 ",sn);
							lockers.get(sn-1).setSelected(true);
							lockers.get(sn-1).setEnabled(false); 
							 
						}
			
			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		
		OK = new JButton("좌석 선택(입실)");
		OK.setBounds(290,380,100,50);
		p1.add(OK);
		OK.addActionListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(600,150);
		setVisible(true);  
		
		p1.setBounds(0, 100, 600, 500);
		p1.setLayout(null);
		this.add(p1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		int count_only=0; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;

			String msg=""; 
			for(int i=0;i<=19;i++) {//자리 체크(비활성화 되있는건 제외)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"번 (1인석) 자리\n";  
					count_only++; 
			}
		} 
			msg+="예약하시겠습니까?";
			if(count_only==0) {
				msg="예약할 자리를 선택해주세요";
				JOptionPane.showMessageDialog(this,msg);//예약이 없으면 다시선택 메세지 창 띄우기(메세지 길이로 체크)
			}else if(count_only>1) {
				String warning="1인 1선택 가능";
				JOptionPane.showMessageDialog(this,warning); 
			}else {
				// (창끄기 or 예 or 취소)버튼 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(재 확인 창 끄기) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"취소");//취소 메세지
				}else {   
					 
			 // yes버튼 -> 좌석예약
						
		for(int i=0;i<20;i++) {
			if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크
				seats.get(i).setEnabled(false);
													
				//사용중으로 db저장
				String sql = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				int row = pstmt.executeUpdate();

				//입실/퇴실시간 저장
				String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
				pstmt = conn.prepareStatement(sqlt1);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
		 		pstmt.setTimestamp(2, _00main.time_seat);
				pstmt.setInt(3, i+1);
				int rowt1 = pstmt.executeUpdate();
								
				//회원info 테이블에 저장(좌석번호,사물함번호,입실)
				sql = "update person_info set seat_number=? where person_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				pstmt.setInt(2, _00main.id);
								
				int row3 = pstmt.executeUpdate();
								
				System.out.printf("%d번 자리가 예약되었습니다.(%d행 업데이트)\n", i+1,row);
				System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt1); 
				System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
			}
		}
		setVisible(false);
					 
				 
					 
				}
			} 
			 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} 
	} 
	
	
		
	public static void main(String[] args) {
		 
	} 

}

