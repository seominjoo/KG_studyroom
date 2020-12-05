package loginFeature.findPW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FindPasswordPage extends JFrame {

	static String imagePath;
	static BufferedImage image;
	static ImageIcon icon;

	static {
		imagePath = "C:\\Users\\Hyun\\Desktop\\자바SW개발자 양성과정 10월 현태환\\민짱.jpg";
		try {
			image = ImageIO.read(new File(imagePath));
			System.out.println(image.getHeight() + " " + image.getWidth());
			icon = new ImageIcon(image.getScaledInstance(2241 / 5, 2542 / 7, Image.SCALE_SMOOTH));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FindPasswordPage() {
		setLayout(null);

		JPanel background = new JPanel(new BorderLayout(100, 0)) {
			public void paintComponent(Graphics g) {
				// Approach 1: Dispaly image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);
				// Approach 2: Scale image to size of component
				// Dimension d = getSize();
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);

			}
		};
		background.setBounds(0, 0, 2241 / 5, 2542 / 7);
		add(background);
		background.setOpaque(false);
		JPanel blank = new JPanel();
		// background.add(blank,BorderLayout.WEST);

		JPanel gridAll = new JPanel(new GridLayout(3, 1, 0, 0));
		background.add(gridAll);
		gridAll.setOpaque(false);

		JLabel title = new JLabel("비밀번호 찾기", JLabel.CENTER);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		title.setForeground(Color.decode("#cfab8b"));
		gridAll.add(title);

		JPanel grid2 = new JPanel();
		grid2.setOpaque(false);
		grid2.setLayout(null);

		JLabel phoneKor = new JLabel("전화 번호 : ");
		phoneKor.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		phoneKor.setForeground(Color.decode("#cfab8b"));
		phoneKor.setBounds(85, 30, 100, 50);
		phoneKor.setOpaque(false);
		grid2.add(phoneKor);
		gridAll.add(grid2);

//		JTextField text = new JTextField();
//		text.setOpaque(false);
//		text.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
//		text.setForeground(Color.decode("#cfab8b"));
//		text.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
//		text.setBounds(250, 30, 100, 50);
//		grid2.add(text);
		JTextField phone_number1 = new JTextField("010");
		phone_number1.setOpaque(false); // 배경 투명
		phone_number1.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		phone_number1.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number1.setForeground(Color.decode("#cfab8b"));
		phone_number1.setBounds(180, 40, 40, 30);
		grid2.add(phone_number1);

		JLabel str = new JLabel("-", JLabel.CENTER);
		str.setBounds(230, 40, 10, 30);
		str.setForeground(Color.decode("#cfab8b"));
		grid2.add(str);

		JTextField phone_number2 = new JTextField();
		phone_number2.setOpaque(false); // 배경 투명
		phone_number2.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		phone_number2.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number2.setForeground(Color.decode("#cfab8b"));
		phone_number2.setBounds(250, 40, 45, 30);
		grid2.add(phone_number2);

		JLabel str2 = new JLabel("-", JLabel.CENTER);
		str2.setBounds(300, 40, 10, 30);
		str2.setForeground(Color.decode("#cfab8b"));
		grid2.add(str2);

		JTextField phone_number3 = new JTextField();
		phone_number3.setOpaque(false); // 배경 투명
		phone_number3.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		phone_number3.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number3.setForeground(Color.decode("#cfab8b"));
		phone_number3.setBounds(320, 40, 45, 30);
		grid2.add(phone_number3);

		JPanel grid3 = new JPanel();
		grid3.setLayout(null);
		grid3.setOpaque(false);

		JButton find = new JButton("검색");
		JButton cancel = new JButton("취소");
		JButton[] findCancel = { find, cancel };
		int x = 120;
		for (int i = 0; i < findCancel.length; i++) {
			findCancel[i].setBackground(Color.decode("#cfab8b"));
			findCancel[i].setFocusable(false);
			findCancel[i].setOpaque(false); // 배경 투명
			findCancel[i].setFocusPainted(false);
			findCancel[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
			findCancel[i].setForeground(Color.decode("#cfab8b"));
			findCancel[i].setFont(new Font("맑은 고딕", Font.BOLD, 17));
			findCancel[i].setBounds(x, 0, 100, 50);
			grid3.add(findCancel[i]);
			x += 120;
		}
		gridAll.add(grid3);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 200, 2241 / 5 + 16, 2542 / 7 + 39);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FindPasswordPage();
	}

}
