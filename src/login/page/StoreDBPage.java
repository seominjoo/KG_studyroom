package login.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import login.design.Style;

public class StoreDBPage extends JPanel implements ActionListener {
   
   static JScrollPane scrollPane;
   JButton seat;
   JButton room;
   JButton locker;
   JButton back;
   static JLabel total;
   
   public StoreDBPage() {
      
      setLayout(null);
      new Style(this);
      
      scrollPane = new JScrollPane();
      new Style(scrollPane);
      scrollPane.getVerticalScrollBar().setUnitIncrement(20);
      scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
      scrollPane.setBounds(80, 85, 500, 350);
      add(scrollPane);
      
      seat = new JButton("�¼�");
      new Style(seat);
      seat.setBounds(90, 40, 120, 30);
      add(seat);
      seat.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("�¼�");
         }
      });
      
      room = new JButton("��");
      new Style(room);
      room.setBounds(255, 40, 120, 30);
      add(room);
      room.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("��");
         }
      });
      
      locker = new JButton("�繰��");
      new Style(locker);
      locker.setBounds(420, 40, 120, 30);
      add(locker);
      locker.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("�繰��");
         }
      });
      
      total = new JLabel();
      new Style(total);
      total.setBounds(390, 443, 200, 100);
      add(total);
      
      back = new JButton("����");
      back.setBounds(479, 481, 100, 30);
      new Style(back);
      add(back);

      back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            MainPage.main_page_panel.add("�������", new StoreManagementPage());
            MainPage.main_cards.show(MainPage.main_page_panel, "�������");
            MainPage.userToggle = "�������";
         }
      });   
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      MainPage.main_page_panel.add("����������̺�", new StoreDBPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
      MainPage.main_cards.show(MainPage.main_page_panel, "����������̺�");
      MainPage.userToggle = "����������̺�";
   }

}