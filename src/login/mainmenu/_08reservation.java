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

import login.design.Style;


public class _08reservation extends JPanel implements ActionListener{
	 static LocalDateTime time11;
	 static int price11; 
	 static int price; 
	 static int price_r; 
	 static String type11;
	 
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
	public static String number="";
	JLabel label_msg;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	 
	
	public _08reservation(LocalDateTime ss,int price,String seat_type) {
		 
		new Style(this);
		JButton OK;
		JButton back;
		JLabel label = new JLabel("1인석");
		JLabel label02 = new JLabel("룸");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	 
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.add(label_msg); 
		this.add(label02);
		
		JLabel label03 = new JLabel("사물함");
		label_msg = new JLabel("");
		label03.setBounds(10,175,50,30);
		label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.add(label_msg); 
		
		for(int i=0; i<10;i++) { // 1인석 체크박스 위치 설정
			seats.get(i).setBounds(20+a,40,40,30);
			this.add(seats.get(i));
			a+=40;
			if(!(price>=3000&&price<=10000||price>=90000)) {
				seats.get(i).setEnabled(false);
			}
		}
		for(int i=10; i<20;i++) {// 1인석 체크박스 위치 설정
			seats.get(i).setBounds(20+b,70,40,30);
			this.add(seats.get(i));
			b+=40;
			if(!(price>=3000&&price<=10000||price>=90000)) {
				seats.get(i).setEnabled(false);
			}
		}
		 
		for(int i=0; i<4;i++) {// 룸 체크박스 위치 설정
			room.get(i).setBounds(20+e,140,100,30);
			this.add(room.get(i));
			e+=100;
			if(!(price>=12000&&price<=40000&&price!=25000))
				room.get(i).setEnabled(false); 
		}
		for(int i=0; i<10;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(20+c,200,40,30);
			this.add(lockers.get(i));
			c+=40;
			if(price!=25000) {
				lockers.get(i).setEnabled(false);
			}
		}
		for(int i=10; i<20;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(60+d,230,40,30);
			this.add(lockers.get(i));
			d+=40;
			if(price!=25000) {
				lockers.get(i).setEnabled(false);
			}
		}
		
		

		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				for(int i=0;i<20;i++) {
					seats.get(i).setSelected(false);
				} 
				for(int i=0;i<4;i++) {
					room.get(i).setSelected(false);
				}
				for(int i=0;i<20;i++) {
					lockers.get(i).setSelected(false);
				} 
				_01start frame = new _01start();
				frame.setVisible(true);
				 number="";
			}
		};
		
		back = new JButton("이전 화면");
		back.setBounds(180,380,100,50);
		this.add(back);
		back.addActionListener(back_btn);
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
//			//시간 비교
//			String sql = "SELECT seat_number, time_checkout FROM seat "
//					+ "WHERE seat_statement ='사용 중'";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			ResultSet rs = pstm.executeQuery();
//		 
//			while(rs.next()) {//퇴실 시간 지난 좌석 퇴실처리
//				int seat_chk = rs.getInt("seat_number");
//				Timestamp time_chk = rs.getTimestamp("time_checkout");
//				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
//					String change = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
//					PreparedStatement pstmtas = conn.prepareStatement(change);
//					pstmtas.setInt(1, seat_chk);
//					int row3 = pstmtas.executeUpdate();
//				}  
//			} 
			
			//사용중인 좌석이면 체크박스 체크 및 비활성화 
			String sql = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
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
			
//			// 사용중인 사물함 중 이용기간이 지나면 사용가능으로 업데이트
//						sql = "SELECT Locker_Number,l_time_checkout FROM locker "
//								+ "WHERE Locker_Statement='사용 중'";
//						 pstm = conn.prepareStatement(sql);
//						 rs = pstm.executeQuery();
//					 
//						while(rs.next()) { 
//							int locker_chk = rs.getInt("Locker_Number");
//							Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
//							if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
//								String change = "update locker set Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
//								PreparedStatement pstm2 = conn.prepareStatement(change);
//								pstm2.setInt(1, locker_chk);
//								int row4 = pstm2.executeUpdate();
//							}  
//						}
					 
							
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
		
		OK = new JButton("결제하기");
		OK.setBounds(290,380,100,50);
		this.add(OK);
		OK.addActionListener(this);

		setSize(600,500);
		setLocation(600,150);
		
		this.setBounds(0, 100, 600, 500);
		this.setLayout(null);
		time11  = ss;
		price11 = price; 
		type11 = seat_type;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		int count_only=0;
		int count_only2=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;

			String msg="";
			 
			price=0;
			if((price11>=3000&&price11<=10000||price11>=90000)) {
			for(int i=0;i<=19;i++) {//자리 체크(비활성화 되있는건 제외)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"번 (1인석) 자리\n"; 
					number+=i+1+"번 좌석 ";
					 
					price+=price11;
					count_only++;
				}
			}
		}
			if(price11>=12000&&price11<=40000&&price11!=25000) {
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg=i+101+"호 룸\n"; 
					number+=i+101+" 호 룸 ";
					 
					price+=price11;
					count_only++;
					 
				}
			}
		}
			if(price11==25000) {
			for(int i=0;i<=19;i++) {
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg=i+1+"번 사물함\n"; 
					number+=i+1+"번 사물함 ";
					price+=price11;
					count_only++;
				} 
			}
		}
			 
			msg+="결제하시겠습니까?";
			if(count_only==0) {
				msg="결제할 자리를 선택해주세요";
				JOptionPane.showMessageDialog(this,msg);//예약이 없으면 다시선택 메세지 창 띄우기(메세지 길이로 체크)
			}else if(count_only>1) {
				String warning="   1인 1선택 가능";
				JOptionPane.showMessageDialog(this,warning);
				number="";
			}else {
				// (창끄기 or 예 or 취소)버튼 
			
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(재 확인 창 끄기) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"취소");//취소 메세지
				}else {   
					setVisible(false);
					// yes버튼 -> 결제 페이지
					 
					new _09payment(time11,price,type11);
					 
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

