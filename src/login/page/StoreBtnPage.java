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

public class StoreBtnPage extends JFrame {

	static BufferedImage image;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	JButton out;
	JButton move;

	String sql_out ="";
	String type ="";

	public StoreBtnPage() {
		
		String sql="";	
		String sql2="";
		int id=0;
		String exp = "";
		String name ="";
		String pn = "";
		Timestamp time = null;
		JLabel title = null;
		JLabel info = null;
		out = new JButton("���");
		move = new JButton("�̵�");

		
		try {
			
			image = ImageIO.read(new File("image/�ΰ�.png"));
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;
			
			if (StoreManagementPage.type.equals("�繰��")) {
				
				sql = "SELECT Person_Id, Person_Name, Phone_Number, l_time_checkout FROM person_info, locker WHERE locker.locker_number =?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.locker_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					pn = rs.getString(3);
					time = rs.getTimestamp(4);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}
				
				title = new JLabel(StoreManagementPage.locker_number +"�� �繰�� ����");
				info = new JLabel(
								"<html>ȸ����ȣ : "+id+"��"+
								"<br/>ȸ���̸� : "+name+
								"<br/>�ڵ�����ȣ : "+pn+
								"<br/>����Ⱓ : "+exp
						);

				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							sql_out = "UPDATE locker SET Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null WHERE Locker_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row = pstmt.executeUpdate(); 
							
							sql_out = "UPDATE person_info SET locker_number=null WHERE Locker_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.locker_number);
							int row2 = pstmt.executeUpdate(); 
							
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
							System.out.printf("%d�� �繰���� �ݳ��Ǿ����ϴ�. (locker %d��, person_info %d���� ����Ǿ����ϴ�)\n",StoreManagementPage.locker_number, row, row2);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, StoreManagementPage.locker_number+"�� �繰���� �ݳ��Ǿ����ϴ�");
						setVisible(false);
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {					
						int result = JOptionPane.showConfirmDialog(null, "�̵��� �繰���� �������ּ���",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("�������-�̵�", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.main_cards.show(MainPage.main_page_panel, "�������-�̵�");
						MainPage.userToggle = "�������-�̵�";
						}
					}
				});

			} else if (StoreManagementPage.type.equals("��")) {
				
				sql = "SELECT Person_Id, Person_Name, seat_type, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.room_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					type = rs.getString(3);
					pn = rs.getString(4);
					time = rs.getTimestamp(5);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}

				title = new JLabel(StoreManagementPage.room_number +"ȣ �� ����");
				info = new JLabel(
								"<html>ȸ����ȣ : "+id+"��"+
								"<br/>ȸ���̸� : "+name+
								"<br/>�ڵ�����ȣ : "+pn+
								"<br/>����Ⱓ : "+exp+
								"<br/>�̿�� : "+type
						);
				
				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_out = "UPDATE seat SET Seat_Statement ='��� ����',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row = pstmt.executeUpdate(); 
							
							sql_out = "UPDATE person_info SET room_number=null, Seat_Type ='x' WHERE Room_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.room_number);
							int row2 = pstmt.executeUpdate(); 
							
							System.out.printf("%dȣ ���� ��ǵǾ����ϴ�. (seat %d��, person_info %d���� ����Ǿ����ϴ�)\n",StoreManagementPage.room_number, row, row2);
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, StoreManagementPage.room_number+ "ȣ ���� ��ǵǾ����ϴ�");
						setVisible(false);
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "�̵��� ���� �������ּ���",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("�������-�̵�", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.main_cards.show(MainPage.main_page_panel, "�������-�̵�");
						MainPage.userToggle = "�������-�̵�";
						}
					}
				});

			} else if (StoreManagementPage.type.equals("�¼�")) {

				sql = "SELECT Person_Id, Person_Name, seat_type, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StoreManagementPage.seat_number); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) { 
					id = rs.getInt(1);
					name = rs.getString(2);
					type = rs.getString(3);
					pn = rs.getString(4);
					time = rs.getTimestamp(5);
					exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
				}

				title = new JLabel(StoreManagementPage.seat_number +"�� �¼� ����");
				info = new JLabel(
								"<html>ȸ����ȣ : "+id+"��"+
								"<br/>ȸ���̸� : "+name+
								"<br/>�ڵ�����ȣ : "+pn+
								"<br/>����Ⱓ : "+exp+
								"<br/>�̿�� : "+type
						);
				
				out.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							Connection conn;
							conn = DriverManager.getConnection(
									"jdbc:oracle:thin:@localhost:1521/XEPDB1",
									"hr",
									"1234"
									);
							
							sql_out = "UPDATE seat SET Seat_Statement ='��� ����',time_enter=null,time_checkout=null WHERE Seat_Number=?";
							PreparedStatement pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row = pstmt.executeUpdate(); 
							System.out.printf("%d�� �¼��� ��ǵǾ����ϴ�.(seat %d���� ����Ǿ����ϴ�.)\n",StoreManagementPage.seat_number, row);
							
							if (type.equals("���� �̿��")) {
							sql_out = "UPDATE person_info SET seat_number=null, Seat_Type ='x' WHERE Seat_Number=?";
							pstmt = conn.prepareStatement(sql_out);
							pstmt.setInt(1, StoreManagementPage.seat_number);
							int row2 = pstmt.executeUpdate(); 
							System.out.printf("person_info %d���� ����Ǿ����ϴ�.\n", row2);
							}
							if (pstmt != null) pstmt.close();
							if (rs != null) rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, StoreManagementPage.seat_number+ "�� �¼��� ��ǵǾ����ϴ�");
						setVisible(false);
						MainPage.main_page_panel.add("����", new MainPage());
						MainPage.main_page_panel.add("�������", new StoreManagementPage());
						MainPage.main_cards.show(MainPage.main_page_panel, "�������");
						MainPage.userToggle = "�������";
					}
				});
				
				move.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "�̵��� �¼��� �������ּ���",null, JOptionPane.OK_CANCEL_OPTION);
						if(result==0) {					
						setVisible(false);
						MainPage.main_page_panel.add("�������-�̵�", new StoreMovePage());
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.main_cards.show(MainPage.main_page_panel, "�������-�̵�");
						MainPage.userToggle = "�������-�̵�";
						}
					}
				});	
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} 

			title.setBounds(120,10,300,40);
			info.setBounds(20,0,300,190);
			title.setForeground(Color.white);
			info.setForeground(Color.white);
			add(title);
			add(info);

			new Style(out);
			new Style(move);
			out.setBorder(BorderFactory.createLineBorder(Color.white));
			out.setForeground(Color.white);
			move.setBorder(BorderFactory.createLineBorder(Color.white));
			move.setForeground(Color.white);
			add(out);
			add(move);
			move.setBounds(50,180,100,50);
			out.setBounds(180,180,100,50);
			
			setLayout(null);
			getContentPane().setBackground(Color.decode("#404040"));
			setBounds(550, 300, 350, 300);
			setVisible(true);

			if (conn != null) conn.close();

		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}

