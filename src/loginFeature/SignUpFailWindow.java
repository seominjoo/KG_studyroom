package loginFeature;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SignUpFailWindow extends JFrame {

	private JPanel contentPane;
	
	JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					SignUpFailWindow frame = new SignUpFailWindow();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpFailWindow() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * Create the frame.
	 */
	public SignUpFailWindow(SQLException e1) {
//		int person_id = ClickSignUp.person_id;
//		String person_name =ClickSignUp.person_name;
		setTitle("회원 가입 실패");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton toLoginPageButton = new JButton("\uD655\uC778");
		toLoginPageButton.setBounds(90, 143, 145, 23);
		contentPane.add(toLoginPageButton);
		
		toLoginPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});
		
		lblNewLabel_1 = new JLabel();
		
		String[] errorNames = {"phone_number","person_name","pw"};
		String[] errorKorNames = {"전화번호","성함","비밀번호"};
		
		for(int i = 0; i < errorNames.length; ++i) {
			if(e1.getMessage().toLowerCase().contains(errorNames[i])) {
				lblNewLabel_1 = new JLabel("<html>\uAC00\uC785\uC774 "
						+ "\uC2E4\uD328\uD558\uC600\uC2B5\uB2C8\uB2E4 <br/> "
						+ errorKorNames[i] 
						+"를(을) \uB2E4\uC2DC \uC785\uB825\uD558\uC2ED\uC2DC\uC694</html>");
				break;
			}

		}
	
		lableTools();
		frameTools();
	}

	void pwNoMatch() {
			lblNewLabel_1 = new JLabel("<html>가입이 실패하였습니다 <br/>비밀번호를 다시 확인하세요.</html>");
			lableTools();
			frameTools();
	}
	
	void lableTools() {
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(12, 57, 306, 76);
		contentPane.add(lblNewLabel_1);
	}
	
	void frameTools() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 150, 343, 287);
		setVisible(true);
	}
	
}


