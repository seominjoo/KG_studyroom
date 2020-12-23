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
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

public class _08reservation extends JPanel implements ActionListener {

   public static String number = "";
   static LocalDateTime time_now = LocalDateTime.now();
   static LocalDateTime time11;
   static String type11;
   static int price11;
   static int price;
   static LocalDateTime whatclass_now;
   JLabel label_msg;
   String time_checkout;

   int a = 0;//위치 설정할때 필요한 변수
   int d = 0;
   int e = 0;
   int f = 0;
   int g = 0;
   int c = 0;

   static ArrayList<JButton> seats_btn = new ArrayList<>(); // 1~20번 좌석 (1인석) 버튼
   static ArrayList<JButton> room_btn = new ArrayList<>(); // 1~4번 좌석 (룸) 버튼
   static ArrayList<JButton> locker_btn = new ArrayList<>(); // 1~20번 좌석 (락커) 버튼

   static {
      for (int i = 0; i < 20; i++) {
         seats_btn.add(new JButton());
         locker_btn.add(new JButton());
      }
      for (int i = 0; i < 4; i++) {
         room_btn.add(new JButton());
      }
   }

   public _08reservation(LocalDateTime whatclass_now, LocalDateTime ss, int price, String seat_type) {
	   number = "";
      this.whatclass_now = whatclass_now;
      new Style(this);
      setLayout(null);

      JLabel label01 = new JLabel("1인석");
      label01.setBounds(41,40,50,30);
      label01.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      add(label01);
      new Style(label01); 

      JLabel label02 = new JLabel("룸");
      label02.setBounds(58,190,50,30);
      label02.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      add(label02);
      new Style(label02);

      JLabel label03 = new JLabel("사물함");
      label03.setBounds(60,350,50,30);
      label03.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      this.add(label03);
      label03.setForeground(Color.gray);

      JLabel label04 = new JLabel("휴게실");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.decode("#241614") );
      label04.setForeground(Color.decode("#a8877d"));
      label04.setBounds(315,255,180,85);
      label04.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      label04.setHorizontalAlignment(JLabel.CENTER);
      this.add(label04);

      JLabel label05 = new JLabel("사용 가능");
      label05.setOpaque(true);
      label05.setBackground(Color.decode("#241614"));
      label05.setForeground(Color.orange);
      label05.setHorizontalAlignment(JLabel.CENTER);
      label05.setBounds(90,460,177,30);
      this.add(label05);

      JLabel label06 = new JLabel("사용 중");
      label06.setOpaque(true);
      label06.setBackground(Color.decode("#241614"));
      label06.setForeground(Color.gray);
      label06.setHorizontalAlignment(JLabel.CENTER);
      label06.setBounds(90,495,177,30);
      this.add(label06);

      JButton back;
      back = new JButton("이전 화면");
      new Style(back);
      back.setBounds(315,460,140,65);
      this.add(back);

      JButton OK;
      OK = new JButton("결제하기");
      this.add(OK);
      new Style(OK);
      OK.setBounds(465,460,140,65);
      OK.addActionListener(this);

