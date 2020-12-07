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

public class _05selectLocker extends JFrame implements ActionListener{
	 
	private static final long serialVersionUID = 1L;
	boolean selected = false;

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
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
//	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	
	
	 
	
	JPanel p1 = new JPanel();
	_05selectLocker() {
 
		JButton OK;
		JLabel label03 = new JLabel("사물함");
		label_msg = new JLabel("");
		label03.setBounds(10,185,50,30);
		label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label_msg); 


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

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			 
			//시간 비교
	 	 
			String sqlmt2 = "SELECT Locker_Number,l_time_checkout FROM locker "
					+ "WHERE Locker_Statement='사용 중'";
			PreparedStatement pstmtt2 = conn.prepareStatement(sqlmt2);
			ResultSet rst2 = pstmtt2.executeQuery();
		 
			while(rst2.next()) {//사용중인 사물함 중 이용기간이 지나면 사용가능으로 업뎃 
				int locker_chk = rst2.getInt("Locker_Number");
				Timestamp l_time_chk = rst2.getTimestamp("l_time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstmtas2 = conn.prepareStatement(change2);
					pstmtas2.setInt(1, locker_chk);
					int row4 = pstmtas2.executeUpdate();
				}  
			}
		 
				
			//사물함 - db에서 '사용 중'인지 읽은 다음 '사용 중'이면 체크박스 체크 및 비활성화(사용중 이므로 예약 못하게)
			String sqlm2 = "select locker_number from locker where locker_statement='사용 중'";
			PreparedStatement pstmt2 = conn.prepareStatement(sqlm2);
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

			if(rs2!=null) rs2.close(); 
			if (pstmt2 != null) pstmt2.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		OK = new JButton("결제하기");
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
			
			for(int i=0;i<=19;i++) {
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg+=i+1+"번 사물함\n"; 
					number+=i+1+"번 사물함 ";
				}
			}
			

			msg+="결제 하시겠습니까?";
			if(msg.length()<15) {
				msg="결제할 사물함을 선택해주세요";
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
					new _09paymentLocker();
					 				
				}
			} 

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		new _05selectLocker(); 
	} 
}

	
	


