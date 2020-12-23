package studyroom.user.usermode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import studyroom.MainPage;
import studyroom.design.Style;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Payment extends JPanel{

   private JTable table;
   static LocalDateTime time_now;
   String time_checkout;
   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
   LocalDateTime pluss;
   public Payment(LocalDateTime ss, int seat_price, String seat_type) {
      time_now = LocalDateTime.now().plusSeconds(30);
      
      // 결제 창 기준 time_now로 만료 시간 적용 (+ 결제 시간 30초)
      this.pluss = ss;
      pluss = ss.plusSeconds(((int) Math.ceil(time_now.getMinute() -Reservation.whatclass_now.getMinute())));
      pluss = ss.plusSeconds(((int) Math.ceil(time_now.getSecond() -Reservation.whatclass_now.getSecond())));
      
      this.setLayout(null);
      new Style(this);
       
      String header[] = {"결제","정보"};
      String contents[][]= {
          {"","  주문 내역 확인"},
          {"",""},
            {"    번호","   "+Reservation.number},
            {"    결제 시간","   "+time_now.format(dateTimeFormatter).substring(0, 24)},
            {"    사용 만료 시간","   "+pluss.format(dateTimeFormatter).substring(0, 24)}, 
            {"    이용권","   "+Reservation.type11},
            {"    결제 금액","   "+Integer.toString((Reservation.price))},
            {"",""}
      };


      DefaultTableModel model = new DefaultTableModel(contents,header);
      table = new JTable(model);
      
        // 가로 길이
      table.getColumnModel().getColumn(0).setPreferredWidth(130);
      table.getColumnModel().getColumn(1).setPreferredWidth(250);
      
      table.getColumnModel().getColumn(0).setPreferredWidth(130);
      table.getColumnModel().getColumn(1).setPreferredWidth(250);
      
      new Style(table);
      table.setBounds(155, 80, 380, 280);
      table.setRowHeight(35);
      table.setForeground(Color.decode("#805b38"));
      table.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      table.setShowGrid(false);
      table.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
      this.add(table);


      JRadioButton card_btn = new JRadioButton("카드");
      new Style(card_btn);
      card_btn.setBounds(433, 380, 70, 23);
      this.add(card_btn);

      JRadioButton cash_btn = new JRadioButton("현금");
      new Style(cash_btn);
      cash_btn.setBounds(200, 380, 70, 23);
      this.add(cash_btn);

      ButtonGroup group = new ButtonGroup();
      group.add(cash_btn);
      group.add(card_btn);

      JButton back_btn = new JButton("돌아가기");
      new Style(back_btn);
      back_btn.setBounds(167, 429, 121, 42);
      this.add(back_btn);

      JButton pay_btn = new JButton("결제하기");
      new Style(pay_btn);
      pay_btn.setBounds(393, 429, 121, 42);
      this.add(pay_btn);
      
      pay_btn.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) { 
            try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               Connection conn = DriverManager.getConnection(
                     "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                     "hr",
                     "1234"
                     );
               PreparedStatement pstmt = null;

         if(cash_btn.isSelected()) {// 현금 결제 프레임 창 띄우기
        	 new Receipt("현금결제");
            new Paycash(pluss, seat_type);
         }


   if(card_btn.isSelected()==true) {//카드 결제
	   new Receipt("카드결제");
      int result= JOptionPane.showConfirmDialog(null, "카드를 삽입하세요","Message",JOptionPane.YES_NO_OPTION);
      if(result==JOptionPane.CLOSED_OPTION) {

      }else if(result==JOptionPane.NO_OPTION) { //취소 메세지
         JOptionPane.showMessageDialog(null,"취소");
      }else { //(카드 결제 버튼 누를시)

      for(int i=0;i<20;i++) {
         if( Reservation.seats_btn.get(i).isSelected()&&(Reservation.seats_btn.get(i).isEnabled()==true)) {//이미 예약되있는 건(비활성화) 빼고 체크
            Reservation.seats_btn.get(i).setEnabled(false);
                           
            //사용중으로 db저장
            String sql = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, i+1);
            int row = pstmt.executeUpdate();

            //입실/퇴실시간 저장
            String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
            pstmt = conn.prepareStatement(sqlt1);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            pstmt.setInt(3, i+1);
            int rowt1 = pstmt.executeUpdate();

            //결제테이블에 저장
            String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql_pay);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            pstmt.setInt(3, Mainmenu.id);
            pstmt.setString(4, Reservation.type11);
            pstmt.setString(5, "카드");
            pstmt.setInt(6,Reservation.price);
            int rowp = pstmt.executeUpdate(); 
              
            //회원info 테이블에 저장(좌석번호,사물함번호,입실)
            sql = "update person_info set seat_number=?,Expiration_seat=?,seat_type=? where person_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, i+1);
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            if(seat_type.contains("주")) {
            pstmt.setString(3, "정기 이용권");
            }else {
            pstmt.setString(3, "당일 이용권");
            }
            pstmt.setInt(4, Mainmenu.id);
            int row3 = pstmt.executeUpdate();
         }
      } 
      
      for(int i=0;i<4;i++) {
         if(Reservation.room_btn.get(i).isSelected()&&(Reservation.room_btn.get(i).isEnabled()==true)) {
            Reservation.room_btn.get(i).setEnabled(false);
         String sql2 = "update seat set Seat_Statement ='사용 중' where Seat_Number= ?";
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1, i+101);
         int row2 = pstmt.executeUpdate();

         String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
         pstmt = conn.prepareStatement(sqlt3);
         pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
         pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
         pstmt.setInt(3, i+101);
         int rowt3 = pstmt.executeUpdate();
                           
         //결제테이블에 저장
         String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql_pay);
         pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
         pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
         pstmt.setInt(3, Mainmenu.id);
         pstmt.setString(4, Reservation.type11);
         pstmt.setString(5, "카드");
         pstmt.setInt(6,Reservation.price);
         int rowp = pstmt.executeUpdate();
                           
         //회원info 테이블에 저장(좌석번호,사물함번호,입실)
         sql2 = "update person_info set room_number=? where person_id=?";
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1,i+101);
         pstmt.setInt(2, Mainmenu.id);
         int row5 = pstmt.executeUpdate();
         
      }   
   }
      for(int i=0;i<20;i++) {//(예약 완료 버튼 누를시)

         if( Reservation.locker_btn.get(i).isSelected()&&(Reservation.locker_btn.get(i).isEnabled()==true)) {
            Reservation.locker_btn.get(i).setEnabled(false);
            String sql3 = "update locker set Locker_Statement ='사용 중' where Locker_Number= ?";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, i+1);
            int row3 = pstmt.executeUpdate();
                     
            String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
            pstmt = conn.prepareStatement(sqlt2);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
            pstmt.setInt(3, i+1);
            int rowt2 = pstmt.executeUpdate();

            //결제테이블에 저장
            String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql_pay);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
            pstmt.setInt(3, Mainmenu.id);
            pstmt.setString(4, Reservation.type11);
            pstmt.setString(5, "카드");
            pstmt.setInt(6, Reservation.price11);
            int rowp = pstmt.executeUpdate();
            
            //회원info 테이블에 저장(좌석번호,사물함번호,입실)
            sql3 = "update person_info set locker_number=? where person_id=?";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, i+1);
            pstmt.setInt(2, Mainmenu.id);
            int row1 = pstmt.executeUpdate();

            }
         }

         JOptionPane.showMessageDialog(null,"결제 완료");
         
         MainPage.user_page_panel.add("영수증",new Receipt(pluss,Reservation.price));
         MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
         MainPage.user_cards.show(MainPage.user_page_panel, "영수증");
         MainPage.userToggle = "영수증"; 
         
         }
      }
         if (pstmt != null) pstmt.close();
         if (conn != null) conn.close();
      } catch (ClassNotFoundException | SQLException e1) { 
         e1.printStackTrace();
      } 
    }
 }); 
      
      back_btn.addActionListener(new ActionListener() { 
   @Override
   public void actionPerformed(ActionEvent e) {
      Reservation.number="";
      for(int i=0;i<20;i++) {
         Reservation.seats_btn.get(i).setSelected(false);
      } 
      for(int i=0;i<4;i++) {
         Reservation.room_btn.get(i).setSelected(false);
      }
      for(int i=0;i<20;i++) {
         Reservation.locker_btn.get(i).setSelected(false);
      } 
      MainPage.user_page_panel.add ("예약페이지", new Reservation(Reservation.whatclass_now, Reservation.time11, Reservation.price11, Reservation.type11));
      MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
      MainPage.user_cards.show(MainPage.user_page_panel, "예약페이지");
      MainPage.userToggle = "예약페이지"; 
      }
      }); 
   } 
}