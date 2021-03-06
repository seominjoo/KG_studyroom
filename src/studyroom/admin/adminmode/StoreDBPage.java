package studyroom.admin.adminmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import studyroom.MainPage;
import studyroom.design.Style;

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
      scrollPane.setBounds(72, 110, 560, 350);
      new Style(scrollPane);
      add(scrollPane);
      
      seat = new JButton("좌석");
      seat.setBounds(127, 65, 120, 30);
      new Style(seat);
      add(seat);
      seat.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("좌석");
         }
      });
      
      room = new JButton("룸");
      room.setBounds(292, 65, 120, 30);
      new Style(room);
      add(room);
      room.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("룸");
         }
      });
      
      locker = new JButton("사물함");
      locker.setBounds(457, 65, 120, 30);
      new Style(locker);
      add(locker);
      locker.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            new StoreDB("사물함");
         }
      });
      
      total = new JLabel();
      total.setBounds(72, 475, 200, 30);
      new Style(total);
      add(total);
      
      back = new JButton("이전");
      back.setBounds(540, 475, 90, 30);
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
      MainPage.main_page_panel.add("매장관리테이블", new StoreDBPage());
      MainPage.main_cards.show(MainPage.main_page_panel, "사용자메뉴");
      MainPage.main_cards.show(MainPage.main_page_panel, "매장관리테이블");
      MainPage.userToggle = "매장관리테이블";
   }
}