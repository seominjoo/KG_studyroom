package loginFeature;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.JComboBox;

public class SignUpSuccessWindow extends JPanel {

//	private JFrame frame;
	private static JPanel contentPane;

	JFrame frame = new JFrame();


	public SignUpSuccessWindow(int person_id, String person_name) {

//		int person_id = ClickSignUp.person_id;
//		String person_name =ClickSignUp.person_name;
		frame.setTitle("\uD68C\uC6D0 \uAC00\uC785 \uC131\uACF5!");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 439, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton toLoginPageButton = new JButton("\uB85C\uADF8\uC778 \uD398\uC774\uC9C0\uB85C");
		toLoginPageButton.setBounds(90, 143, 145, 23);
		contentPane.add(toLoginPageButton);
		
		toLoginPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					frame.dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("<html>\uAC00\uC785\uC774 "
				+ "\uC9C4\uD589\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"
				+ " <br/> " + person_name + "\uB2D8\uC758 \uD68C\uC6D0\uBC88\uD638 : "
				+ person_id +"</html>");
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(12, 57, 306, 76);
		contentPane.add(lblNewLabel_1);
		
		frame.getContentPane().setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(190, 150, 343, 287);
		frame.setVisible(true);
		
	}
	
	static JPanel getpanel() {
		return contentPane;
	}
}
