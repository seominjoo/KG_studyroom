package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
 
import login.design.Style;
import login.page.MainPage;


import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.ImageIcon;

public class _01start extends JPanel {

	
	public static Connection conn;

//	private JPanel this;
	static Timestamp time_chk_seat;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy�� M�� d��");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h�� m�� ");

	public _01start() {

		new Style(this);
		setBounds(600, 150, 450, 400);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		// �޴� ��ư 4��
		JButton seat_btn = new JButton("�¼� �̿��");
		seat_btn.setBounds(85, 195, 208, 121);
		this.add(seat_btn);

		JButton locker_btn = new JButton("�繰�� �̿��");
		locker_btn.setBounds(298, 195, 213, 121);
		this.add(locker_btn);

		JButton room_btn = new JButton("�� �̿��");
		room_btn.setBounds(85, 321, 208, 126);
		this.add(room_btn);

		JButton back_btn = new JButton("���� ȭ��");
		back_btn.setBounds(298, 321, 213, 126);
		this.add(back_btn);
		new Style(seat_btn);
		new Style(locker_btn);
		new Style(room_btn);
		new Style(back_btn);
		
		 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			seat_btn.addActionListener(new ActionListener() { //�¼� �̿�� ������
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("�¼���ȣ ����"+_00main.seat_chk);
					System.out.println("�̿�� ����"+_00main.type);
		 
					 
					 if(_00main.seat_chk>0) {
			            	String msg= "������ �¼��� �̹� �����մϴ�";
							JOptionPane.showMessageDialog(null,msg); 
			            } else if(_00main.type.equals("���� �̿��")) {
							 String msg= "���� �̿�� �̿��ڴ� �Խ��� �̿��ϼ���";
							 JOptionPane.showMessageDialog(null,msg); 
						 }else {
							 MainPage.user_page_panel.add("�¼��̿��", new _02dayOrWeek()); // �¼� �̿�� ������
							 MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
								MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
								MainPage.userToggle = "�¼��̿��";
			            }
			         }
				
		  
			}); 
			
		      room_btn.addActionListener(new ActionListener() { //�� �̿�� ������
		          @Override
		          public void actionPerformed(ActionEvent e) {
		        	  try {//�� ����ð��� �������� ���� �Ұ� 
							String sql = "SELECT room_number from person_info where login_state='On'";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							ResultSet rs = pstmt.executeQuery();
							
							 while(rs.next()) { 
						          
								 int room_chk = rs.getInt("room_number");
						            if(room_chk>0) {
						            	String msg= "������ ���� �̹� �����մϴ�";
										JOptionPane.showMessageDialog(null,msg); 
						            }else {				         
						            	MainPage.user_page_panel.add("���̿��", new _02dayRoom());// �� �̿�� ������
						            	MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
										MainPage.user_cards.show(MainPage.user_page_panel, "���̿��");
										MainPage.userToggle = "���̿��";
						            }
						         }
							
						} catch (SQLException e1) { 
							e1.printStackTrace();
						} 
		          }
		       });  
			 
			locker_btn.addActionListener(new ActionListener() { //�繰�� �̿�� ������
				@Override
				public void actionPerformed(ActionEvent e) {
					 try {//�繰�Ը���ð��� �������� ���� �Ұ� 
							String sql = "SELECT locker_number from person_info where login_state='On'";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							ResultSet rs = pstmt.executeQuery();
							
							 while(rs.next()) { 
						          
						            int locker_chk = rs.getInt("locker_number");
						            if(locker_chk>0) {
						            	String msg= "������ �繰���� �̹� �����մϴ�";
										JOptionPane.showMessageDialog(null,msg); 
						            }else {	 
						            	MainPage.user_page_panel.add("�繰���̿��", new _05locker());
						            	MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
										MainPage.user_cards.show(MainPage.user_page_panel, "�繰���̿��");
										MainPage.userToggle = "�繰���̿��";
						            }
						         }
							
						} catch (SQLException e1) { 
							e1.printStackTrace();
						}
			 
				}
			});
			
			  back_btn.addActionListener(new ActionListener() { //���� ������
		          @Override
		          public void actionPerformed(ActionEvent e) {
		        	  MainPage.user_page_panel.add("���θ޴�", new _00main()); // �޴�������
		        	  MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
						MainPage.userToggle = "���θ޴�";
		          }
		       });

		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

	}

}
