package loginFeature;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ConsentContentPage extends MouseAdapter{

	private JPanel contentPane;
	private JFrame frame;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
//			int person_id = ClickSignUp.person_id;
//			String person_name =ClickSignUp.person_name;
			frame.setTitle("��� ����");
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setBounds(100, 100, 439, 483);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JButton confirmButton = new JButton("\uD655\uC778");
			confirmButton.setBounds(90, 143, 145, 23);
			contentPane.add(confirmButton);
			
			confirmButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1)
						frame.dispose();
				}
			});
			
			JLabel lblNewLabel_1 = new JLabel("<html>������ �����Ͽ����ϴ� <br/>��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.</html>", JLabel.CENTER);
			SignUp.textList.get(3).setText("");
			
			lblNewLabel_1.setToolTipText("");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 18));
			lblNewLabel_1.setBounds(12, 57, 306, 76);
			contentPane.add(lblNewLabel_1);
			
			frame.getContentPane().setLayout(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setBounds(190, 150, 343, 287);
			frame.setVisible(true);
		}
	}
	
}
