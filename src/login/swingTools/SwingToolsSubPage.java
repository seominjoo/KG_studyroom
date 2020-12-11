package login.swingTools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SwingToolsSubPage {
	static BufferedImage image;
 // 알림창 프레임
	public static void initTestFrame(JFrame frame) {
		try {
			image = ImageIO.read(new File("image\\로고.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.decode("#404040"));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(190, 150, 370, 220);
		frame.setVisible(true);
	}
	
}
