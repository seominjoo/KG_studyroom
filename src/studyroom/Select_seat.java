package studyroom;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Select_seat extends JFrame implements ActionListener{
	 
	
	boolean selected = false;
	static ArrayList<JCheckBox> seats = new ArrayList<>(); //1~20번 좌석 (1인석)
	{
		for(int i=1;i<=20;i++) {
			seats.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20번 사물함
	{
		for(int i=1;i<=20;i++) {
			lockers.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> room = new ArrayList<>(); //101~104호 (룸)
	{
		for(int i=101;i<=104;i++) {
			room.add(new JCheckBox(i+"호",selected));
		}
	}
	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	static String number="";
	JLabel label_msg;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
//	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	
	
	 
	
	JPanel p1 = new JPanel();
	Select_seat() {
  
		
		
		JButton OK;
		JLabel label = new JLabel("1인석");
		JLabel label02 = new JLabel("룸");
		JLabel label03 = new JLabel("사물함");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label02);
		label03.setBounds(10,185,50,30);
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
		for(int i=0; i<10;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(20+c,220,40,30);
			p1.add(lockers.get(i));
			c+=40;
		}
		for(int i=10; i<20;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(20+d,250,40,30);
			p1.add(lockers.get(i));
			d+=40;
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
			String sqlmt1 = "select seat_number,time_checkout from seat where seat_statement='사용 중'";
			PreparedStatement pstmtt1 = 
					conn.prepareStatement(sqlmt1);
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
	 	 
			String sqlmt2 = "select Locker_Number,l_time_checkout from locker where Locker_Statement='사용 중'";
			PreparedStatement pstmtt2 = 
					conn.prepareStatement(sqlmt2);
			ResultSet rst2 = pstmtt2.executeQuery();
		 
			while(rst2.next()) {//사용중인 좌석중 퇴실시간이 지나면 사용가능으로 업뎃 
				int locker_chk = rst2.getInt("Locker_Number");
				Timestamp l_time_chk = rst2.getTimestamp("l_time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstmtas2 = conn.prepareStatement(change2);
					pstmtas2.setInt(1, locker_chk);
					int row4 = pstmtas2.executeUpdate();
				}  
			}
		 
			
			//db에서 '사용 중'인지 읽은 다음 '사용 중'이면 체크박스 체크 및 비활성화(사용중 이므로 예약 못하게) -좌석
			String sqlm = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstmt = 
					conn.prepareStatement(sqlm);
			ResultSet rs = pstmt.executeQuery();
		 
			System.out.print("예약된 자리 : ");

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
			
			//db에서 '사용 중'인지 읽은 다음 '사용 중'이면 체크박스 체크 및 비활성화(사용중 이므로 예약 못하게) -사물함
			String sqlm2 = "select locker_number from locker where locker_statement='사용 중'";
			PreparedStatement pstmt2 = 
					conn.prepareStatement(sqlm2);
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println();
			System.out.printf("예약된 사물함 : ");
			while(rs2.next()) {
				int sn = rs2.getInt("locker_number");

				System.out.printf("%d번 ",sn);
				lockers.get(sn-1).setSelected(true);
				lockers.get(sn-1).setEnabled(false); 
			}
			System.out.println();

			if(rs!=null) rs.close(); 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		OK = new JButton("결제 하기");
		OK.setBounds(230,380,100,50);
		p1.add(OK);
		OK.addActionListener(this);

	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(1250,100);
		setVisible(true);  
		
		p1.setBounds(1250, 100, 600, 500);
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

			for(int i=0;i<=19;i++) {//예약 할 자리 체크(비활성화 되있는건 제외)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"번 (1인석) 자리\n"; 
					number+=i+1+"번 좌석 ";
				}
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg+=i+1+"번 사물함\n"; 
					number+=i+1+"번 사물함 ";
				}
			}
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg+=i+101+"호 룸\n"; 
					number+=i+101+" 호 룸 ";
				}
			}


			msg+="결제 하시겠습니까?";
			if(msg.length()<15) {
				msg="결제할 자리 및 사물함을 선택해주세요";
				JOptionPane.showMessageDialog(this,msg);//예약이 없으면 다시선택 메세지 창 띄우기(메세지 길이로 체크)
			}else {
				//예약하기 버튼 누를 시 (예약 하시겠습니까?)재확인 -> (창끄기 or 예 or 취소)버튼 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(재 확인 창 끄기) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"취소");//취소 메세지
					 
				}else {   
					
					setVisible(false);
					
					// yes버튼 -> 결제페이지 
					new Payment();
					 
					
				}
			} 

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		new Select_seat();
		 
		 
	} 
}
