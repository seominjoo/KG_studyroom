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
import login.swingTools.State;

public class _07in_selectSeat extends JPanel implements ActionListener{

   static ArrayList<JButton> seats_btn = new ArrayList<>();; //1~20�� �¼� (1�μ�) ��ư
   static ArrayList<JButton> room_btn=  new ArrayList<>();; //1~20�� �¼� (1�μ�) ��ư
   static ArrayList<JButton> locker_btn = new ArrayList<>();; //1~20�� �¼� (1�μ�) ��ư
   static LocalDateTime time_now = LocalDateTime.now();

static {
    for(int i = 0; i < 20; i++) {
        seats_btn.add(new JButton());
        locker_btn.add(new JButton()); 
     }
     for(int i = 0; i < 4; i++) {
        room_btn.add(new JButton()); 
     }
}

   int f=0;
   int g=0;
   int c=0;
   int d=0;
   int e=0; 
   int a=0;

   JLabel label_msg;
   String time_checkout;

   public _07in_selectSeat() {
//	   seats_btn = new ArrayList<>();
//	   room_btn = new ArrayList<>();
//	   locker_btn = new ArrayList<>();
//	      for(int i = 0; i < 20; i++) {
//	          seats_btn.add(new JButton());
//	          locker_btn.add(new JButton()); 
//	       }
//	       for(int i = 0; i < 4; i++) {
//	          room_btn.add(new JButton()); 
//	       }
	   
      new Style(this);
      this.setLayout(null);

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
      label03.setBounds(55,350,50,30);
      new Style(label03);
      label03.setFont(new Font("���� ���", Font.BOLD, 12));
      add(label03);

      JLabel label04 = new JLabel("�ްԽ�");
      label04.setOpaque(true);
      label04.setBorder(BorderFactory.createLineBorder(Color.gray));
      label04.setBackground(Color.decode("#241614"));
      label04.setForeground(Color.decode("#a8877d"));
      label04.setBounds(315,255,180,85);
      label04.setFont(new Font("���� ���", Font.BOLD, 15));
      label04.setHorizontalAlignment(JLabel.CENTER);
      this.add(label04);

      JLabel label05 = new JLabel("���� ����");
      label05.setOpaque(true);
      label05.setBackground(Color.decode("#241614"));
      label05.setForeground(Color.decode("#cfc1b8"));
      label05.setHorizontalAlignment(JLabel.CENTER);
      label05.setBounds(90,460,177,30);
      this.add(label05);

      JLabel label06 = new JLabel("���� �Ұ�");
      label06.setOpaque(true);
      label06.setBackground(Color.decode("#cfc1b8"));
      label06.setForeground(Color.decode("#241614"));
      label06.setHorizontalAlignment(JLabel.CENTER);
      label06.setBounds(90,495,177,30);
      this.add(label06);
      
      back = new JButton("���� ȭ��");
      back.setBounds(315,460,140,65);
      this.add(back);
      new Style(back);
      
      OK = new JButton("�¼� ����");
      this.add(OK);
      new Style(OK);
      OK.setBounds(465,460,140,65);
      OK.addActionListener(this);   

      for(int i=0;i<3;i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(85+f,40,60,60); 
         f+=60;  
      }

      for(int i=3;i<6;i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(135+f,40,60,60); 
         f+=60;  
      }

      for(int i=6;i<11;i++) {// 1�μ� ��ư ��ġ ����
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(185+f,40+a,60,60); 
         a+=60;
      }

      for(int i=11; i<14;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(85+g,100,60,60);
         g+=60;  
      }

      for(int i=14; i<17;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(135+g,100,60,60);
         g+=60;  
      }

      g-=180;
      for(int i=17; i<20;i++) { // 1�μ� ��ư ��ġ ����
         seats_btn.add(new JButton()); 
         seats_btn.get(i).setBackground(Color.decode("#241614"));
         seats_btn.get(i).setText(i+1+"��");
         seats_btn.get(i).setForeground(Color.decode("#cfc1b8"));
         this.add(seats_btn.get(i));
         seats_btn.get(i).setEnabled(true);
         seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i), "����"));
         seats_btn.get(i).setBounds(135+g,190,60,60);
         g+=60;  
      }

      for(int i=0;i<2;i++) {//0~3
         room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         room_btn.get(i).setText(i+101+"ȣ");
         room_btn.get(i).setForeground(Color.decode("#241614"));
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i), "����")); 
         room_btn.get(i).setBounds(85+e,190,90,75);
         e+=90; 
         room_btn.get(i).setEnabled(false);
      }

      for(int i=2;i<4;i++) {//0~3
         room_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         room_btn.get(i).setText(i+101+"ȣ");
         room_btn.get(i).setForeground(Color.decode("#241614"));
         this.add(room_btn.get(i));
         room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i), "����")); 
         room_btn.get(i).setBounds(85+d,265,90,75);
         d+=90; 
         room_btn.get(i).setEnabled(false);
      }

      for(int i=0;i<10;i++) {// �繰�� ��ư ��ġ ����
         locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         locker_btn.get(i).setText(i+1+"��");
         locker_btn.get(i).setForeground(Color.decode("#241614"));
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i), "����"));
         locker_btn.get(i).setBounds(50+c,380,60,30);
         c+=60;
         locker_btn.get(i).setEnabled(false);
      }   

      d=0;
      for(int i=10; i<20;i++) { // �繰�� ��ư ��ġ ����

         locker_btn.get(i).setBackground(Color.decode("#cfc1b8"));
         locker_btn.get(i).setText(i+1+"��");
         locker_btn.get(i).setForeground(Color.decode("#241614"));
         this.add(locker_btn.get(i));
         locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i), "����"));
         locker_btn.get(i).setBounds(50+d,410,60,30);
         d+=60;   
         locker_btn.get(i).setEnabled(false);
      }

      ActionListener back_btn = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            for(int i=0;i<20;i++) {
               seats_btn.get(i).setSelected(false);
            } 
            MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
            MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
            MainPage.userToggle = "���θ޴�";
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

         // ������� �繰���̸� üũ�ڽ� üũ �� ��Ȱ��ȭ 
         sql = "select locker_number from locker where locker_statement='��� ��'";
         pstm = conn.prepareStatement(sql);
         rs = pstm.executeQuery();
         System.out.println();
         System.out.printf("����� �繰�� : ");
         while(rs.next()) {
            int sn = rs.getInt("locker_number");
            System.out.printf("%d�� ",sn);
            locker_btn.get(sn-1).setSelected(true);
            locker_btn.get(sn-1).setEnabled(false); 

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
                  msg=i+1+"�� (1�μ�) �ڸ�\n";  
                  count_only++; 
            }
         } 
            msg+="�����Ͻðڽ��ϱ�?";
            if(count_only==0) {
               msg="������ �ڸ��� �������ּ���";
               JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
            }else if(count_only>1) {
               String warning="1�� 1���� ����";
               JOptionPane.showMessageDialog(this,warning); 
            }else { // (â���� or �� or ���) ��ư 
               
               int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
               if(result ==JOptionPane.CLOSED_OPTION) { // ��Ȯ�� â ����   
               }else if (result ==JOptionPane.NO_OPTION) { //��� �޼���
                  JOptionPane.showMessageDialog(this,"���");
               }else { // yes��ư -> �¼����� 
                   
             
         for(int i=0;i<20;i++) {
            if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
               seats_btn.get(i).setEnabled(false);
                                          
               //��������� db����
               String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, i+1);
               int row = pstmt.executeUpdate();

               //�Խ�/��ǽð� ����
               String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
               pstmt = conn.prepareStatement(sqlt1);
               pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
                pstmt.setTimestamp(2, _00main.time_seat);
               pstmt.setInt(3, i+1);
               int rowt1 = pstmt.executeUpdate();
                           
               //ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
               sql = "update person_info set seat_number=? where person_id=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, i+1);
               pstmt.setInt(2, _00main.id);
                           
               int row3 = pstmt.executeUpdate();
                           
               System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
               System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt1); 
               System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
               MainPage.user_page_panel.add("���θ޴�", new _00main()); // �޴�������
               MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
               MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
               MainPage.userToggle = "���θ޴�";
                MainPage.updateTable.add(new State());
                MainPage.statecard.next(MainPage.updateTable);
            }
         }
         msg = "�ԽǵǾ����ϴ�.";
         JOptionPane.showMessageDialog(this,msg);
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