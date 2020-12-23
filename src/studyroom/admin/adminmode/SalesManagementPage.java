package studyroom.admin.adminmode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import studyroom.MainPage;
import studyroom.design.Style;
import studyroom.user.signUp.BirthEnum;
import studyroom.user.signUp.SignUpEnum;
import studyroom.user.signUp.YearMonthClick;
import studyroom.user.signUp.window.ResultWindow;
import studyroom.user.usermode.Reservation;
import studyroom.user.usermode.Payment;
import studyroom.user.usermode.Paycash;

public class SalesManagementPage extends JPanel implements ActionListener {

   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
   JLabel title;
   static JScrollPane scrollPane;

   public static JComboBox<String> year;
   public static JComboBox<String> month;
   public static JComboBox<String> day;

   JButton yearTotal;
   JButton monthTotal;
   JButton dayTotal;
   JButton total;

   JButton back;
   static JLabel totalPayment;

   static JLabel[] weekTotal = new JLabel[5];

   // 관리자 매출관리 페이지 클래스
   
   public SalesManagementPage() {
      setLayout(null);
      new Style(this);

      total = new JButton("누적매출");
      new Style(total);
      total.setBounds(540, 455, 90, 30);
      add(total);

      int x = 78;
      int y = 0;
      for (int i = 0; i < weekTotal.length; i++) {
         weekTotal[i] = new JLabel("");
         new Style(weekTotal[i]);
         weekTotal[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
         weekTotal[i].setBounds(x, 447+y, 135, 50);
         x += 141;
         if(i==2) {
        	 x = 78;
        	 y += 36;
         }
//         weekTotal[i].setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
         add(weekTotal[i]);
      }

      back = new JButton("이전");
      new Style(back);
      back.setBounds(540, 495, 90, 30);
      add(back);

      back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            MainPage.main_cards.show(MainPage.main_page_panel, "관리자메뉴");
            MainPage.userToggle = "관리자메뉴";
         }
      });

      totalPayment = new JLabel();
      new Style(totalPayment);
      totalPayment.setBounds(360, 493, 200, 30);
      add(totalPayment);

      scrollPane = new JScrollPane();
      new Style(scrollPane);
      scrollPane.getVerticalScrollBar().setUnitIncrement(20);
      scrollPane.setBounds(72, 110, 560, 335);
      add(scrollPane);

      year = new JComboBox<String>(new ManagementDate().yearTable);
      year.setBounds(112, 65, 65, 30);
      add(year);
      new Style(year);
      year.setSelectedItem("2020");

      month = new JComboBox<String>(new ManagementDate().monthTable);
      month.setBounds(277, 65, 80, 30);
      add(month);
      new Style(month);

      day = new JComboBox<String>(new ManagementDate().dayTable);
      day.setBounds(442, 65, 80, 30);
      add(day);
      new Style(day);

      // 연도, 월 클릭
      year.addActionListener(new YearMonthClick("year", "매출관리"));
      month.addActionListener(new YearMonthClick("month", "매출관리"));

      yearTotal = new JButton("연매출");
      new Style(yearTotal);
      yearTotal.setBounds(180, 65, 70, 30);
      add(yearTotal);

      // 연매출 버튼 클릭 시 DB로 넘길 sql
      yearTotal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record where substr(paid_time,1,2) = ? order by paid_time", 1);
         }
      });

      monthTotal = new JButton("월매출");
      new Style(monthTotal);
      monthTotal.setBounds(360, 65, 60, 30);
      add(monthTotal);

   // 월매출 버튼 클릭 시 DB로 넘길 sql
      monthTotal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record where substr(paid_time,1,2) = ? "
                  + "and substr(paid_time,4,2) = ? order by paid_time", 2);
         }
      });

      dayTotal = new JButton("일매출");
      new Style(dayTotal);
      dayTotal.setBounds(525, 65, 70, 30);
      add(dayTotal);

   // 일매출 버튼 클릭 시 DB로 넘길 sql
      dayTotal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB(
                  "SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                        + " FROM payment_record where substr(paid_time,1,2) = ? "
                        + "and substr(paid_time,4,2) = ? " + "and substr(paid_time,7,2) = ? order by paid_time",
                  3);
         }
      });

   // 누적매출 버튼 클릭 시 DB로 넘길 sql
      total.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record order by paid_time", 0);
         }
      });

      // 초기 화면
      new SalesDB(
            "SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment" + " FROM payment_record order by paid_time",
            0);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      MainPage.main_page_panel.add("매출관리", new SalesManagementPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "매출관리");
      MainPage.userToggle = "매출관리";

   }

}