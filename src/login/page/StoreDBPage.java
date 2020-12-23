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
   static JLabel total;
   JButton seat;
   JButton room;
   JButton locker;
   JButton back;
   
   public StoreDBPage() {
      
      setLayout(null);
      new Style(this);
      
      scrollPane = new JScrollPane();
      scrollPane.getVerticalScrollBar().setUnitIncrement(20);
      scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
      scrollPane.setBounds(80, 85, 500, 350);
      new Style(scrollPane);
      add(scrollPane);
      
      seat = new JButton("�¼�");
      seat.setBounds(90, 40, 120, 30);
      new Style(seat);
      add(seat);
      seat.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("�¼�");
         }
      });
      
      room = new JButton("��");
      room.setBounds(255, 40, 120, 30);
      new Style(room);
      add(room);
      room.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("��");
         }
      });
      
      locker = new JButton("�繰��");
      locker.setBounds(420, 40, 120, 30);
      new Style(locker);
      add(locker);
      locker.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("�繰��");
         }
      });
      
      total = new JLabel();
      total.setBounds(396, 408, 200, 100);
      new Style(total);
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
      MainPage.main_page_panel.add("����������̺�", new StoreDBPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
      MainPage.main_cards.show(MainPage.main_page_panel, "����������̺�");
      MainPage.userToggle = "����������̺�";
   }
}