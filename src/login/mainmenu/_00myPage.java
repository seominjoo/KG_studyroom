package login.mainmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import login.design.Style;

public class _00myPage extends JFrame {
   static BufferedImage image;
    // 알림창 프레임
       public  _00myPage( ) {
          new Style(this);
           JLabel info = new JLabel("<html>좌석 : "+_00main.seat_chk+"번 / 만료기간 : "+Time.TimeStampTOlocalDateTime(_00main.time_seat).format(_00main.datetime) +
                  "<br/>룸 : "+_00main.room_chk+"호 / 만료기간 : "+Time.TimeStampTOlocalDateTime(_00main.time_room).format(_00main.datetime)+
                  "<br/>사물함 : "+_00main.locker_chk+"번 / 만료기간 : "+Time.TimeStampTOlocalDateTime(_00main.time_locker).format(_00main.datetime)+
                  "<br/>이용권 : "+_00main.type);
           new Style(info);
           add(info);
           info.setBounds(20,0,400,190);
         
           
           
           setLayout(null);
           setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           setBounds(550, 300, 450, 220);
           setVisible(true);
       }
       public static void main(String[] args) {
         new _00myPage();
      }
}