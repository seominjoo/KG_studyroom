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
import login.mainmenu._00main;
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
							PreparedStatement pstmt = null;
							
							//seat ���̺� �̵��� �繰�� <-> ���� �繰��
							String sql = "SELECT locker_statement, l_time_enter, l_time_checkout FROM locker WHERE locker_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.locker_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("locker_statement");
								Timestamp tc = rs.getTimestamp("l_time_enter");
								Timestamp tco = rs.getTimestamp("l_time_checkout");
								
								sql = "UPDATE locker SET locker_statement =?, l_time_enter=?, l_time_checkout=? WHERE Locker_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.locker_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE locker SET locker_statement='��� ����', time_enter='01/01/01 00:00:00.000000000',"
									+ " time_checkout = '01/01/01 00:00:00.000000000' WHERE Locker_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row = pstmt.executeUpdate();
							
							//ȸ��info ���̺� �¼���ȣ ������Ʈ
							sql = "UPDATE person_info SET locker_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.locker_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d�� �ڸ��� �̵��Ǿ����ϴ�.(%d�� ������Ʈ)\n", StoreMovePage.locker_move_number,row);
							System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();
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
							PreparedStatement pstmt = null;
							
							//seat ���̺� �̵��� �� <-> ���� ��
							String sql = "SELECT seat_statement, time_enter, time_checkout FROM seat WHERE seat_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.room_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("seat_statement");
								Timestamp tc = rs.getTimestamp("time_enter");
								Timestamp tco = rs.getTimestamp("time_checkout");
								
								sql = "UPDATE seat SET seat_statement =?, time_enter=?, time_checkout=? WHERE Seat_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.room_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE seat SET seat_statement='��� ����', time_enter='01/01/01 00:00:00.000000000', "
									+ "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row = pstmt.executeUpdate();
							
							//ȸ��info ���̺� �¼���ȣ ������Ʈ
							sql = "UPDATE person_info SET room_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.room_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d�� �ڸ��� �̵��Ǿ����ϴ�.(%d�� ������Ʈ)\n", StoreMovePage.room_move_number,row);
							System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();

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
							PreparedStatement pstmt = null;
							
							//seat ���̺� �̵��� �¼� <-> ���� �¼�
							String sql = "SELECT seat_statement, time_enter, time_checkout FROM seat WHERE seat_number =?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreManagementPage.seat_number); 
							ResultSet rs = pstmt.executeQuery(); 
							
							while(rs.next()) { 
								String st = rs.getString("seat_statement");
								Timestamp tc = rs.getTimestamp("time_enter");
								Timestamp tco = rs.getTimestamp("time_checkout");
								
								sql = "UPDATE seat SET seat_statement =?, time_enter=?, time_checkout=? WHERE Seat_Number= ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, st);
								pstmt.setTimestamp(2, tc);
								pstmt.setTimestamp(3, tco);
								pstmt.setInt(4, StoreMovePage.seat_move_number);
								int row2 = pstmt.executeUpdate();
							}
									
							String sql2 = "UPDATE seat SET seat_statement='��� ����', time_enter='01/01/01 00:00:00.000000000', "
									+ "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row = pstmt.executeUpdate();
							
							//ȸ��info ���̺� �¼���ȣ ������Ʈ
							sql = "UPDATE person_info SET seat_number=? WHERE person_id=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, StoreMovePage.seat_move_number);
							pstmt.setInt(2, id);
							int row3 = pstmt.executeUpdate();
												
							System.out.printf("%d�� �ڸ��� �̵��Ǿ����ϴ�.(%d�� ������Ʈ)\n", StoreMovePage.seat_move_number,row);
							System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
							
							if (pstmt != null) pstmt.close();
							if (conn != null) conn.close();
							
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


