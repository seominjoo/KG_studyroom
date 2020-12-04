package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
		JTextField phone_number1 = new JTextField("010");
		phone_number1.setOpaque(false); // ��� ����
		phone_number1.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		phone_number1.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number1.setForeground(Color.decode("#cfab8b"));
		phone_number1.setBounds(340, 137, 40, 30);
		background.add(phone_number1);
		
		JLabel str = new JLabel("-", JLabel.CENTER);
		str.setBounds(380, 137, 10, 30);
		str.setForeground(Color.decode("#cfab8b"));
		background.add(str);
		
		JTextField phone_number2 = new JTextField();
		phone_number2.setOpaque(false); // ��� ����
		phone_number2.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		phone_number2.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number2.setForeground(Color.decode("#cfab8b"));
		phone_number2.setBounds(390, 137, 45, 30);
		background.add(phone_number2);
		
		JLabel str2 = new JLabel("-", JLabel.CENTER);
		str2.setBounds(435, 137, 10, 30);
		str2.setForeground(Color.decode("#cfab8b"));
		background.add(str2);
		
		JTextField phone_number3 = new JTextField();
		phone_number3.setOpaque(false); // ��� ����
		phone_number3.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		phone_number3.setHorizontalAlignment(SwingConstants.CENTER);
		phone_number3.setForeground(Color.decode("#cfab8b"));
		phone_number3.setBounds(445, 137, 45, 30);
		background.add(phone_number3);
		
		JPasswordField logpass = new JPasswordField("��й�ȣ");
		logpass.setOpaque(false); // ��� ����
		logpass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		logpass.setHorizontalAlignment(SwingConstants.CENTER);
		logpass.setForeground(Color.decode("#cfab8b"));
		logpass.setBounds(340, 177, 150, 30);
		background.add(logpass);
		
		JButton login = new JButton("�α���");
		login.setBackground(Color.decode("#cfab8b"));
		login.setFocusable(false);
		login.setOpaque(false); // ��� ����
		login.setFocusPainted(false);
		login.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
		login.setForeground(Color.decode("#cfab8b"));
		login.setBounds(340, 217, 150, 30);
		login.addActionListener(new Notification(login));
		
		background.add(login);
		
		
		
		JButton find_PW = new JButton(new Conversion_image("image/PWã��.png", 40, 40).imageicon_smooth);
		find_PW.setForeground(Color.decode("#cfab8b"));
		find_PW.setBorderPainted(false); // �ܰ����� ����
		find_PW.setContentAreaFilled(false); //���뿵���� ��ä��
		find_PW.setFocusPainted(false); // ���ý� �׵θ� ��
		find_PW.setBounds(545, 247, 50, 50);
		background.add(find_PW);
	
		JButton Signup = new JButton(new Conversion_image("image/ȸ������.png", 40	, 40).imageicon_smooth);
		Signup.setForeground(Color.decode("#cfab8b"));
		Signup.setBorderPainted(false); // �ܰ����� ����
		Signup.setContentAreaFilled(false); //���뿵���� ��ä��
		Signup.setFocusPainted(false); // ���ý� �׵θ� ��
		Signup.setBounds(543, 292, 50, 50);
		background.add(Signup);
		
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
