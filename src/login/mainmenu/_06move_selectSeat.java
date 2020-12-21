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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

public class _06move_selectSeat extends JPanel implements ActionListener{

   int f=0;
   int g=0;
   int c=0;
   int d=0;
   int e=0; 
   int a=0;

   static ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼
   static ArrayList<JButton> room_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼
   static ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20번 좌석 (1인석) 버튼

   static {
      for(int i = 0; i < 20; i++) {
         seats_btn.add(new JButton());
         locker_btn.add(new JButton()); 
      }
      for(int i = 0; i < 4; i++) {
         room_btn.add(new JButton()); 
      }
   }
   JLabel label_msg;
   JLabel label_msg1;
   static LocalDateTime time_now = LocalDateTime.now();
   String time_checkout;

   public _06move_selectSeat() {

      new Style(this);
      setLayout(null);

      JButton OK;
      JButton back;
      JLabel label03 = new JLabel("사물함");

      label03.setBounds(65,350,50,30);
      label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      this.add(label03);
      new Style(label03);

      label_msg = new JLabel("현재 좌석 : "+_06move.num_seat+"번");
      label_msg1 = new JLabel("현재 룸 : "+_06move.num_room+"호");

      label_msg.setBounds(172,465,150,30);
      label_msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      this.add(label_msg); 
      label_msg1.setBounds(172,500,150,30);
      label_msg1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      this.add(label_msg1); 
      JLabel label04 = new JLabel("휴게실");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.black);
      label04.setForeground(Color.decode("#5590cf"));
      label04.setBounds(300,255,180,85);
      label04.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      label04.setHorizontalAlignment(JLabel.CENTER);

      this.add(label04);
      new Style(label_msg);
      new Style(label_msg1);

      JLabel label05 = new JLabel("선택 가능");
      label05.setOpaque(true);
      label05.setBackground(Color.black);
      label05.setForeground(Color.orange);
      label05.setHorizontalAlignment(JLabel.CENTER);
      label05.setBounds(70,465,100,30);
      this.add(label05);

      JLabel label06 = new JLabel("선택 불가");
      label06.setOpaque(true);
      label06.setBackground(Color.black);
      label06.setForeground(Color.gray);
      label06.setHorizontalAlignment(JLabel.CENTER);
      label06.setBounds(70,500,100,30);
      this.add(label06);

