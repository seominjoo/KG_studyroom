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

	static ArrayList<JTextField> textList;

	static {
		textList = new ArrayList<JTextField>();
	}

	public SignUp() { // 생성자
		JLabel signup = new JLabel("회원가입", JLabel.CENTER); // 타이틀
		grid_panel.add(signup);
		for (SignUpEnum value : SignUpEnum.values()) {
			JTextField text = value.text; // 텍스트생성

			grid_panel.add(text);
			textList.add(text);
			text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("값 : "+ e.getKeyChar() +", 코드 : " + e.getKeyCode());
				System.out.println(text.getText());
			}
			
			});
		}
		for(int i = 0; i < textList.size(); i++)
		textList.get(i).addMouseListener(new ClearTextField(textList, i));

		// 약관 패널
		JPanel p1 = new JPanel(new GridLayout(2, 2, 0, 5));
		Map<JLabel, JButton> consent = new HashMap<>();
		consent.put(new JLabel("⬜ ▣서비스이용동의"), new JButton("약관보기"));
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

		// 바깥쪽 눌렀을 때 기본 값 만들어버리기
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = 0;
				for (SignUpEnum value : SignUpEnum.values()) {
					if (textList.get(i).getText().equals(""))
						textList.get(i).setText(value.labelName);
					i++;
				}
			}
		});
		this.setBackground(Color.black);
	}

	public static void main(String[] args) {
		new SignUp();
	}

}
