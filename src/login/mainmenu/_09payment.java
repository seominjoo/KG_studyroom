package login.mainmenu;

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
import javax.swing.table.DefaultTableModel;

import login.design.Style;
import login.page.MainPage;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class _09payment extends JPanel{

   private JTable table;
   static LocalDateTime time_now;
   String time_checkout;
   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� a hh�� mm�� ss��");
   LocalDateTime pluss;
   public _09payment(LocalDateTime ss, int seat_price, String seat_type) {
	   time_now = LocalDateTime.now().plusSeconds(30);
	   
	   // ���� â ���� time_now�� ���� �ð� ���� (+ ���� �ð� 30��)
	   this.pluss = ss;
	   pluss = ss.plusSeconds(time_now.getMinute() -_08reservation.whatclass_now.getMinute());
	   pluss = ss.plusSeconds(((int) Math.ceil(time_now.getSecond() -_08reservation.whatclass_now.getSecond())));
      
	  setLayout(null);
      new Style(this);

      JPanel p2 = new JPanel();
      p2.setBounds(0, 0, 706, 453);
      this.add(p2);
      p2.setLayout(null);
      new Style(p2);
       
      String header[] = {"����","����"};
      String contents[][]= {
            {"��ȣ",_08reservation.number},
            {"���� �ð�",time_now.format(dateTimeFormatter).substring(0, 24)},
            {"��� ���� �ð�",pluss.format(dateTimeFormatter).substring(0, 24)}, 
            {"�̿��",_08reservation.type11},
            {"���� �ݾ�",Integer.toString((_08reservation.price))}
      };


      DefaultTableModel model = new DefaultTableModel(contents,header);
      table = new JTable(model);
      new Style(table);
      table.setBounds(60, 104, 450, 175);
      table.setRowHeight(35);
      p2.add(table);

//      Color color = UIManager.getColor("Table.gridColor");
//      MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
//      table.setBorder(border);

      JRadioButton card_btn = new JRadioButton("ī��");
      new Style(card_btn);
      card_btn.setBounds(350, 320, 70, 23);
      p2.add(card_btn);

      JRadioButton cash_btn = new JRadioButton("����");
      new Style(cash_btn);
      cash_btn.setBounds(150, 320, 70, 23);
      p2.add(cash_btn);

      ButtonGroup group = new ButtonGroup();
      group.add(cash_btn);
      group.add(card_btn);

      JButton back_btn = new JButton("���ư���");
      new Style(back_btn);
      back_btn.setBounds(120, 381, 121, 42);
      p2.add(back_btn);

      JButton pay_btn = new JButton("�����ϱ�");
      new Style(pay_btn);
      pay_btn.setBounds(330, 381, 121, 42);
      p2.add(pay_btn);

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

         if(cash_btn.isSelected()) {// ���� ���� ������ â ����
            new _10paycash(pluss);
         }


   if(card_btn.isSelected()==true) {//ī�� ����
      int result= JOptionPane.showConfirmDialog(null, "ī�带 �����ϼ���","Message",JOptionPane.YES_NO_OPTION);
      if(result==JOptionPane.CLOSED_OPTION) {

      }else if(result==JOptionPane.NO_OPTION) { //��� �޼���
         JOptionPane.showMessageDialog(null,"���");
      }else { //(ī�� ���� ��ư ������)

      for(int i=0;i<20;i++) {
         if( _08reservation.seats_btn.get(i).isSelected()&&(_08reservation.seats_btn.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
            _08reservation.seats_btn.get(i).setEnabled(false);
                           
            //��������� db����
            String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, i+1);
            int row = pstmt.executeUpdate();

            //�Խ�/��ǽð� ����
            String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
            pstmt = conn.prepareStatement(sqlt1);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            pstmt.setInt(3, i+1);
            int rowt1 = pstmt.executeUpdate();

            //�������̺� ����
            String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql_pay);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            pstmt.setInt(3, _00main.id);
            pstmt.setString(4, _08reservation.type11);
            pstmt.setString(5, "ī��");
            pstmt.setInt(6,_08reservation.price);
            int rowp = pstmt.executeUpdate(); 
              
            //ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
            sql = "update person_info set seat_number=?,Expiration_seat=?,seat_type=? where person_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, i+1);
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
            if(_08reservation.price>=90000) {
            pstmt.setString(3, "���� �̿��");
            }else {
            pstmt.setString(3, "���� �̿��");
            }
            pstmt.setInt(4, _00main.id);
            int row3 = pstmt.executeUpdate();
            System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
            System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt1);
            System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
            System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3);
         }
      } 
      
      for(int i=0;i<4;i++) {
         if(_08reservation.room_btn.get(i).isSelected()&&(_08reservation.room_btn.get(i).isEnabled()==true)) {
            _08reservation.room_btn.get(i).setEnabled(false);
         String sql2 = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1, i+101);
         int row2 = pstmt.executeUpdate();

         String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
         pstmt = conn.prepareStatement(sqlt3);
         pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
         pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
         pstmt.setInt(3, i+101);
         int rowt3 = pstmt.executeUpdate();
                           
         //�������̺� ����
         String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql_pay);
         pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
         pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(pluss));
         pstmt.setInt(3, _00main.id);
         pstmt.setString(4, _08reservation.type11);
         pstmt.setString(5, "ī��");
         pstmt.setInt(6,_08reservation.price);
         int rowp = pstmt.executeUpdate();
                           
         //ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
         sql2 = "update person_info set room_number=? where person_id=?";
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1,i+101);
         pstmt.setInt(2, _00main.id);
         int row5 = pstmt.executeUpdate();
         
         System.out.printf("%dȣ ���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+101,row2);
         System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt3); 
         System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
         System.out.printf("ȸ�������� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row5);
      }   
   }
      for(int i=0;i<20;i++) {//(���� �Ϸ� ��ư ������)

         if( _08reservation.locker_btn.get(i).isSelected()&&(_08reservation.locker_btn.get(i).isEnabled()==true)) {
            _08reservation.locker_btn.get(i).setEnabled(false);
            String sql3 = "update locker set Locker_Statement ='��� ��' where Locker_Number= ?";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, i+1);
            int row3 = pstmt.executeUpdate();
                     
            String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
            pstmt = conn.prepareStatement(sqlt2);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
            pstmt.setInt(3, i+1);
            int rowt2 = pstmt.executeUpdate();

            //�������̺� ����
            String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql_pay);
            pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
            pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
            pstmt.setInt(3, _00main.id);
            pstmt.setString(4, _08reservation.type11);
            pstmt.setString(5, "ī��");
            pstmt.setInt(6, _08reservation.price11);
            int rowp = pstmt.executeUpdate();
            
            //ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
            sql3 = "update person_info set locker_number=? where person_id=?";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, i+1);
            pstmt.setInt(2, _00main.id);
            int row1 = pstmt.executeUpdate();

            System.out.printf("%d�� �繰���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row3);
            System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt2);
            System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
            System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row1); 
            }
         }

         JOptionPane.showMessageDialog(null,"���� �Ϸ�");
         
         MainPage.user_page_panel.add("������",new _11receipt(pluss,_08reservation.price));
         MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
         MainPage.user_cards.show(MainPage.user_page_panel, "������");
         MainPage.userToggle = "������"; 
         
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
      _08reservation.number="";
      for(int i=0;i<20;i++) {
         _08reservation.seats_btn.get(i).setSelected(false);
      } 
      for(int i=0;i<4;i++) {
         _08reservation.room_btn.get(i).setSelected(false);
      }
      for(int i=0;i<20;i++) {
         _08reservation.locker_btn.get(i).setSelected(false);
      } 
      MainPage.user_page_panel.add ("����������", new _08reservation(_08reservation.whatclass_now, _08reservation.time11, _08reservation.price11, _08reservation.type11));
      MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
      MainPage.user_cards.show(MainPage.user_page_panel, "����������");
      MainPage.userToggle = "����������"; 
      }
      }); 
   } 
}