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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

public class _08reservation extends JPanel implements ActionListener {
   static LocalDateTime time11;
   static int price11;
   static int price;
   static String type11;


   public static String number = "";
   JLabel label_msg;
   static LocalDateTime time_now = LocalDateTime.now();
   String time_checkout;

   int a = 0;
   int d = 0;
   int e = 0;
   int f = 0;
   int g = 0;
   int c = 0;
   
   static ArrayList<JButton> seats_btn = new ArrayList<>(); // 1~20�� �¼� (1�μ�) ��ư
   static ArrayList<JButton> room_btn = new ArrayList<>(); // 1~4�� �¼� (��) ��ư
   static ArrayList<JButton> locker_btn = new ArrayList<>(); // 1~20�� �¼� (��Ŀ) ��ư
   
   static {
      for (int i = 0; i < 20; i++) {
         seats_btn.add(new JButton());
         locker_btn.add(new JButton());
      }
      for (int i = 0; i < 4; i++) {// 0~3
         room_btn.add(new JButton());
      }
   }
   
   public _08reservation(LocalDateTime ss, int price, String seat_type) {
   
      new Style(this);
      JButton OK;
      JButton back;
//      JLabel label = new JLabel("1�μ�");
//      new Style(label);
//      JLabel label02 = new JLabel("��");
//      new Style(label02);
//      label_msg = new JLabel("");
//      new Style(label_msg);
//      label.setBounds(10,10,50,30);
//      label.setFont(new Font("���� ���", Font.BOLD, 15));
//      this.add(label);
//      label02.setBounds(10,103,50,30);
//      label02.setFont(new Font("���� ���", Font.BOLD, 15));
//    
//      label_msg.setBounds(200,310,500,30);
//      label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
//      this.add(label_msg); 
//      this.add(label02);

      JLabel label03 = new JLabel("�繰��");
      label03.setBounds(15, 350, 50, 30);
      label03.setFont(new Font("���� ���", Font.BOLD, 15));
      this.add(label03);
      label03.setForeground(Color.gray);

      JLabel label04 = new JLabel("�ްԽ�");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.black);
      label04.setForeground(Color.decode("#5590cf"));
      label04.setBounds(250, 255, 180, 85);
      label04.setFont(new Font("���� ���", Font.BOLD, 15));
      label04.setHorizontalAlignment(JLabel.CENTER);
      this.add(label04);

      JLabel label05 = new JLabel("��� ����");
      label05.setOpaque(true);
      label05.setBackground(Color.black);
      label05.setForeground(Color.orange);
      label05.setHorizontalAlignment(JLabel.CENTER);
      label05.setBounds(40, 465, 100, 30);
      this.add(label05);

      JLabel label06 = new JLabel("��� ��");
      label06.setOpaque(true);
      label06.setBackground(Color.black);
      label06.setForeground(Color.gray);
      label06.setHorizontalAlignment(JLabel.CENTER);
      label06.setBounds(40, 500, 100, 30);
      this.add(label06);

