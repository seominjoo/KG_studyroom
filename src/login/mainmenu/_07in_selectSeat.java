package login.mainmenu;

import java.awt.Color;
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
import login.page.MainPage;


public class _07in_selectSeat extends JPanel implements ActionListener{
 
	 
	static ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼
	{
		for(int i=0;i<20;i++) {
			seats_btn.add(new JButton());
		}
	 }
	static ArrayList<JButton> room_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼
	{
		for(int i=0;i<4;i++) {//0~3
			room_btn.add(new JButton()); 
		}
	}
	static ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼
	{
		for(int i=0;i<20;i++) {
			locker_btn.add(new JButton()); 
		}
	}

	int f=0;
	int g=0;
	int c=0;
	int d=0;
	int e=0; 
	int a=0;
	JLabel label_msg;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	 
	
	public _07in_selectSeat() {
		 
		new Style(this);
		JButton OK;
		JButton back;
//		JLabel label = new JLabel("1인석");
//		JLabel label02 = new JLabel("룸");
//		label_msg = new JLabel("");
//		label.setBounds(10,10,50,30);
//		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		this.add(label);
//		label02.setBounds(10,100,50,30);
//		label02.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//	 
//		label_msg.setBounds(200,310,500,30);
//		label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		this.add(label_msg); 
//		this.add(label02);
		
		JLabel label03 = new JLabel("사물함");
	 
		label03.setBounds(15,320,50,30);
		label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.add(label03); 
		new Style(label03);
		
		JLabel label04 = new JLabel("휴게실");
		label04.setOpaque(true);
		label04.setBackground(Color.gray);
		label04.setBounds(250,225,180,85);
		label04.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label04.setHorizontalAlignment(JLabel.CENTER);
		this.add(label04);
		
		JLabel label05 = new JLabel("선택 가능");
		label05.setOpaque(true);
		label05.setBackground(Color.black);
		label05.setForeground(Color.orange);
		label05.setHorizontalAlignment(JLabel.CENTER);
		label05.setBounds(40,435,100,30);
		this.add(label05);
		
		JLabel label06 = new JLabel("선택 불가");
		label06.setOpaque(true);
		label06.setBackground(Color.black);
		label06.setForeground(Color.gray);
		label06.setHorizontalAlignment(JLabel.CENTER);
		label06.setBounds(40,470,100,30);
		this.add(label06);

		for(int i=0;i<3;i++) {// 1인석 버튼 위치 설정

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(30+f,10,60,60); 
			f+=60;  
		}

		for(int i=3;i<6;i++) {// 1인석 버튼 위치 설정

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+f,10,60,60); 
			f+=60;  
		}
		for(int i=6;i<11;i++) {// 1인석 버튼 위치 설정

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(120+f,10+a,60,60); 
			a+=60;
		}
		for(int i=11; i<14;i++) { // 1인석 버튼 위치 설정
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(30+g,70,60,60);
			g+=60;  
		}
		for(int i=14; i<17;i++) { // 1인석 버튼 위치 설정
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+g,70,60,60);
			g+=60;  
		}
		g-=180;
		for(int i=17; i<20;i++) { // 1인석 버튼 위치 설정
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"번");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+g,160,60,60);
			g+=60;  
		}

		for(int i=0;i<2;i++) {//0~3
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"호");
			room_btn.get(i).setForeground(Color.orange);
			this.add(room_btn.get(i));
			room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
			room_btn.get(i).setBounds(30+e,160,90,75);
			e+=90; 
			room_btn.get(i).setEnabled(false);
		}
		for(int i=2;i<4;i++) {//0~3
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"호");
			room_btn.get(i).setForeground(Color.orange);
			this.add(room_btn.get(i));
			room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
			room_btn.get(i).setBounds(30+d,235,90,75);
			d+=90; 
			room_btn.get(i).setEnabled(false);
		}
		for(int i=0;i<10;i++) {// 사물함 버튼 위치 설정
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"번");
			locker_btn.get(i).setForeground(Color.orange);
			this.add(locker_btn.get(i));
			locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
			locker_btn.get(i).setBounds(10+c,350,60,30);
			c+=55;
			locker_btn.get(i).setEnabled(false);
		}	
		d=0;
		for(int i=10; i<20;i++) { // 사물함 버튼 위치 설정

			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"번");
			locker_btn.get(i).setForeground(Color.orange);
			this.add(locker_btn.get(i));
			locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
			locker_btn.get(i).setBounds(10+d,380,60,30);
			d+=55;   
			locker_btn.get(i).setEnabled(false);
		}

		
		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				for(int i=0;i<20;i++) {
					seats_btn.get(i).setSelected(false);
				} 
				
				MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
				MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
				MainPage.userToggle = "메인메뉴";
				
			}
		};
		
		back = new JButton("이전 화면");
		back.setBounds(200,430,150,80);
		this.add(back);
		back.addActionListener(back_btn);
		new Style(back);
	
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
					seats_btn.get(sn-1).setSelected(true);
					seats_btn.get(sn-1).setEnabled(false);
				}else if (sn>=101) {
					System.out.printf("[%d호] ",sn); 
					room_btn.get(sn-101).setSelected(true);
					room_btn.get(sn-101).setEnabled(false);
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
							locker_btn.get(sn-1).setSelected(true);
							locker_btn.get(sn-1).setEnabled(false); 
							 
						}
			
			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		
		OK = new JButton("좌석 선택(입실)");
		OK.setBounds(360,430,150,80);
		this.add(OK);
		OK.addActionListener(this);
		new Style(OK);
		setSize(600,500);
		setLocation(600,150);
		setVisible(true);  
		
		this.setBounds(0, 100, 600, 500);
		this.setLayout(null);
		
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
				if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) { 
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
			if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크
				seats_btn.get(i).setEnabled(false);
													
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

