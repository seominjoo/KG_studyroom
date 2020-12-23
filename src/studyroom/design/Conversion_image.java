package studyroom.design;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Conversion_image {
	static Image source;
	public static Image image_smooth;
	static ImageIcon imageicon;
	public static ImageIcon imageicon_smooth;
	BufferedImage buf;
	public int x;
	public int y;

	// 사이즈 비율로 줄이는 작업
	public Conversion_image(String path, int sizexy) {
		try {
			buf = ImageIO.read(new File(path));
			this.x = buf.getWidth() / sizexy;
			this.y = buf.getHeight() / sizexy;
			this.imageicon = new ImageIcon(buf);

			// 사이즈에 맞게 스무스 적용
			this.image_smooth = buf.getScaledInstance(x, y, source.SCALE_SMOOTH);
			this.imageicon_smooth = new ImageIcon(image_smooth);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 받은 사이즈로 스무스
	public Conversion_image(String path, int sizex, int sizey) {
		try {
			this.source = ImageIO.read(new File(path));
			this.image_smooth = source.getScaledInstance(sizex, sizey, source.SCALE_SMOOTH);
			this.imageicon = new ImageIcon(source);
			this.imageicon_smooth = new ImageIcon(image_smooth);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
