package login.swingTools;

import java.awt.Color;

import javax.swing.JFrame;

import login.design.Style;
import login.page.MainPage;

public class Login_SwingTool extends JFrame{
   
   public static void initFrame(JFrame frame) {
      
      new Style(frame);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(MainPage.x+15, MainPage.y+30);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null); 
      frame.setBackground(Color.decode("#ede4df"));

   }
}