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
import javax.swing.JFrame;
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

   static ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
   static ArrayList<JButton> room_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
   static ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư

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

      JLabel label01 = new JLabel("1�μ�");
      label01.setBounds(41,40,50,30);
      new Style(label01); 
      label01.setFont(new Font("���� ���", Font.BOLD, 12));
      add(label01);

      JLabel label02 = new JLabel("��");
      label02.setBounds(58,190,50,30);
      new Style(label02);
      label02.setFont(new Font("���� ���", Font.BOLD, 13));
      add(label02);

      JLabel label03 = new JLabel("�繰��");
      label03.setBounds(60,350,50,30);
      new Style(label03);
      label03.setFont(new Font("���� ���", Font.BOLD, 12));
      add(label03);

      JLabel label04 = new JLabel("�ްԽ�");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.decode("#241614"));
      label04.setForeground(Color.decode("#5590cf"));
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

      back = new JButton("���� ȭ��");
      back.setBounds(315,460,140,65);
      this.add(back);
      new Style(back);

      OK = new JButton("�¼� �̵�");
      OK.setBounds(465,460,140,65);
      this.add(OK);
      OK.addActionListener(this);
      new Style(OK);

      label_msg = new JLabel("���� �¼� : "+_06move.num_seat+"��  / ");
      label_msg1 = new JLabel("���� �� : "+_06move.num_room+"ȣ");
      new Style(label_msg);
      new Style(label_msg1);

      label_msg.setBounds(716/2-115,10,120,20);
      label_msg.setFont(new Font("���� ���", Font.BOLD, 13));
      add(label_msg); 

      label_msg1.setBounds(716/2+5,10,120,20);
      label_msg1.setFont(new Font("���� ���", Font.BOLD, 13));
      add(label_msg1); 

      for(int i=0;i<3;i++) {// 1�μ� ��ư ��ġ ����

         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(85+f,40,60,60); 
         f+=60; 
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         } 
      }

      for(int i=3;i<6;i++) {// 1�μ� ��ư ��ġ ����

         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(135+f,40,60,60); 
         f+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=6;i<11;i++) {// 1�μ� ��ư ��ġ ����

         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(185+f,40+a,60,60); 
         a+=60;
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=11; i<14;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(85+g,100,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=14; i<17;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(135+g,100,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      g-=180;
      for(int i=17; i<20;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.orange);
         this.add(seats_btn.get(i));
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "�̵�"));
         seats_btn.get(i).setBounds(135+g,190,60,60);
         g+=60;  
         if(_06move.chk==2) {
            seats_btn.get(i).setEnabled(false);
            seats_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=0;i<2;i++) {//0~3
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setText(i+101+"ȣ");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i), "�̵�")); 
         room_btn.get(i).setBounds(85+e,190,90,75);
         e+=90; 
         if(_06move.chk==1) {
            room_btn.get(i).setEnabled(false);
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=2;i<4;i++) {//0~3
         room_btn.get(i).setBackground(Color.decode("#241614"));
         room_btn.get(i).setText(i+101+"ȣ");
         room_btn.get(i).setForeground(Color.orange);
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i), "�̵�")); 
         room_btn.get(i).setBounds(85+d,265,90,75);
         d+=90; 
         if(_06move.chk==1) {
            room_btn.get(i).setEnabled(false);
            room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         }
      }

      for(int i=0;i<10;i++) {// �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.decode("#241614"));
         locker_btn.get(i).setText(i+1+"��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i), "�̵�"));
         locker_btn.get(i).setBounds(50+c,380,60,30);
         c+=60;
         locker_btn.get(i).setEnabled(false);
         locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
      }

      d=0;
      for(int i=10; i<20;i++) { // �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.decode("#241614"));
         locker_btn.get(i).setText(i+1+"��");
         locker_btn.get(i).setForeground(Color.orange);
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i), "�̵�"));
         locker_btn.get(i).setBounds(50+d,410,60,30);
         d+=60;   
         locker_btn.get(i).setEnabled(false);
         locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
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
            MainPage.user_page_panel.add("�ڸ��̵�", new _06move());
            MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
            MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ��̵�");
            MainPage.userToggle = "�ڸ��̵�";
         }
      };
      back.addActionListener(back_btn);


      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection(
               "jdbc:oracle:thin:@localhost:1521/XEPDB1",
               "hr",
               "1234"
               );

         //������� �¼��̸� üũ�ڽ� üũ �� ��Ȱ��ȭ 
         String sql = "select seat_number from seat where seat_statement='��� ��'";
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery(); 

         while(rs.next()) { 
            int sn = rs.getInt("seat_number"); 
            if(sn<=20) {
               System.out.printf("%d�� ",sn); 
               seats_btn.get(sn-1).setSelected(true);
               seats_btn.get(sn-1).setEnabled(false);
            }else if (sn>=101) {
               System.out.printf("[%dȣ] ",sn); 
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

         for(int i=0;i<=19;i++) {//�ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
            if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) { 
               msg=i+1+"�� (1�μ�)�ڸ���\n";  
               count_only++; 
            }
         }
         for(int i=0;i<4;i++) {//�� üũ(��Ȱ��ȭ ���ִ°� ����)
            if(room_btn.get(i).isSelected()&&(room_btn.get(i).isEnabled()==true)) { 
               msg=i+101+"ȣ ������\n";  
               count_only++; 
            }   
         }

         msg+="�̵��Ͻðڽ��ϱ�?";
         if(count_only==0) {
            msg="�̵��� �ڸ��� �������ּ���";
            JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
         }else if(count_only>1) {
            String warning="1�� 1���� ����";
            JOptionPane.showMessageDialog(this,warning); 
         }else {
            // (â���� or �� or ���)��ư 
            int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
            if(result ==JOptionPane.CLOSED_OPTION) { 
               //(�� Ȯ�� â ����) 
            }else if (result ==JOptionPane.NO_OPTION) {
               JOptionPane.showMessageDialog(this,"���");//��� �޼���
            }else {   

               // yes��ư -> �¼��̵�
               for(int i=0;i<20;i++) {
                  if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ

                     //seat ���̺� �̵��� �¼� <-> ���� �¼� 
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

                     sql = "UPDATE seat SET seat_statement='��� ����', time_enter='01/01/01 00:00:00.000000000', "
                           + "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_seat);
                     int row = pstmt.executeUpdate();



                     //ȸ��info ���̺� �¼���ȣ ������Ʈ
                     sql = "UPDATE person_info SET seat_number=? WHERE person_id=?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, i+1);
                     pstmt.setInt(2, _00main.id);
                     int row3 = pstmt.executeUpdate();

                     System.out.printf("%d�� �ڸ��� �̵��Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
                     System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
                     seats_btn.get( _06move.num_seat-1).setEnabled(true);
                     seats_btn.get( _06move.num_seat-1).setSelected(false);
                  }
                  MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
                  MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
                  MainPage.userToggle = "���θ޴�";
               }

               for(int i=0;i<=3;i++){
                  if(room_btn.get(i).isSelected()&&(room_btn.get(i).isEnabled()==true)) {
                     //seat ���̺� �̵��� �¼� <-> ���� �¼� 
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

                     sql = "UPDATE seat SET seat_statement='��� ����', time_enter='01/01/01 00:00:00.000000000', "
                           + "time_checkout = '01/01/01 00:00:00.000000000' WHERE Seat_Number= ?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, _06move.num_room);
                     int row = pstmt.executeUpdate();



                     //ȸ��info ���̺� �¼���ȣ ������Ʈ
                     sql = "UPDATE person_info SET room_number=? WHERE person_id=?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, i+101);
                     pstmt.setInt(2, _00main.id);
                     int row3 = pstmt.executeUpdate();

                     System.out.printf("%d�� �ڸ��� �̵��Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
                     System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
                     room_btn.get( _06move.num_room-101).setEnabled(true);
                     room_btn.get( _06move.num_room-101).setSelected(false);
                  }
                  MainPage.user_page_panel.add("���θ޴�", new _00main());
                  MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
                  MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
                  MainPage.userToggle = "���θ޴�";
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
}