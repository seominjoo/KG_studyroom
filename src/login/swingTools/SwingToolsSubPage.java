package login.swingTools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import login.design.Style;

public class SwingToolsSubPage {
   
 // 알림창 프레임
   public static void initTestFrame(JFrame frame) {
      frame.setLayout(null);
      new Style(frame);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setBounds(400, 200, 370, 220);
      frame.setVisible(true);
   }
   
}