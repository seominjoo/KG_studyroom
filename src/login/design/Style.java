package login.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;

import login.signUp.SignUpPage;

public class Style {

	private static JPanel panel;
	private JFrame frame;
	private JOptionPane optionpane;
	private JLabel label;
	private JButton btn;
	private JTextField text;
	private JPasswordField pass;
	private JComboBox<?> jcombo;
	private JScrollPane jscroll;
	private JCheckBox jcheck;
	private JTable jtable;
	private JRadioButton jradio;
	private DefaultTableCellRenderer renderer;
	private JComponent cp;
	private int size;
	static BufferedImage image;

	// 컴포넌트 스타일
	public Style(JComponent cp) {
		this.cp = cp;
//      .getViewport().setBackground(Color.decode("#404040")); 색상 보류
		if (cp instanceof JPanel) {
			panel = (JPanel) cp;
			panel.setOpaque(false); // 배경 투명
			panel.setBackground(Color.decode("#fffcfa"));
//         panel.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
		} else if (cp instanceof JLabel) {
			label = (JLabel) cp;
			label.setOpaque(false); // 배경 투명
			if (label.getBackground() == Color.decode("#eeeeee")) {
				System.out.println("배경 없음 기본색 적용");
				label.setBackground(Color.decode("#fffcfa"));
			} else if (label.getText().equals("-")) {
				label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			} else if (label.getText().equals("")) {
				label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			} else {
				label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			}
			label.setForeground(Color.decode("#805b38"));
		} else if (cp instanceof JButton) {
			btn = (JButton) cp;
			if (btn.getText().length() != 0) {
				btn.setBackground(Color.decode("#fffcfa"));
				btn.setFocusable(false);
				btn.setOpaque(false); // 배경 투명
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // 테두리?
				btn.setForeground(Color.decode("#805b38"));
				btn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			} else {
				btn.setBackground(Color.decode("#fffcfa"));
				btn.setFocusable(false);
				btn.setOpaque(false); // 배경 투명
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // 테두리?
				btn.setForeground(Color.decode("#805b38"));
				btn.setBorderPainted(false); // 외곽선을 없앰
				btn.setContentAreaFilled(false); // 내용영역을 안채움
				btn.setFocusPainted(false); // 선택시 테두리 끔
			}
		} else if (cp instanceof JTextField) {
			text = (JTextField) cp;
			text.setOpaque(false); // 배경 투명
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // 테두리?
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setForeground(Color.decode("#805b38"));
			text.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			text.setCaretColor(Color.decode("#805b38")); // 커서 색상
		} else if (cp instanceof JPasswordField) {
			pass = (JPasswordField) cp;
			pass.setOpaque(false); // 배경 투명
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // 테두리?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#805b38"));
			pass.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
			text.setCaretColor(Color.decode("#805b38")); // 커서 색상
		} else if (cp instanceof JComboBox<?>) {
			jcombo = (JComboBox<?>) cp;
			jcombo.setOpaque(false); // 배경 투명
			jcombo.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // 테두리?
			jcombo.setForeground(Color.decode("#805b38"));
			jcombo.setBackground(Color.decode("#fffcfa"));
			jcombo.setUI(new BasicComboBoxUI() {
				@Override
				protected ComboPopup createPopup() {
					BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
					basicComboPopup.setBorder(new LineBorder(Color.decode("#805b38")));
					return basicComboPopup;
				}
			});
		} else if (cp instanceof JScrollPane) {
			jscroll = (JScrollPane) cp;
			jscroll.setOpaque(false); // 배경 투명
			jscroll.getViewport().setBackground(Color.decode("#fffcfa"));
			jscroll.getVerticalScrollBar().setBackground(Color.decode("#805b38"));
			// jscroll.getHorizontalScrollBar().setBackground(Color.decode("#805b38"));
			jscroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
				@Override
				protected void configureScrollBarColors() {
					this.thumbColor = Color.decode("#fffcfa");
				}

				@Override
				protected JButton createIncreaseButton(int orientation) {
					JButton button = new BasicArrowButton(orientation);
					button.setBackground(Color.decode("#fffcfa"));
					button.setForeground(Color.decode("#805b38"));
					return button;
				}

				@Override
				protected JButton createDecreaseButton(int orientation) {
					JButton button = new BasicArrowButton(orientation);
					button.setBackground(Color.decode("#fffcfa"));
					button.setForeground(Color.decode("#805b38"));
					return button;
				}
				
			});

		} else if (cp instanceof JCheckBox) {
			jcheck = (JCheckBox) cp;
			jcheck.setForeground(Color.decode("#805b38"));
			jcheck.setOpaque(false);
			jcheck.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else if (cp instanceof JTable) {
			jtable = (JTable) cp;
			// 아래 두개는 표 내부를 투명화할 때 반드시 필요
			jtable.setOpaque(false);
			((DefaultTableCellRenderer) jtable.getDefaultRenderer(Object.class)).setOpaque(false);
			jtable.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
			jtable.setForeground(Color.decode("#805b38"));
			jtable.setGridColor(Color.decode("#805b38")); // 테이블 내부 선 색
			jtable.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
			jtable.getTableHeader().setBackground(Color.decode("#fffcfa"));
			jtable.getTableHeader().setForeground(Color.decode("#805b38"));
			jtable.setEnabled(false); // 사용자가 테이블을 클릭하거나 편집하는 것을 방지
		} else if (cp instanceof JRadioButton) {
			jradio = (JRadioButton) cp;
			jradio.setOpaque(false);
			jradio.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			jradio.setForeground(Color.decode("#805b38"));
		} else if (cp instanceof DefaultTableCellRenderer) {
			renderer = (DefaultTableCellRenderer) cp;
			renderer.setOpaque(false);
		}
	}

	// 프레임 스타일
	public Style(JFrame cp) {
		frame = (JFrame) cp;

		try {
			image = ImageIO.read(new File("image\\로고.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(image);
		frame.getContentPane().setBackground(Color.decode("#fffcfa"));
	}

	// 텍스트 칸제한
	public Style(JTextField cp, int size) {
		new Style(cp);
		cp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= size)
					e.consume();
			}
		});
	}

	// 새로운 패널 받기위한 생성자
	public Style() {
	}

	public static JPanel getnewPanel() {
		panel = new JPanel();
		panel.setOpaque(false); // 배경 투명
		panel.setBackground(Color.decode("#fffcfa"));
		return panel;
	}

}