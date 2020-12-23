package login.page;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.BirthEnum;
import login.YearMonthClick;
import login.design.Style;
import login.mainmenu._08reservation;
import login.mainmenu._09payment;
import login.mainmenu._10paycash;
import login.signUp.SignUpEnum;
import login.signUp.window.ResultWindow;

public class SalesManagementPage extends JPanel implements ActionListener {

   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
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

   // ������ ������� ������ Ŭ����
   
   public SalesManagementPage() {
      setLayout(null);
      new Style(this);

      total = new JButton("��������");
      new Style(total);
      total.setBounds(80, 481, 100, 30);
      add(total);

      int y = 430;
      for (int i = 0; i < weekTotal.length; i++) {
         weekTotal[i] = new JLabel("");
         new Style(weekTotal[i]);
         weekTotal[i].setFont(new Font("���� ���", Font.BOLD, 13));
         weekTotal[i].setBounds(240, y, 200, 50);
         y += 20;
         add(weekTotal[i]);
      }

      back = new JButton("����");
      new Style(back);
      back.setBounds(479, 481, 100, 30);
      add(back);

      back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            MainPage.main_cards.show(MainPage.main_page_panel, "�����ڸ޴�");
            MainPage.userToggle = "�����ڸ޴�";
         }
      });

      totalPayment = new JLabel();
      new Style(totalPayment);
      totalPayment.setBounds(430, 403, 200, 100);
      add(totalPayment);

      scrollPane = new JScrollPane();
      new Style(scrollPane);
      scrollPane.getVerticalScrollBar().setUnitIncrement(20);
      scrollPane.setBounds(80, 85, 500, 350);
      add(scrollPane);

      year = new JComboBox<String>(new ManagementDate().yearTable);
      year.setBounds(90, 40, 65, 30);
      add(year);
      new Style(year);
      year.setSelectedItem("2020");

      month = new JComboBox<String>(new ManagementDate().monthTable);
      month.setBounds(255, 40, 80, 30);
      add(month);
      new Style(month);

      day = new JComboBox<String>(new ManagementDate().dayTable);
      day.setBounds(420, 40, 80, 30);
      add(day);
      new Style(day);

      // ����, �� Ŭ��
      year.addActionListener(new YearMonthClick("year", "�������"));
      month.addActionListener(new YearMonthClick("month", "�������"));

      yearTotal = new JButton("������");
      new Style(yearTotal);
      yearTotal.setBounds(158, 40, 70, 30);
      add(yearTotal);

      // ������ ��ư Ŭ�� �� DB�� �ѱ� sql
      yearTotal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record where substr(paid_time,1,2) = ? order by paid_time", 1);
         }
      });

      monthTotal = new JButton("������");
      new Style(monthTotal);
      monthTotal.setBounds(338, 40, 60, 30);
      add(monthTotal);

   // ������ ��ư Ŭ�� �� DB�� �ѱ� sql
      monthTotal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record where substr(paid_time,1,2) = ? "
                  + "and substr(paid_time,4,2) = ? order by paid_time", 2);
         }
      });

      dayTotal = new JButton("�ϸ���");
      new Style(dayTotal);
      dayTotal.setBounds(503, 40, 70, 30);
      add(dayTotal);

   // �ϸ��� ��ư Ŭ�� �� DB�� �ѱ� sql
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

   // �������� ��ư Ŭ�� �� DB�� �ѱ� sql
      total.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new SalesDB("SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment"
                  + " FROM payment_record order by paid_time", 0);
         }
      });

      // �ʱ� ȭ��
      new SalesDB(
            "SELECT paid_time,person_id,seat_type,locker_type,pay_method,payment" + " FROM payment_record order by paid_time",
            0);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      MainPage.main_page_panel.add("�������", new SalesManagementPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "�������");
      MainPage.userToggle = "�������";

   }

}