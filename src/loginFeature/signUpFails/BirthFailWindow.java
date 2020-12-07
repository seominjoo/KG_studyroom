package loginFeature.signUpFails;

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
import loginFeature.Style;
import loginFeature.SwingToolsSubPage;

public class BirthFailWindow extends JFrame {

	private JPanel panel;
	private JButton confirmButton;
	private JLabel failLabel;

	public BirthFailWindow() {
		setLayout(null);
		setTitle("회원 가입 실패");
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(100, 100, 100, 100));
		panel.setLayout(null);
		new Style(panel);
		add(panel);
		
		confirmButton = new JButton("확인");
		confirmButton.setBounds(90, 143, 145, 23);
		new Style(confirmButton);
		panel.add(confirmButton);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});
		
		failLabel = new JLabel();

		failLabel = new JLabel("<html>가입이 실패하였습니다<br/>"
						+ "생년 월일을 다시 입력하십시요</html>");
				SignUpEnum.BIRTHDAY.text.setText("");
		
			
		new Style(failLabel);
		failLabel.setBounds(12, 57, 306, 76);
		panel.add(failLabel);

		SwingToolsSubPage.initTestFrame(this);
	}

	
}
