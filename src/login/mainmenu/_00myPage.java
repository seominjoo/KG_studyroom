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
    // �˸�â ������
       public  _00myPage( ) {
          new Style(this);
           JLabel info = new JLabel("<html>�¼� : "+_00main.seat_chk+"�� / ����Ⱓ : "+Time.TimeStampTOlocalDateTime(_00main.time_seat).format(_00main.datetime) +
                  "<br/>�� : "+_00main.room_chk+"ȣ / ����Ⱓ : "+Time.TimeStampTOlocalDateTime(_00main.time_room).format(_00main.datetime)+
                  "<br/>�繰�� : "+_00main.locker_chk+"�� / ����Ⱓ : "+Time.TimeStampTOlocalDateTime(_00main.time_locker).format(_00main.datetime)+
                  "<br/>�̿�� : "+_00main.type);
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