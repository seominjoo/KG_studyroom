package studyroom.admin.adminmode;

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

import studyroom.MainPage;
import studyroom.design.Style;
import studyroom.swingTools.State;
import studyroom.user.usermode.Time;

public class StoreBtnPage extends JFrame {

   static BufferedImage image;
   static String seat_type ="";
   static int id=0;

   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
   String sql_out ="";
   String sql_out2 ="";

   public StoreBtnPage() {

      String sql="";   
      String exp = "";
      String name ="";
      String pn = "";
      Timestamp time = null;
      JLabel title = null;
      JLabel info = null;
      JButton out = new JButton("퇴실");
      JButton out_locker = new JButton("반납");
      JButton move = new JButton("이동");
      new Style(out);
      new Style(move);
      new Style(out_locker);
      out_locker.setBorder(BorderFactory.createLineBorder(Color.white));
      out_locker.setForeground(Color.white);
      out.setBorder(BorderFactory.createLineBorder(Color.white));
      out.setForeground(Color.white);
      move.setBorder(BorderFactory.createLineBorder(Color.white));
      move.setForeground(Color.white);
      add(out_locker);
      add(out);
      add(move);
      move.setBounds(50,180,100,50);
      out.setBounds(180,180,100,50);
      out_locker.setBounds(180,180,100,50);

      try {

         image = ImageIO.read(new File("image/로고.png"));
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn;
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
         PreparedStatement pstmt = null;
         if (StoreManagementPage.type.equals("사물함")) {
        	 
            out.setVisible(false);
            sql = "SELECT Person_Id, Person_Name, Phone_Number, l_time_checkout FROM person_info, locker WHERE locker.locker_number =? AND locker.locker_number = person_info.locker_number";
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

            title = new JLabel(StoreManagementPage.locker_number +"번 사물함 정보");
            info = new JLabel(
                  "<html>회원번호 : "+id+"번"+
                  "<br/>회원이름 : "+name+
                  "<br/>핸드폰번호 : "+pn+
                  "<br/>만료기간 : "+exp);

            out_locker.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "반납처리 하시겠습니까?",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn;
                        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                        PreparedStatement pstmt = null;

                        String sql_out = "UPDATE locker SET Locker_Statement ='사용 가능',l_time_enter='01/01/01 00:00:00.000000000',l_time_checkout='01/01/01 00:00:00.000000000' WHERE Locker_Number=?";
                        pstmt = conn.prepareStatement(sql_out);
                        pstmt.setInt(1, StoreManagementPage.locker_number);
                        int row = pstmt.executeUpdate(); 

                        String sql_out2 = "UPDATE person_info SET locker_number=0 WHERE Person_Id=?";
                        pstmt = conn.prepareStatement(sql_out2);
                        pstmt.setInt(1, id);
                        int row2 = pstmt.executeUpdate(); 
                       
                        MainPage.updateTable.add(new State());
                        MainPage.statecard.next(MainPage.updateTable);
                        
                     } catch (SQLException | ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     }

                     JOptionPane.showMessageDialog(null, StoreManagementPage.locker_number+"번 사물함이 반납되었습니다");
                     setVisible(false);
                     MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
                     MainPage.userToggle = "매장관리";
                     MainPage.updateTable.add(new State());
                     MainPage.statecard.next(MainPage.updateTable);
                  }
               }
            });

            move.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {               
                  int result = JOptionPane.showConfirmDialog(null, "이동할 사물함을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
                     MainPage.userToggle = "매장관리-이동";
                  }
               }
            });
            if (rs != null) rs.close();

         } else if (StoreManagementPage.type.equals("룸")) {

            out_locker.setVisible(false);
            String sql2 = "SELECT Person_Id, Person_Name, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =? AND seat.seat_number = person_info.room_number";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, StoreManagementPage.room_number); 
            ResultSet rs2 = pstmt.executeQuery(); 
            while(rs2.next()) { 
               id = rs2.getInt(1);
               name = rs2.getString(2);
               pn = rs2.getString(3);
               time = rs2.getTimestamp(4);
               exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
            }

            title = new JLabel(StoreManagementPage.room_number +"호 룸 정보");
            info = new JLabel(
                  "<html>회원번호 : "+id+"번"+
                  "<br/>회원이름 : "+name+
                  "<br/>핸드폰번호 : "+pn+
                  "<br/>만료기간 : "+exp);

            out.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "퇴실처리 하시겠습니까?",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn;
                        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                        PreparedStatement pstmt = null;
                        
                        String sql_out = "UPDATE seat SET Seat_Statement ='사용 가능',time_enter='01/01/01 00:00:00.000000000',time_checkout='01/01/01 00:00:00.000000000' WHERE seat_Number=?";
                        pstmt = conn.prepareStatement(sql_out);
                        pstmt.setInt(1, StoreManagementPage.room_number);
                        int row = pstmt.executeUpdate(); 

                        String sql_out2 = "UPDATE person_info SET room_number=0 WHERE Person_Id=?";
                        pstmt = conn.prepareStatement(sql_out2);
                        pstmt.setInt(1, id);
                        int row2 = pstmt.executeUpdate(); 

                        MainPage.updateTable.add(new State());
                        MainPage.statecard.next(MainPage.updateTable);
                        
                        if (pstmt != null) pstmt.close();
                        
                     } catch (SQLException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                     }
                     JOptionPane.showMessageDialog(null, StoreManagementPage.room_number+ "호 룸이 퇴실되었습니다");
                     setVisible(false);
                     MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
                     MainPage.userToggle = "매장관리";
                     MainPage.updateTable.add(new State());
                     MainPage.statecard.next(MainPage.updateTable);
                  }
               }
            });

            move.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "이동할 룸을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
                     MainPage.userToggle = "매장관리-이동";
                  }
               }
            });
            if (rs2 != null) rs2.close();

         } else if (StoreManagementPage.type.equals("좌석")) {

            out_locker.setVisible(false);
            String sql3 = "SELECT Person_Id, Person_Name, seat_type, Phone_Number, time_checkout FROM person_info, seat WHERE seat.seat_number =? AND seat.seat_number = person_info.seat_number";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, StoreManagementPage.seat_number); 
            ResultSet rs3 = pstmt.executeQuery(); 
            while(rs3.next()) { 
               id = rs3.getInt(1);
               name = rs3.getString(2);
               seat_type = rs3.getString(3);
               pn = rs3.getString(4);
               time = rs3.getTimestamp(5);
               exp = Time.TimeStampTOlocalDateTime(time).format(dateTimeFormatter);
            }

            title = new JLabel(StoreManagementPage.seat_number +"번 좌석 정보");
            info = new JLabel(
                  "<html>회원번호 : "+id+"번"+
                  "<br/>회원이름 : "+name+
                  "<br/>핸드폰번호 : "+pn+
                  "<br/>만료기간 : "+exp+
                  "<br/>이용권 : "+seat_type);

            out.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "퇴실처리 하시겠습니까?",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn;
                        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                        PreparedStatement pstmt = null;

                        if (seat_type.equals("당일 이용권")) {
                           
                           String sql_out = "UPDATE seat SET Seat_Statement = '사용 가능', time_enter='01/01/01 00:00:00.000000000', time_checkout='01/01/01 00:00:00.000000000' WHERE Seat_Number=?";
                           pstmt = conn.prepareStatement(sql_out);
                           pstmt.setInt(1, StoreManagementPage.seat_number);
                           int row = pstmt.executeUpdate(); 
                          
                           String sql_out2 = "UPDATE person_info SET seat_number=0, seat_type='없음', expiration_seat ='01/01/01 00:00:00.000000000' WHERE Person_Id = ?";
                           pstmt = conn.prepareStatement(sql_out2);
                           pstmt.setInt(1, id);
                           int row2 = pstmt.executeUpdate(); 
                          
                           MainPage.updateTable.add(new State());
                           MainPage.statecard.next(MainPage.updateTable);
                           
                           if (pstmt != null) pstmt.close();

                        } else if (seat_type.equals("정기 이용권")) {
               
                           String sql_out = "UPDATE seat SET Seat_Statement = '사용 가능', time_enter='01/01/01 00:00:00.000000000', time_checkout='01/01/01 00:00:00.000000000' WHERE Seat_Number=?";
                           pstmt = conn.prepareStatement(sql_out);
                           pstmt.setInt(1, StoreManagementPage.seat_number);
                           int row = pstmt.executeUpdate();     
                           
                           String sql_out2 = "UPDATE person_info SET seat_number=0 WHERE Person_Id = ?";
                           pstmt = conn.prepareStatement(sql_out2);
                           pstmt.setInt(1, id);
                           int row2 = pstmt.executeUpdate(); 

                           MainPage.updateTable.add(new State());
                           MainPage.statecard.next(MainPage.updateTable);
                           
                           if (pstmt != null) pstmt.close();
                        }
                     } catch (SQLException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                     }
                     JOptionPane.showMessageDialog(null, StoreManagementPage.seat_number+ "번 좌석이 퇴실되었습니다");
                     MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
                     MainPage.userToggle = "매장관리";
                     MainPage.updateTable.add(new State());
                     MainPage.statecard.next(MainPage.updateTable);
                  }
               }
            });

            move.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int result = JOptionPane.showConfirmDialog(null, "이동할 좌석을 선택해주세요",null, JOptionPane.OK_CANCEL_OPTION);
                  if(result==0) {               
                     setVisible(false);
                     MainPage.main_page_panel.add("매장관리-이동", new StoreMovePage());
                     MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
                     MainPage.main_cards.show(MainPage.main_page_panel, "매장관리-이동");
                     MainPage.userToggle = "매장관리-이동";
                  }
               }
            });   
            if (rs3 != null) rs3.close();
         } 

         title.setBounds(120,10,300,40);
         info.setBounds(20,0,300,190);
         title.setForeground(Color.white);
         info.setForeground(Color.white);
         add(title);
         add(info);
         setLayout(null);
         setLocationRelativeTo(null);
         getContentPane().setBackground(Color.decode("#404040"));
         setBounds(550, 300, 350, 300);
         setVisible(true);

         if (pstmt != null) pstmt.close();
         if (conn != null) conn.close();

      } catch (ClassNotFoundException | IOException | SQLException e1) {
         e1.printStackTrace();
      }
   }
}