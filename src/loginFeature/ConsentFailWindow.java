package loginFeature;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ConsentFailWindow extends JFrame {
	private JPanel contentPane;

	public ConsentFailWindow() {
//		int person_id = ClickSignUp.person_id;
//		String person_name =ClickSignUp.person_name;
		setTitle("회원 가입 실패");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 439, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton confirmButton = new JButton("\uD655\uC778");
		confirmButton.setBounds(90, 143, 145, 23);
		contentPane.add(confirmButton);

		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("<html>가입이 실패하였습니다 <br/>약관 동의가 되지 않았습니다.</html>", JLabel.CENTER);

		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(12, 57, 306, 76);
		contentPane.add(lblNewLabel_1);

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 150, 343, 287);
		setVisible(true);
	}
}
