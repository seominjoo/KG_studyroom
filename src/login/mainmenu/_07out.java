package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.page.MainPage;

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

public class _07out extends JPanel {
	
	int num_seat;
	int room_seat;
	int room_seat1;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	public _07out() {
		
		new Style(this);
		setBounds(600, 150, 450, 300);
		setLayout(null);
	
		JButton out_seat = new JButton("�¼� ����ϱ�");
		this.add(out_seat);
		out_seat.setBounds(160,100,300,100);
		
		JButton out_room = new JButton("�� ����ϱ�");
		this.add(out_room);
		out_room.setBounds(160,220,300,100);
		new Style(out_seat);
		new Style(out_room);
		
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
				  
			 //�α��� �� ȸ���� �¼���ȣ Ȯ��
			sql = "SELECT seat_number,seat_type from person_info where login_state = 'On'";
			 
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
		         
				 while(rs.next()) { 
					 num_seat = rs.getInt("seat_number");
					  type = rs.getString("seat_type");
				 }
				 sql = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
			     pstmt = conn.prepareStatement(sql);
			     pstmt.setInt(1, num_seat);
			     int row3 = pstmt.executeUpdate();
			     if(num_seat==0) {
			    		String msg= "������ �¼��� �����ϴ�";
						JOptionPane.showMessageDialog(null,msg); 
			     }else {
			     System.out.printf("%d�� �¼��� ��� �Ǿ����ϴ�.(%d�� ������Ʈ)\n",num_seat,row3);
			     
			     //�����̿�� ����ڴ� ��� �� �¼��� ����
			     if(type.equals("���� �̿��")) {
			     sql = "update person_info set seat_number=null where login_state = 'On'";
			     pstmt = conn.prepareStatement(sql);
			     int row5 = pstmt.executeUpdate();
			     System.out.printf("(%d�� ������Ʈ)\n",row5);	
			     }
			     //�����̿�� ����ڴ� ����ð� ����
			     else { 
			     sql = "update person_info set seat_number=null,expiration_seat='20/01/01 00:00:00.000000000' where login_state = 'On'";
				 pstmt = conn.prepareStatement(sql);
				 int row5 = pstmt.executeUpdate();
				 System.out.printf("(%d�� ������Ʈ)\n",row5);	
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
					 
					 
			    	sql = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, _00main.room_chk);
					int row3 = pstmt.executeUpdate();
					  if(_00main.room_chk==0) {
				    		String msg= "������ �¼��� �����ϴ�";
							JOptionPane.showMessageDialog(null,msg); 
				     }else {
					System.out.printf("%d�� ���� ��� �Ǿ����ϴ�.(%d�� ������Ʈ)\n",_00main.room_chk,row3);
					
					sql = "update person_info set room_number=null,expiration_room='20/01/01 00:00:00.000000000' where login_state = 'On'";
					pstmt = conn.prepareStatement(sql);
					int row5 = pstmt.executeUpdate();
					System.out.printf("(%d�� ������Ʈ)\n",row5);	
				     }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		
 
		
		JButton back_btn = new JButton("���� ȭ��");
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(160,340,300,100);
		back_btn.addActionListener(new ActionListener() { //���� ������
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  MainPage.user_page_panel.add("���θ޴�", new _00main()); // �޴�������
	        	  MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
					MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
					MainPage.userToggle = "���θ޴�";
	          }
	       }); 
	}

}
