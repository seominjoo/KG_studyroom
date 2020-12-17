package login.page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import login.design.Style;
import login.mainmenu.Time;
import login.mainmenu._06move;

public class StoreMoveBtnPage extends JFrame {

	static BufferedImage image;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JButton move;

	String sql_move ="";
	String type ="";
	
	int id=0;
	String exp = "";
	String name ="";
	String pn = "";
	Timestamp time = null;

	public StoreMoveBtnPage() {
		
		String sql="";			
		JLabel title = null;

		move = new JButton("Ȯ��");
		
		if (StoreManagementPage.type != StoreMovePage.type) {
			JOptionPane.showMessageDialog(null,"�̵� �Ұ��� �����Դϴ�.");
		} else {
		
		try {
			
			image = ImageIO.read(new File("image/�ΰ�.png"));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			if (StoreManagementPage.type.equals("�繰��")) {

				title = new JLabel(StoreMovePage.locker_move_number +"�� �繰������ �̵��Ͻðڽ��ϱ�?");
			
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_move = "SELECT l_time_enter, l_time_checkout FROM locker WHERE locker_number =?";
							PreparedStatement pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							ResultSet rs0 = pstmt.executeQuery(); 
							while(rs0.next()) { 
								Timestamp te = rs0.getTimestamp("l_time_enter");
								Timestamp tco = rs0.getTimestamp("l_time_checkout");
								String sql = "UPDATE locker SET Locker_Statement ='��� ��',l_time_enter=?,l_time_checkout=? WHERE Locker_Number=?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setTimestamp(1, te);
								pstmt.setTimestamp(2, tco);
								pstmt.setInt(3, StoreMovePage.locker_move_number);
								int row = pstmt.executeUpdate(); 
							}
		
							sql_move = "UPDATE locker SET Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null WHERE Locker_Number=?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row2 = pstmt.executeUpdate(); 
												
							sql_move = "UPDATE person_info SET Locker_number=? WHERE Person_Id =?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreMovePage.locker_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate(); 
							
							if (pstmt != null) pstmt.close();
							if (rs0 != null) rs0.close();
							if (conn != null) conn.close();
							
							System.out.printf("%d�� �繰���� �̵��Ǿ����ϴ�. (locker %d��, person_info %d���� ����Ǿ����ϴ�)\n",StoreManagementPage.locker_number, row2, row3);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
						JOptionPane.showMessageDialog(null, "�̵��Ǿ����ϴ�.");
						setVisible(false);
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";
					}
				});

			} else if (StoreManagementPage.type.equals("��")) {

				title = new JLabel(StoreMovePage.room_move_number +"ȣ ������ �̵��Ͻðڽ��ϱ�?");	
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
											
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							sql_move = "SELECT time_enter, time_checkout FROM seat WHERE seat_number =?";
							PreparedStatement pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.room_number);
							ResultSet rs0 = pstmt.executeQuery(); 
							while(rs0.next()) { 
								Timestamp te = rs0.getTimestamp("time_enter");
								Timestamp tco = rs0.getTimestamp("time_checkout");
								String sql = "UPDATE seat SET seat_Statement ='��� ��',time_enter=?,time_checkout=? WHERE seat_Number=?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setTimestamp(1, te);
								pstmt.setTimestamp(2, tco);
								pstmt.setInt(3, StoreMovePage.room_move_number);
								int row = pstmt.executeUpdate(); 
							}
							sql_move = "UPDATE seat SET Seat_Statement ='��� ����',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row2 = pstmt.executeUpdate(); 
												
							sql_move = "UPDATE person_info SET room_number=? WHERE Person_Id =?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreMovePage.room_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate(); 
							
							if (pstmt != null) pstmt.close();
							if (rs0 != null) rs0.close();
							if (conn != null) conn.close();
							
							System.out.printf("%dȣ ���� �̵��Ǿ����ϴ�. (seat %d��, person_info %d���� ����Ǿ����ϴ�)\n",StoreManagementPage.locker_number, row2, row3);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "�̵��Ǿ����ϴ�.");
						setVisible(false);
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";						
					}
				});

			} else if (StoreManagementPage.type.equals("�¼�")) {

				title = new JLabel(StoreMovePage.seat_move_number +"�� �¼����� �̵��Ͻðڽ��ϱ�?");

				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_move = "SELECT time_enter, time_checkout FROM seat WHERE seat_number =?";
							PreparedStatement pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							ResultSet rs0 = pstmt.executeQuery(); 
							while(rs0.next()) { 
								Timestamp te = rs0.getTimestamp("time_enter");
								Timestamp tco = rs0.getTimestamp("time_checkout");
								String sql = "UPDATE seat SET Seat_Statement ='��� ��',time_enter=?,time_checkout=? WHERE Seat_Number=?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setTimestamp(1, te);
								pstmt.setTimestamp(2, tco);
								pstmt.setInt(3, StoreMovePage.seat_move_number);
								int row = pstmt.executeUpdate(); 
							}
		
							sql_move = "UPDATE seat SET Seat_Statement ='��� ����',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row2 = pstmt.executeUpdate(); 
												
							sql_move = "UPDATE person_info SET seat_number=? WHERE Person_Id =?";
							pstmt = conn.prepareStatement(sql_move);
							pstmt.setInt(1, StoreMovePage.seat_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate(); 
							
							if (pstmt != null) pstmt.close();
							if (rs0 != null) rs0.close();
							if (conn != null) conn.close();
							
							System.out.printf("%d�� �¼��� �̵��Ǿ����ϴ�. (seat %d��, person_info %d���� ����Ǿ����ϴ�)\n",StoreManagementPage.locker_number, row2, row3);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
						JOptionPane.showMessageDialog(null, "�̵��Ǿ����ϴ�.");
						setVisible(false);
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";
					}
				});
			} 

			title.setBounds(70,10,300,80);
			title.setForeground(Color.white);
			add(title);

			new Style(move);
			move.setBorder(BorderFactory.createLineBorder(Color.white));
			move.setForeground(Color.white);
			add(move);
			move.setBounds(80,100,180,50);
			
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setBounds(550, 200, 350, 250);
			setVisible(true);


		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		}
	}
}


