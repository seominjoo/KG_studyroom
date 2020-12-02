package loginFeature;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SignUp extends JFrame {
	final int GRID = 7;
	JPanel grid_panel = new JPanel(new GridLayout(GRID, 1, 0, 5));

	public SignUp() {		// 생성자
		JLabel signup = new JLabel("회원가입", JLabel.CENTER);		// 타이틀
		grid_panel.add(signup);
		for (SignUpEnum value : SignUpEnum.values()) {
			JTextField text = value.text; 				// 텍스트생성
			text.addMouseListener(new MouseAdapter() {	// 첫 터치 시에만 입력값 초기화
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1 
							&& text.getText().contains(value.labelName)) {
						text.setText(""); 
					}
				}
			});
			StringBuilder keystr = new StringBuilder();
			text.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
//					System.out.println(e.getKeyChar()+", "+e.getKeyCode());
//					if(java.util.regex.Pattern.matches("[a-zA-Z]", ""+e.getKeyChar())){
//					keystr.append(e.getKeyChar());
//					System.out.println(keystr.toString());
//					}
					// System.out.println(ktext.getText());
					/*
					 * 0-48 ~ 9-57
					 * 스페이스 - 32
					 * 지움 - 8
					 */
				}
			});
			grid_panel.add(text);
		}

		// 약관 패널
		JPanel p1 = new JPanel(new GridLayout(2, 2, 0, 5));
		Map<JLabel, JButton> consent = new HashMap<>();
		consent.put(new JLabel("⬜ 서비스이용동의"), new JButton("약관보기"));
		consent.put(new JLabel("⬜ 사용정보동의약관보기"), new JButton("약관보기"));

		for (Entry<JLabel, JButton> kv : consent.entrySet()) {
			p1.add(kv.getKey());
			kv.getValue().setBackground(Color.white);
			p1.add(kv.getValue());
		}

		// 승인,거절 패널
		JPanel p2 = new JPanel(new FlowLayout());
		JButton s_Yes = new JButton("가입");
		JButton s_No = new JButton("취소");
		s_Yes.setBackground(Color.white);
		s_No.setBackground(Color.white);
		p2.add(s_Yes);
		p2.add(s_No);

		grid_panel.add(p1);
		grid_panel.add(p2);

		SwingTools.initTestFrame(this);
		setLayout(new BorderLayout(20, 20));
		add(new JPanel(), BorderLayout.EAST);
		add(grid_panel, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
	}

	public static void main(String[] args) {
		new SignUp();
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (e.getX() > 150 && e.getX() < 300 && e.getY() > 0 && e.getY() < 30) {
////	            name.setText("");
//			repaint();
//		}
//	}
}
