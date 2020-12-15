package login.window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import login.design.Conversion_image;
import login.design.Style;

public class Login_SwingTool extends JFrame{
   static int x = new Conversion_image("image/배경화면.jpg", 4).x;
   static int y = new Conversion_image("image/배경화면.jpg", 4).y;
   

   public static void initFrame(JFrame frame) {
      
      new Style(frame);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBounds(90, 50, x+12, y+35);
      frame.setVisible(true);
   }
}