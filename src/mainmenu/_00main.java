package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

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

public class _00main extends JFrame {
 
   JTable table;
   static int count_seat;
   static int count_room;
   static int count_locker;
  public static int seat_chk;
   int locker_chk;
   public static int room_chk;
  static Timestamp time_seat;
   Timestamp time_locker;
   Timestamp time_room;
   static int id;
   private JPanel contentPane;
  static String type;
   DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
   DateTimeFormatter time = DateTimeFormatter.ofPattern("a h시 m분 ");
    
   public _00main() { 
	   
	   count_locker=0;
	   count_room=0;
	   count_seat=0;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection(
               "jdbc:oracle:thin:@localhost:1521/XEPDB1",
               "hr",
               "1234"
               );
         
         // 로그인된 회원번호 읽기
         String sql = "SELECT person_id from person_info where login_state = 'On'";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         int row;
          
         
         while(rs.next()) { 
            id = rs.getInt("person_id") ; 
            System.out.println("로그인 된 회원번호: " + id);
         }
 
         // 퇴실 시간이 지난 좌석 퇴실 처리
          sql = "SELECT seat_number, time_checkout FROM seat "
               + "WHERE seat_statement ='사용 중'";
          pstmt = conn.prepareStatement(sql);
          rs = pstmt.executeQuery();

         while(rs.next()) { 
            int seat_chk = rs.getInt("seat_number");
            Timestamp time_chk = rs.getTimestamp("time_checkout");
            if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
               String change = "update seat set Seat_Statement ='사용 가능',time_enter=null,time_checkout=null where Seat_Number= ?";
               PreparedStatement pstmt2 = conn.prepareStatement(change);
               pstmt2.setInt(1, seat_chk);
               int row3 = pstmt2.executeUpdate();
            }  
         }
         
          
         
         // 사용중인 좌석 수 확인  
         sql = "select seat_number from seat where seat_statement='사용 중'";
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
       //퇴실시간(만료시간) 지나면 회원정보 만료시간 리셋
         sql = "SELECT seat_number,room_number,locker_number,expiration_seat,expiration_locker,expiration_room,seat_type "
      		+ "FROM person_info WHERE login_state = 'On'";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

        while(rs.next()) { 
           seat_chk = rs.getInt("seat_number");
           room_chk = rs.getInt("room_number");
           locker_chk= rs.getInt("locker_number");
           time_seat = rs.getTimestamp("expiration_seat");
           time_locker = rs.getTimestamp("expiration_locker");
           time_room = rs.getTimestamp("expiration_room");
           type = rs.getString("seat_type");
           if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_seat))) {
              sql = "update person_info set seat_number =null,expiration_seat='20/01/01 00:00:00.000000000',seat_type='x' where login_state = 'On'";
              pstmt = conn.prepareStatement(sql); 
              row = pstmt.executeUpdate();
           } if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_locker))) {
         	  sql = "update person_info set locker_number =null,expiration_locker='20/01/01 00:00:00.000000000' where login_state = 'On'";
         	  pstmt = conn.prepareStatement(sql); 
         	  row = pstmt.executeUpdate();
            }  if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(time_room))) {
         	   sql = "update person_info set room_number =null,expiration_room='20/01/01 00:00:00.000000000' where login_state = 'On'";
         	   pstmt = conn.prepareStatement(sql); 
         	   row = pstmt.executeUpdate();
             }   
        }
     
         // 만료 시간이 지난 사물함 만료 처리
         sql = "SELECT Locker_Number,l_time_checkout FROM locker "
               + "WHERE Locker_Statement='사용 중'";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while(rs.next()) { 
            int locker_chk = rs.getInt("Locker_Number");
            Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
            if(LocalDateTime.now().isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
               String change2 = "update locker set Locker_Statement ='사용 가능',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
               PreparedStatement pstmt3 = conn.prepareStatement(change2);
               pstmt3.setInt(1, locker_chk);
               int row4 = pstmt3.executeUpdate();
            } 
         }

         // 사용중인 사물함 수 확인  
         sql = "select locker_number from locker where locker_statement='사용 중'";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

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

      //메뉴 버튼 4개
      JButton ticket_btn = new JButton("이용권 구매"); 
      ticket_btn.setBounds(5, 95, 208, 121);  
      contentPane.add(ticket_btn);

      JButton in_btn = new JButton("입실하기");
      in_btn.setBounds(218, 95, 213, 121); 
      contentPane.add(in_btn);

      JButton move_btn = new JButton("자리 이동");
      move_btn.setBounds(5, 221, 208, 126); 
      contentPane.add(move_btn);

      JButton out_btn = new JButton("퇴실하기");
      out_btn.setBounds(218, 221, 213, 126); 
      contentPane.add(out_btn);

      //스터디룸 상황표
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
      
      //테두리
      Color color = UIManager.getColor("Table.gridColor");
      MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
      table.setBorder(border);

      //상황표 글씨 중앙 정렬
      DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
      celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
      table.getColumn("1인석").setCellRenderer(celAlignCenter);
      table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
      table.getColumn("사물함").setCellRenderer(celAlignCenter);
      table.getColumn("현재시간").setCellRenderer(celAlignCenter);
      contentPane.add(table); 
      
      setVisible(true);
      
      ticket_btn.addActionListener(new ActionListener() { //이용권 페이지
         @Override
         public void actionPerformed(ActionEvent e) {
          setVisible(false);
          _01start frame = new _01start();
          frame.setVisible(true);
         }
      }); 
      
      move_btn.addActionListener(new ActionListener() { //자리이동 페이지
          @Override
          public void actionPerformed(ActionEvent e) {
         if((seat_chk>0&&seat_chk<21)||(room_chk>=101&&room_chk<=104)) {
        	 setVisible(false);
             _06move frame = new _06move();
             frame.setVisible(true);
         	 
         } else {
        	 String msg= "사용 중인 좌석이 없습니다.";
 			 JOptionPane.showMessageDialog(null,msg); 
         	}
          }
       });  
      
      out_btn.addActionListener(new ActionListener() { //퇴실 페이지
         @Override
         public void actionPerformed(ActionEvent e) {
          setVisible(false);
          _07out frame = new _07out();
          frame.setVisible(true);
         }
      });
     
      in_btn.addActionListener(new ActionListener() { //입실 페이지(정기이용권 이용자)
     
         @Override
         public void actionPerformed(ActionEvent e) {
          if(seat_chk>0) {
        		 String msg= "이미 좌석이 있습니다.";
				 JOptionPane.showMessageDialog(null,msg); 
        	 } else if(type.equals("정기 이용권")) {
                 setVisible(false);
                 _07in_seletSeat frame = new _07in_seletSeat();
                 frame.setVisible(true);
             }else {
             String msg1= "정기 이용권 이용자만 가능합니다";
        	 JOptionPane.showMessageDialog(null,msg1); 
            }
         }
      });
    
   }  
   
   public static void main(String[] args) {
      new _00main();    
   }
}
