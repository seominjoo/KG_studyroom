package login.mainmenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import login.page.MainPage;

public class ActionBtn_select extends JFrame implements ActionListener {

   JButton btn;
   int on = 0;

   public ActionBtn_select(JButton btn) {

      this.btn = btn;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (on++ % 2 == 0) {
         for (JButton seat_btn : _08reservation.seats_btn) {
            if (seat_btn.isEnabled() == true) { // 이미 있는 좌석은 제외
               seat_btn.setBackground(Color.BLACK);
               seat_btn.setForeground(Color.orange);
               seat_btn.setSelected(false);
            }
         }
         btn.setBackground(Color.orange);
         btn.setForeground(Color.BLACK);
         btn.setSelected(true);
         System.out.println();

      } else {
         btn.setBackground(Color.BLACK);
         btn.setForeground(Color.orange);
         btn.setSelected(false);

      }
      System.out.println("이 메서드는 무엇  ?" + btn.isSelected());
   }

   public static void main(String[] args) {
      new MainPage();
   }
}