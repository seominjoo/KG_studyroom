package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
 

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

public class _01start extends JFrame {
 
	JTable table;
	static int count_seat=0;
	static int count_room=0;
	static int count_locker=0;
 
	private JPanel contentPane;

	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy�� M�� d��");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h�� m�� ");
	 
	public _01start( ) {
		  
		 new Style(contentPane);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);

			// ��� �ð��� ���� �¼� ��� ó��
			String sql = "SELECT seat_number, time_checkout FROM seat "
					+ "WHERE seat_statement ='��� ��'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) { 
				int seat_chk = rs.getInt("seat_number");
				Timestamp time_chk = rs.getTimestamp("time_checkout");
				if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmt2 = conn.prepareStatement(change);
					pstmt2.setInt(1, seat_chk);
					int row3 = pstmt2.executeUpdate();
				}  
			}
			// ������� �¼� �� Ȯ��  
			sql = "select seat_number from seat where seat_statement='��� ��'";
		    pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					count_seat++;
				}else if (sn>=101) {
					count_room++;
				}
			}
			// ���� �ð��� ���� �繰�� ���� ó��
			sql = "SELECT Locker_Number,l_time_checkout FROM locker "
					+ "WHERE Locker_Statement='��� ��'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) { 
				int locker_chk = rs.getInt("Locker_Number");
				Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
				if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstmt3 = conn.prepareStatement(change2);
					pstmt3.setInt(1, locker_chk);
					int row4 = pstmt3.executeUpdate();
				}  
			}

			// ������� �繰�� �� Ȯ��  
			sql = "select locker_number from locker where locker_statement='��� ��'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println();

			while(rs.next()) {
				int sn = rs.getInt("locker_number");
				count_locker++; 
			} 
			if(rs!=null) rs.close(); 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//�޴� ��ư 4��
		JButton seat_btn = new JButton("�¼� �̿��"); 
		seat_btn.setBounds(5, 95, 208, 121);  
		contentPane.add(seat_btn);

		JButton locker_btn = new JButton("�繰�� �̿��");
		locker_btn.setBounds(218, 95, 213, 121); 
		contentPane.add(locker_btn);

		JButton move_btn = new JButton("�ڸ� �̵�");
		move_btn.setBounds(5, 221, 208, 126); 
		contentPane.add(move_btn);

		JButton out_btn = new JButton("����ϱ�");
		out_btn.setBounds(218, 221, 213, 126); 
		contentPane.add(out_btn);

		//���͵�� ��Ȳǥ
		String header[] = {"1�μ�","���͵��","�繰��","����ð�"};
		String contents[][]= {
				{
					"<html>����� 1�μ�<br/>&emsp;&emsp;"+Integer.toString(count_seat)+" / 20",
					"<html>����� ���͵��<br/>&emsp;&emsp;&emsp;"+Integer.toString(count_room)+" / 4",
					"<html>����� �繰��<br/>&emsp;&emsp;"+Integer.toString(count_locker)+" / 20",
					"<html>&emsp;&nbsp;&nbsp;&nbsp;����ð�<br/>"+LocalDate.now().format(date)+"<br/>&nbsp;&nbsp;&nbsp;"+LocalTime.now().format(time)} 
		};

		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(0, 0, 437, 80);
		table.setRowHeight(80);
		
		//�׵θ�
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);

		//��Ȳǥ �۾� �߾� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("1�μ�").setCellRenderer(celAlignCenter);
		table.getColumn("���͵��").setCellRenderer(celAlignCenter);
		table.getColumn("�繰��").setCellRenderer(celAlignCenter);
		table.getColumn("����ð�").setCellRenderer(celAlignCenter);
		contentPane.add(table); 
		
		setVisible(true);
		
		seat_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 _02dayOrWeek frame = new _02dayOrWeek();
			 frame.setVisible(true);
			}
		}); 
		
		move_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 _06moveSeat frame = new _06moveSeat();
			 frame.setVisible(true);
			}
		}); 
		
		out_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 _07out frame = new _07out();
			 frame.setVisible(true);
			}
		});
		
		locker_btn.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 _05locker frame = new _05locker();
			 frame.setVisible(true);
			}
		});
	 
	}  
	
	public static void main(String[] args) {
		new _01start(); 
		 
	}
}


