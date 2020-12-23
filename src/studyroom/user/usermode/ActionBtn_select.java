package studyroom.user.usermode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import studyroom.user.MainPage;

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

      if (on++ % 2 == 0) { //선택 버튼 누를때마다 바뀌는 액션
            for (JButton seat_btn : Reservation.seats_btn) {
               if (seat_btn.isSelected() == true && seat_btn.isEnabled() == true) { // 이미 있는 좌석은 제외
                  seat_btn.setBackground(Color.decode("#241614"));
                  seat_btn.setForeground(Color.orange);
                  seat_btn.setSelected(false);
               }
            }
            for (JButton room_btn : Reservation.room_btn) {
               if (room_btn.isSelected() == true && room_btn.isEnabled() == true) {  
                  room_btn.setBackground(Color.decode("#241614"));
                  room_btn.setForeground(Color.orange);
                  room_btn.setSelected(false);
               }
            }
            for (JButton locker_btn : Reservation.locker_btn) {
               if (locker_btn.isSelected() == true && locker_btn.isEnabled() == true) {  
                  locker_btn.setBackground(Color.decode("#241614"));
                  locker_btn.setForeground(Color.orange);
                  locker_btn.setSelected(false);
               }
            }
            for (JButton seats_btn : Move_selectSeat.seats_btn) {
               if (seats_btn.isSelected() == true && seats_btn.isEnabled() == true) {  
                  seats_btn.setBackground(Color.decode("#241614"));
                  seats_btn.setForeground(Color.orange);
                  seats_btn.setSelected(false);
               }
            }
            for (JButton room_btn : Move_selectSeat.room_btn) {
               if (room_btn.isSelected() == true && room_btn.isEnabled() == true) {  
                  room_btn.setBackground(Color.decode("#241614"));
                  room_btn.setForeground(Color.orange);
                  room_btn.setSelected(false);
               }
            }
            for (JButton seats_btn : In_selectSeat.seats_btn) {
               if (seats_btn.isSelected() == true && seats_btn.isEnabled() == true) {  
                  seats_btn.setBackground(Color.decode("#241614"));
                  seats_btn.setForeground(Color.orange);
                  seats_btn.setSelected(false);
               }
            }
         btn.setForeground(Color.decode("#241614"));
         btn.setBackground(Color.orange);
         btn.setSelected(true); 

         on = 0;
      }
   
   }

}