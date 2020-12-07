package login.design;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Conversion_image {
	static Image source;
	static Image image_smooth;
	static ImageIcon imageicon;
	public static ImageIcon imageicon_smooth;
	
	public Conversion_image(String path, int sizex, int sizey) {
		try {
			this.source = ImageIO.read(new File(path));
			this.image_smooth = source.getScaledInstance(sizex, sizey, source.SCALE_SMOOTH);
			this.imageicon = new ImageIcon(source);
			this.imageicon_smooth = new ImageIcon(image_smooth);
			System.out.println("이미지 사이즈 : " + source.getWidth(null) + ", " + source.getHeight(null));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
