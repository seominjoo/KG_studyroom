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
      
      seat = new JButton("좌석");
      new Style(seat);
      seat.setBounds(90, 40, 120, 30);
      add(seat);
      seat.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("좌석");
         }
      });
      
      room = new JButton("룸");
      new Style(room);
      room.setBounds(255, 40, 120, 30);
      add(room);
      room.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("룸");
         }
      });
      
      locker = new JButton("사물함");
      new Style(locker);
      locker.setBounds(420, 40, 120, 30);
      add(locker);
      locker.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            new StoreDB("사물함");
         }
      });
      
      total = new JLabel();
      new Style(total);
      total.setBounds(390, 443, 200, 100);
      add(total);
      
      back = new JButton("이전");
      back.setBounds(479, 481, 100, 30);
      new Style(back);
      add(back);

      back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            MainPage.main_page_panel.add("매장관리", new StoreManagementPage());
            MainPage.main_cards.show(MainPage.main_page_panel, "매장관리");
            MainPage.userToggle = "매장관리";
         }
      });   
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      MainPage.main_page_panel.add("매장관리테이블", new StoreDBPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
      MainPage.main_cards.show(MainPage.main_page_panel, "매장관리테이블");
      MainPage.userToggle = "매장관리테이블";
   }

}