      for (int i = 0; i < 3; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         System.out.println(   seats_btn.get(i).getText());
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(30 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }
      for (int i = 3; i < 6; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(70 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }
      for (int i = 6; i < 11; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(120 + f, 40 + a, 60, 60);
         a += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }
      for (int i = 11; i < 14; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(30 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }
      for (int i = 14; i < 17; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(70 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }
      g -= 180;
      for (int i = 17; i < 20; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.BLACK);
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
         seats_btn.get(i).setBounds(70 + g, 190, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if (!(price >= 3000 && price <= 10000 || price >= 90000)) {
            seats_btn.get(i).setEnabled(false);
         }
      }

      for (int i = 0; i < 2; i++) {// 0~3
         room_btn.get(i).setBackground(Color.BLACK);
         room_btn.get(i).setText(i + 101 + "ȣ");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i)));
         room_btn.get(i).setBounds(30 + e, 190, 90, 75);
         e += 90;
         room_btn.get(i).setEnabled(true);
         if (!(price >= 12000 && price <= 40000 && price != 25000))
            room_btn.get(i).setEnabled(false);
      }

      for (int i = 2; i < 4; i++) {// 0~3
         room_btn.get(i).setBackground(Color.BLACK);
         room_btn.get(i).setText(i + 101 + "ȣ");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i)));
         room_btn.get(i).setBounds(30 + d, 265, 90, 75);
         d += 90;
         room_btn.get(i).setEnabled(true);
         if (!(price >= 12000 && price <= 40000 && price != 25000))
            room_btn.get(i).setEnabled(false);
      }
      for (int i = 0; i < 10; i++) {// �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.BLACK);
         locker_btn.get(i).setText(i + 1 + "��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i)));
         locker_btn.get(i).setBounds(10 + c, 380, 60, 30);
         c += 55;
         locker_btn.get(i).setEnabled(true);
         if (price != 25000) {
            locker_btn.get(i).setEnabled(false);
         }

      }
      d = 0;
      for (int i = 10; i < 20; i++) { // �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.BLACK);
         locker_btn.get(i).setText(i + 1 + "��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i)));
         locker_btn.get(i).setBounds(10 + d, 410, 60, 30);
         d += 55;
         locker_btn.get(i).setEnabled(true);
         if (price != 25000) {
            locker_btn.get(i).setEnabled(false);
         }
      }

      //System.out.println(seats_btn.get(0).getSize() +" " + room_btn.get(0).getSize() + " " + locker_btn.get(0).getSize());
      
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

            MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
            MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
            MainPage.userToggle = "�̿�Ǳ���";
            number = "";
         }
      };

      back = new JButton("���� ȭ��");
      new Style(back);
      back.setBounds(200, 460, 150, 80);
      this.add(back);
      back.addActionListener(back_btn);

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
//         //�ð� ��
//         String sql = "SELECT seat_number, time_checkout FROM seat "
//               + "WHERE seat_statement ='��� ��'";
//         PreparedStatement pstm = conn.prepareStatement(sql);
//         ResultSet rs = pstm.executeQuery();
//       
//         while(rs.next()) {//��� �ð� ���� �¼� ���ó��
//            int seat_chk = rs.getInt("seat_number");
//            Timestamp time_chk = rs.getTimestamp("time_checkout");
//            if(time_now.isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
//               String change = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
//               PreparedStatement pstmtas = conn.prepareStatement(change);
//               pstmtas.setInt(1, seat_chk);
//               int row3 = pstmtas.executeUpdate();
//            }  
//         } 

         // ������� �¼��̸� üũ�ڽ� üũ �� ��Ȱ��ȭ
         String sql = "select seat_number from seat where seat_statement='��� ��'";
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery();
         System.out.print("����� �ڸ� : ");

         while (rs.next()) {
            int sn = rs.getInt("seat_number");
            if (sn <= 20) {
               System.out.printf("%d�� ", sn);
               seats_btn.get(sn - 1).setSelected(true);
               seats_btn.get(sn - 1).setEnabled(false);
            } else if (sn >= 101) {
               System.out.printf("[%dȣ] ", sn);
               room_btn.get(sn - 101).setSelected(true);
               room_btn.get(sn - 101).setEnabled(false);
            }
         }

