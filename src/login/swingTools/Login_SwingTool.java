package login.swingTools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import login.design.Conversion_image;
import login.design.Style;
import login.page.MainPage;

public class Login_SwingTool extends JFrame{
   
   public static void initFrame(JFrame frame) {
      
      new Style(frame);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(MainPage.x+122, MainPage.y+35);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null); 

   }
}