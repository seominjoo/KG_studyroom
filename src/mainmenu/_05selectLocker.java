package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;

public class _05selectLocker extends JFrame implements ActionListener{
	
	private JPanel p1;
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
	
 
	
	_05selectLocker() {
		
		p1 = new JPanel();
		 
		JLabel label03 = new JLabel("사물함");
		label_msg = new JLabel("");
		label03.setBounds(60,120,50,30);
		label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p1.add(label_msg); 
		

		for(int i=0; i<10;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(60+c,170,40,30);
			p1.add(lockers.get(i));
			c+=40;
		}
		for(int i=10; i<20;i++) {// 사물함 체크박스 위치 설정
			lockers.get(i).setBounds(60+d,200,40,30);
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
			 
			// 사용중인 사물함 중 이용기간이 지나면 사용가능으로 업데이트
			String sql = "SELECT Locker_Number,l_time_checkout FROM locker "
					+ "WHERE Locker_Statement='사용 중'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
		 
			while(rs.next()) { 
				int locker_chk = rs.getInt("Locker_Number");
				Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change = "update locker set Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstm2 = conn.prepareStatement(change);
					pstm2.setInt(1, locker_chk);
					int row4 = pstm2.executeUpdate();
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
			System.out.println();

			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		 
		JButton btn_pay = new JButton("결제하기");
		btn_pay.setBounds(310,380,100,50);
		p1.add(btn_pay);
		btn_pay.addActionListener(this);
 
		 
		
		JButton btn_back = new JButton("이전 화면");
		btn_back.setBounds(160,380,100,50);
		p1.add(btn_back);
		btn_back.addActionListener(new ActionListener() { //이전 페이지
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_01start frame = new _01start();
				frame.setVisible(true);
			}
		});
	 
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
	} 
 
	public static void main(String[] args) {
		_05selectLocker frame = new _05selectLocker();
		frame.setVisible(true);
	}  
}