//         // ������� �繰�� �� �̿�Ⱓ�� ������ ��밡������ ������Ʈ
//                  sql = "SELECT Locker_Number,l_time_checkout FROM locker "
//                        + "WHERE Locker_Statement='��� ��'";
//                   pstm = conn.prepareStatement(sql);
//                   rs = pstm.executeQuery();
//                
//                  while(rs.next()) { 
//                     int locker_chk = rs.getInt("Locker_Number");
//                     Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
//                     if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
//                        String change = "update locker set Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
//                        PreparedStatement pstm2 = conn.prepareStatement(change);
//                        pstm2.setInt(1, locker_chk);
//                        int row4 = pstm2.executeUpdate();
//                     }  
//                  }

         // ������� �繰���̸� üũ�ڽ� üũ �� ��Ȱ��ȭ
         sql = "select locker_number from locker where locker_statement='��� ��'";
         pstm = conn.prepareStatement(sql);
         rs = pstm.executeQuery();
         System.out.println();
         System.out.printf("����� �繰�� : ");
         while (rs.next()) {
            int sn = rs.getInt("locker_number");
            System.out.printf("%d�� ", sn);
            locker_btn.get(sn - 1).setSelected(true);
            locker_btn.get(sn - 1).setEnabled(false);

         }

         if (rs != null)
            rs.close();
         if (pstm != null)
            pstm.close();
         if (conn != null)
            conn.close();
      } catch (ClassNotFoundException | SQLException e1) {
         e1.printStackTrace();
      }

      OK = new JButton("�����ϱ�");
      new Style(OK);
      OK.setBounds(360, 460, 150, 80);
      this.add(OK);
      OK.addActionListener(this);

      setSize(600, 500);
      setLocation(600, 150);

      this.setBounds(0, 100, 600, 500);
      this.setLayout(null);
      time11 = ss;
      price11 = price;
      type11 = seat_type;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      int count_only = 0;
      int count_only2 = 0;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
         PreparedStatement pstmt = null;

         String msg = "";

         price = 0;
         if ((price11 >= 3000 && price11 <= 10000 || price11 >= 90000)) {
            for (int i = 0; i <= 19; i++) {// �ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
               if (seats_btn.get(i).isSelected() && (seats_btn.get(i).isEnabled() == true)) {
                  msg = i + 1 + "�� (1�μ�) �ڸ�\n";
                  number += i + 1 + "�� �¼� ";

                  price += price11;
                  count_only++;
               }
            }
         }
         if (price11 >= 12000 && price11 <= 40000 && price11 != 25000) {
            for (int i = 0; i <= 3; i++) {
               if (room_btn.get(i).isSelected() && (room_btn.get(i).isEnabled() == true)) {
                  msg = i + 101 + "ȣ ��\n";
                  number += i + 101 + " ȣ �� ";

                  price += price11;
                  count_only++;

               }
            }
         }
         if (price11 == 25000) {
            for (int i = 0; i <= 19; i++) {
               if (locker_btn.get(i).isSelected() && (locker_btn.get(i).isEnabled() == true)) {
                  msg = i + 1 + "�� �繰��\n";
                  number += i + 1 + "�� �繰�� ";
                  price += price11;
                  count_only++;
               }
            }
         }

         msg += "�����Ͻðڽ��ϱ�?";
         if (count_only == 0) {
            msg = "������ �ڸ��� �������ּ���";
            JOptionPane.showMessageDialog(this, msg);// ������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
         } else if (count_only > 1) {
            String warning = "   1�� 1���� ����";
            JOptionPane.showMessageDialog(this, warning);
            number = "";
         } else {
            // (â���� or �� or ���)��ư

            int result = JOptionPane.showConfirmDialog(null, msg, "Message", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.CLOSED_OPTION) {
               // (�� Ȯ�� â ����)
            } else if (result == JOptionPane.NO_OPTION) {
               JOptionPane.showMessageDialog(this, "���");// ��� �޼���
            } else {
               setVisible(false);
               // yes��ư -> ���� ������

               MainPage.user_page_panel.add("����������", new _09payment(time11, price, type11));
               MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
               MainPage.user_cards.show(MainPage.user_page_panel, "����������");
               MainPage.userToggle = "����������";

            }
         }
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      } catch (ClassNotFoundException | SQLException e1) {
         e1.printStackTrace();
      }
   }
   public static void main(String[] args) {
      new MainPage();
   }
}