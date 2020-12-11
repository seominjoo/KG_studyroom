package login.window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import login.design.Conversion_image;

public class Login_SwingTool extends JFrame{
	static BufferedImage image;
	static int x = new Conversion_image("image/로그인화면.jpg", 5).x;
	static int y = new Conversion_image("image/로그인화면.jpg", 5).y;
	

	public static void initFrame(JFrame frame) {
		
		try {
			image = ImageIO.read(new File("image\\로고.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(image);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(90, 50, x+12, y+35);
		frame.setVisible(true);
	}
}
