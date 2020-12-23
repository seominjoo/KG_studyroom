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

   int a = 0;//��ġ �����Ҷ� �ʿ��� ����
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
      for (int i = 0; i < 4; i++) {
         room_btn.add(new JButton());
      }
   }

   public _08reservation(LocalDateTime whatclass_now, LocalDateTime ss, int price, String seat_type) {
	   number = "";
      this.whatclass_now = whatclass_now;
      new Style(this);
      setLayout(null);

      JLabel label01 = new JLabel("1�μ�");
      label01.setBounds(41,40,50,30);
      label01.setFont(new Font("���� ���", Font.BOLD, 10));
      add(label01);
      new Style(label01); 

      JLabel label02 = new JLabel("��");
      label02.setBounds(58,190,50,30);
      label02.setFont(new Font("���� ���", Font.BOLD, 10));
      add(label02);
      new Style(label02);

      JLabel label03 = new JLabel("�繰��");
      label03.setBounds(60,350,50,30);
      label03.setFont(new Font("���� ���", Font.BOLD, 15));
      this.add(label03);
      label03.setForeground(Color.gray);

      JLabel label04 = new JLabel("�ްԽ�");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.decode("#241614") );
      label04.setForeground(Color.decode("#a8877d"));
      label04.setBounds(315,255,180,85);
      label04.setFont(new Font("���� ���", Font.BOLD, 15));
      label04.setHorizontalAlignment(JLabel.CENTER);
      this.add(label04);

      JLabel label05 = new JLabel("��� ����");
      label05.setOpaque(true);
      label05.setBackground(Color.decode("#241614"));
      label05.setForeground(Color.orange);
      label05.setHorizontalAlignment(JLabel.CENTER);
      label05.setBounds(90,460,177,30);
      this.add(label05);

      JLabel label06 = new JLabel("��� ��");
      label06.setOpaque(true);
      label06.setBackground(Color.decode("#241614"));
      label06.setForeground(Color.gray);
      label06.setHorizontalAlignment(JLabel.CENTER);
      label06.setBounds(90,495,177,30);
      this.add(label06);

      JButton back;
      back = new JButton("���� ȭ��");
      new Style(back);
      back.setBounds(315,460,140,65);
      this.add(back);

      JButton OK;
      OK = new JButton("�����ϱ�");
      this.add(OK);
      new Style(OK);
      OK.setBounds(465,460,140,65);
      OK.addActionListener(this);

      for (int i = 0; i < 3; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(85 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 3; i < 6; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(135 + f, 40, 60, 60);
         f += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 6; i < 11; i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(185 + f, 40 + a, 60, 60);
         a += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 11; i < 14; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(85 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 14; i < 17; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(135 + g, 110, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }
      g -= 180;
      for (int i = 17; i < 20; i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton());
         seats_btn.get(i).setBackground(Color.decode("#241614") );
         seats_btn.get(i).setText(i + 1 + "��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i),"����"));
         seats_btn.get(i).setBounds(135 + g, 190, 60, 60);
         g += 60;
         seats_btn.get(i).setEnabled(true);
         if(!seat_type.contains("1�μ�")) {
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            seats_btn.get(i).setEnabled(false);
         } 
      }

      for (int i = 0; i < 2; i++) {// 0~3
         room_btn.get(i).setText(i + 101 + "ȣ");
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i),"����"));
         room_btn.get(i).setBounds(85 + e, 190, 90, 75);
         e += 90;
         room_btn.get(i).setEnabled(true);
         if(!seat_type.contains("��")) {
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            room_btn.get(i).setEnabled(false);
         } 
      }

      for (int i = 2; i < 4; i++) {// 0~3
         room_btn.get(i).setText(i + 101 + "ȣ");
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i),"����"));
         room_btn.get(i).setBounds(85 + d, 265, 90, 75);
         d += 90;
         room_btn.get(i).setEnabled(true);
         if(!seat_type.contains("��")) {
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            room_btn.get(i).setEnabled(false);
         } 
      }
      for (int i = 0; i < 10; i++) {// �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.decode("#241614"));
         locker_btn.get(i).setText(i + 1 + "��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i),"����"));
         locker_btn.get(i).setBounds(50 + c, 380, 60, 30);
         c += 60;
         locker_btn.get(i).setEnabled(true);
         if(!seat_type.contains("�繰��")) {
            locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
            locker_btn.get(i).setEnabled(false);
         } 

      }
      d = 0;
      for (int i = 10; i < 20; i++) { // �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.decode("#241614") );
         locker_btn.get(i).setText(i + 1 + "��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select(locker_btn.get(i),"����"));
         locker_btn.get(i).setBounds(50 + d, 410, 60, 30);
         d += 60;
         locker_btn.get(i).setEnabled(true);
         if(!seat_type.contains("�繰��")) {
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

            MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
            MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
            MainPage.userToggle = "�̿�Ǳ���";
            number = "";
         }
      };
      back.addActionListener(back_btn);

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

         // ������� �¼��̸� üũ�ڽ� üũ �� ��Ȱ��ȭ
         String sql = "select seat_number from seat where seat_statement='��� ��'";
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

         // ������� �繰���̸� üũ�ڽ� üũ �� ��Ȱ��ȭ
         sql = "select locker_number from locker where locker_statement='��� ��'";
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

      if (type11.contains("1�μ�")) {
         for (int i = 0; i <= 19; i++) {// �ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
            if (seats_btn.get(i).isSelected() && (seats_btn.get(i).isEnabled() == true)) {
               msg = i + 1 + "�� (1�μ�) �ڸ�\n";
               number += i + 1 + "�� �¼� ";
               price += price11;
               count_only++;
            }
         }
      }
      
      if (type11.contains("��")) {
         for (int i = 0; i <= 3; i++) {// �ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
            if (room_btn.get(i).isSelected() && (room_btn.get(i).isEnabled() == true)) {
               msg = i + 101 + "ȣ ��\n";
               number += i + 101 + " ȣ �� ";
               price += price11;
               count_only++;

            }
         }
      }
      
      if (type11.contains("�繰��")) {
         for (int i = 0; i <= 19; i++) {// �繰�� üũ(��Ȱ��ȭ ���ִ°� ����)
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
      } else { // (â���� or �� or ���)��ư
         int result = JOptionPane.showConfirmDialog(null, msg, "Message", JOptionPane.YES_NO_OPTION);
         if (result == JOptionPane.CLOSED_OPTION) { // ��Ȯ�� â ����
         } else if (result == JOptionPane.NO_OPTION) { // ��� �޼���
            JOptionPane.showMessageDialog(this, "���");
         } else { // yes��ư -> ���� ������
            setVisible(false);
            MainPage.user_page_panel.add("����������", new _09payment(time11, price, type11));
            MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
            MainPage.user_cards.show(MainPage.user_page_panel, "����������");
            MainPage.userToggle = "����������";
         }
      }
   }

   public static void main(String[] args) {
      new MainPage();
   }
}