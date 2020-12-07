package mainmenu;

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


public class _06moveSeat2 extends JFrame implements ActionListener{
	 
	private static final long serialVersionUID = 1L;
	boolean selected = false;
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
//	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20번 사물함
//	{
//		for(int i=1;i<=20;i++) {
//			lockers.add(new JCheckBox(i+"",selected));
//		}
//	}

	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	public static String number="";
	JLabel label_msg;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
//	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	
	
	 
	
	JPanel p1 = new JPanel();
	_06moveSeat2() {
 
		JButton OK;
		JLabel label = new JLabel("1인석");
		JLabel label02 = new JLabel("룸");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label02);
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
		}

	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			 
			//시간 비교
			String sqlmt1 = "SELECT seat_number, time_checkout FROM seat "
					+ "WHERE seat_statement ='사용 중'";
			PreparedStatement pstmtt1 = conn.prepareStatement(sqlmt1);
			ResultSet rst1 = pstmtt1.executeQuery();
		 
			while(rst1.next()) {//사용중인 좌석중 퇴실시간이 지나면 사용가능으로 업뎃 
				int seat_chk = rst1.getInt("seat_number");
				Timestamp time_chk = rst1.getTimestamp("time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmtas = conn.prepareStatement(change);
					pstmtas.setInt(1, seat_chk);
					int row3 = pstmtas.executeUpdate();
				}  
			}
	 	 

			
			//좌석 - db에서 '사용 중'인지 읽은 다음 '사용 중'이면 체크박스 체크 및 비활성화(사용중 이므로 예약 못하게) 
			String sqlm = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstmt = conn.prepareStatement(sqlm);
			ResultSet rs = pstmt.executeQuery();
		 
			System.out.print("사용중인 자리 : ");

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
			
			if(rs!=null) rs.close(); 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		OK = new JButton("이동 하기");
		OK.setBounds(230,380,100,50);
		p1.add(OK);
		OK.addActionListener(this);

	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(0,100);
		setVisible(true);  
		
		p1.setBounds(0, 100, 600, 500);
		p1.setLayout(null);
		this.add(p1);
		 
	}

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
 
			String msg="";

			for(int i=0;i<=19;i++) {//이동 할 자리 체크(비활성화 되있는건 제외)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"번 (1인석) 자리로\n"; 
					number+=i+1+"번 좌석 ";
				}
			}
			
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg+=i+101+"호 룸으로\n"; 
					number+=i+101+" 호 룸 ";
				}
			}
				

			msg+="이동하시겠습니까?";
			if(msg.length()<15) {
				msg="이동할 자리를 선택해주세요";
				JOptionPane.showMessageDialog(this,msg);//예약이 없으면 다시선택 메세지 창 띄우기(메세지 길이로 체크)
			}else {
				//이동하기 버튼 누를 시 (이동하시겠습니까?)재확인 -> (창끄기 or 예 or 취소)버튼 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(재 확인 창 끄기) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"취소");//취소 메세지
					 
				}else {   
					
					setVisible(false);
					
					// yes버튼 -> 이동확인 페이지
//					new Payment();
					 
					
				}
			} 

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		new _06moveSeat2(); 
	} 

}