      for(int i=0;i<3;i++) {// 1인석 버튼 위치 설정

         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(80+f,40,60,60); 
         f+=60; 
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         } 
      }

      for(int i=3;i<6;i++) {// 1인석 버튼 위치 설정

         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(120+f,40,60,60); 
         f+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      for(int i=6;i<11;i++) {// 1인석 버튼 위치 설정

         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(170+f,40+a,60,60); 
         a+=60;
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      for(int i=11; i<14;i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(80+g,100,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      for(int i=14; i<17;i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(120+g,100,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      g-=180;
      for(int i=17; i<20;i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i+1+"번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(120+g,190,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      for(int i=0;i<2;i++) {//0~3
         room_btn.get(i).setBackground(Color.BLACK);
         room_btn.get(i).setText(i+101+"호");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
         room_btn.get(i).setBounds(80+e,190,90,75);
         e+=90; 
         if(_06move.chk==1) {
            room_btn.get(i).setEnabled(false);
         }
      }

      for(int i=2;i<4;i++) {//0~3
         room_btn.get(i).setBackground(Color.BLACK);
         room_btn.get(i).setText(i+101+"호");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
         room_btn.get(i).setBounds(80+d,265,90,75);
         d+=90; 
         if(_06move.chk==1) {
            room_btn.get(i).setEnabled(false);
         }
      }

      for(int i=0;i<10;i++) {// 사물함 버튼 위치 설정
         locker_btn.get(i).setBackground(Color.BLACK);
         locker_btn.get(i).setText(i+1+"번");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
         locker_btn.get(i).setBounds(60+c,380,60,30);
         c+=60;
         locker_btn.get(i).setEnabled(false);
      }

      d=0;
      for(int i=10; i<20;i++) { // 사물함 버튼 위치 설정
         locker_btn.get(i).setBackground(Color.BLACK);
         locker_btn.get(i).setText(i+1+"번");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
         locker_btn.get(i).setBounds(60+d,410,60,30);
         d+=60;   
         locker_btn.get(i).setEnabled(false);
      }

      ActionListener back_btn = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i=0;i<20;i++) {
               seats_btn.get(i).setSelected(false);
               seats_btn.get(i).setEnabled(true);
            } 
            for(int i=0; i<4;i++) {
               room_btn.get(i).setSelected(false);
               room_btn.get(i).setEnabled(true);
            } 
            MainPage.user_page_panel.add("자리이동", new _06move());
            MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
            MainPage.user_cards.show(MainPage.user_page_panel, "자리이동");
            MainPage.userToggle = "자리이동";
         }
      };

      back = new JButton("이전 화면");
      back.setBounds(290,460,140,80);
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
         if(rs!=null) rs.close(); 
         if (pstm != null) pstm.close();
         if (conn != null) conn.close();
      } catch (ClassNotFoundException | SQLException e1) { 
         e1.printStackTrace();
      }

      OK = new JButton("좌석 이동하기");
      OK.setBounds(450,460,140,80);
      this.add(OK);
      OK.addActionListener(this);
      new Style(OK);

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
               msg=i+1+"번 (1인석)자리로\n";  
               count_only++; 
            }
         }
         for(int i=0;i<4;i++) {//룸 체크(비활성화 되있는건 제외)
            if(room_btn.get(i).isSelected()&&(room_btn.get(i).isEnabled()==true)) { 
               msg=i+101+"호 룸으로\n";  
               count_only++; 
            }   
         }

         msg+="이동하시겠습니까?";
         if(count_only==0) {
            msg="이동할 자리를 선택해주세요";
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

               // yes버튼 -> 좌석이동
               for(int i=0;i<20;i++) {
                  if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크

                     //seat 테이블에 이동한 좌석 <-> 원래 좌석 
                     String sql = "SELECT seat_statement, time_enter, time_checkout"
                           + " FROM seat WHERE seat_number =?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_seat); 
                     ResultSet rs = pstmt.executeQuery(); 

                     while(rs.next()) { 
                        String st = rs.getString("seat_statement");
                        Timestamp tc = rs.getTimestamp("time_enter");
                        Timestamp tco = rs.getTimestamp("time_checkout");

                        sql = "UPDATE seat SET seat_statement =?,"
                              + "time_enter=?, time_checkout=? WHERE Seat_Number= ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, st);
                        pstmt.setTimestamp(2, tc);
                        pstmt.setTimestamp(3, tco);
                        pstmt.setInt(4, i+1);
                        int row2 = pstmt.executeUpdate();
                     }

                     sql = "UPDATE seat SET seat_statement='사용 가능', time_enter='01/01/01 00:00:00.000000000', "
                           + "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_seat);
                     int row = pstmt.executeUpdate();



                     //회원info 테이블에 좌석번호 업데이트
                     sql = "UPDATE person_info SET seat_number=? WHERE person_id=?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, i+1);
                     pstmt.setInt(2, _00main.id);
                     int row3 = pstmt.executeUpdate();

                     System.out.printf("%d번 자리로 이동되었습니다.(%d행 업데이트)\n", i+1,row);
                     System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
                     seats_btn.get( _06move.num_seat-1).setEnabled(true);
                     seats_btn.get( _06move.num_seat-1).setSelected(false);
                  }
                  MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
                  MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
                  MainPage.userToggle = "메인메뉴";
               }

               for(int i=0;i<=3;i++){
                  if(room_btn.get(i).isSelected()&&(room_btn.get(i).isEnabled()==true)) {
                     //seat 테이블에 이동한 좌석 <-> 원래 좌석 
                     String sql = "SELECT seat_statement, time_enter, time_checkout"
                           + " FROM seat WHERE seat_number =?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_room); 
                     ResultSet rs = pstmt.executeQuery(); 

                     while(rs.next()) { 
                        String st = rs.getString("seat_statement");
                        Timestamp tc = rs.getTimestamp("time_enter");
                        Timestamp tco = rs.getTimestamp("time_checkout");

                        sql = "UPDATE seat SET seat_statement =?,"
                              + "time_enter=?, time_checkout=? WHERE Seat_Number= ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, st);
                        pstmt.setTimestamp(2, tc);
                        pstmt.setTimestamp(3, tco);
                        pstmt.setInt(4, i+101);
                        int row2 = pstmt.executeUpdate();
                     }

                     sql = "UPDATE seat SET seat_statement='사용 가능', time_enter='01/01/01 00:00:00.000000000', "
                           + "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_room);
                     int row = pstmt.executeUpdate();



                     //회원info 테이블에 좌석번호 업데이트
                     sql = "UPDATE person_info SET room_number=? WHERE person_id=?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, i+101);
                     pstmt.setInt(2, _00main.id);
                     int row3 = pstmt.executeUpdate();

                     System.out.printf("%d번 자리로 이동되었습니다.(%d행 업데이트)\n", i+1,row);
                     System.out.printf("회원 정보가 업데이트되었습니다.(%d행 업데이트)\n",row3); 
                     room_btn.get( _06move.num_room-101).setEnabled(true);
                     room_btn.get( _06move.num_room-101).setSelected(false);
                  }
                  MainPage.user_page_panel.add("메인메뉴", new _00main());
                  MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
                  MainPage.user_cards.show(MainPage.user_page_panel, "메인메뉴");
                  MainPage.userToggle = "메인메뉴";
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