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
		String path = "image/�׽�Ʈ�̹���.jpg";
		JPanel login_panel = new JPanel();
		JLabel background = new JLabel(new Conversion_image(path, 624,373).imageicon_smooth);
		background.setBounds(0, 0, 624, 373);
		
		JTextField phone_number = new JTextField("   010 - ");
		phone_number.setOpaque(false); // ��� ����
		phone_number.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		phone_number.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number.setForeground(Color.decode("#cfab8b"));
		phone_number.setBounds(340, 137, 150, 30);
		background.add(phone_number);
		
		
		JPasswordField logpass = new JPasswordField("��й�ȣ");
		logpass.setOpaque(false); // ��� ����
		logpass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		logpass.setHorizontalAlignment(SwingConstants.CENTER);
		logpass.setForeground(Color.decode("#cfab8b"));
		logpass.setBounds(340, 177, 150, 30);
		background.add(logpass);
		
		JButton login = new JButton("�α���");
		login.setBackground(Color.decode("#cfab8b"));
//		login.setContentAreaFilled(false);
		login.setFocusable(false);
		login.setOpaque(false); // ��� ����
		login.setFocusPainted(false);
		login.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		login.setForeground(Color.decode("#cfab8b"));
		login.setBounds(340, 217, 150, 30);
		background.add(login);
		
		JButton Signup = new JButton(new Conversion_image("image/ȸ������.png", 50, 50).imageicon_smooth);
		Signup.setForeground(Color.decode("#cfab8b"));
		Signup.setOpaque(false); // ��� ����
		Signup.setBorderPainted(false); // �ܰ����� ����
		Signup.setContentAreaFilled(false); //���뿵���� ��ä��
		Signup.setFocusPainted(false); // ���ý� �׵θ� ��
		Signup.setBounds(548, 307, 50, 50);
		background.add(Signup);
		//�ӹ�
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("x : " + e.getX() + " Y : " + e.getY());
				}
			}
		});
//		login_panel.setOpaque(false); // ����
		
		SwingTool_logo.initFrame(this);
		add(background);
		setSize(624,405);
	}


	public static void main(String[] args) throws IOException {
			new Login();
	}
}
