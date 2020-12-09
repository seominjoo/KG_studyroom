package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h시 m분 ");
	/**
	 * Launch the application.
	 */
	
	Image img;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_01start frame = new _01start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public _01start() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			 
			//시간 비교
			String sqlmt1 = "SELECT seat_number, time_checkout FROM seat "
					+ "WHERE seat_statement ='사용 중'";
			PreparedStatement pstmtt1 = conn.prepareStatement(sqlmt1);
			ResultSet rst1 = pstmtt1.executeQuery();
		 
			while(rst1.next()) {//사용중인 좌석중 퇴실시간이 지나면 사용가능으로 업뎃 
				int seat_chk = rst1.getInt("seat_number");
				Timestamp time_chk = rst1.getTimestamp("time_checkout");
				if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmtas = conn.prepareStatement(change);
					pstmtas.setInt(1, seat_chk);
					int row3 = pstmtas.executeUpdate();
				}  
			}
			//좌석 - db에서 '사용 중'인지 읽은 다음 '사용 중'이면 체크박스 체크 및 비활성화(사용중 이므로 예약 못하게) 
			String sqlm = "select seat_number from seat where seat_statement='사용 중'";
			PreparedStatement pstmt = conn.prepareStatement(sqlm);
			ResultSet rs = pstmt.executeQuery();
		 
			 

			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					count_seat++;
				}else if (sn>=101) {
					count_room++;
				}
			}
			// 사물함
			String sqlmt2 = "SELECT Locker_Number,l_time_checkout FROM locker "
					+ "WHERE Locker_Statement='사용 중'";
			PreparedStatement pstmtt2 = conn.prepareStatement(sqlmt2);
			ResultSet rst2 = pstmtt2.executeQuery();
		 
			while(rst2.next()) {//사용중인 사물함 중 이용기간이 지나면 사용가능으로 업뎃 
				int locker_chk = rst2.getInt("Locker_Number");
				Timestamp l_time_chk = rst2.getTimestamp("l_time_checkout");
				if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
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
		 
			while(rs2.next()) {
				int sn = rs2.getInt("locker_number");
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
	      
	      JButton btnNewButton_2 = new JButton("좌석 이용권");
	      btnNewButton_2.setBounds(5, 95, 208, 121);
	      btnNewButton_2.setIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
	      btnNewButton_2.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
	      btnNewButton_2.setAction(action);
	      contentPane.add(btnNewButton_2);
	      
	    
	      
	      
	      JButton btnNewButton = new JButton("사물함 이용권");
	      btnNewButton.setBounds(218, 95, 213, 121);
	      btnNewButton.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/locker.png")));
	      btnNewButton.setAction(action_1);
	      contentPane.add(btnNewButton);
	      
	      JButton btnNewButton_1 = new JButton("자리 이동");
	      btnNewButton_1.setBounds(5, 221, 208, 126);
	      btnNewButton_1.setIcon(new ImageIcon(_01start.class.getResource("/image/seat.jpg")));
	      btnNewButton_1.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/move.png")));
	      btnNewButton_1.setAction(action_2);
	      contentPane.add(btnNewButton_1);
	      
	      JButton btnNewButton_3 = new JButton("퇴실하기");
	      btnNewButton_3.setBounds(218, 221, 213, 126);
	      btnNewButton_3.setSelectedIcon(new ImageIcon(_01start.class.getResource("/image/out.png")));
	      btnNewButton_3.setAction(action_3);
	      contentPane.add(btnNewButton_3);

	      String header[] = {"1인석","스터디룸","사물함","현재시간"};
			String contents[][]= {
					{
					 "<html>사용중 1인석<br/>&emsp;&emsp;"+Integer.toString(count_seat)+" / 20",
					 "<html>사용중 스터디룸<br/>&emsp;&emsp;&emsp;"+Integer.toString(count_room)+" / 4",
					 "<html>사용중 사물함<br/>&emsp;&emsp;"+Integer.toString(count_locker)+" / 20",
					 "<html>&emsp;&nbsp;&nbsp;&nbsp;현재시간<br/>"+LocalDate.now().format(date)+"<br/>&nbsp;&nbsp;&nbsp;"+LocalTime.now().format(time)} 
			};
 

			DefaultTableModel model = new DefaultTableModel(contents,header);
			table = new JTable(model);
			table.setBounds(0, 0, 437, 80);
			table.setRowHeight(80);
			
			Color color = UIManager.getColor("Table.gridColor");
			MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
			table.setBorder(border);
			 
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
			table.getColumn("1인석").setCellRenderer(celAlignCenter);
			table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
			table.getColumn("사물함").setCellRenderer(celAlignCenter);
			table.getColumn("현재시간").setCellRenderer(celAlignCenter);
			contentPane.add(table);
			
			 
		 
 
		 
	 
		 
	}

	private class SwingAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public SwingAction() {
			putValue(NAME, "좌석 이용권");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_02dayOrWeek frame = new _02dayOrWeek();
			frame.setVisible(true);
		}

	}
	private class SwingAction_1 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			   try {
					img = ImageIO.read(new File("src/image/locker.png"));
					Image reImage = img.getScaledInstance(80, 80,img.SCALE_SMOOTH);
					ImageIcon ic = new ImageIcon(reImage);
				
				
					putValue(SMALL_ICON,ic);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_05locker frame = new _05locker();
			frame.setVisible(true);
			
		}
	}
	private class SwingAction_2 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "자리 이동");
			putValue(SHORT_DESCRIPTION, "Some short description");
			Image img;
			   try {
			    img = ImageIO.read(new File("image/move.png"));
			    Image resizedImage = img.getScaledInstance(40, 40,
			      Image.SCALE_DEFAULT);
			    ImageIcon ic = new ImageIcon(resizedImage);
			    this.putValue(Action.SMALL_ICON, ic);
			   } catch (IOException e) {
			   }
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_06moveSeat frame = new _06moveSeat();
			frame.setVisible(true);
			
		}
	}
	
	private class SwingAction_3 extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "퇴실하기");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			_07out frame = new _07out();
			frame.setVisible(true);
		}
	}
}
