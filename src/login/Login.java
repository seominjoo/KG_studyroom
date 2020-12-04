package login;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Login extends JFrame {

	public Login() {
		String path = "image/테스트이미지.jpg";
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image(path, 624,373).imageicon_smooth);
		background.setBounds(0, 0, 624, 373);
		
		JTextField phone_number = new JTextField("   010 - ");
		phone_number.setOpaque(false); // 배경 투명
		phone_number.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		phone_number.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number.setForeground(Color.decode("#cfab8b"));
		phone_number.setBounds(340, 137, 150, 30);
		background.add(phone_number);
		
		
		JPasswordField logpass = new JPasswordField("비밀번호");
		logpass.setOpaque(false); // 배경 투명
		logpass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		logpass.setHorizontalAlignment(SwingConstants.CENTER);
		logpass.setForeground(Color.decode("#cfab8b"));
		logpass.setBounds(340, 177, 150, 30);
		background.add(logpass);
		
		JButton login = new JButton("로그인");
		login.setBackground(Color.decode("#cfab8b"));
//		login.setContentAreaFilled(false);
		login.setFocusable(false);
		login.setOpaque(false); // 배경 투명
		login.setFocusPainted(false);
		login.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
		login.setForeground(Color.decode("#cfab8b"));
		login.setBounds(340, 217, 150, 30);
		background.add(login);
		
		JButton Signup = new JButton(new Conversion_image("image/회원가입.png", 50, 50).imageicon_smooth);
		Signup.setForeground(Color.decode("#cfab8b"));
		Signup.setOpaque(false); // 배경 투명
		Signup.setBorderPainted(false); // 외곽선을 없앰
		Signup.setContentAreaFilled(false); //내용영역을 안채움
		Signup.setFocusPainted(false); // 선택시 테두리 끔
		Signup.setBounds(548, 307, 50, 50);
		background.add(Signup);
		//머밋
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("x : " + e.getX() + " Y : " + e.getY());
				}
			}
		});
//		login_panel.setOpaque(false); // 투명
		
		SwingTool_logo.initFrame(this);
		add(background);
		setSize(624,405);
	}


	public static void main(String[] args) throws IOException {
			new Login();
	}
}
