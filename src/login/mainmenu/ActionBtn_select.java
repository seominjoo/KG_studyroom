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
   String type;

   public ActionBtn_select(JButton btn, String type) {
      this.type = type;
      this.btn = btn;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (on++ % 2 == 0) {
            for (JButton seat_btn : _08reservation.seats_btn) {
               if (seat_btn.isSelected() == true && seat_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  seat_btn.setBackground(Color.decode("#241614"));
                  seat_btn.setForeground(Color.orange);
                  seat_btn.setSelected(false);
               }
            }
            for (JButton room_btn : _08reservation.room_btn) {
               if (room_btn.isSelected() == true && room_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  room_btn.setBackground(Color.decode("#241614"));
                  room_btn.setForeground(Color.orange);
                  room_btn.setSelected(false);
               }
            }
            for (JButton locker_btn : _08reservation.locker_btn) {
               if (locker_btn.isSelected() == true && locker_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  locker_btn.setBackground(Color.decode("#241614"));
                  locker_btn.setForeground(Color.orange);
                  locker_btn.setSelected(false);
               }
            }
            for (JButton seats_btn : _06move_selectSeat.seats_btn) {
               if (seats_btn.isSelected() == true && seats_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  seats_btn.setBackground(Color.decode("#241614"));
                  seats_btn.setForeground(Color.orange);
                  seats_btn.setSelected(false);
               }
            }
            for (JButton room_btn : _06move_selectSeat.room_btn) {
               if (room_btn.isSelected() == true && room_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  room_btn.setBackground(Color.decode("#241614"));
                  room_btn.setForeground(Color.orange);
                  room_btn.setSelected(false);
               }
            }
            for (JButton seats_btn : _07in_selectSeat.seats_btn) {
               if (seats_btn.isSelected() == true && seats_btn.isEnabled() == true) { // �̹� �ִ� �¼��� ����
                  seats_btn.setBackground(Color.decode("#241614"));
                  seats_btn.setForeground(Color.orange);
                  seats_btn.setSelected(false);
               }
            }
         btn.setForeground(Color.decode("#241614"));
         btn.setBackground(Color.orange);
         btn.setSelected(true);
         System.out.println();

         on = 0;
      }
   
   }

}