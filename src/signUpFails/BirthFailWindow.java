package signUpFails;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import loginFeature.SignUp;
import loginFeature.SignUpEnum;

public class BirthFailWindow extends JFrame {

	private JPanel contentPane;
	
	JLabel lblNewLabel_1;


	public BirthFailWindow() {
//		int person_id = ClickSignUp.person_id;
//		String person_name =ClickSignUp.person_name;
		setTitle("회원 가입 실패");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 439, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton confirmButton = new JButton("확인");
		confirmButton.setBounds(90, 143, 145, 23);
		contentPane.add(confirmButton);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});
		
		lblNewLabel_1 = new JLabel();

				lblNewLabel_1 = new JLabel("<html>가입이 실패하였습니다<br/>"
						+ "생년 월일을 다시 입력하십시요</html>");
				SignUp.textList.get(SignUpEnum.BIRTHDAY.index).setText("");
		
			
	
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
