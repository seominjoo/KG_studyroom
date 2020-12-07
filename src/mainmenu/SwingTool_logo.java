package mainmenu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SwingTool_logo extends JFrame{
	static BufferedImage image;

	public static void initFrame(JFrame frame) {
		
		try {
			image = ImageIO.read(new File("image\\·Î°í.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(image);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(90, 50, 500, 500);
		frame.setVisible(true);
	}
}