      for (int i = 0; i < 3; i++) {// 1인석 버튼 위치 설정
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(85 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 3; i < 6; i++) {// 1인석 버튼 위치 설정
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(135 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 6; i < 11; i++) {// 1인석 버튼 위치 설정
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(185 + f, 40 + a, 60, 60);
         a += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 11; i < 14; i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(85 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 14; i < 17; i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(135 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      g -= 180;
      for (int i = 17; i < 20; i++) { // 1인석 버튼 위치 설정
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "번");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"당일"));
         seats_btn.get(i).setBounds(135 + g, 190, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1인석")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }

      for (int i = 0; i < 2; i++) {// 0~3
         room_btn.get(i).setText(i + 101 + "호");
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i),"당일"));
         room_btn.get(i).setBounds(85 + e, 190, 90, 75);
         e += 90;
         room_btn.get(i).setEnabled(true);
         if(!seat_type.contains("룸")) {
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            room_btn.get(i).setEnabled(false);
         } 
      }

      for (int i = 2; i < 4; i++) {// 0~3
         room_btn.get(i).setText(i + 101 + "호");
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i),"당일"));
         room_btn.get(i).setBounds(85 + d, 265, 90, 75);
         d += 90;
         room_btn.get(i).setEnabled(true);
         if(!seat_type.contains("룸")) {
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            room_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 0; i < 10; i++) {// 사물함 버튼 위치 설정
         locker_btn.get(i).setBackground(Color.decode("#241614"));
         locker_btn.get(i).setText(i + 1 + "번");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i),"당일"));
         locker_btn.get(i).setBounds(50 + c, 380, 60, 30);
         c += 60;
         locker_btn.get(i).setEnabled(true);
         if(!seat_type.contains("사물함")) {
            locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            locker_btn.get(i).setEnabled(false);
         } 

      }
      d = 0;
      for (int i = 10; i < 20; i++) { // 사물함 버튼 위치 설정
         locker_btn.get(i).setBackground(Color.decode("#241614") );
         locker_btn.get(i).setText(i + 1 + "번");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i),"당일"));
         locker_btn.get(i).setBounds(50 + d, 410, 60, 30);
         d += 60;
         locker_btn.get(i).setEnabled(true);
         if(!seat_type.contains("사물함")) {
            locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            locker_btn.get(i).setEnabled(false);
         } 
      }
 
      ActionListener back_btn = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            for (int i = 0; i < 20; i++) {
               seats_btn.get(i).setSelected(false);
            }
            for (int i = 0; i < 4; i++) {
               room_btn.get(i).setSelected(false);
            }
            for (int i = 0; i < 20; i++) {
               locker_btn.get(i).setSelected(false);
            }

            MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
            MainPage.user_cards.show(MainPage.user_page_panel, "이용권구매");
            MainPage.userToggle = "이용권구매";
            number = "";
         }
      };
      back.addActionListener(back_btn);

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

         // 사용중인 좌석이면 체크박스 체크 및 비활성화
         String sql = "select seat_number from seat where seat_statement='사용 중'";
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery(); 

         while (rs.next()) {
            int sn = rs.getInt("seat_number");
            if (sn <= 20) { 
               seats_btn.get(sn - 1).setEnabled(false);
               seats_btn.get(sn - 1).setSelected(true);
               
            } else if (sn >= 101) { 
               room_btn.get(sn - 101).setEnabled(false);
               room_btn.get(sn - 101).setSelected(true);
            }
         }

         // 사용중인 사물함이면 체크박스 체크 및 비활성화
         sql = "select locker_number from locker where locker_statement='사용 중'";
         pstm = conn.prepareStatement(sql);
         rs = pstm.executeQuery();
         while (rs.next()) {
            int sn = rs.getInt("locker_number");
            locker_btn.get(sn - 1).setEnabled(false);
            locker_btn.get(sn - 1).setSelected(true);
         }

         if (rs != null) rs.close();
         if (pstm != null) pstm.close();
         if (conn != null) conn.close();

      } catch (ClassNotFoundException | SQLException e1) {
         e1.printStackTrace();
      }
      time11 = ss;
      price11 = price;
      type11 = seat_type;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      int count_only = 0;
      String msg = "";
      price = 0;

      if (type11.contains("1인석")) {
         for (int i = 0; i <= 19; i++) {// 자리 체크(비활성화 되있는건 제외)
            if (seats_btn.get(i).isSelected() && (seats_btn.get(i).isEnabled() == true)) {
               msg = i + 1 + "번 (1인석) 자리\n";
               number += i + 1 + "번 좌석 ";
               price += price11;
               count_only++;
            }
         }
      }
      
      if (type11.contains("룸")) {
         for (int i = 0; i <= 3; i++) {// 자리 체크(비활성화 되있는건 제외)
            if (room_btn.get(i).isSelected() && (room_btn.get(i).isEnabled() == true)) {
               msg = i + 101 + "호 룸\n";
               number += i + 101 + " 호 룸 ";
               price += price11;
               count_only++;

            }
         }
      }
      
      if (type11.contains("사물함")) {
         for (int i = 0; i <= 19; i++) {// 사물함 체크(비활성화 되있는건 제외)
            if (locker_btn.get(i).isSelected() && (locker_btn.get(i).isEnabled() == true)) {
               msg = i + 1 + "번 사물함\n";
               number += i + 1 + "번 사물함 ";
               price += price11;
               count_only++;
            }
         }
      }

      msg += "결제하시겠습니까?";
      if (count_only == 0) {
         msg = "결제할 자리를 선택해주세요";
         JOptionPane.showMessageDialog(this, msg);// 예약이 없으면 다시선택 메세지 창 띄우기(메세지 길이로 체크)
      } else if (count_only > 1) {
         String warning = "   1인 1선택 가능";
         JOptionPane.showMessageDialog(this, warning);
         number = "";
      } else { // (창끄기 or 예 or 취소)버튼
         int result = JOptionPane.showConfirmDialog(null, msg, "Message", JOptionPane.YES_NO_OPTION);
         if (result == JOptionPane.CLOSED_OPTION) { // 재확인 창 끄기
         } else if (result == JOptionPane.NO_OPTION) { // 취소 메세지
            JOptionPane.showMessageDialog(this, "취소");
         } else { // yes버튼 -> 결제 페이지
            setVisible(false);
            MainPage.user_page_panel.add("결제페이지", new _09payment(time11, price, type11));
            MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
            MainPage.user_cards.show(MainPage.user_page_panel, "결제페이지");
            MainPage.userToggle = "결제페이지";
         }
      }
   }

   public static void main(String[] args) {
      new MainPage();
   }
}